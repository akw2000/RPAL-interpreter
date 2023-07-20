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
    private EnvNode currEnvNode;
    
    public CSE(List<List<CSNode>> deltaLists) {
        this.deltaLists = deltaLists;
        this.ControlList = new Stack<CSNode>();
        this.StackList = new Stack<CSNode>();
        this.curr_env = 0;
        this.currEnvNode = null;
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
        CSNode parent_env = new CSNode("env", curr_env);
        EnvNode parEnvNode = new EnvNode(0, null, null);

        this.ControlList.push(parent_env);
        this.StackList.push(parent_env);
        this.currEnvNode = parEnvNode;

        this.insertToControl(0);
        this.expandDelta();
    }

    public CSNode lookUpEnv(EnvNode envNode, String variable) {
        CSNode envVar = envNode.getVariable();
        List<String> varList = envVar.getLambdavar();
        if (varList.contains(variable)) {
            int idx = varList.indexOf(variable);
            return envVar.getTuple().get(idx);
        }
        else {
            return lookUpEnv(envNode.getParentEnv(), variable);
        }
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
                
                case "TRUTHVALUE":
                    this.getStackList().push(topCtrlNode);
                    break;

                case "NIL":
                    this.getStackList().push(topCtrlNode);
                    break;

                case "DUMMY":
                    this.getStackList().push(topCtrlNode);
                    break;

                case "Y":
                    this.getStackList().push(topCtrlNode);
                    break;

                case "IDENTIFIER":

                    if (RPALFunc.checkInBuilt(topCtrlNode.getName())) {
                        this.StackList.push(topCtrlNode);
                    } else {
                        // get the name of the indentifier variable
                        String varName = topCtrlNode.getName();
                        // lookup the value of the identifier using the environment tree
                        CSNode valueNode = lookUpEnv(currEnvNode, varName);
                        // add the value of the identifier to the stack
                        this.StackList.push(valueNode);
                    }

                    /*
                     * Need to implement a way to read functions determined by user
                     */

                    break;

                // CSE Rules 2
                // Stack Lambda into the Stack
                case "lambdaClosure":
                    topCtrlNode.setEnvno(curr_env);
                    this.StackList.push(topCtrlNode);
                    break;
  
                // CSE Rules 3, 4, 10, 11, 12, 13
                case "gamma":
                    topStackNode1 = this.getStackList().pop();

                    switch (topStackNode1.getType()) {
                        
                        // CSE Rule 3
                        // Apply Rator
                        case "IDENTIFIER":
                            topStackNode2 = this.getStackList().pop();
                            switch (topStackNode1.getName()) {
                                case "Print":
                                    RPALFunc.Print(topStackNode2);
                                    this.getStackList().push(topStackNode2);
                                    break;

                                case "Conc":
                                    CSNode topStackNode3 = this.getStackList().pop();
                                    CSNode concatNode = RPALFunc.Conc(topStackNode2, topStackNode3);
                                    this.getControlList().pop();
                                    this.getStackList().push(concatNode);
                                    break;
                                
                                case "Stem":
                                    CSNode stemNode = RPALFunc.Stem(topStackNode2);
                                    this.getStackList().push(stemNode);
                                    break;

                                case "Stern":
                                    CSNode sternNode = RPALFunc.Stern(topStackNode2);
                                    this.getStackList().push(sternNode);
                                    break;

                                case "Order":
                                    CSNode numNode = RPALFunc.Order(topStackNode2);
                                    this.getStackList().push(numNode);
                                    break;

                                case "Null":
                                    CSNode nullNode = RPALFunc.Order(topStackNode2);
                                    this.getStackList().push(nullNode);
                                    break;

                                default:

                                /*
                                 * Need to implement code for Defined functions by the user 
                                 */
                                    break;
                            }

                            break;

                        // CSE Rule 4
                        // Apply Lambda
                        case "lambdaClosure":
                            // Obtaining Random Value
                            topStackNode2 = this.getStackList().pop();
                            
                            // moving to next environment
                            curr_env++;

                            // creating new environment variable to insert to control-stack
                            CSNode envCSNode = new CSNode("env", curr_env);

                            if (topStackNode2.getName().equals("tuple")) {
                                List<CSNode> tuple1 = topStackNode2.getTuple();
                                for (int i = 0; i < tuple1.size(); i++) {
                                    topStackNode1.getTuple().add(tuple1.get(i));
                                }

                            } else {
                                topStackNode1.getTuple().add(topStackNode2);
                            }

                            
                            EnvNode envNode = new EnvNode(curr_env, topStackNode1, this.currEnvNode);

                            this.setEnvNode(envNode);

                            this.getControlList().push(envCSNode);
                            this.getStackList().push(envCSNode);

                            int delta_no = topStackNode1.getLambdano();
                            this.insertToControl(delta_no);

                            break;

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
                            ArrayList<String> varList = new ArrayList<String>();
                            varList.add(topStackNode1.getName());
                            newlambdaNode = new CSNode("lambda", varList, topStackNode1.getLambdano());
                            this.getStackList().push(topStackNode1);        // pushing the eta node back into the stack
                            this.getStackList().push(newlambdaNode);        // pushing the lambda into the stack
                            break;
                    
                        default:
                            break;
                    }

                    break;

                // CSE Rules 5
                // Exit Environment
                case "env":
                    // value node to be reinserted to stack
                    topStackNode1 = this.getStackList().pop();
                    
                    // environment variable found in stack
                    topStackNode2 = this.getStackList().pop();

                    /*
                     * There is an error regarding when Lambda variables are found causing a loss of environment
                     */

                    // checking if the environment variables are correct
                    if (topCtrlNode.getType().equals(topStackNode2.getType()) & 
                                        topCtrlNode.getEnvno() == topStackNode2.getEnvno()){
                        this.getStackList().push(topStackNode1);

                        /*
                         * Need to verify how the Environment Tree and 
                         * current environment should be affected when exiting Environments
                         */
                        EnvNode parEnv = this.getEnvNode().getParentEnv();
                        if (this.getEnvNode().getEnv_no() != 0) {
                            this.setCurr_env(parEnv.getEnv_no());
                            this.setEnvNode(parEnv);
                        }
                    } else {
                        // Dummy Error Message
                        System.out.println("There's an Error in Envs");
                    }
                    break;

                    
                // CSE Rules 6
                // Binary Operators
                case "OPERATOR":
                    topStackNode1 = this.getStackList().pop();
                    topStackNode2 = this.getStackList().pop();
                    switch (topCtrlNode.getName()) {
                        case "+":
                            CSNode sumNode = RPALBinaryOps.add(topStackNode1, topStackNode2);
                            this.getStackList().push(sumNode);    
                            break;
                        case "-":
                            CSNode diffNode = RPALBinaryOps.subtract(topStackNode1, topStackNode2);
                            this.getStackList().push(diffNode);    
                            break;
                        case "*":
                            CSNode productNode = RPALBinaryOps.multiply(topStackNode1, topStackNode2);
                            this.getStackList().push(productNode);
                            break;
                        case "/":
                            CSNode quotientNode = RPALBinaryOps.divide(topStackNode1, topStackNode2);
                            this.getStackList().push(quotientNode);
                            break;
                        case "**":
                            CSNode powerNode = RPALBinaryOps.power(topStackNode1, topStackNode2);
                            this.getStackList().push(powerNode);
                            break;
                        case "eq":
                            CSNode isEqual = RPALBinaryOps.isEqual(topStackNode1, topStackNode2);
                            this.getStackList().push(isEqual);
                            break;
                        case "ne":
                            CSNode isNotEqual = RPALBinaryOps.isNotEqual(topStackNode1, topStackNode2);
                            this.getStackList().push(isNotEqual);
                            break;
                        case "ls":
                        case "<":
                            CSNode isLess = RPALBinaryOps.isLessThan(topStackNode1, topStackNode2);
                            this.getStackList().push(isLess);
                            break;
                        case "gr":
                        case ">":
                            CSNode isGreater = RPALBinaryOps.isLessThan(topStackNode1, topStackNode2);
                            this.getStackList().push(isGreater);
                            break;
                        case "le":
                        case "<=":
                            CSNode isLessEqual = RPALBinaryOps.isLessThan(topStackNode1, topStackNode2);
                            this.getStackList().push(isLessEqual);
                            break;
                        case "ge":
                        case ">=":
                            CSNode isGreaterEqual = RPALBinaryOps.isLessThan(topStackNode1, topStackNode2);
                            this.getStackList().push(isGreaterEqual);
                            break;
                        case "or":
                            CSNode logicOR = RPALBinaryOps.logicOR(topStackNode1, topStackNode2);
                            this.getStackList().push(logicOR);
                            break;
                        case "&":
                            CSNode logicAND = RPALBinaryOps.logicAND(topStackNode1, topStackNode2);
                            this.getStackList().push(logicAND);
                            break;
                        default:
                            break;
                    }
                    
                    break;

                // CSE Rules 7
                // Unary Operators
                case "not":
                    topStackNode1 = this.getStackList().pop();
                    this.getStackList().push(RPALUnaryOps.logicNot(topStackNode1));
                    break;
                case "neg":
                    topStackNode1 = this.getStackList().pop();
                    this.getStackList().push(RPALUnaryOps.neg(topStackNode1));
                    break;

                // CSE Rules 8
                // Conditional
                
                /*
                 * Check on this
                 */

                case "beta":
                    topStackNode1 = this.getStackList().pop();
                    if (topStackNode1.getName().equals("true")) {
                        this.getStackList().pop();
                        this.expandDelta();
                    } else if (topStackNode1.getName().equals("false")) {
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
            
                case "delta":
                    this.ControlList.push(topCtrlNode);
                    this.expandDelta();
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

    public EnvNode getEnvNode() {
        return currEnvNode;
    }
    
    public void setEnvNode(EnvNode envNode) {
        this.currEnvNode = envNode;
    }

}
