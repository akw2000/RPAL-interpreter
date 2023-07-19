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
public class CSNode {
    private boolean isTuple;
    private List<CSNode> tuple;
    private String type;
    private String name;
    private String lambdavar;
    private int lambdano;
    private int lambdaenv;
    private int envno;
    private int thenno;
    private int elseno;
    private int tauno;

    public CSNode() {
        isTuple = false;
        tuple = new ArrayList<CSNode>();
        type = name = lambdavar = "";
        lambdano = lambdaenv = envno = thenno = elseno = tauno = -1;
    }

    // used for tau node and identifier type variables
        // only type and name are made
        // but for Tau node need to declare tauno (# of variables in tuple)
    // may need to make them as 2 different functions to separately handle identifiers and tau nodes
    public CSNode(String t, String n) {
        type = t;
        name = n;
        isTuple = false;
        tuple = new ArrayList<CSNode>();
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
    public CSNode(String t, String lambda, int lambda_no) {
        type = t;
        lambdavar = lambda;
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
        name = lambdavar = "";
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
        name = lambdavar = "";
        lambdano = lambdaenv = envno = tauno = -1;
    }

    // used to create an object for inserting a delta structure into the control stack 
    public CSNode(String t, int delta_no, List<CSNode> delta_struct) {
        type = t;
        envno = delta_no;
        tuple = delta_struct;
        isTuple = false;
        name = lambdavar = "";
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

    public void setLambdavar(String lambdavar) {
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
    
}
