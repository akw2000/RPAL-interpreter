package Parser;
import java.util.ArrayList;
import java.util.Stack;
import lexical_analyzer.Token;
public class ParseTree {
    private ArrayList <Token> token_list;
    private Stack<Node> stack;
    private Token curr_token; 

    public ParseTree(ArrayList<Token> tokens){
        this.token_list = tokens;
        this.stack = new Stack<Node>();
    }

    public AST buildAst(){
        parse();
        return (new AST(stack.pop()));
    }

    private void parse(){
        readNext();
        procE(); 
        if(!curr_token.getType().equals("EOF")){
            throw new ParseException("Expected EOF.");
            
        }
    }

    private void readNext(){
        if (token_list.size()>0){
        
        do{
            curr_token = token_list.remove(0);
           
          }while(isType(curr_token,"DELETE"));
        }
        if (curr_token!=null){

            
            if (isType(curr_token,"IDENTIFIER")){
                LeafNode curr_leaf = new LeafNode("IDENTIFIER", curr_token.getValue());
                stack.push(curr_leaf);
                
            }
            else if (isType(curr_token, "INTEGER")){
                LeafNode curr_leaf = new LeafNode("INTEGER", curr_token.getValue());
                stack.push(curr_leaf);
                
            }
            else if (isType(curr_token, "STRING")){
                LeafNode curr_leaf = new LeafNode("STRING", curr_token.getValue());
                stack.push(curr_leaf);
               
            }
            
        }
    }
    private boolean isType(Token token, String type){
        if (token!=null){
            if (token.getType().equals(type)){
                return true;
            }
        }
        return false;
    }

    private void procE(){
        /*
          E -> 'let' D in E => ’let’
            -> 'fn' Vb+ '.' E => ’lambda’
            -> Ew
         */
        
        
        if (curr_token.getValue().equals("let")){
           
            readNext();
            procD();
            if (!curr_token.getValue().equals("in")){
                //error
                throw new ParseException("E:  'in' expected");
            }
            readNext();
            procE();
            buildTree("let",2);
        }
        else if (curr_token.getValue().equals("fn")){
            
            int N = 0;
            readNext();
            while (isType(curr_token,"IDENTIFIER")||curr_token.getValue().equals("(")){
                procVb();
                N++;
            }

            if (N==0){
                //error
                throw new ParseException("E: at least one 'Vb' expected");
            }
            if (!curr_token.getValue().equals(".")){
                //error
                throw new ParseException("E: '.' expected");
            }
            readNext();
            procE();
            buildTree("lambda",N+1);
        }
        else{
            
            procEW();
        }

    }

    private void procEW(){
        /* 
         Ew -> T ’where’ Dr => ’where’
            -> T;
        */
        procT(); //read next token in process T
        if (curr_token.getValue().equals("where")){
           
            readNext();
            procDr(); //read next in Dr
            buildTree("where",2);
        }
        


    }

    private void procT(){
        /* 
         T - > Ta (',' Ta)+ => 'tau'
           - > Ta
        */
        procTa(); 
        int N = 0;
        while(curr_token.getValue().equals(",")){ 
          readNext();
          procTa(); //extra readToken() done in procTA()
          N++;
        }
        if(N> 0) {
            buildTree("tau", N+1);
        }


    }

    private void procTa(){
        /* 
         Ta -> Ta 'aug' Tc => 'aug'
            - > Tc
        */
        procTc(); 

        while(curr_token.getValue().equals("aug")){ 
          readNext();
          procTc();
          buildTree("aug", 2);
        }

    }

    private void procTc(){
        /* 
         Tc -> B ’->’ Tc ’|’ Tc => ’->’
            -> B ;
        */
        procB();

        if(curr_token.getValue().equals("->")){ 

            readNext();
            procTc();
        
            if(!curr_token.getValue().equals("|")){
                //error
                throw new ParseException("Expected | ");
            }
            readNext();
            procTc();

            buildTree("->", 3);
        }
        
    }

    private void procB(){
        /*
         B ->B’or’ Bt => ’or’
           -> Bt ; 
        */
        
        procBt();
        
        while(curr_token.getValue().equals("or")){ 
          readNext();
          procBt();
          buildTree("or", 2);
        }
    }

    private void procBt(){

        /*
         Bt -> Bt ’&’ Bs => ’&’
            -> Bs ;
         */
        
        procBs();
        while(curr_token.getValue().equals("&")){
          readNext();
          procBs(); //extra readNT in procBS()
          buildTree("&", 2);
        }

    }

    private void procBs(){
        /*
         Bs -> ’not’ Bp => ’not’
            -> Bp ;
        */
        
        if(curr_token.getValue().equals("not")){ 
            readNext();
            procBp(); 
            buildTree("not", 1);
        }
        else
            procBp();    
    }

