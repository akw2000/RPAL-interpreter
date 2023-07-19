package cse_machine;

/*
 * Although this class is designed 
 * to store a single variable and single value,
 * the class has to be built to 
 * store multiple variables in cases of multi-variable functions
 */
public class EnvNode {
    
    private int env_no;
    private String env_variable;
    private String value;
    private EnvNode parentEnv;

    public EnvNode(int env_no, String env_variable, String value, EnvNode parentEnv) {
        this.env_no = env_no;
        this.env_variable = env_variable;
        this.value = value;
        this.parentEnv = parentEnv;
    }

    public int getEnv_no() {
        return env_no;
    }

    public void setEnv_no(int env_no) {
        this.env_no = env_no;
    }

    public String getEnv_variable() {
        return env_variable;
    }

    public void setEnv_variable(String env_variable) {
        this.env_variable = env_variable;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public EnvNode getParentEnv() {
        return parentEnv;
    }

    public void setParentEnv(EnvNode parentEnv) {
        this.parentEnv = parentEnv;
    }

    

    
}
