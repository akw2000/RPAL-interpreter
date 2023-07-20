package cse_machine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import control_structures.CSNode;

public class RPALBinaryOps {
    
    public static CSNode add(CSNode node1, CSNode node2){
        if (node1.getType().equals("INTEGER") & node2.getType().equals("INTEGER")) {
            int num1 = Integer.parseInt(node1.getName());
            int num2 = Integer.parseInt(node2.getName());
            int sum = num1 + num2;
            return new CSNode("INTEGER",String.valueOf(sum));
        } else {
            throw new EvaluationException("Operator is not Integer");
        }
    } 

    // public static CSNode subtract(CSNode node1, CSNode node2) {

    // }

    // public static CSNode multiply(CSNode node1, CSNode node2) {

    // }

    // public static CSNode divide(CSNode node1, CSNode node2) {
        /*Remember to use Integer Division here - Answer should be floor value */
    // }

    // public static CSNode power(CSNode node1, CSNode node2) {

    // }

    public static CSNode isEqual(CSNode node1, CSNode node2) {
        
        List<String> acceptableTypes = new ArrayList<String>(); 
        Collections.addAll(acceptableTypes,"INTEGER","STRING","TRUTHVALUE");

        if (acceptableTypes.contains(node1.getType()) & node1.getType().equals(node2.getType())) {
            if (node1.getName().equals(node2.getName())){
                return new CSNode("TRUEVALUE", "true");
            } else {
                return new CSNode("TRUEVALUE", "false");
            }
        } else {
            throw new EvaluationException("Types do not match");
        }
    }

    public static CSNode isNotEqual(CSNode node1, CSNode node2) {
        
        List<String> acceptableTypes = new ArrayList<String>(); 
        Collections.addAll(acceptableTypes,"INTEGER","STRING","TRUTHVALUE");

        if (acceptableTypes.contains(node1.getType()) & node1.getType().equals(node2.getType())) {
            if (node1.getName().equals(node2.getName())){
                return new CSNode("TRUEVALUE", "false");
            } else {
                return new CSNode("TRUEVALUE", "false");
            }
        } else {
            throw new EvaluationException("Types do not match");
        }
    }

    public static CSNode isLessThan(CSNode node1, CSNode node2) {
        
        if (node1.getType().equals("INTEGER") & node2.getType().equals("INTEGER")) {
            if (Integer.parseInt(node1.getName()) < Integer.parseInt(node2.getName())){
                return new CSNode("TRUEVALUE", "false");
            } else {
                return new CSNode("TRUEVALUE", "false");
            }
        } else {
            throw new EvaluationException("Types do not match");
        }
    }

    public static CSNode isGreaterThan(CSNode node1, CSNode node2) {
        
        if (node1.getType().equals("INTEGER") & node2.getType().equals("INTEGER")) {
            if (Integer.parseInt(node1.getName()) > Integer.parseInt(node2.getName())){
                return new CSNode("TRUEVALUE", "false");
            } else {
                return new CSNode("TRUEVALUE", "false");
            }
        } else {
            throw new EvaluationException("Types do not match");
        }
    }

    public static CSNode isLessEqualThan(CSNode node1, CSNode node2) {
        
        if (node1.getType().equals("INTEGER") & node2.getType().equals("INTEGER")) {
            if (Integer.parseInt(node1.getName()) <= Integer.parseInt(node2.getName())){
                return new CSNode("TRUEVALUE", "false");
            } else {
                return new CSNode("TRUEVALUE", "false");
            }
        } else {
            throw new EvaluationException("Types do not match");
        }
    }

    public static CSNode isGreaterEqualThan(CSNode node1, CSNode node2) {
        
        if (node1.getType().equals("INTEGER") & node2.getType().equals("INTEGER")) {
            if (Integer.parseInt(node1.getName()) >= Integer.parseInt(node2.getName())){
                return new CSNode("TRUEVALUE", "false");
            } else {
                return new CSNode("TRUEVALUE", "false");
            }
        } else {
            throw new EvaluationException("Types do not match");
        }
    }

    // public static CSNode LogicOR(CSNode node1, CSNode node2) {

    // }

    // public static CSNode LogicAND(CSNode node1, CSNode node2) {

    // }

}
