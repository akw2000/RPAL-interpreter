package Parser;

public class Node {
    private String type;
    private Node left_child;
    private Node right_child;


    public Node(String type){
        this.type = type ;
        this.left_child = null;
        this.right_child = null;
    }
    
    public String getType(){
        return this.type;
    }

    public void setType(String type){
        this.type = type;
    }
  
    public void setLeft (Node leftNode){
        this.left_child=leftNode;
    }
    public void setRight (Node rightNode){
        this.right_child=rightNode;
    }
    public Node getLeft (){
        return this.left_child;
    }
    public Node getRight(){
        return this.right_child;
    }
    public void appendRight(Node newNode){
        Node tempNode = this;
        while (tempNode.getRight()!=null){
            tempNode=tempNode.getRight();
        }
        tempNode.setRight(newNode);
    }

}
