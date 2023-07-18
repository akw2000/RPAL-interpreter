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
public class Node {
    private String value;
    private Node child;
    private Node sibling;

    public Node() {
        value = "";
        child = null;
        sibling = null;
    }
    
    public void setValue(String x){
        value = x;
    }
    public void setChild(Node n){
        child = n;
    }
    public void setSibling(Node n){
        sibling = n;
    }
    public String getValue(){
        return value;
    }
    public Node getChild(){
        return child;
    }
    public Node getSibling(){
        return sibling;
    }
}
