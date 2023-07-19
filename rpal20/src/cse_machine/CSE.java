package cse_machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import control_structures.CSNode;

public class CSE {
    private List<List<CSNode>> deltaLists;
    private Stack<CSNode> ControlList;
    private Stack<CSNode> StackList;
    private int curr_env;
    
    public CSE(List<List<CSNode>> deltaLists) {
        this.deltaLists = deltaLists;
        this.ControlList = new Stack<CSNode>();
        this.StackList = new Stack<CSNode>();
        this.curr_env = 0;
    }

    public void insertToControl(int delta_num) {
        List<CSNode> delta_i = deltaLists.get(delta_num);
        CSNode delta_cs = new CSNode("delta", delta_num, delta_i);
        this.getControlList().push(delta_cs);
    }

    public void expandDelta() {
        CSNode delta_cs = this.getControlList().pop();

        if (delta_cs.getType().equals("delta")) {
            List<CSNode> ctrl_struct = delta_cs.getTuple();
            for (int i=0; i< ctrl_struct.size(); i++) {
                this.getControlList().push(ctrl_struct.get(i)); 
            }
        } else {
            this.getControlList().push(delta_cs);
        }
    }

    private void setupCSE() {
        CSNode parent_env = new CSNode("env", curr_env, (CSNode) null);
        this.ControlList.push(parent_env);
        this.StackList.push(parent_env);

        this.insertToControl(0);
        this.expandDelta();
    }

    public void runCSE() {
        setupCSE();

        while(!this.getControlList().empty()) {
            
            CSNode topCtrlNode = this.getControlList().pop();
            CSNode topStackNode1;
            CSNode topStackNode2;

            CSNode newGammaNode;
            CSNode newlambdaNode;

            switch (topCtrlNode.getType()) {
                
                // CSE Rules 1
                case "INTEGER":
                    this.getStackList().push(topCtrlNode);
                    break;
                
                case "STRING":
                    this.getStackList().push(topCtrlNode);
                    break;
                
                case "TRUE":
                    this.getStackList().push(topCtrlNode);
                    break;

                case "FALSE":
                    this.getStackList().push(topCtrlNode);
                    break;

                case "NIL":
                    this.getStackList().push(topCtrlNode);
                    break;

                case "DUMMY":
                    this.getStackList().push(topCtrlNode);
                    break;

                // CSE Rules 3, 4, 10, 11, 12, 13
                case "gamma":
                    topStackNode1 = this.getStackList().pop();

                    switch (topStackNode1.getType()) {

                        // CSE Rule 10
                        // Tuple Selection
                        case "tuple":
                            // get the index of the element to select from tuple
                            topStackNode2 = this.getStackList().pop();
                            int index_i = Integer.parseInt(topStackNode2.getName());

                            // extract tuple
                            List<CSNode> tuple = topStackNode1.getTuple();
                            
                            // selecting the required tuple element
                            CSNode tup_elem = tuple.get(index_i-1);

                            // inserting the selected tuple element
                            this.getStackList().push(tup_elem);
                            
                            break;
                        
                        // CSE Rule 12
                        // Applying Y to lambda
                        case "Y":
                            topStackNode2 = this.getStackList().pop();
                            topStackNode2.setType("eta");
                            this.getStackList().push(topStackNode2);
                            break;
                        
                        // CSE Rule 13
                        // Applying f.p.
                        case "eta":
                            // updating the control
                            newGammaNode = new CSNode("gamma", "gamma");
                            this.getControlList().push(newGammaNode);       // pushing a gamma node into the control
                            this.getControlList().push(topStackNode1);      // pushing a gamma node into the control

                            // updating the stack
                            newlambdaNode = new CSNode("lambda", topStackNode1.getName(), topStackNode1.getLambdano());
                            this.getStackList().push(topStackNode1);        // pushing the eta node back into the stack
                            this.getStackList().push(newlambdaNode);        // pushing the lambda into the stack
                            break;
                    
                        default:
                            break;
                    }


                    
                    break;

                // CSE Rules 8
                // Conditional
                case "beta":
                    topStackNode1 = this.getStackList().pop();
                    if (topStackNode1.getType().equals("TRUE")) {
                        this.getStackList().pop();
                        this.expandDelta();
                    } else if (topStackNode1.getType().equals("FALSE")) {
                        CSNode temp = this.getStackList().pop();
                        this.getStackList().pop();
                        this.getStackList().push(temp);
                        this.expandDelta();
                    }
                    break;
                
                // CSE Rules 9
                // Tuple Formation
                case "tau":
                    // get number of elements in the tuple
                    int n = topCtrlNode.getTauno();
                    
                    // creating the tuple Object to be added into the stack
                    CSNode tuple = new CSNode("tuple", "tuple");

                    // extracting each of the tuple items from the loop 
                        // and adding to the tuple object
                    for (int i=0; i<n; i++) {
                        CSNode tup_elem = this.getStackList().pop();
                        tuple.getTuple().add(tup_elem);
                    }

                    // adding the tuple object to the stack
                    this.getStackList().push(tuple);

                    break;
            
                default:
                    break;
            }
            /*
             * Switch Case to be implemented
             */

        }


    }

    public Stack<CSNode> getControlList() {
        return ControlList;
    }

    public void setControlList(Stack<CSNode> controlList) {
        ControlList = controlList;
    }

    public Stack<CSNode> getStackList() {
        return StackList;
    }

    public void setStackList(Stack<CSNode> stackList) {
        StackList = stackList;
    }

    public int getCurr_env() {
        return curr_env;
    }

    public void setCurr_env(int curr_env) {
        this.curr_env = curr_env;
    }

}
