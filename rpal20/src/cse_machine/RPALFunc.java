package cse_machine;

import java.util.ArrayList;
import java.util.Collections;

import control_structures.CSNode;

public class RPALFunc {

    public static boolean checkInBuilt(String name) {
        ArrayList<String> functionNames = new ArrayList<String>();

        Collections.addAll(functionNames, "Print","Stem","Stern","Conc","Order","Null",
                "Isinteger","Istruthvalue","Isstring","Istuple","Isfunction","Isdummy","ItoS");

        return functionNames.contains(name);
    }

    public static void Print(CSNode node) {
        switch (node.getType()) {
            case "INTEGER":
            case "STRING":
            case "TRUTHVALUE":
            case "NIL":
                // if getName string contains escape characters, print them accordingly
                if (node.getName().contains("\\n")) {
                    node.setName(node.getName().replace("\\n", "\n"));
                }
                if(node.getName().contains("\\t")) {
                    node.setName(node.getName().replace("\\t", "\t"));
                }
                System.out.print(node.getName());
                break;
            case "tau":
            case "tuple":
                // print as a tuple like (1,2,3)
                System.out.print("(");
                for (int i = 0; i < node.getTuple().size(); i++) {
                    System.out.print(node.getTuple().get(i).getName());
                    if (i != node.getTuple().size() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println(")");
                break;
            case "lambdaClosure":
                // print like [lambda closure: x: 2]
                System.out.print("[lambda closure: ");
                for (int i = 0; i < node.getLambdavar().size(); i++) {
                    System.out.print(node.getLambdavar().get(i));       
                    /*
                     * Have to check how it should be printed for many lambda variables
                     */
                }
                System.out.print(": ");
                System.out.print(node.getLambdano());
                System.out.println("]");
                break;
            default:
                System.out.println(); // check this
                break;
            /*
             * Need to implement the rest of the Printing here
             * Make sure no other cases are missed as well
             */
        }        
    }

    public static CSNode Stem(CSNode node) {
        CSNode newNode = node.duplicate();
        newNode.setName(newNode.getName().substring(0,1));
        return newNode;
    }

    public static CSNode Stern(CSNode node) {
        CSNode newNode = node.duplicate();
        newNode.setName(newNode.getName().substring(1));
        return newNode;
    }

    public static CSNode ConcOne(CSNode node1) {
        CSNode concOne = new CSNode("IDENTIFIER","ConcOne");
        concOne.getTuple().add(node1);
        return concOne;
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
        CSNode newNode = tupleNode.duplicate();
        newNode.getTuple().add(new_elem);
        return newNode;
    }

    /*
     * Code to check Type of the variable
     */
    public static CSNode Isinteger(CSNode node) {
        if (node.getType().equals("INTEGER")) {
            return new CSNode("TRUTHVALUE", "true");
        } else {
            return new CSNode("TRUTHVALUE", "false");
        }
    }

    public static CSNode Istruthvalue(CSNode node) {
        if (node.getType().equals("TRUTHVALUE")) {
            return new CSNode("TRUTHVALUE", "true");
        } else {
            return new CSNode("TRUTHVALUE", "false");
        }
    }

    public static CSNode Isstring(CSNode node) {
        if (node.getType().equals("STRING")) {
            return new CSNode("TRUTHVALUE", "true");
        } else {
            return new CSNode("TRUTHVALUE", "false");
        }
    }

    public static CSNode Istuple(CSNode node) {
        if (node.getIsTuple()) { ///////////////////// check this
            return new CSNode("TRUTHVALUE", "true");
        } else {
            return new CSNode("TRUTHVALUE", "false");
        }
    }

    public static CSNode Isfunction(CSNode node) {
        if (node.getType().equals("FUNCTION")) { //////////check this
            return new CSNode("TRUTHVALUE", "true");
        } else {
            return new CSNode("TRUTHVALUE", "false");
        }
    }

    public static CSNode Isdummy(CSNode node) {
        if (node.getType().equals("DUMMY")) {
            return new CSNode("TRUTHVALUE", "true");
        } else {
            return new CSNode("TRUTHVALUE", "false");
        }
    }

    public static CSNode intToStr(CSNode intNode) {
        if (intNode.getType().equals("INTEGER")) {
            CSNode strNode = intNode.duplicate();
            strNode.setType("STRING");
            return strNode;
        } else {
            throw new EvaluationException("Argument is not an Integer");
        }
    }

    
}
