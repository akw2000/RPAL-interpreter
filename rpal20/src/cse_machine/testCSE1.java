package cse_machine;

import Parser.LeafNode;
import Parser.Node;
import control_structures.ControlStructures;
import control_structures.CSNode;
import java.util.List;

public class testCSE1 {
    
    public static void main(String[] args) {
        
        Node n1 = new Node("->");
        Node n2 = new Node("TRUE");
        Node n3 = new LeafNode("IDENTIFIER","3");
        Node n4 = new LeafNode("IDENTIFIER","3");

        n1.setLeft(n2);
        n2.setRight(n3);
        n3.setRight(n4);
        
        ControlStructures controlStructuresGen = new ControlStructures();

        controlStructuresGen.genControlStructures(n1);

        List<List<CSNode>> delta_lists = controlStructuresGen.getCS();

        CSE cse_m = new CSE(delta_lists);

        cse_m.runCSE();

    }
}
