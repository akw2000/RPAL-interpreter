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
        // to run from command line simply type: make check
        // check the makefile for more details

        // Step 1: Lexical Analysis
        File file = new File(args[0]); // for makefile
        //  File file = new File ("rpal20\\src\\rpal_test_programs\\rpal_01"); // for debugging

        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(file);
        ArrayList<Token> tokenList = lexicalAnalyzer.getTokenList();
        // System.out.println("------------------Token List---------------------");
        // for (Token token : tokenList) {
        //     System.out.println(token);
        // }

        // Step 2: Parsing
        ParseTree parser = new ParseTree(tokenList);

        // Step 3: Abstract Syntax Tree(AST)
        AST tree = parser.buildAst();
        // System.out.println("-----------------AST----------------------");
        // tree.print();
        
        // Step 4: Standardize Tree
        tree.standardize();
        // System.out.println("-------------------ST----------------------");
        // tree.print();
        
        // Step 5: Control Structures
        ControlStructures ctrlstruct = new ControlStructures();
        ctrlstruct.genControlStructures(tree.getRoot());
        List<List<CSNode>> deltc_struct = ctrlstruct.getCS();

        // Step 6: CSE
        CSE cse_m = new CSE(deltc_struct);
        cse_m.runCSE();
        System.out.println();
    }
}
