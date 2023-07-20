package cse_machine;

import java.util.ArrayList;
import java.util.Collections;

import control_structures.CSNode;

public class RPALFunc {

    public static boolean checkInBuilt(String name) {
        ArrayList<String> functionNames = new ArrayList<String>();

        Collections.addAll(functionNames, "Print","Stem","Stern","Conc","Order","Null",
                "Isinteger","Istruthvalue","Isstring","Istuple","Isfunction","Isdummy");

        return functionNames.contains(name);
    }

    public static void Print(CSNode node) {
        System.out.println(node.getName());
    }

    public static CSNode Stem(CSNode node) {
        node.setName(node.getName().substring(0,1));
        return node;
    }

    public static CSNode Stern(CSNode node) {
        node.setName(node.getName().substring(1));
        return node;
    }

    public static CSNode Conc(CSNode node1, CSNode node2) {
        String conc = node1.getName().concat(node2.getName());
        return new CSNode("STRING", conc);
    }

    public static CSNode Order(CSNode tupleNode) {
        int num = tupleNode.getTuple().size();
        return new CSNode("INTEGER", String.valueOf(num));
    }

    public static CSNode Null(CSNode tupleNode) {
        if (tupleNode.getTuple().size() == 0) {
            return new CSNode("TRUTHVALUE", "true");
        } else {
            return new CSNode("TRUTHVALUE", "false");
        }
    }

    public static CSNode aug(CSNode tupleNode, CSNode new_elem) {
        tupleNode.getTuple().add(new_elem);
        return tupleNode;
    }

    /*
     * Code to check Type of the variable
     */
}
