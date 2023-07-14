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

import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.LinkedList;

public class ControlStructures {
    private int deltano;
    private int delta_count;
    private Queue<Node> pendingdelta;
    private List<List<Object>> delta;

    public ControlStructures() {
        deltano = 0;
        delta_count = 0;
        pendingdelta = new LinkedList<>();
        delta = new ArrayList<>();
    }

    public void genControlStructures(Node root) {
        pendingdelta.add(root);
        while (!pendingdelta.isEmpty()) {
            List<Object> currentdelta = new ArrayList<>();
            Node current = pendingdelta.poll();
            preorder(current, (ArrayList<Object>) currentdelta);
            delta.add(currentdelta);
            deltano++;
        }
    }

    public void preorder(Node root, ArrayList<Object> currentdelta) {
    	if (root.value.equals("lambda")) {
        	if (!root.child.value.equals(",")) {
            		String name = "";
            		if (root.child.value.substring(0, 3).equals("<ID")) {
                		name = root.child.value.substring(4, root.child.value.length() - 5);
            		}
            		Object lambdaclosure = new Object("lambdaClosure", name, ++delta_count);
            		currentdelta.add(lambdaclosure);
        	} else {
            		Node commachild = root.child.child;
            		String tuple = "";
            		while (commachild != null) {
                		String name = "";
                		if (commachild.value.substring(0, 3).equals("<ID")) {
                    			name = commachild.value.substring(4, commachild.value.length() - 5);
                		}
                		tuple += name + ",";
            	    		commachild = commachild.sibling;
            		}
            		Object lambdaclosure = new Object("lambdaClosure", tuple, ++delta_count);
            		lambdaclosure.isTuple = true;
            		currentdelta.add(lambdaclosure);
        	}
        	pendingdelta.add(root.child.sibling);
        	if (root.sibling != null)
            		preorder(root.sibling, currentdelta);
    	}
    	else if(root.value.equals("->")) {
    		Object betaObject = new Object("beta", delta_count + 1, delta_count + 2);
    		currentdelta.add(betaObject);
    		pendingdelta.add(root.child.sibling);
    		Node temp = new Node();
    		//System.arraycopy(root.child.sibling.sibling, 0, temp, 0, sizeof(Node));
                temp.value = root.child.sibling.sibling.value;
                temp.child = root.child.sibling.sibling.child;
                temp.sibling = root.child.sibling.sibling.sibling;
    		pendingdelta.add(temp);
    		root.child.sibling.sibling = null;
    		root.child.sibling = null;
    		delta_count += 2;
    		if(root.child != null) {
        		preorder(root.child, currentdelta);
    		}
    		if(root.sibling != null) {
        		preorder(root.sibling, currentdelta);
    		}
    	}
    	else if(root.value.equals("tau")) {
    		String name = "tau";
    		String type = "tau";
    		int n = 0;
    		Node temp = root.child;
    		while(temp != null) {
        		++n;
        		temp = temp.sibling;
    		}
    		Object t = new Object(type, name);
    		t.tauno = n;
    		currentdelta.add(t);
    		if(root.child != null)
        		preorder(root.child, currentdelta);
    		if(root.sibling != null)
        		preorder(root.sibling, currentdelta);
    	}
    	else {
 		String type = "";
    		String name = "";
    		if (root.value.substring(0, 3).equals("<ID")) {
        		type = "IDENTIFIER";
        		name = root.value.substring(4, root.value.length() - 5);
    		} else if (root.value.substring(0, 4).equals("<STR")) {
        		type = "STRING";
        		name = root.value.substring(5, root.value.length() - 6);
    		} else if (root.value.substring(0, 4).equals("<INT")) {
        		type = "INTEGER";
        		name = root.value.substring(5, root.value.length() - 6);
    		} else if (root.value.equals("gamma")) {
        		type = "gamma";
        		name = "gamma";
    		} else if (root.value.equals("<Y*>")) {
        		type = "Y*";
        		name = "Y*";
    		} else if (root.value.equals("<true>")) {
        		type = "true";
        		name = "true";
    		} else if (root.value.equals("<false>")) {
        		type = "false";
        		name = "false";
    		} else if (root.value.equals("not")) {
        		type = "not";
        		name = "not";
    		} else if (root.value.equals("neg")) {
        		type = "neg";
        		name = "neg";
    		} else if (root.value.equals("<nil>")) {
        		type = "nil";
        		name = "nil";
    		} else if (root.value.equals("<dummy>")) {
        		type = "dummy";
        		name = "dummy";
    		} else if(root.value.equals("let") || root.value.equals("in") || root.value.equals("fn") || root.value.equals("where") || root.value.equals("aug") || root.value.equals("nil") || root.value.equals("dummy") || root.value.equals("within") || root.value.equals("and") || root.value.equals("rec") || root.value.equals("list")) {
    			type = "KEYWORD";
    			name = root.value;
		} else {
    			type = "OPERATOR";
    			name = root.value;
		}
		Object t = new Object(type, name);
		currentdelta.add(t);
		if(root.child != null)
    			preorder(root.child, currentdelta);
		if(root.sibling != null)
    			preorder(root.sibling, currentdelta);
    	}

    }
    public void display() {
        
	for (int i = 0; i <= delta_count; ++i) {
            
		System.out.print("Delta " + i + ": ");
            
		for (int j = 0; j < delta.get(i).size(); ++j) {
                
			System.out.println("\n" + delta.get(i).get(j).name + "," + delta.get(i).get(j).type + "," + delta.get(i).get(j).lambdaenv + "," + delta.get(i).get(j).lambdano + "," + delta.get(i).get(j).lambdavar + "," + delta.get(i).get(j).envno + "," + delta.get(i).get(j).thenno + "," + delta.get(i).get(j).elseno + "," + delta.get(i).get(j).tauno + "," + delta.get(i).get(j).isTuple);
            
		}
            
		System.out.println();
        
	}
    
    }

    
    public List<List<Object>> getCS() {
        
	return delta;
    
    }
}
