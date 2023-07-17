package Parser;

public class AST {
    private Node root;
    private boolean std ;

    public AST(Node root){
        this.root = root;
        this.std = false;
    }

    public Node getRoot(){
        return this.root;
    }

    public void setRoot(Node root){
        this.root=root;
    }

    public void standardize(){
        standardizeNode(this.root);
        this.std = true;
      
    }

    public boolean isStandardized(Node rooNode){
      return this.std;
    }
    private void standardizeNode(Node node){
      //traverse to the bootom most node
      if (node.getLeft()!=null){
        Node child = node.getLeft();
        while (child!=null){
          standardizeNode(child);
          child = child.getRight();
        }
      }

      // standardize using standardization rules
      switch (node.getType()){
        case "let":
          //standardize let 
          /*          let                   gamma
          *          /  \                  /   \
          *         =    P     =>      lambda   E
          *        / \                 /   \
          *       X   E                X    P
          */

          Node equalNode = node.getLeft();
          Node p = node.getRight();
          node.setRight(equalNode.getRight());
          equalNode.setRight(p);
          node.setType("gamma");
          equalNode.setType("lambda");
          break;
        case "where":
          /*      where                     gamma
           *      /   \                     /   \
           *     P     =      =>         lambda  E
           *          / \                 /   \
           *         X   E               X     P
           */

          equalNode = node.getRight();
          p = node.getLeft();
          node.setRight(equalNode.getRight());
          equalNode.setRight(p);
          node.setLeft(equalNode);
          node.setType("gamma");
          equalNode.setType("lambda");

        
        
        case "within":
          /*        within                      =
           *        /   \                     /   \
           *       =     =      =>           X2   gamma
           *      / \   / \                        /  \
           *     X1 E1 X2  E2                  lambda  E1
           *                                    /   \
           *                                   X1    E2
           */
          
           equalNode = node.getLeft();
           Node equalNode2 = node.getRight();
           Node e1 = equalNode.getRight();
           equalNode.setRight(equalNode2.getRight());
           node.setLeft(equalNode2.getLeft());
           equalNode2.setRight(e1);
           equalNode2.setLeft(equalNode);
           node.setType("=");
           equalNode.setType("lambda");
           equalNode2.setType("gamma");
        
        case "rec":

          /*      rec                           =
           *       |                           /  \
           *       =              =>          X   gamma
           *      / \                              /  \
           *     X   E                            Y   lambda
           *                                          /   \
           *                                          X    E
           */
          equalNode = node.getLeft();
          Node lamNode = new Node("lambda");
          lamNode.setLeft(equalNode.getLeft());
          lamNode.setRight(equalNode.getRight());
          Node gamNode = new Node("gamma");
          gamNode.setLeft(new Node("Y"));
          gamNode.setRight(lamNode);
          node.setLeft(equalNode.getLeft());
          node.setRight(gamNode);
          node.setType("=");
        


        default:
      }

    }

    
    public void print(){
        printPreorder(root,"");
        
    }
    
    private void printPreorder(Node node, String printPrefix){
        if(node==null){
          return;
        }
    
        printNode(node, printPrefix);
        printPreorder(node.getLeft(),printPrefix+".");
        printPreorder(node.getRight(),printPrefix);
      }
    
      private void printNode(Node node, String printPrefix){
        if(node.getType() == "IDENTIFIER" ||
            node.getType() == "INTEGER"){
          
          System.out.printf(printPrefix+node.getType()+": "+((LeafNode)node).getValue()+"\n");
          
        }
        else if(node.getType() == "STRING")
          System.out.printf(printPrefix+node.getType()+": "+((LeafNode)node).getValue());
        else
          System.out.println(printPrefix+node.getType());
      }

     
}
