/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package control_structures;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author OshadiPC
 */
/*
 * Class for Objects used as Nodes inside the Control Structures and CSE Machine
 */
public class CSNode {
    private boolean isTuple;            // indicates if node is tuple
    private List<CSNode> tuple;         // stores the elements of the tuple
    private String type;                // stores the type of node
    private String name;                // contains the value of the node for Integer, String, Truthvalue
    private List<String> lambdavar;     // stores the list of nodes enclosed by the lambda node
    private int lambdano;               // indicates the delta control structure it connects to
    private int lambdaenv;              // 
    private int envno;                  // indicates the environment number the node celongs too
                                                // used in lambda and env nodes
    private int thenno;                 // indicates the control structure to load if condition is true
                                                // used in Beta nodes
    private int elseno;                 // indicates the control structure to load if condition is false
                                                // used in Beta nodes
    private int tauno;                  // indicates the number of elements in tau node
                                                // used in tau nodes

    public CSNode() {
        isTuple = false;
        tuple = new ArrayList<CSNode>();
        lambdavar = new ArrayList<String>();
        type = name = "";
        lambdano = lambdaenv = envno = thenno = elseno = tauno = -1;
    }

    // used for tau node and identifier type variables
        // only type and name are made
        // but for Tau node need to declare tauno (# of variables in tuple)
    public CSNode(String t, String n) {
        type = t;
        name = n;
        isTuple = false;
        tuple = new ArrayList<CSNode>();
        lambdavar = new ArrayList<String>();
        lambdano = lambdaenv = envno = thenno = elseno = tauno = -1;
    }

    // used for lambda variables
        // type will always be declared as "lambdaClosure"
        // lambdavar indicates the name of the variable to subsitute for
        // lambdano gives the number of the delta control structure
    public CSNode(String t, List<String> varLambda, int lambda_no) {
        type = t;
        lambdavar = varLambda;
        lambdano = lambda_no;
        isTuple = false;
        tuple = new ArrayList<CSNode>();
        name = "";
        lambdaenv = envno = thenno = elseno = tauno = -1;
    }

    // used for environment variables 
        // need type, env number
    public CSNode(String t, int env_no) {
        type = t;
        envno = env_no;
        isTuple = false;
        tuple = new ArrayList<CSNode>();
        name = "";
        lambdavar = new ArrayList<String>();
        lambdano = lambdaenv = thenno = elseno = tauno = -1;
    }

    // used for conditional statements 
        // type is always given as "beta"
        // need the then_no and else_no as the parameters then
    public CSNode(String t, int then_no, int else_no) {
        type = t;
        thenno = then_no;
        elseno = else_no;
        isTuple = false;
        tuple = new ArrayList<CSNode>();
        name = "";
        lambdavar =new ArrayList<String>();
        lambdano = lambdaenv = envno = tauno = -1;
    }

    // used to create an object for inserting a delta structure into the control stack 
        // delta no is stored in the envno parameter
    public CSNode(String t, int delta_no, List<CSNode> delta_struct) {
        type = t;
        envno = delta_no;
        tuple = delta_struct;
        isTuple = false;
        name =  "";
        lambdavar = new ArrayList<String>();
        lambdano = lambdaenv = thenno = elseno = tauno = -1;
    }

    public boolean getIsTuple() {
        return isTuple;
    }

    public List<CSNode> getTuple() {
        return tuple;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public List<String> getLambdavar() {
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

    public void setIsTuple(boolean isTuple) {
        this.isTuple = isTuple;
    }

    public void setTuple(List<CSNode> tuple) {
        this.tuple = tuple;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLambdavar(List<String> lambdavar) {
        this.lambdavar = lambdavar;
    }

    public void setLambdano(int lambdano) {
        this.lambdano = lambdano;
    }

    public void setLambdaenv(int lambdaenv) {
        this.lambdaenv = lambdaenv;
    }

    public void setEnvno(int envno) {
        this.envno = envno;
    }

    public void setThenno(int thenno) {
        this.thenno = thenno;
    }

    public void setElseno(int elseno) {
        this.elseno = elseno;
    }

    public void setTauno(int tauno) {
        this.tauno = tauno;
    }
    
    /*
     * Function to duplicate contents of Control Structure Nodes
     */
    public CSNode duplicate() {
        CSNode dupNode = new CSNode();
        dupNode.setIsTuple(this.getIsTuple());
        dupNode.setTuple(this.getTuple());
        dupNode.setType(this.getType());
        dupNode.setName(this.getName());
        dupNode.setLambdavar(this.getLambdavar());
        dupNode.setLambdano(this.getLambdano());
        dupNode.setLambdaenv(this.getLambdaenv());
        dupNode.setEnvno(this.getEnvno());
        dupNode.setThenno(this.getThenno());
        dupNode.setElseno(this.getElseno());
        dupNode.setTauno(this.getTauno());

        return dupNode;
    }

}
