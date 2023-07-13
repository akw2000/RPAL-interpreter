package lexical_analyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LexicalAnalyzer {
    private ArrayList<Token> tokenList;
    private BufferedReader reader;

    public LexicalAnalyzer(File file) {
        this.tokenList = new ArrayList<Token>();
        try{
            this.reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // scanner
        String nextChr = null;
        while ((nextChr = nextChr()) != null) {
            Token token = new Token();
            String value = "";
            if (nextChr.matches("[A-Za-z]")) { // Identifier(starts with a Letter -> ’A’..’Z’ | ’a’..’z’;)
                value = nextChr;
                while ((nextChr = nextChr()) != null) {
                    if (nextChr.matches("[A-Za-z0-9_]*")) { // Identifier -> Letter (Letter | Digit | ’_’)* => check the (Letter | Digit | ’_’)* part
                        value += nextChr;
                    } else {
                        // buffer back the last character
                        break;
                    }
                }
                token.setType("IDENTIFIER");
                token.setValue(value);
                tokenList.add(token);
            } else if (nextChr.matches("[0-9]")) { // Integer (starts with a Digit -> ’0’..’9’)
                while ((nextChr = nextChr()) != null) {
                    if (nextChr.matches("[0-9]*")) { // Integer -> Digit Digit* => check the Digit* part
                        value += nextChr;
                    } else {
                        // buffer back the last character
                        break;
                    }
                }
                token.setType("INTEGER");
                token.setValue(value);
            } else {

            }
        }

    }

    public ArrayList<Token> getTokenList() {
        return tokenList;
    }


    private String nextChr() {
        String nextChr = null;
        try {
            int chr = reader.read(); // read the first character
            if(chr != -1) {
                nextChr = Character.toString((char) chr);
                System.out.println(nextChr); //
            } else {
                reader.close();
            }
        }  catch (IOException e) {
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
