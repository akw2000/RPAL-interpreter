package cse_machine;

import control_structures.CSNode;

/**
 * 
 * @author navindu-ds
 */

/*
 * Class to represent an Environment Node inserted into the Environment Tree
 *      and stores the variables and values for the environment
 */
public class EnvNode {
    
    private int env_no;             // environment number 
    private CSNode variable;        // the variable/s and its corresponding value/s
    private EnvNode parentEnv;      // the parent environment node

    public EnvNode(int env_no, CSNode variable, EnvNode parentEnv) {
        this.env_no = env_no;
        this.variable = variable;
        this.parentEnv = parentEnv;
    }

    public int getEnv_no() {
        return env_no;
    }

    public void setEnv_no(int env_no) {
        this.env_no = env_no;
    }

    public CSNode getVariable() {
        return variable;
    }

    public void setEnv_variable(CSNode env_variable) {
        this.variable = env_variable;
    }

    public EnvNode getParentEnv() {
        return parentEnv;
    }

    public void setParentEnv(EnvNode parentEnv) {
        this.parentEnv = parentEnv;
    }
    
}
