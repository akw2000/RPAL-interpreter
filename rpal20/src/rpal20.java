import java.io.File;
import java.io.FileNotFoundException;

import lexical_analyzer.LexicalAnalyzer;

public class rpal20 {
    public static void main(String[] args) throws Exception {
        // eg: java rpal20 rpal_test_programs/rpal_01 > output.01 -> filename = rpal_test_programs/rpal_01
        File file = new File(args[0]);
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(file);
        System.out.println(lexicalAnalyzer.getTokenList());
    }
}
