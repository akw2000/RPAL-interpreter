package lexical_analyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LexicalAnalyzer {
    private ArrayList<Token> tokenList;
    private BufferedReader reader;
    private List<String> keywords = Arrays.asList("let", "in", "fn", "where", "aug", "or", "not", "gr", "ge", "ls",
            "le", "eq", "ne",
            "true", "false", "nil", "dummy", "within", "and", "rec");

    private String letter = "[A-Za-z]";
    private String digit = "[0-9]";
    private String operator_symbol = "['+'|'\\-'|'*'|'<'|'>'|'&'|'.'|'@'|'/'|':'|'='|'~'|\\||'$'|'!'|'#'|'%'|'`'|'_'|'\\['|'\\]'|'{'|'}'|'\\\"'|'`'|'?']";
    private boolean readerClosed = false;

    public LexicalAnalyzer(File file) {
        this.tokenList = new ArrayList<Token>();
        try {
            this.reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String nextChr = null;
        String buffer = null;

        while ((nextChr = nextChr()) != null) {
            // System.out.println("first check ##" + nextChr + "##");
            constructToken(nextChr, buffer);
            if (readerClosed) {
                break;
            }
        }
        // end of file token
        Token EofToken = new Token();
        EofToken.setType("EOF");
        EofToken.setValue("EOF");
        tokenList.add(EofToken);
    }

    public ArrayList<Token> getTokenList() {
        return tokenList;
    }

    private void constructToken(String nextChr, String buffer) {
        Token token = new Token();
        String value = "";
        if (nextChr.matches(letter)) { // Identifier(starts with a Letter -> ’A’..’Z’ | ’a’..’z’;)
            value = nextChr;
            while ((nextChr = nextChr()) != null) {
                if (nextChr.matches("[" + letter + "|" + digit + "|'_']*")) { // Identifier -> Letter (Letter | Digit | ’_’)* => check the (Letter | Digit | ’_’)* part
                    value += nextChr;
                } else {
                    // buffer back the last character
                    // System.out.println("buffer back the last character ##" + nextChr + "##");
                    buffer = nextChr;
                    break;
                }
            }
            if (keywords.contains(value)) {
                token.setType("KEYWORD");
            } else {
                token.setType("IDENTIFIER");
            }
            token.setValue(value);
            tokenList.add(token);

        } else if (nextChr.matches(digit)) { // Integer (starts with a Digit -> ’0’..’9’)
            value = nextChr;
            while ((nextChr = nextChr()) != null) {
                if (nextChr.matches("[" + digit + "]*")) { // Integer -> Digit Digit* => check the Digit* part
                    value += nextChr;
                } else {
                    // System.out.println("buffer back the last character ##" + nextChr + "##");
                    buffer = nextChr;
                    break;
                }
            }
            token.setType("INTEGER");
            token.setValue(value);
            tokenList.add(token);

        } else if (nextChr.matches("[\']")) { // String (starts with a -> ’''’)
            value = nextChr;
            String prevChr = nextChr;
            // System.out.println("first check ##" + nextChr + "##");
            while ((nextChr = nextChr()) != null) {
                // System.out.println("second check ##" + nextChr + "##");
                if (!(prevChr.equals("\\")) && nextChr.matches("[\']")) { // last character is a -> '
                    value += nextChr;
                    // System.out.println("buffer back the last character ##" + nextChr + "##");
                    // buffer = nextChr;
                    // value = value.toString().replace("\\n", System.getProperty("line.separator"));
                    // value = value.toString().replaceAll("\\t", "\t");
                    token.setType("STRING");
                    token.setValue(value);
                    // System.out.println("string: " + value);
                    tokenList.add(token);
                    break;
                } else if (nextChr.matches("[\\t|\\n|\\\\|\\\'|'('|')'|';'|','|' '|" + letter + "|" + digit + "|"
                        + operator_symbol + "]*")) { // String ->( ’\’ ’t’ | ’\’ ’n’ | ’\’ ’\’ | ’\’ ’’’’| ’(’ | ’)’ | ’;’ | ’,’|’’| Letter | Digit | Operator_symbol)* ’’’’
                    prevChr = nextChr;
                    value += nextChr;
                }  else if (prevChr.equals("\\") && nextChr.matches("[\']")) { // String -> ’\’ ’’’’ check
                    prevChr = nextChr;
                    value += nextChr;
                } else {
                    // System.out.println("buffer back the last character ##" + nextChr + "##");
                    buffer = nextChr;
                    break;
                }
            }

        }  else if (nextChr.matches(operator_symbol)) { // Operator (starts with a OperatorSymbol -> ’+’ | ’-’ | ’*’ | ’<’ | ’>’ | ’&’ | ’.’ | ’@’ | ’/’ | ’:’ | ’=’ | ’~’ | ’|’ | ’$’ | ’!’ | ’#’ | ’%’ | ’`’ | ’_’ | ’[’ | ’]’ | ’{’ | ’}’ | ’"’ | ’’’ | ’?’)
            value = nextChr;
            String prevChr = nextChr;
            boolean isComment = false;    
            while ((nextChr = nextChr()) != null) {
                if (prevChr.matches("[/]") && nextChr.matches("[/]")) { // Comment (starts with a -> ’//’) made higher precedence than operator to avoid conflict 
                    isComment = true;
                    value += nextChr;
                    // System.out.println("Comment -> #####################" + value);
                    while ((nextChr = nextChr()) != null) {
                        if (nextChr.matches("['\''|'('|')'|';'|','|' '|'\\t'|" + letter + "|" + digit + "|"
                                + operator_symbol + "]*")) { // Comment -> ( ’\’ ’t’ | ’\’ ’n’ | ’\’ ’\’ | ’\’ ’’’’| ’(’ | ’)’ | ’;’ | ’,’|’’| Letter | Digit | Operator_symbol)* part
                            value += nextChr;
                        } else if (nextChr.matches("[\\n]")) { // last character is Eol
                            value += nextChr;
                            // System.out.println("buffer back the last character ##" + nextChr + "##");
                            // buffer = nextChr;
                            token.setType("DELETE");
                            token.setValue(value );
                            tokenList.add(token);
                            break;
                        }
                    }
                } else if (nextChr.matches("[" + operator_symbol + "]*")) { // Operator -> OperatorSymbol OperatorSymbol* => check the OperatorSymbol* part
                    // System.out.println("while * check ##" + nextChr + "##");
                    value += nextChr;
                    prevChr = nextChr; // check if needed
                } 
                if (isComment) {
                    break; // no buffer back since last character is Eol
                } else {
                    // System.out.println("buffer back the last character ##" + nextChr + "##");
                    buffer = nextChr;
                    token.setType("OPERATOR");
                    token.setValue(value);
                    tokenList.add(token);
                    break;
                }
            }

        } else if (nextChr.matches("[\\s|\\t|\\n]")) { // Space (starts with a -> ’ ’ | ’\t’(ht)| ’\n’(Eol))
            value = nextChr;
            // System.out.println("Space detected" + nextChr + "##");

            while ((nextChr = nextChr()) != null) {
                if (nextChr.matches("[\\s\\t\\n]*")) { // Space -> ( ’ ’ | ht | Eol )+ => check the + part
                    value += nextChr;
                } else {
                    // System.out.println("buffer back the last character ##" + nextChr + "##");
                    buffer = nextChr;
                    break;
                }
            }
            token.setType("DELETE");
            token.setValue(value);
            tokenList.add(token);
        
        } else if (nextChr.matches("[(]")) { // Punction -> LeftParenthesis -> ’(’
            value = nextChr;
            token.setType("L_PAREN");
            token.setValue(value);
            tokenList.add(token);

        }  else if (nextChr.matches("[)]")) { // Punction -> RightParenthesis -> ’)’
            value = nextChr;
            token.setType("R_PAREN");
            token.setValue(value);
            tokenList.add(token);

        } else if (nextChr.matches("[;]")) { // Punction -> Semicolon -> ’;’
            value = nextChr;
            token.setType("SEMICOLON");
            token.setValue(value);
            tokenList.add(token);

        } else if (nextChr.matches("[,]")) { // Punction -> Comma -> ’,’
            value = nextChr;
            token.setType("COMMA");
            token.setValue(value);
            tokenList.add(token);

        }
        // if there is a buffered character(read but not used), use it first
        if(buffer!=null)
        {
            nextChr = buffer;
            buffer = null;
            constructToken(nextChr, buffer);
        }
    }


    private String nextChr() {
        String nextChr = null;
        try {
            if (readerClosed) {
                return null;
            }
            int chr = reader.read(); // read the first character
            // System.out.println("read " + chr + "##"); //
            if (chr != -1) {
                nextChr = Character.toString((char) chr);
                // System.out.println("after change to char" +nextChr + "##"); //
            } else {
                readerClosed = true;
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nextChr;
    }

    // private Token lexiconMatcher(String scannedToken) {
    //     Token token = new Token();
    //     //Identifier
    //     if (scannedToken.matches("[A-Za-z][A-Za-z0-9_]*")) {
    //         token.setType("IDENTIFIER");
    //         token.setValue(scannedToken);
    //     }
    // }
}
