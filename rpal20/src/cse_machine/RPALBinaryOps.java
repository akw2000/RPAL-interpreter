package cse_machine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import control_structures.CSNode;

/**
 * 
 * @author navindu-ds
 */

/*
 * Class of Methods related to Binary Operations between nodes in RPAL
 */
public class RPALBinaryOps {
    
    /*
     * Static Method to add two integer values
     */
    public static CSNode add(CSNode node1, CSNode node2){

        // check if the nodes are integers before operation
        if (node1.getType().equals("INTEGER") && node2.getType().equals("INTEGER")) {
            int num1 = Integer.parseInt(node1.getName());
            int num2 = Integer.parseInt(node2.getName());
            int sum = num1 + num2;

            // storing the new integer node with the sum
            return new CSNode("INTEGER",String.valueOf(sum));
        } else {
            // if one of the nodes are not integer throw exception
            throw new EvaluationException("Operator is not Integer");
        }
    } 

    /*
     * Static Method to get difference between two integer values
     */
    public static CSNode subtract(CSNode node1, CSNode node2) {
        // check if the nodes are integers before operation
        if (node1.getType().equals("INTEGER") && node2.getType().equals("INTEGER")) {
            int num1 = Integer.parseInt(node1.getName());
            int num2 = Integer.parseInt(node2.getName());
            int diff = num1 - num2;

            // storing the new integer node with the difference
            return new CSNode("INTEGER",String.valueOf(diff));
        } else {
            // if one of the nodes are not integer throw exception
            throw new EvaluationException("Operator is not Integer");
        }
    }

    /*
     * Static Method to get the product of two integer values
     */
    public static CSNode multiply(CSNode node1, CSNode node2) {
        // check if the nodes are integers before operation
        if (node1.getType().equals("INTEGER") && node2.getType().equals("INTEGER")) {
            int num1 = Integer.parseInt(node1.getName());
            int num2 = Integer.parseInt(node2.getName());
            int mult = num1 * num2;

            // storing the new integer node with the product
            return new CSNode("INTEGER",String.valueOf(mult));
        } else {
            // if one of the nodes are not integer throw exception
            throw new EvaluationException("Operator is not Integer");
        }
    }

    /*
     * Static Method to perform floor division between two integer values
     */
    public static CSNode divide(CSNode node1, CSNode node2) {
        // check if the nodes are integers before operation
        if (node1.getType().equals("INTEGER") && node2.getType().equals("INTEGER")) {
            int num1 = Integer.parseInt(node1.getName());
            int num2 = Integer.parseInt(node2.getName());
            int div = Math.floorDiv(num1, num2);

            // storing the new integer node with the quotient
            return new CSNode("INTEGER",String.valueOf(div));
        } else {
            // if one of the nodes are not integer throw exception
            throw new EvaluationException("Operator is not Integer");
        }
    }

    /*
     * Static Method to obtain powers of numbers
     */
    public static CSNode power(CSNode node1, CSNode node2) {
        // check if the nodes are integers before operation
        if (node1.getType().equals("INTEGER") && node2.getType().equals("INTEGER")) {
            int num1 = Integer.parseInt(node1.getName());
            int num2 = Integer.parseInt(node2.getName());
            int power = (int) Math.pow(num1, num2);
            
            // storing the new integer node with power 
            return new CSNode("INTEGER",String.valueOf(power));
        } else {
            // if one of the nodes are not integer throw exception
            throw new EvaluationException("Operator is not Integer");
        }
    }

    /*
     * Static Method to check if the nodes are equal in value
     *      Applicable for Integers, Strings and TruthValues
     */
    public static CSNode isEqual(CSNode node1, CSNode node2) {
        
        List<String> acceptableTypes = new ArrayList<String>(); 
        Collections.addAll(acceptableTypes,"INTEGER","STRING","TRUTHVALUE");

        // checking if the types are compatible and are the same types
        if (acceptableTypes.contains(node1.getType()) && node1.getType().equals(node2.getType())) {
            if (node1.getName().equals(node2.getName())){
                return new CSNode("TRUTHVALUE", "true");
            } else {
                return new CSNode("TRUTHVALUE", "false");
            }
        } else {
            // if the types are not compatible throw exception
            throw new EvaluationException("Types do not match");
        }
    }

    /*
     * Static Method to check if the nodes are not equal in value
     *      Applicable for Integers, Strings and TruthValues
     */
    public static CSNode isNotEqual(CSNode node1, CSNode node2) {
        
        List<String> acceptableTypes = new ArrayList<String>(); 
        Collections.addAll(acceptableTypes,"INTEGER","STRING","TRUTHVALUE");

        // checking if the types are compatible and are the same types
        if (acceptableTypes.contains(node1.getType()) && node1.getType().equals(node2.getType())) {
            if (node1.getName().equals(node2.getName())){
                return new CSNode("TRUTHVALUE", "false");
            } else {
                return new CSNode("TRUTHVALUE", "true");
            }
        } else {
            // if the types are not compatible throw exception
            throw new EvaluationException("Types do not match");
        }
    }

