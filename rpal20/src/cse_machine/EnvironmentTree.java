package cse_machine;

import java.util.ArrayList;
import java.util.List;

import control_structures.CSNode;

public class EnvironmentTree {
    private List<EnvNode> envList;

    public EnvironmentTree() {
        envList = new ArrayList<EnvNode>();
    }

    public void addEnv(int env_no, CSNode variable, EnvNode parentEnv) {
        EnvNode envNode = new EnvNode(env_no, variable, parentEnv);
        envList.add(envNode);
    }

    public void removeEnv(int env_no){
        for (int i = 0; i < envList.size(); i++) {
            if (envList.get(i).getEnv_no() == env_no) {
                envList.remove(i);
                break;
            }
        }
    }

    public EnvNode getEnvNode(int env_no) {
        for (int i = 0; i < envList.size(); i++) {
            if (envList.get(i).getEnv_no() == env_no) {
                return envList.get(i);
            }
        }
        throw new EvaluationException("Missing Environment");
    }
    

}
