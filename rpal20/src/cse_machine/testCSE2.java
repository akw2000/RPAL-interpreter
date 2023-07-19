package cse_machine;

import Parser.LeafNode;
import Parser.Node;
import control_structures.ControlStructures;
import control_structures.CSNode;
import java.util.List;

public class testCSE2 {
    
    /*
     * Testing the tau node and forming tuples and tuple selection
     */
    public static void main(String[] args) {
        
        // Node n1 = new Node("->");
        // Node n2 = new Node("TRUE");
        // Node n3 = new LeafNode("IDENTIFIER","3");
        // Node n4 = new LeafNode("IDENTIFIER","9");

        Node n1 = new Node("gamma");
        Node n2 = new Node("tau");
        Node n3 = new LeafNode("INTEGER","2");
        Node n4 = new LeafNode("INTEGER","4");
        Node n5 = new LeafNode("INTEGER","5");
        Node n6 = new LeafNode("INTEGER","6");

        n1.setLeft(n2);
        n2.setLeft(n4);
        n2.setRight(n3);
        n4.setRight(n5);
        n5.setRight(n6);
        
        ControlStructures controlStructuresGen = new ControlStructures();

        controlStructuresGen.genControlStructures(n1);

        List<List<CSNode>> delta_lists = controlStructuresGen.getCS();

        CSE cse_m = new CSE(delta_lists);

        cse_m.runCSE();

    }
}
