package cse_machine;

import java.util.List;
import java.util.Stack;

import control_structures.NodeCS;

public class CSE {
    private List<List<NodeCS>> deltaLists;
    private Stack<NodeCS> ControlList;
    private Stack<NodeCS> StackList;
    private int curr_env;
    
    public CSE(List<List<NodeCS>> deltaLists) {
        this.deltaLists = deltaLists;
        this.ControlList = new Stack<NodeCS>();
        this.StackList = new Stack<NodeCS>();
        this.curr_env = 0;
    }

    public void insertToControl(int delta_num) {
        List<NodeCS> delta_i = deltaLists.get(delta_num);
        NodeCS delta_cs = new NodeCS("delta", delta_num, delta_i);
        this.getControlList().push(delta_cs);
    }

    public void expandDelta() {
        NodeCS delta_cs = this.getControlList().pop();

        if (delta_cs.getType().equals("delta")) {
            List<NodeCS> ctrl_struct = delta_cs.getTuple();
            for (int i=0; i< ctrl_struct.size(); i++) {
                this.getControlList().push(ctrl_struct.get(i)); 
            }
        } else {
            this.getControlList().push(delta_cs);
        }
    }

    public void setupCSE() {
        NodeCS parent_env = new NodeCS("env", curr_env, (NodeCS) null);
        this.ControlList.push(parent_env);

        this.insertToControl(0);
        this.expandDelta();
    }

    public void runCSE() {
        while(!this.getControlList().empty()) {
            
            NodeCS topCtrlNodeCS = this.getControlList().pop();

            /*
             * Switch Case to be implemented
             */

        }


    }

    public Stack<NodeCS> getControlList() {
        return ControlList;
    }

    public void setControlList(Stack<NodeCS> controlList) {
        ControlList = controlList;
    }

    public Stack<NodeCS> getStackList() {
        return StackList;
    }

    public void setStackList(Stack<NodeCS> stackList) {
        StackList = stackList;
    }

    public int getCurr_env() {
        return curr_env;
    }

    public void setCurr_env(int curr_env) {
        this.curr_env = curr_env;
    }

}
