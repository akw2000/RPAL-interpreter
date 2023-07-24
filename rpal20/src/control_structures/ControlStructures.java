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

// Class to create the Control Structures using the Standardized Tree
public class ControlStructures {
    private int deltano;					// The Delta number of the control structure currently ongiung
    private int delta_count;				// Number of total number of delta structures generated
    private Queue<Node> pendingdelta;		// Queue containing nodes to begin visiting for subsequent control structures
    private List<List<CSNode>> delta;		// List of Already created Control Structures

    public ControlStructures() {
        deltano = 0;
        delta_count = 0;
        pendingdelta = new LinkedList<>();
        delta = new ArrayList<>();
    }

	// Function to generate the control structures starting from the root of the ST
    public void genControlStructures(Parser.Node n1) {
        pendingdelta.add(n1);
        while (!pendingdelta.isEmpty()) {
			// create new control structure as a list
            List<CSNode> currentdelta = new ArrayList<>();
			// get the starting node of the control structure
            Node current = pendingdelta.poll();
			// conduct preorder traversal from the node along the ST tree
            preorder(current, (ArrayList<CSNode>) currentdelta);
			// add the new control structure to list
            delta.add(currentdelta);
            deltano++;
        }
    }

	// Function to perform the preorder traversal from the starting node
    public void preorder(Node root, ArrayList<CSNode> currentdelta) {
		
		// ST node is a lambda node
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

		// ST node is a Conditional Node
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

		// ST node is a Tau Node
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

		// Other cases of ST nodes
    	else {
 			String type = "";
			String name = "";
			// If ST node contains an Identifier
    		if (root.getType().equals("IDENTIFIER")) {
        		type = "IDENTIFIER";
				name = ((LeafNode) root).getValue();
        	
			// If ST node contains a String
    		} else if (root.getType().equals("STRING")) {
        		type = "STRING";
        		name = ((LeafNode) root).getValue();
				name = name.substring(1, name.length()-1);
				
			// If ST node contains an Integer
    		} else if (root.getType().equals("INTEGER")) {
        		type = "INTEGER";
				name = ((LeafNode) root).getValue();
        	
			// If ST node contains a gamma node
    		} else if (root.getType().equals("gamma")) {
        		type = "gamma";
        		name = "gamma";

			// If ST node contains an Y* node
    		} else if (root.getType().equals("Y")) {
        		type = "Y";
        		name = "Y";

			// If ST node contains an TruthValue Node (Either True or False)
    		} else if (root.getType().equals("TRUE")) {
        		type = "TRUTHVALUE";
        		name = "true";
    		} else if (root.getType().equals("FALSE")) {
        		type = "TRUTHVALUE";
        		name = "false";

			// If ST node contains a Not node
    		} else if (root.getType().equals("not")) {
        		type = "not";
        		name = "not";

			// If ST node contains a neg node
    		} else if (root.getType().equals("neg")) {
        		type = "neg";
        		name = "neg";
			
			// If ST node contains a NIL node (tuple with no elements)
    		} else if (root.getType().equals("NIL")) {
        		type = "NIL";
        		name = "nil";
			
			// If ST node contains an Dummy variable
    		} else if (root.getType().equals("DUMMY")) {
        		type = "DUMMY";
        		name = "dummy";
			
			// else it is an Operator Node
			} else {
					type = "OPERATOR";
					name = root.getType();
			}
		
		// Create the new Node 
		CSNode t = new CSNode(type, name);
		
		// the created node is NIL it should be marked as a Tuple Type as well 
		if (t.getType().equals("NIL")) {
			t.setIsTuple(true);
		}

		// add the new node to the control structure
		currentdelta.add(t);

		// traverse to left node and then to the right node 
		if(root.getLeft() != null)
    			preorder(root.getLeft(), currentdelta);
		if(root.getRight() != null)
    			preorder(root.getRight(), currentdelta);
    	}
    }

	/*
	 * Function used to display the control structures
	 */
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

    /*
	 * Function to obtain the Control Structures
	 */
    public List<List<CSNode>> getCS() {
		return delta;
    }

	// Number of Control Structures generated
	public int getDeltano() {
		return deltano;
	}
}
