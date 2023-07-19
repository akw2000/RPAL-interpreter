package cse_machine;

import java.util.List;

import Parser.LeafNode;
import Parser.Node;
import control_structures.CSNode;
import control_structures.ControlStructures;

public class testCSE3 {
    
    /*
     * Testing the use of environment variables
     */
    public static void main(String[] args) {
        
        Node n1 = new Node("gamma");
        Node n2 = new Node("lambda");
        Node n3 = new LeafNode("IDENTIFIER","x");
        Node n4 = new LeafNode("IDENTIFIER","x");
        Node n5 = new LeafNode("INTEGER", "2");

        n1.setLeft(n2);
        n2.setLeft(n3);
        n2.setRight(n5);
        n3.setRight(n4);

        ControlStructures controlStructuresGen = new ControlStructures();

        controlStructuresGen.genControlStructures(n1);

        List<List<CSNode>> delta_lists = controlStructuresGen.getCS();

        CSE cse_m = new CSE(delta_lists);

        cse_m.runCSE();
    }
}
