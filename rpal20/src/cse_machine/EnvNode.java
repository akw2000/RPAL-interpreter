package cse_machine;

import control_structures.CSNode;

/*
 * Although this class is designed 
 * to store a single variable and single value,
 * the class has to be built to 
 * store multiple variables in cases of multi-variable functions
 */
public class EnvNode {
    
    private int env_no;
    private CSNode variable;
    private EnvNode parentEnv;

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
