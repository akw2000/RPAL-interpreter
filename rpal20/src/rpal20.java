import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Parser.AST;
import Parser.ParseTree;
import lexical_analyzer.LexicalAnalyzer;
import lexical_analyzer.Token;

import control_structures.ControlStructures;
import cse_machine.CSE;
import control_structures.CSNode;

public class rpal20 {
    public static void main(String[] args) throws Exception {
        // eg: java rpal20 rpal_test_programs/rpal_01 > output.01 -> filename = rpal_test_programs/rpal_01
        //File file = new File(args[0]);
        File file = new File ("rpal20\\test progs\\add");
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(file);
        ArrayList<Token> tokenList = lexicalAnalyzer.getTokenList();
        System.out.println("#######################Token List:");
        for (Token token : tokenList) {
            System.out.println(token);
        }
        
        //System.out.println(tokenList);
        

        ParseTree parser = new ParseTree(tokenList);
        AST tree = parser.buildAst();
        System.out.println("-----------------AST----------------------");
        tree.print();
        tree.standardize();
        System.out.println("-------------------ST----------------------");
        tree.print();

        ControlStructures ctrlstruct = new ControlStructures();

        ctrlstruct.genControlStructures(tree.getRoot());

        List<List<CSNode>> deltc_struct = ctrlstruct.getCS();

        CSE cse_m = new CSE(deltc_struct);
    }
}
