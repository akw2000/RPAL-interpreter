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
    private ArrayList<Token> tokenList; // list of tokens as the output, input for the parser
    private BufferedReader reader;
    // keywords (reserved words) defined in the RPAL language
    private List<String> keywords = Arrays.asList("let", "in", "fn", "where", "aug", "or", "not", "gr", "ge", "ls",
            "le", "eq", "ne",
            "true", "false", "nil", "dummy", "within", "and", "rec");

    // regex for the primary lexical units
    private String letter = "[A-Za-z]";
    private String digit = "[0-9]";
    private String operator_symbol = "[+|\\-|*|<|>|&|.|@|/|:|=|~|\\||$|!|#|%|`|_|\\[|\\]|{|}|\\\"|?]";

    private boolean readerClosed = false; // flag to check if the reader is closed, to avoid reading after EOF

    public LexicalAnalyzer(File file) {
        this.tokenList = new ArrayList<Token>();
        try {
            this.reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        String nextChr = null; // to store the next read character
        String buffer = null;  // to store the last read character that is not yet scanned
        
        while ((nextChr = nextChr()) != null) { // read until EOF(returns null)
            constructToken(nextChr, buffer);
            if (readerClosed) {
                break;
            }
        }

        // Add additional end of file token for easy parsing
        Token EofToken = new Token();
        EofToken.setType("EOF");
        EofToken.setValue("EOF");
        tokenList.add(EofToken);
    }


    /** 
     *  return the token list for the parser
     * 
     * @return ArrayList<Token>
     */
    public ArrayList<Token> getTokenList() {
        return tokenList;
    }

    /**
     *  construct a token from the input string
     * 
     * @param nextChr
     * @param buffer
     */
    private void constructToken(String nextChr, String buffer) {
        Token token = new Token();
        String value = "";

        if (nextChr.matches(letter)) { // Identifier(starts with a Letter -> ’A’..’Z’ | ’a’..’z’;)
            value = nextChr;
            while ((nextChr = nextChr()) != null) {
                // Identifier -> Letter (Letter | Digit | ’_’)* => check the (Letter | Digit | ’_’)* part
                if (nextChr.matches("[" + letter + "|" + digit + "|'_']*")) {
                    value += nextChr;
                } else {
                    // buffer back the last character
                    buffer = nextChr;
                    break;
                }
            }

            // update the identifier type, if it is a keyword
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
                // Integer -> Digit Digit* => check the Digit* part
                if (nextChr.matches("[" + digit + "]*")) {
                    value += nextChr;
                } else {
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
            while ((nextChr = nextChr()) != null) {
                // last character is a -> ' and previous character checked for \' type strings(special case otherwise it'll end after the first ')
                if (!(prevChr.equals("\\")) && nextChr.matches("[\']")) {
                    value += nextChr;
                    token.setType("STRING");
                    token.setValue(value);
                    tokenList.add(token);
                    break;
                } else if (nextChr.matches("[\\t|\\n|\\\\|\\\'|'('|')'|';'|','|' '|" + letter + "|" + digit + "|"
                        + operator_symbol + "]*")) { // String ->( ’\’ ’t’ | ’\’ ’n’ | ’\’ ’\’ | ’\’ ’’’’| ’(’ | ’)’ | ’;’ | ’,’|’’| Letter | Digit | Operator_symbol)* ’’’’
                    prevChr = nextChr;
                    value += nextChr;
                } else if (prevChr.equals("\\") && nextChr.matches("[\']")) { // String -> \' check(special case)
                    prevChr = nextChr;
                    value += nextChr;
                } else {
                    buffer = nextChr;
                    break;
                }
            }

        } else if (nextChr.matches(operator_symbol)) { // Operator (starts with a OperatorSymbol -> ’+’ | ’-’ | ’*’ | ’<’ | ’>’ | ’&’ | ’.’ | ’@’ | ’/’ | ’:’ | ’=’ | ’~’ | ’|’ | ’$’ | ’!’ | ’#’ | ’%’ | ’`’ | ’_’ | ’[’ | ’]’ | ’{’ | ’}’ | ’"’ | ’’’ | ’?’)
            value = nextChr;
            String prevChr = nextChr;
            boolean isComment = false;
            while ((nextChr = nextChr()) != null) {
                // Comment (starts with a -> ’//’) 
                if (prevChr.matches("[/]") && nextChr.matches("[/]")) {
                    // strip the characters before the comment and make an operator token(+//comment type cases)
                    if (value.length() > 1) { // strip the operator out
                        String tokenValue = value.substring(0, value.length() - 1);
                        token.setType("OPERATOR");
                        token.setValue(tokenValue);
                        tokenList.add(token);
                    }
                    token = new Token();
                    isComment = true;
                    value = "//";
                    while ((nextChr = nextChr()) != null) {
                        // Comment -> ( ’\’ ’t’ | ’\’ ’n’ | ’\’ ’\’ | ’\’ ’’’’| ’(’ | ’)’ | ’;’ | ’,’|’’| Letter | Digit | Operator_symbol)* part
                        if (nextChr.matches("['\''|'('|')'|';'|','|' '|'\\t'|" + letter + "|" + digit + "|"
                                + operator_symbol + "]*")) {
                            value += nextChr;
                        } else if (nextChr.matches("[\\n]")) { // last character is Eol
                            value += nextChr;
                            token.setType("DELETE");
                            token.setValue(value);
                            tokenList.add(token);
                            break;
                        }
                    }
                } else if (nextChr.matches("[" + operator_symbol + "]*")) { // Operator -> OperatorSymbol OperatorSymbol* => check the OperatorSymbol* part
                    value += nextChr;
                    prevChr = nextChr;
                } else {
                    buffer = nextChr;
                    token.setType("OPERATOR");
                    token.setValue(value);
                    tokenList.add(token);
                    break;
                }
                if (isComment) {
                    break; // no buffer back since last character is Eol
                }
            }

        } else if (nextChr.matches("[\\s|\\t|\\n]")) { // Space (starts with a -> ’ ’ | ’\t’(ht)| ’\n’(Eol))
            value = nextChr;
            while ((nextChr = nextChr()) != null) {
                // Space -> ( ’ ’ | ht | Eol )+ => check the + part
                if (nextChr.matches("[\\s\\t\\n]*")) {
                    value += nextChr;
                } else {
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

        } else if (nextChr.matches("[)]")) { // Punction -> RightParenthesis -> ’)’
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
        if (buffer != null) {
            nextChr = buffer;
            buffer = null;
            constructToken(nextChr, buffer);
        }
    }

    
    /**
     *  read the next character from the file
     * 
     * @return String
     */
    private String nextChr() {
        String nextChr = null;
        try {
            if (readerClosed) {
                return null; // abort if the reader is closed
            }
            int chr = reader.read(); // read the first character
            if (chr != -1) { // if not EOF(-1)
                nextChr = Character.toString((char) chr);
            } else {
                readerClosed = true; // set the flag to true if EOF
                reader.close(); // close the reader
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
        return nextChr;
    }
}