    /*
     * Static Method to check if a node is less than the other in value
     *      Applicable for Integers, Strings
     */
    public static CSNode isLessThan(CSNode node1, CSNode node2) {
        
        // Integer comparision of values
        if (node1.getType().equals("INTEGER") && node2.getType().equals("INTEGER")) {
                if (Integer.parseInt(node1.getName()) < Integer.parseInt(node2.getName())){
                    return new CSNode("TRUTHVALUE", "true");
                } else {
                    return new CSNode("TRUTHVALUE", "false");
                }
        } 

        // string comparison
        // compare 2 strings lexographically, if result is negative, node1 comes before node2 lexicographically
        else if (node1.getType().equals("STRING") && node2.getType().equals("STRING")) {
            
            if (node1.getName().compareTo(node2.getName()) < 0){
                return new CSNode("TRUTHVALUE", "true");
            } else {
                return new CSNode("TRUTHVALUE", "false");
            }
        } else {
            // throw exception if any other combinations of types are found
            throw new EvaluationException("Types do not match");
        }
    }

    /*
     * Static Method to check if a node is greater than the other in value
     *      Applicable for Integers, Strings
     */
    public static CSNode isGreaterThan(CSNode node1, CSNode node2) {
        // Integer comparision of values      
        if (node1.getType().equals("INTEGER") && node2.getType().equals("INTEGER")) {
            if (Integer.parseInt(node1.getName()) > Integer.parseInt(node2.getName())) {
                return new CSNode("TRUTHVALUE", "true");
            } else {
                return new CSNode("TRUTHVALUE", "false");
            }
        } 
        
        // string comparison
        // compare 2 strings lexographically, if result is positive, node1 comes after node2 lexicographically
        else if (node1.getType().equals("STRING") && node2.getType().equals("STRING")) {
            
            if (node1.getName().compareTo(node2.getName()) > 0) {
                return new CSNode("TRUTHVALUE", "true");
            } else {
                return new CSNode("TRUTHVALUE", "false");
            }
        } else {
            throw new EvaluationException("Types do not match");
        }
    }

    /*
     * Static Method to check if a node is less than or equal the other in value
     *      Applicable for Integers, Strings
     */
    public static CSNode isLessEqualThan(CSNode node1, CSNode node2) {
        // Integer comparision    
        if (node1.getType().equals("INTEGER") && node2.getType().equals("INTEGER")) {
                if (Integer.parseInt(node1.getName()) <= Integer.parseInt(node2.getName())) {
                    return new CSNode("TRUTHVALUE", "true");
                } else {
                    return new CSNode("TRUTHVALUE", "false");
                }
        } 
        
        // string comparison
        // compare 2 strings lexographically, if result is negative, node1 comes before node2 lexicographically, if equal to 0, they are equal
        else if (node1.getType().equals("STRING") && node2.getType().equals("STRING")) {
            
            if (node1.getName().compareTo(node2.getName()) <= 0) {
                return new CSNode("TRUTHVALUE", "true");
            } else {
                return new CSNode("TRUTHVALUE", "false");
            }
        } else {
            throw new EvaluationException("Types do not match");
        }
    }

    /*
     * Static Method to check if a node is greater than or equal the other in value
     *      Applicable for Integers, Strings
     */
    public static CSNode isGreaterEqualThan(CSNode node1, CSNode node2) {
         // Integer comparision of values      
        if (node1.getType().equals("INTEGER") && node2.getType().equals("INTEGER")) {
            if (Integer.parseInt(node1.getName()) >= Integer.parseInt(node2.getName())){
                return new CSNode("TRUTHVALUE", "true");
            } else {
                return new CSNode("TRUTHVALUE", "false");
            }
        } 
        
        // string comparison
        // compare 2 strings lexographically, if result is positive, node1 comes after node2 lexicographically, if equal to 0, they are equal
        else if (node1.getType().equals("STRING") && node2.getType().equals("STRING")) {
            if (node1.getName().compareTo(node2.getName()) >= 0){
                return new CSNode("TRUTHVALUE", "true");
            } else {
                return new CSNode("TRUTHVALUE", "false");
            }
        } else {
            throw new EvaluationException("Types do not match");
        }
    }

    /*
     * Static Method to apply OR operation to Truth Values
     */
    public static CSNode logicOR(CSNode node1, CSNode node2) {
        
        if (node1.getType().equals("TRUTHVALUE") && node2.getType().equals("TRUTHVALUE")) {
            if (node1.getName().equals("true") || node2.getName().equals("true")){
                return new CSNode("TRUTHVALUE", "true");
            } else {
                return new CSNode("TRUTHVALUE", "false");
            }
        } else {
            // throw new Exception when types or not TuthValues
            throw new EvaluationException("Types do not match");
        }
    }

    /*
     * Static Method to apply AND operation to Truth Values
     */
    public static CSNode logicAND(CSNode node1, CSNode node2) {
        
        if (node1.getType().equals("TRUTHVALUE") && node2.getType().equals("TRUTHVALUE")) {
            if (node1.getName().equals("true") && node2.getName().equals("true")){
                return new CSNode("TRUTHVALUE", "true");
            } else {
                return new CSNode("TRUTHVALUE", "false");
            }
        } else {
            // throw new Exception when types or not TuthValues
            throw new EvaluationException("Types do not match");
        }
    }

    /*
     * Static Method to augment a tuple or nil node to another node
     */
    public static CSNode augment(CSNode node1, CSNode node2) { 
        
        List<String> acceptableTypes = new ArrayList<String>(); 
        Collections.addAll(acceptableTypes,"tau","NIL","tuple");
        
        if (acceptableTypes.contains(node1.getType())) {
            CSNode augNode = node1.duplicate();
            augNode.setName("tuple");
            augNode.setType("tuple");
            augNode.getTuple().add(node2);
            augNode.setIsTuple(true);
            return augNode;
        } else {
            // throw exception when the first node is not a tuple/nil
            throw new EvaluationException("Cannot Augment to a non-tuple");
        }
    }    

}
