package control_structures;

import java.util.List;
import cse_machine.CSE;
import Parser.Node;

/*
 * Created as a test implementation for the Control Structures
 * Test Applicative Expression
 * fx.(x+3) 3
 */
public class test1 {
    public static void main(String[] args) {
        Node n1 = new Node("gamma");
        Node n2 = new Node("lambda");
        Node n3 = new Node("<ID:x>");
        Node n4 = new Node("<OPERATOR:+>");
        Node n5 = new Node("<ID:x>");
        Node n6 = new Node("<INT:3>");
        Node n7 = new Node("<INT:3>");

        n1.setLeft(n2);
        n2.setRight(n7);
        n2.setLeft(n3);
        n3.setRight(n4);
        n4.setLeft(n5);
        n5.setRight(n6);

        ControlStructures ctrller = new ControlStructures();

        ctrller.genControlStructures(n1);

        List<List<CSNode>> cs = ctrller.getCS();

        CSE cse_m = new CSE(cs);

        //cse_m.insertToControl(0);

        //cse_m.expandDelta();

        cse_m.runCSE();

        
    }
}
