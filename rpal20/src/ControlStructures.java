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
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

public class ControlStructures {
    private int deltano;
    private int delta_count;
    private Queue<Node> pendingdelta;
    private List<List<CSNode>> delta;

    public ControlStructures() {
        deltano = 0;
        delta_count = 0;
        pendingdelta = new LinkedList<>();
        delta = new ArrayList<>();
    }

    public void genControlStructures(Node root) {
        pendingdelta.add(root);
        while (!pendingdelta.isEmpty()) {
            List<CSNode> currentdelta = new ArrayList<>();
            Node current = pendingdelta.poll();
            preorder(current, (ArrayList<CSNode>) currentdelta);
            delta.add(currentdelta);
            deltano++;
        }
    }

    public void preorder(Node root, ArrayList<CSNode> currentdelta) {
    	if (root.getValue().equals("lambda")) {
        	if (!root.getChild().getValue().equals(",")) {
            		String name = "";
            		if (root.getChild().getValue().substring(0, 3).equals("<ID")) {
                		name = root.getChild().getValue().substring(4, root.getChild().getValue().length() - 1);
            		}
            		CSNode lambdaclosure = new CSNode("lambdaClosure", name, ++delta_count);
            		currentdelta.add(lambdaclosure);
        	} else {
            		Node commachild = root.getChild().getChild();
            		String tuple = "";
            		while (commachild != null) {
                		String name = "";
                		if (commachild.getValue().substring(0, 3).equals("<ID")) {
                    			name = commachild.getValue().substring(4, commachild.getValue().length() - 1);
                		}
                		tuple += name + ",";
            	    		commachild = commachild.getSibling();
            		}
            		CSNode lambdaclosure = new CSNode("lambdaClosure", tuple, ++delta_count);
            		lambdaclosure.setIstuple(true);
            		currentdelta.add(lambdaclosure);
        	}
        	pendingdelta.add(root.getChild().getSibling());
        	if (root.getSibling() != null)
                    preorder(root.getSibling(), currentdelta);
    	}
    	else if(root.getValue().equals("->")) {
    		CSNode betaObject = new CSNode("beta", delta_count + 1, delta_count + 2);
    		currentdelta.add(betaObject);
    		pendingdelta.add(root.getChild().getSibling());
    		Node temp = new Node();
    		//System.arraycopy(root.child.sibling.sibling, 0, temp, 0, sizeof(Node));
                temp.setValue(root.getChild().getSibling().getSibling().getValue());
                temp.setChild(root.getChild().getSibling().getSibling().getChild());
                temp.setSibling(root.getChild().getSibling().getSibling().getSibling());
    		pendingdelta.add(temp);
    		root.getChild().getSibling().setSibling(null);
    		root.getChild().setSibling(null);
    		delta_count += 2;
    		if(root.getChild() != null) {
        		preorder(root.getChild(), currentdelta);
    		}
    		if(root.getSibling() != null) {
        		preorder(root.getSibling(), currentdelta);
    		}
    	}
    	else if(root.getValue().equals("tau")) {
    		String name = "tau";
    		String type = "tau";
    		int n = 0;
    		Node temp = root.getChild();
    		while(temp != null) {
        		++n;
        		temp = temp.getSibling();
    		}
    		CSNode t = new CSNode(type, name);
                t.setTauno(n);
    		currentdelta.add(t);
    		if(root.getChild() != null)
        		preorder(root.getChild(), currentdelta);
    		if(root.getSibling() != null)
        		preorder(root.getSibling(), currentdelta);
    	}
    	else {
 		String type = "";
    		String name = "";
    		if (root.getValue().substring(0, 3).equals("<ID")) {
        		type = "IDENTIFIER";
        		name = root.getValue().substring(4, root.getValue().length() - 1);
    		} else if (root.getValue().substring(0, 4).equals("<STR")) {
        		type = "STRING";
        		name = root.getValue().substring(5, root.getValue().length() - 1);
    		} else if (root.getValue().substring(0, 4).equals("<INT")) {
        		type = "INTEGER";
        		name = root.getValue().substring(5, root.getValue().length() - 1);
    		} else if (root.getValue().equals("gamma")) {
        		type = "gamma";
        		name = "gamma";
    		} else if (root.getValue().equals("<Y*>")) {
        		type = "Y*";
        		name = "Y*";
    		} else if (root.getValue().equals("<true>")) {
        		type = "true";
        		name = "true";
    		} else if (root.getValue().equals("<false>")) {
        		type = "false";
        		name = "false";
    		} else if (root.getValue().equals("<UNARY_OP:not>")) {
        		type = "not";
        		name = "not";
    		} else if (root.getValue().equals("<UNARY_OP:neg>")) {
        		type = "neg";
        		name = "neg";
    		} else if (root.getValue().equals("<nil>")) {
        		type = "nil";
        		name = "nil";
    		} else if (root.getValue().equals("<dummy>")) {
        		type = "dummy";
        		name = "dummy";
    		} else if(root.getValue().equals("let") || root.getValue().equals("in") || root.getValue().equals("fn") || root.getValue().equals("where") || root.getValue().equals("aug") || root.getValue().equals("nil") || root.getValue().equals("dummy") || root.getValue().equals("within") || root.getValue().equals("and") || root.getValue().equals("rec") || root.getValue().equals("list")) {
    			type = "KEYWORD";
    			name = root.getValue();
		} else {
    			type = "OPERATOR";
    			name = root.getValue();
		}
		CSNode t = new CSNode(type, name);
		currentdelta.add(t);
		if(root.getChild() != null)
    			preorder(root.getChild(), currentdelta);
		if(root.getSibling() != null)
    			preorder(root.getSibling(), currentdelta);
    	}

    }
    public void display() {
        
	for (int i = 0; i <= delta_count; ++i) {
            
		System.out.print("Delta " + i + ": ");
            
		for (int j = 0; j < delta.get(i).size(); ++j) {
                
			System.out.println("\n" + delta.get(i).get(j).getName() + "," + delta.get(i).get(j).getType() + "," + delta.get(i).get(j).getLambdaenv() + "," + delta.get(i).get(j).getLambdano() + "," + delta.get(i).get(j).getLambdavar() + "," + delta.get(i).get(j).getEnvno() + "," + delta.get(i).get(j).getThenno() + "," + delta.get(i).get(j).getElseno() + "," + delta.get(i).get(j).getTauno() + "," + delta.get(i).get(j).getIstuple());
            
		}
            
		System.out.println();
        
	}
    
    }

    
    public List<List<CSNode>> getCS() {
        
	return delta;
    
    }
}

