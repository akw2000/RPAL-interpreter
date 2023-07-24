/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control_structures;

/**
 *
 * @author OshadiPC
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

import Parser.LeafNode;
import Parser.Node;

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

    public void genControlStructures(Parser.Node n1) {
        pendingdelta.add(n1);
        while (!pendingdelta.isEmpty()) {
            List<CSNode> currentdelta = new ArrayList<>();
            Node current = pendingdelta.poll();
            preorder(current, (ArrayList<CSNode>) currentdelta);
            delta.add(currentdelta);
            deltano++;
        }
    }

    public void preorder(Node root, ArrayList<CSNode> currentdelta) {
    	if (root.getType().equals("lambda")) {
        	if (!root.getLeft().getType().equals("comma")) {
            		ArrayList<String> name = new ArrayList<String>();
            		if (root.getLeft().getType().equals("IDENTIFIER")) {
                		String varname = ((LeafNode) root.getLeft()).getValue();
						name.add(varname);
            		}
            		CSNode lambdaclosure = new CSNode("lambdaClosure", name, ++delta_count);
            		currentdelta.add(lambdaclosure);
        	} else {
            		Node commachild = root.getLeft().getLeft();
            		ArrayList<String> tuple = new ArrayList<String>();
            		while (commachild != null) {
                		String name = "";
                		if (commachild.getType().equals("IDENTIFIER")) {
                    			name = ((LeafNode) commachild).getValue();
                		}
                		tuple.add(name);
            	    		commachild = commachild.getRight();
            		}
            		CSNode lambdaclosure = new CSNode("lambdaClosure", tuple, ++delta_count);
            		lambdaclosure.setIsTuple(true);
            		currentdelta.add(lambdaclosure);
        	}
        	pendingdelta.add(root.getLeft().getRight());
        	if (root.getRight() != null)
            		preorder(root.getRight(), currentdelta);
    	}
    	else if(root.getType().equals("->")) {
    		CSNode betaObject = new CSNode("beta", delta_count + 1, delta_count + 2);
    		currentdelta.add(betaObject);
    		pendingdelta.add(root.getLeft().getRight());
			pendingdelta.add(root.getLeft().getRight().getRight());

			root.getLeft().getRight().setRight(null);
    		root.getLeft().setRight(null);
			root.setRight(null);
    		delta_count += 2;
    		if(root.getLeft() != null) {
        		preorder(root.getLeft(), currentdelta);
    		}
    		if(root.getRight() != null) {
        		preorder(root.getRight(), currentdelta);
    		}
    	}
    	else if(root.getType().equals("tau")) {
    		String name = "tau";
    		String type = "tau";
    		int n = 0;
    		Node temp = root.getLeft();
    		while(temp != null) {
        		++n;
        		temp = temp.getRight();
    		}
    		CSNode t = new CSNode(type, name);
    		t.setTauno(n);
    		currentdelta.add(t);
    		if(root.getLeft() != null)
        		preorder(root.getLeft(), currentdelta);
    		if(root.getRight() != null)
        		preorder(root.getRight(), currentdelta);
    	}
    	else {
 		String type = "";
    		String name = "";
    		if (root.getType().equals("IDENTIFIER")) {
        		type = "IDENTIFIER";
				name = ((LeafNode) root).getValue();
        		// name = root.getType().substring(3, root.getType().length() - 1);
    		} else if (root.getType().equals("STRING")) {
        		type = "STRING";
        		name = ((LeafNode) root).getValue();
				name = name.substring(1, name.length()-1);
				// name = root.getType().substring(5, root.getType().length() - 1);
    		} else if (root.getType().equals("INTEGER")) {
        		type = "INTEGER";
				name = ((LeafNode) root).getValue();
        		// name = root.getType().substring(5, root.getType().length() - 1);
    		} else if (root.getType().equals("gamma")) {
        		type = "gamma";
        		name = "gamma";
    		} else if (root.getType().equals("Y")) {
        		type = "Y";
        		name = "Y";
    		} else if (root.getType().equals("TRUE")) {
        		type = "TRUTHVALUE";
        		name = "true";
    		} else if (root.getType().equals("FALSE")) {
        		type = "TRUTHVALUE";
        		name = "false";
    		} else if (root.getType().equals("not")) {
        		type = "not";
        		name = "not";
    		} else if (root.getType().equals("neg")) {
        		type = "neg";
        		name = "neg";
    		} else if (root.getType().equals("NIL")) {
        		type = "NIL";
        		name = "nil";
    		} else if (root.getType().equals("DUMMY")) {
        		type = "DUMMY";
        		name = "dummy";

		} else {
    			type = "OPERATOR";
    			name = root.getType();
		}
		CSNode t = new CSNode(type, name);
		
		// the created node is NIL it should be marked as a Tuple Type as well 
		if (t.getType().equals("NIL")) {
			t.setIsTuple(true);
		}
		currentdelta.add(t);
		if(root.getLeft() != null)
    			preorder(root.getLeft(), currentdelta);
		if(root.getRight() != null)
    			preorder(root.getRight(), currentdelta);
    	}

    }
    public void display() {
        
	for (int i = 0; i <= delta_count; ++i) {
            
		System.out.print("Delta " + i + ": ");
            
		for (int j = 0; j < delta.get(i).size(); ++j) {
                
			System.out.println("\n" + delta.get(i).get(j).getName() + "," + delta.get(i).get(j).getType() + "," 
				+ delta.get(i).get(j).getLambdaenv() + "," + delta.get(i).get(j).getLambdano() + "," 
				+ delta.get(i).get(j).getLambdavar() + "," + delta.get(i).get(j).getEnvno() + "," 
				+ delta.get(i).get(j).getThenno() + "," + delta.get(i).get(j).getElseno() + "," 
				+ delta.get(i).get(j).getTauno() + "," + delta.get(i).get(j).getIsTuple()
				);
		}

			System.out.println();
        
		}
    
    }

    
    public List<List<CSNode>> getCS() {
        
		return delta;
    }

	public int getDeltano() {
		return deltano;
	}
}