    private void procBp(){
        /*
         Bp -> A (’gr’ | ’>’ ) A => ’gr’
            -> A (’ge’ | ’>=’) A => ’ge’
            -> A (’ls’ | ’<’ ) A => ’ls’
            -> A (’le’ | ’<=’) A => ’le’
            -> A ’eq’ A => ’eq’
            -> A ’ne’ A => ’ne’
            -> A ;
        */
        
        procA();
        if(curr_token.getValue().equals("gr")||curr_token.getValue().equals(">")){ 
          readNext();
          procA();
          buildTree("gr", 2);
        }
        else if(curr_token.getValue().equals("ge")||curr_token.getValue().equals(">=")){ 
          readNext();
          procA();
          buildTree("ge", 2);
        }
        else if(curr_token.getValue().equals("ls")||curr_token.getValue().equals("<")){ 
          readNext();
          procA();
          buildTree("ls", 2);
        }
        else if(curr_token.getValue().equals("le")||curr_token.getValue().equals("<=")){ 
          readNext();
          procA();
          buildTree("le", 2);
        }
        else if(curr_token.getValue().equals("eq")){ 
          readNext();
          procA();
          buildTree("eq", 2);
        }
        else if(curr_token.getValue().equals("ne")){ 
          readNext();
          procA();
          buildTree("ne", 2);
        }
        
    }

    private void procA(){
        /*
         A ->A’+’ At => ’+’
           -> A ’-’ At => ’-’
           -> ’+’ At
           -> ’-’ At => ’neg’
           -> At ;
        */
        
        if(curr_token.getValue().equals("+")){ 
          readNext();
          procAt();
        }
        else if(curr_token.getValue().equals("-")){ 
          readNext();
          procAt();
          buildTree("neg", 1);
        }
        else{
            procAt();
            
            while (curr_token.getValue().equals("+")||curr_token.getValue().equals("-")){
                String val = curr_token.getValue();
                readNext();
                procAt();
                buildTree(val, 2);
            }
        }
    }

    private void procAt(){
        /*
        At -> At ’*’ Af => ’*’
           -> At ’/’ Af => ’/’
           -> Af 
        */
        ;
        procAf();
        
        while (curr_token.getValue().equals("*")||curr_token.getValue().equals("/")){
            String val = curr_token.getValue();
            readNext();
            procAf();
            buildTree(val, 2);
        }
    }

    private void procAf(){
        /* 
         Af -> Ap ’**’ Af => ’**’
            -> Ap ;
         Ap -> Ap ’@’ ’<IDENTIFIER>’ R => ’@’
            -> R ;
        */
    
        
        procAp(); 
        if(curr_token.getValue().equals("**")){ 
                readNext();
                procAf();
                buildTree("**",2);
        }
    }

    private void procAp(){
        /* 
         Ap -> Ap ’@’ ’<IDENTIFIER>’ R => ’@’
            -> R ;
        */
        
        procR(); 
    
        while(curr_token.getValue().equals("@")){ 
            readNext();
            if(!isType(curr_token, "IDENTIFIER")){
            //error

              throw new ParseException("Expected an identifier.");
            }
            readNext();
            procR(); //extra readNT in procR()
            buildTree("@", 3);
        }
    }

    private void procR(){
        /* 
         R ->RRn => ’gamma’
           -> Rn ;
        */
        
        procRn(); 
        readNext();
        while (isType(curr_token, "INTEGER")|| isType(curr_token, "STRING")|| 
        isType(curr_token, "IDENTIFIER")|| curr_token.getValue().equals("true")||
        curr_token.getValue().equals("false")||
        curr_token.getValue().equals("nil")||
        curr_token.getValue().equals("dummy")||
        isType(curr_token, "L_PAREN"))
        { 
            procRn(); 
            buildTree("gamma", 2);
            readNext();
        }

    }

    private void procRn(){
        /*
         Rn -> <IDENTIFIER>
            -> <INTEGER>
            -> <STRING>
            -> 'true'
            -> 'false'
            -> 'nil'
            -> '('E')'
            -> 'dummy'
         */
        LeafNode newLeaf;
        if(isType(curr_token, "IDENTIFIER")|| 
        isType(curr_token, "INTEGER")||
        isType(curr_token, "STRING")){ 
        }

        else if (curr_token.getValue().equals("true")){
            newLeaf = new LeafNode("TRUE", "true");
            stack.push(newLeaf);

        }

        else if (curr_token.getValue().equals("false")){
            newLeaf = new LeafNode("FALSE", "false");
            stack.push(newLeaf);

        }
        else if (curr_token.getValue().equals("nil")){
            newLeaf = new LeafNode("NIL", "nil");
            stack.push(newLeaf);
        }
        else if (curr_token.getValue().equals("dummy")){
            newLeaf = new LeafNode("DUMMY", "dummy");
            stack.push(newLeaf);
        }
        else if(curr_token.getValue().equals("(")){
            readNext();
            procE(); //extra readNT in procE()
            
            if(!curr_token.getValue().equals(")")){
              //error
              throw new ParseException("Expected )");
            } 
            
        }

    }

