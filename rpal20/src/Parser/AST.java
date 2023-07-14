package Parser;

public class AST {
    private Node root;

    public AST(Node root){
        this.root = root;
    }

    public Node getRoot(){
        return this.root;
    }

    public void setRoot(Node root){
        this.root=root;
    }

    public Node standardize(Node root){
        return root;
    }
    public void print(){
        preOrderPrint(root,"");
    }
    
    private void preOrderPrint(Node node, String printPrefix){
        if(node==null){
          return;
        }
    
        printASTNodeDetails(node, printPrefix);
        preOrderPrint(node.getLeft(),printPrefix+".");
        preOrderPrint(node.getRight(),printPrefix);
      }
    
      private void printASTNodeDetails(Node node, String printPrefix){
        if(node.getType() == "IDENTIFIER" ||
            node.getType() == "INTEGER"){
          System.out.printf(printPrefix+node.getType()+"\n",((LeafNode)node).getValue());
        }
        else if(node.getType() == "STRING")
          System.out.printf(printPrefix+node.getType()+"\n",((LeafNode)node).getValue());
        else
          System.out.println(printPrefix+node.getType());
      }
    
}
