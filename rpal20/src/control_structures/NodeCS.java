package control_structures;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author OshadiPC
 */
public class NodeCS {
    boolean isTuple;
    private List<NodeCS> tuple;
    String type;
    String name;
    String lambdavar;
    int lambdano;
    int lambdaenv;
    int envno;
    int thenno;
    int elseno;
    int tauno;

    public NodeCS() {
        isTuple = false;
        tuple = new ArrayList<NodeCS>();
        type = name = lambdavar = "";
        lambdano = lambdaenv = envno = thenno = elseno = tauno = -1;
    }

    // used for tau node and identifier type variables
        // only type and name are made
        // but for Tau node need to declare tauno (# of variables in tuple)
    // may need to make them as 2 different functions to separately handle identifiers and tau nodes
    public NodeCS(String t, String n) {
        type = t;
        name = n;
        isTuple = false;
        tuple = new ArrayList<NodeCS>();
        lambdavar = "";
        lambdano = lambdaenv = envno = thenno = elseno = tauno = -1;
    }

    // used for lambda variables
        // type will always be declared as "lambdaClosure"
        // lambdavar indicates the name of the variable to subsitute for
        // lambdano gives the number of the delta control structure

    // used in two occasions
        // 1. When the subsituted variable is an identifier
        // 2. For the comma node (that occurs when simulatenous definitions)

    // same function can be used in both cases
        // need type, lambdavar or name, lambda number 
    public NodeCS(String t, String lambda, int lambda_no) {
        type = t;
        lambdavar = lambda;
        lambdano = lambda_no;
        isTuple = false;
        tuple = new ArrayList<NodeCS>();
        name = "";
        lambdaenv = envno = thenno = elseno = tauno = -1;
    }

    // used for environment variables 
    // need type, env number and the parent environment variable
    public NodeCS(String t, int env_no, NodeCS parent_env) {
        type = t;
        envno = env_no;
        isTuple = false;
        tuple = new ArrayList<NodeCS>();
        tuple.add(parent_env);
        name = lambdavar = "";
        lambdano = lambdaenv = thenno = elseno = tauno = -1;
    }

    // used for conditional statements 
        // type is always given as "beta"
        // need the then_no and else_no as the parameters then
    public NodeCS(String t, int then_no, int else_no) {
        type = t;
        thenno = then_no;
        elseno = else_no;
        isTuple = false;
        tuple = new ArrayList<NodeCS>();
        name = lambdavar = "";
        lambdano = lambdaenv = envno = tauno = -1;
    }

    // used to create an object for inserting a delta structure into the control stack 
    public NodeCS(String t, int delta_no, List<NodeCS> delta_struct) {
        type = t;
        envno = delta_no;
        tuple = delta_struct;
        isTuple = false;
        name = lambdavar = "";
        lambdano = lambdaenv = thenno = elseno = tauno = -1;
    }

    public boolean isTuple() {
        return isTuple;
    }

    public List<NodeCS> getTuple() {
        return tuple;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getLambdavar() {
        return lambdavar;
    }

    public int getLambdano() {
        return lambdano;
    }

    public int getLambdaenv() {
        return lambdaenv;
    }

    public int getEnvno() {
        return envno;
    }

    public int getThenno() {
        return thenno;
    }

    public int getElseno() {
        return elseno;
    }

    public int getTauno() {
        return tauno;
    }

    
}