    private void procD(){
        /* 
         D -> Da ’within’ D => ’within’
           -> Da 
        */
        
        procDa(); 
        if( curr_token.getValue().equals("within")){ //D -> Da 'within' D => 'within'
            readNext();
            procD();
            buildTree("within", 2);
        }
    }
    private void procDa(){
        /* 
         Da -> Dr ( ’and’ Dr )+ => ’and’
            -> Dr ;
        */
        
        procDr(); 
        int N = 0;
        while(curr_token.getValue().equals("and")){ 
          readNext();
          procDr(); //extra readToken() in procDR()
          N++;
        }
        if(N > 0) buildTree("and", N+1);
    }

    private void procDr(){
        /* 
         Dr -> ’rec’ Db => ’rec’
            -> Db ;
        */
        
        if(curr_token.getValue().equals("rec")){ 
            readNext();
            procDb(); 
            buildTree("rec", 1);
        }
        else{ 
            procDb();
        }

    }

    private void procDb(){
        /* 
         Db -> Vl ’=’ E => ’=’
            -> ’<IDENTIFIER>’ Vb+ ’=’ E => ’fcn_form’
            -> ’(’ D ’)’ ;
        */
        
        if(isType(curr_token, "L_PAREN")){ 
            procD();
            readNext();
            if(!curr_token.getValue().equals(")")){
            //error
                throw new ParseException("Expected ).");
            }
            readNext();
        }
        else if(isType(curr_token, "IDENTIFIER")){
            readNext();
            if(curr_token.getValue().equals(",")){ 
                //comma separated variables
                readNext();
                procVl(); 
                if(!curr_token.getValue().equals("=")){
                //error
                    throw new ParseException("= is expected.");
                }
                buildTree("comma", 2);
                readNext();
                procE(); //extra readNT in procE()
                buildTree("=", 2);
            }

            else {
                //not comma seperated
                
                /// only one variable
                if(curr_token.getValue().equals("=")){ 
                    readNext();
                    procE(); 
                    buildTree("=", 2);
                }

                //More than one variable
                else{
                    int N = 0;

                    while(isType(curr_token, "IDENTIFIER") ||curr_token.getValue().equals("(")){
                        
                        procVb(); //extra readNT in procVB()
                        N++;
                    }

                    if(N==0){
                    //error
                         throw new ParseException("At least one identifier is required");
                    }

                    if(!curr_token.getValue().equals("=")){
                        //error
                         throw new ParseException("= is expected");
                    }
                    

                    readNext();
                    procE();

                    buildTree("fcn_form", N+2); //+2 first and last identifiers
                }

            }
            
        }
    }


    private void procVb(){
        /* 
         Vb -> ’<IDENTIFIER>’
            -> ’(’ Vl ’)’
            -> ’(’ ’)’ => ’()’;
        */
        
        if(isType(curr_token, "IDENTIFIER")){
            readNext();
        }
        else if(curr_token.getValue().equals("(")){
            readNext();
            if(curr_token.getValue().equals(")")){
                LeafNode newLeaf = new LeafNode("()", "");
                stack.push(newLeaf);
            
                readNext();
            }
            else{ 
                //has Vl
                procVl(); 
                if(!curr_token.getValue().equals(")")){
                    //error
                     throw new ParseException(") is expected.");
                }
           
                readNext();
        }
      }

    }

    

    

    private void procVl(){
        /* 
         Vl -> <IDENTIFIER> list ','        => ','?
        */
        if(!isType(curr_token, "IDENTIFIER")){
            //error
            throw new ParseException("Identifier is expected");
        }
        else{
          readNext();
          int N = 0;
          while(isType(curr_token, "COMMA")){ 
            
            readNext();
            if(!isType(curr_token, "IDENTIFIER")){
                //error
                 throw new ParseException("Identifier is expected.");
            }
              
            readNext();
            N++;
          }
          if(N> 0) {
            buildTree("comma", N+1); 
        }
        }
      }
    private void buildTree(String type,int n){
            /*builds the left child first sibling tree */
            Node p = null;
            for (int i =0;i<n;i++){
                Node c = stack.pop();
                c.setRight(p);
                p = c;
            }
            Node newNode = new Node(type);
            newNode.setLeft(p);
            newNode.setRight(null);
            stack.push(newNode);

        }

}
