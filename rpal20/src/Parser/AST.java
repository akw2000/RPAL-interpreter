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
            
            Node x1 = node.getLeft().getLeft();
            Node e1 = x1.getRight();
            Node x2 = node.getLeft().getRight().getLeft();
            Node e2 = x2.getRight();
            lamNode = new Node("lambda");
            x1.setRight(e2);
            lamNode.setLeft(x1);
            lamNode.setRight(e1);
            Node gamNode = new Node ("gammma");
            gamNode.setLeft(lamNode);
            x2.setRight(gamNode);
            node.setLeft(x2);
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
            equalNode = node.getLeft();
            Node xNode = equalNode.getLeft();
            lamNode = new Node("lambda");
            lamNode.setLeft(xNode);
            Node yNode = new Node("Y");
            yNode.setRight(lamNode);
            gamNode = new Node("gamma");
            gamNode.setLeft(yNode);
            //top x is a copy of x node without linking to e
            LeafNode topX  = new LeafNode(xNode.getType(),((LeafNode)xNode).getValue());
            topX.setLeft(xNode.getLeft());
            topX.setRight(gamNode);
            node.setLeft(topX);
            node.setType("=");
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

            e1 = node.getLeft();
            Node n = e1.getRight();
            e2 = n.getRight();
            gamNode = new Node("gamma");
            gamNode.setLeft(n);
            n.setRight(e1);
            e1.setRight(null);
            gamNode.setRight(e2);
            node.setLeft(gamNode);
            node.setType("gamma");

          /* 
          gamNode = new Node("gamma");
          gamNode.setLeft(node.getLeft().getRight());
          e1.setRight(null);
          gamNode.getLeft().setRight(e1);
          gamNode.setRight(e2);
          node.setLeft(gamNode);
          node.setType("gamma");
          */
          break;

          case "and":
            /*
             *          and                       =
             *           |                       /  \
             *          ++=           =>        ,    tau    
             *          / \                     |      |
             *         X   E                  ++X     ++E
             */

             equalNode = node.getLeft();
             Node tauNode = new Node("tau");
             Node commNode = new Node("comma");
             tauNode.setLeft(equalNode.getLeft().getRight());
             commNode.setLeft(equalNode.getLeft());
             commNode.getLeft().setRight(null);
             while(equalNode.getRight()!=null){
                equalNode = equalNode.getRight();
                tauNode.getLeft().appendRight(equalNode.getLeft().getRight());
                equalNode.getLeft().setRight(null);
                commNode.getLeft().appendRight(equalNode.getLeft());
             }
             node.setLeft(commNode);
             commNode.setRight(tauNode);
             node.setType("=");
             break;
        
          case "lambda":
             
             /*       lambda                          ++lambda
              *       /   \             =>             /    \
                     V++   E                          V     .E
              */
            
            vbNode = node.getLeft();
            if (vbNode.getRight().getRight()==null){
              break;
            }
            else{
            lNode = creteLambdas(vbNode.getRight());
            node.getLeft().setRight(lNode);
            break;
            }
          


        

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
