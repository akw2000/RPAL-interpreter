package cse_machine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import control_structures.CSNode;

public class RPALBinaryOps {
    
    public static CSNode add(CSNode node1, CSNode node2){
        if (node1.getType().equals("INTEGER") && node2.getType().equals("INTEGER")) {
            int num1 = Integer.parseInt(node1.getName());
            int num2 = Integer.parseInt(node2.getName());
            int sum = num1 + num2;
            return new CSNode("INTEGER",String.valueOf(sum));
        } else {
            throw new EvaluationException("Operator is not Integer");
        }
    } 

    public static CSNode subtract(CSNode node1, CSNode node2) {
        if (node1.getType().equals("INTEGER") && node2.getType().equals("INTEGER")) {
            int num1 = Integer.parseInt(node1.getName());
            int num2 = Integer.parseInt(node2.getName());
            int diff = num1 - num2;
            return new CSNode("INTEGER",String.valueOf(diff));
        } else {
            throw new EvaluationException("Operator is not Integer");
        }
    }

    public static CSNode multiply(CSNode node1, CSNode node2) {
        if (node1.getType().equals("INTEGER") && node2.getType().equals("INTEGER")) {
            int num1 = Integer.parseInt(node1.getName());
            int num2 = Integer.parseInt(node2.getName());
            int mult = num1 * num2;
            return new CSNode("INTEGER",String.valueOf(mult));
        } else {
            throw new EvaluationException("Operator is not Integer");
        }
    }

    public static CSNode divide(CSNode node1, CSNode node2) {
        /*Remember to use Integer Division here - Answer should be floor value */
        if (node1.getType().equals("INTEGER") && node2.getType().equals("INTEGER")) {
            int num1 = Integer.parseInt(node1.getName());
            int num2 = Integer.parseInt(node2.getName());
            int div = Math.floorDiv(num1, num2);
            return new CSNode("INTEGER",String.valueOf(div));
        } else {
            throw new EvaluationException("Operator is not Integer");
        }
    }

    public static CSNode power(CSNode node1, CSNode node2) {
        if (node1.getType().equals("INTEGER") && node2.getType().equals("INTEGER")) {
            int num1 = Integer.parseInt(node1.getName());
            int num2 = Integer.parseInt(node2.getName());
            int power = (int) Math.pow(num1, num2);
            return new CSNode("INTEGER",String.valueOf(power));
        } else {
            throw new EvaluationException("Operator is not Integer");
        }
    }

    public static CSNode isEqual(CSNode node1, CSNode node2) {
        
        List<String> acceptableTypes = new ArrayList<String>(); 
        Collections.addAll(acceptableTypes,"INTEGER","STRING","TRUTHVALUE");

        if (acceptableTypes.contains(node1.getType()) && node1.getType().equals(node2.getType())) {
            if (node1.getName().equals(node2.getName())){
                return new CSNode("TRUTHVALUE", "true");
            } else {
                return new CSNode("TRUTHVALUE", "false");
            }
        } else {
            throw new EvaluationException("Types do not match");
        }
    }

    public static CSNode isNotEqual(CSNode node1, CSNode node2) {
        
        List<String> acceptableTypes = new ArrayList<String>(); 
        Collections.addAll(acceptableTypes,"INTEGER","STRING","TRUTHVALUE");

        if (acceptableTypes.contains(node1.getType()) && node1.getType().equals(node2.getType())) {
            if (node1.getName().equals(node2.getName())){
                return new CSNode("TRUTHVALUE", "false");
            } else {
                return new CSNode("TRUTHVALUE", "false");
            }
        } else {
            throw new EvaluationException("Types do not match");
        }
    }

    public static CSNode isLessThan(CSNode node1, CSNode node2) {
        
        if (node1.getType().equals("INTEGER") && node2.getType().equals("INTEGER")) {
            if (Integer.parseInt(node1.getName()) < Integer.parseInt(node2.getName())){
                return new CSNode("TRUTHVALUE", "false");
            } else {
                return new CSNode("TRUTHVALUE", "false");
            }
        } else {
            throw new EvaluationException("Types do not match");
        }
    }

    public static CSNode isGreaterThan(CSNode node1, CSNode node2) {
        
        if (node1.getType().equals("INTEGER") && node2.getType().equals("INTEGER")) {
            if (Integer.parseInt(node1.getName()) > Integer.parseInt(node2.getName())){
                return new CSNode("TRUTHVALUE", "false");
            } else {
                return new CSNode("TRUTHVALUE", "false");
            }
        } else {
            throw new EvaluationException("Types do not match");
        }
    }

    public static CSNode isLessEqualThan(CSNode node1, CSNode node2) {
        
        if (node1.getType().equals("INTEGER") && node2.getType().equals("INTEGER")) {
            if (Integer.parseInt(node1.getName()) <= Integer.parseInt(node2.getName())){
                return new CSNode("TRUTHVALUE", "false");
            } else {
                return new CSNode("TRUTHVALUE", "false");
            }
        } else {
            throw new EvaluationException("Types do not match");
        }
    }

    public static CSNode isGreaterEqualThan(CSNode node1, CSNode node2) {
        
        if (node1.getType().equals("INTEGER") && node2.getType().equals("INTEGER")) {
            if (Integer.parseInt(node1.getName()) >= Integer.parseInt(node2.getName())){
                return new CSNode("TRUTHVALUE", "false");
            } else {
                return new CSNode("TRUTHVALUE", "false");
            }
        } else {
            throw new EvaluationException("Types do not match");
        }
    }

    public static CSNode logicOR(CSNode node1, CSNode node2) {
        
        if (node1.getType().equals("TRUTHVALUE") && node2.getType().equals("TRUTHVALUE")) {
            if (node1.getName().equals("true") || node2.getName().equals("true")){
                return new CSNode("TRUTHVALUE", "true");
            } else {
                return new CSNode("TRUTHVALUE", "false");
            }
        } else {
            throw new EvaluationException("Types do not match");
        }
    }

    public static CSNode logicAND(CSNode node1, CSNode node2) {
        
        if (node1.getType().equals("TRUTHVALUE") && node2.getType().equals("TRUTHVALUE")) {
            if (node1.getName().equals("true") && node2.getName().equals("true")){
                return new CSNode("TRUTHVALUE", "true");
            } else {
                return new CSNode("TRUTHVALUE", "false");
            }
        } else {
            throw new EvaluationException("Types do not match");
        }
    }

    public static CSNode augment(CSNode node1, CSNode node2) { 
        
        List<String> acceptableTypes = new ArrayList<String>(); 
        Collections.addAll(acceptableTypes,"tau","NIL");
        
        if (acceptableTypes.contains(node1.getType())) {
            CSNode augNode = node1.duplicate();
            augNode.setName("tau");
            augNode.setType("tau");
            augNode.getTuple().add(node2);
            augNode.setIsTuple(true);
            return augNode;
        } else {
            throw new EvaluationException("Cannot Augment to a non-tuple");
        }
    }    

}
