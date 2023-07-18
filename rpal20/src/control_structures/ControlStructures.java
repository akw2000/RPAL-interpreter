package control_structures;

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
    private List<List<NodeCS>> delta;

    public ControlStructures() {
        deltano = 0;
        delta_count = 0;
        pendingdelta = new LinkedList<>();
        delta = new ArrayList<>();
    }

    public void genControlStructures(Node root) {
        pendingdelta.add(root);
        while (!pendingdelta.isEmpty()) {
            List<NodeCS> currentdelta = new ArrayList<>();
            Node current = pendingdelta.poll();
            preorder(current, (ArrayList<NodeCS>) currentdelta);
            delta.add(currentdelta);
            deltano++;
        }
    }

    public void preorder(Node root, ArrayList<NodeCS> currentdelta) {
    	if (root.getValue().equals("lambda")) {
        	if (!root.getChild().getValue().equals(",")) {
            		String name = "";
            		if (root.getChild().getValue().substring(0, 3).equals("<ID")) {
                		name = root.getChild().getValue().substring(4, root.getChild().getValue().length() - 1);
            		}
            		NodeCS lambdaclosure = new NodeCS("lambdaClosure", name, ++delta_count);
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
            		NodeCS lambdaclosure = new NodeCS("lambdaClosure", tuple, ++delta_count);
            		lambdaclosure.isTuple = true;
            		currentdelta.add(lambdaclosure);
        	}
        	pendingdelta.add(root.getChild().getSibling());
        	if (root.getSibling() != null)
            		preorder(root.getSibling(), currentdelta);
    	}
    	else if(root.getValue().equals("->")) {
    		NodeCS betaObject = new NodeCS("beta", delta_count + 1, delta_count + 2);
    		currentdelta.add(betaObject);
    		pendingdelta.add(root.getChild().getSibling());
    		Node temp = new Node(root.getChild().getSibling().getSibling().getValue());
    		//System.arraycopy(root.getChild().getSibling().getSibling(), 0, temp, 0, sizeof(Node));
                
				temp.setValue(root.getChild().getSibling().getSibling().getValue());
				temp.setChild(root.getChild().getSibling().getSibling().getChild());
				temp.setSibling(root.getChild().getSibling().getSibling().getSibling());
    		pendingdelta.add(temp);

			root.getChild().getSibling().setChild(null);
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
    		NodeCS t = new NodeCS(type, name);
    		t.tauno = n;
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
    		} else if (root.getValue().equals("<not>")) {
        		type = "not";
        		name = "not";
    		} else if (root.getValue().equals("<neg>")) {
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
    			name = root.getValue().substring(10, root.getValue().length() - 1);
		}
		NodeCS t = new NodeCS(type, name);
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
                
			System.out.println("\n" + delta.get(i).get(j).name + "," + delta.get(i).get(j).type + "," + delta.get(i).get(j).lambdaenv + "," + delta.get(i).get(j).lambdano + "," + delta.get(i).get(j).lambdavar + "," + delta.get(i).get(j).envno + "," + delta.get(i).get(j).thenno + "," + delta.get(i).get(j).elseno + "," + delta.get(i).get(j).tauno + "," + delta.get(i).get(j).isTuple);
            
		}
            
		System.out.println();
        
	}
    
    }

    
    public List<List<NodeCS>> getCS() {
        
	return delta;
    
    }
}
