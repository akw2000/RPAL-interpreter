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
       
      if(node.getLeft()!=null){
        Node childNode = node.getLeft();
        while(childNode!=null){
          standardizeNode(childNode);
          childNode = childNode.getRight();
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
          Node exp = equalNode.getLeft().getRight();
          equalNode.getLeft().setRight(equalNode.getRight());
          equalNode.setRight(exp);
          equalNode.setType("lambda");
          node.setType("gamma");
          break;
        case "where":
          /*      where                     gamma
           *      /   \                     /   \
           *     P     =      =>         lambda  E
           *          / \                 /   \
           *         X   E               X     P
           */

          Node lamNode = new Node("lambda");
          lamNode.setRight(node.getLeft().getRight().getLeft().getRight());
          lamNode.setLeft(node.getLeft().getRight().getLeft());
          node.getLeft().setRight(null);
          lamNode.getLeft().setRight(node.getLeft());
          node.setLeft(lamNode);
          node.setType("gamma");
          break;

        
        
        case "within":
          /*        within                      =
           *        /   \                     /   \
           *       =     =      =>           X2   gamma
           *      / \   / \                        /  \
           *     X1 E1 X2  E2                  lambda  E1
           *                                    /   \
           *                                   X1    E2
           */
            Node e1 = node.getLeft().getLeft().getRight();
            Node e2 = node.getLeft().getRight().getLeft().getRight();

            lamNode = new Node("lambda");
            lamNode.setLeft(node.getLeft().getLeft());
            lamNode.getLeft().setRight(e2);
            lamNode.setRight(e1);
            node.setLeft(node.getLeft().getRight().getLeft());
            node.getLeft().setLeft(lamNode);
            node.getLeft().setRight(new Node("gamma"));
            node.setType("=");
            break;
           
        
        case "rec":

          /*      rec                           =
           *       |                           /  \
           *       =              =>          X   gamma
           *      / \                              /  \
           *     X   E                            Y   lambda
           *                                          /   \
           *                                          X    E
           */
          Node xNode = node.getLeft().getLeft();
          node.setLeft(xNode);
          lamNode = new Node("lambda");
          lamNode.setLeft(xNode);
          Node gamNode = new Node("gamma");
          gamNode.setLeft(new Node("Y"));
          gamNode.getLeft().setRight(lamNode);
          node.getLeft().setRight(gamNode);
          break;
        
        case "fcn_form":
          /*      fcn_form                      =
           *      /   |   \     =>             /  \    
           *     P    V+   E                  P   +lambda
           *                                        /   \
           *                                        V   E
           */

          Node vbNode = node.getLeft().getRight();
          Node lNode = creteLambdas(vbNode);
          node.getLeft().setRight(lNode);
          node.setType("=");
          break;
        
        case "@" :
          /*        @                               gamma
           *      / | \           =>                /   \ 
           *    E1  N  E2                         gamma  E2
           *                                      /   \
           *                                     N    E1
           */

          e2 = node.getLeft().getRight().getRight();
          e1 = node.getLeft();
          gamNode = new Node("gamma");
          gamNode.setLeft(node.getLeft().getRight());
          e1.setRight(null);
          gamNode.getLeft().setRight(e1);
          gamNode.setLeft(e2);
          node.setLeft(gamNode);
          node.setType("gamma");
          break;




        default:
          break;

        
      }

    }

    private Node creteLambdas (Node leafNode){
      Node lamNode;
      if (leafNode.getRight()!=null && leafNode.getRight().getRight()==null){
        lamNode = new Node("lambda");
        lamNode.setLeft(leafNode);
        return lamNode;
      }
      else{
        lamNode = new Node("lambda");
        lamNode.setLeft(leafNode);
        leafNode.setRight(creteLambdas(leafNode.getRight()));
        return lamNode;
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
          System.out.printf(printPrefix+node.getType()+": "+((LeafNode)node).getValue()+"\n");
        else
          System.out.println(printPrefix+node.getType());
      }

     
}
