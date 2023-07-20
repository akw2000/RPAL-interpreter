package cse_machine;

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

}
