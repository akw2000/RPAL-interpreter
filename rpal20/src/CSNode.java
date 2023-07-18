/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rpal2;

/**
 *
 * @author OshadiPC
 */
import java.util.ArrayList;

public class CSNode {
    private boolean isTuple;
    private ArrayList<Object> tuple;
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
        tuple = new ArrayList<Object>();
        type = name = lambdavar = "";
        lambdano = lambdaenv = envno = thenno = elseno = tauno = -1;
    }
    
     // used for tau node and identifier type variables
    public CSNode(String t, String n) {
        type = t;
        name = n;
        isTuple = false;
        tuple = new ArrayList<Object>();
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
        tuple = new ArrayList<Object>();
        name = "";
        lambdaenv = envno = thenno = elseno = tauno = -1;
    }
    
    // used for environment variables 
    // need type, env number
    public CSNode(String t, int env_no) {
        type = t;
        envno = env_no;
        isTuple = false;
        tuple = new ArrayList<Object>();
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
        tuple = new ArrayList<Object>();
        name = lambdavar = "";
        lambdano = lambdaenv = envno = tauno = -1;
    }
    
    public void setIstuple(boolean n){
        isTuple = n;
    }
    public void setTuple(ArrayList<Object> t){
        tuple = t;
    }
    public void setType(String t){
        type = t;
    }
    public void setName(String n){
        name = n;
    }
    public void setLambdavar(String n){
        lambdavar = n;
    }
    public void setLambdano(int n){
        lambdano = n;
    }
    public void setLambdaenv(int n){
        lambdaenv = n;
    }
    public void setEnvno(int n){
        envno = n;
    }
    public void setThenno(int n){
        thenno = n;
    }
    public void setElseno(int n){
        elseno = n;
    }
    public void setTauno(int n){
        tauno = n;
    }
    public boolean getIstuple(){
        return this.isTuple;
    }
    public ArrayList<Object> getTuple(){
        return this.tuple;
    }
    public String getType(){
        return this.type;
    }
    public String getName(){
        return this.name;
    }
    public String getLambdavar(){
        return this.lambdavar;
    }
    public int getLambdano(){
        return this.lambdano;
    }
    public int getLambdaenv(){
        return this.lambdaenv;
    }
    public int getEnvno(){
        return this.envno;
    }
    public int getThenno(){
        return this.thenno;
    }
    public int getElseno(){
        return this.elseno;
    }
    public int getTauno(){
        return this.tauno;
    }
}
