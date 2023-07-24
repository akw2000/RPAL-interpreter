package cse_machine;

import control_structures.CSNode;

public class RPALUnaryOps {
    
    public static CSNode logicNot(CSNode node){
        if (node.getType().equals("TRUTHVALUE")) {
            if (node.getName().equals("true")) {
                return new CSNode("TRUTHVALUE", "false");
            } else {
                return new CSNode("TRUTHVALUE","true");
            }
        } else {
            throw new EvaluationException("Not a TruthValue type");
        }
    }
    
    public static CSNode neg(CSNode node){
        if (node.getType().equals("INTEGER")) {
            int num = Integer.parseInt(node.getName());
            node.setName(String.valueOf(-num));
            return node;
        } else {
            throw new EvaluationException("Not an INTEGER type");
        }
    }

}
