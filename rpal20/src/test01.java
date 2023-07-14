import java.io.File;
import java.io.FileNotFoundException;
import java.util.Stack;

import Parser.AST;
import Parser.ParseTree;
import lexical_analyzer.LexicalAnalyzer;
import lexical_analyzer.Token;


public class test01 {
    

    public static void main(String[] args) throws Exception {
        // eg: java rpal20 rpal_test_programs/rpal_01 > output.01 -> filename = rpal_test_programs/rpal_01

        File file = new File("rpal20\\src\\rpal_test_programs\\rpal_01 copy");
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(file);
        System.out.println(lexicalAnalyzer.getTokenList().size());
        ParseTree parser = new ParseTree(lexicalAnalyzer.getTokenList());

        AST tree = parser.buildAst();

        tree.print();
        
    }
}
    

