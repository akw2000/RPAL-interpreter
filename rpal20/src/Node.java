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
public class Node {
    String value;
    Node child;
    Node sibling;

    public Node() {
        value = "";
        child = null;
        sibling = null;
    }
}
