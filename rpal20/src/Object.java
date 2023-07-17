/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rpal;

/**
 *
 * @author OshadiPC
 */
import java.util.ArrayList;

public class Object {
    boolean isTuple;
    private ArrayList<Object> tuple;
    String type;
    String name;
    String lambdavar;
    int lambdano;
    int lambdaenv;
    int envno;
    int thenno;
    int elseno;
    int tauno;

    public Object() {
        isTuple = false;
        tuple = new ArrayList<Object>();
        type = name = lambdavar = "";
        lambdano = lambdaenv = envno = thenno = elseno = tauno = -1;
    }

    // used for tau node and identifier type variables
        // only type and name are made
        // but for Tau node need to declare tauno (# of variables in tuple)
    public Object(String t, String n) {
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
    public Object(String t, String lambda, int lambda_no) {
        type = t;
        lambdavar = lambda;
        lambdano = lambda_no;
        isTuple = false;
        tuple = new ArrayList<Object>();
        name = "";
        lambdaenv = envno = thenno = elseno = tauno = -1;
    }

    // used for environment variables 
    public Object(String t, int env_no) {
        type = t;
        envno = env_no;
        isTuple = false;
        tuple = new ArrayList<Object>();
        name = lambdavar = "";
        lambdano = lambdaenv = thenno = elseno = tauno = -1;
    }

    // used for conditional statements 
        // type is always given as "beta"
    public Object(String t, int then_no, int else_no) {
        type = t;
        thenno = then_no;
        elseno = else_no;
        isTuple = false;
        tuple = new ArrayList<Object>();
        name = lambdavar = "";
        lambdano = lambdaenv = envno = tauno = -1;
    }
}
