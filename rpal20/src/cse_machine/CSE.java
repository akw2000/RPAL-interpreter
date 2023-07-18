package cse_machine;

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

    public void setupCSE() {
        CSNode parent_env = new CSNode("env", curr_env, (CSNode) null);
        this.ControlList.push(parent_env);

        this.insertToControl(0);
        this.expandDelta();
    }

    public void runCSE() {
        while(!this.getControlList().empty()) {
            
            CSNode topCtrlNodeCS = this.getControlList().pop();

            // switch (topCtrlNodeCS.getType()) {
            //     // CSE Rules 3, 4, 10, 11, 12, 13
            //     case "gamma":


                    
            //         break;

            //     // CSE Rules 
            //     case "beta":


            //         break;
            
            //     default:
            //         break;
            // }
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
