import java.util.ArrayList;
import Parser.AST;
import Parser.ParseTree;
import lexical_analyzer.Token;

public class test01 {
    

    public static void main(String[] args) throws Exception {
        // eg: java rpal20 rpal_test_programs/rpal_01 > output.01 -> filename = rpal_test_programs/rpal_01

        
        ArrayList<Token> tok = new ArrayList<Token>();
        Token t1 = new Token();
        t1.setType("KEYWORD");
        t1.setValue("let");
        tok.add(t1);

        Token t2 = new Token();
        t2.setType("IDENTIFIER");
        t2.setValue("Sum");
        tok.add(t2);

        Token t3 = new Token();
        t3.setType("IDENTIFIER");
        t3.setValue("x");
        tok.add(t3);

        Token t4 = new Token();
        t4.setType("IDENTIFIER");
        t4.setValue("y");
        tok.add(t4);

        Token t5 = new Token();
        t5.setType("OPERATOR");
        t5.setValue("=");
        tok.add(t5);

        Token t6 = new Token();
        t6.setType("IDENTIFIER");
        t6.setValue("x");
        tok.add(t6);

        Token t7 = new Token();
        t7.setType("OPERATOR");
        t7.setValue("*");
        tok.add(t7);

        Token t8 = new Token();
        t8.setType("IDENTIFIER");
        t8.setValue("y");
        tok.add(t8);

        Token t9 = new Token();
        t9.setType("KEYWORD");
        t9.setValue("in");
        tok.add(t9);

        Token t10 = new Token();
        t10.setType("IDENTIFIER");
        t10.setValue("Print");
        tok.add(t10);

        Token t11 = new Token();
        t11.setType("L_PAREN");
        t11.setValue("(");
        tok.add(t11);

        Token t12 = new Token();
        t12.setType("IDENTIFIER");
        t12.setValue("Sum");
        tok.add(t12);

        Token t13 = new Token();
        t13.setType("INTEGER");
        t13.setValue("2");
        tok.add(t13);

        Token t14 = new Token();
        t14.setType("INTEGER");
        t14.setValue("5");
        tok.add(t14);

        Token t15 = new Token();
        t15.setType("DELETE");
        t15.setValue(" ");
        tok.add(t15);

        Token t16 = new Token();
        t16.setType("R_PAREN");
        t16.setValue(")");
        tok.add(t16);

        Token nToken = new Token();
        nToken.setType("EOF");
        nToken.setValue("null");
        tok.add(nToken);

        ParseTree parser = new ParseTree(tok);

        AST tree = parser.buildAst();
        tree.standardize();

        tree.print();
        
    }
}
    

