package cse_machine;

public enum NodeCSCategory {
    
    IDENTIFIER("<id:%s>"),
    STRING("<STR:'%s'"),
    INTEGER("<INT:%s>"),
    
    LET("let"),
    LAMBDA("lambda"),
    WHERE("where"),
    
    TAU("tau"),
    AUG("aug"),
    CONDITIONAL("->"),
    
    OR("or"),
    AND("&"),
    NOT("not"),
    GR("gr"),
    GE("ge"),
    LS("ls"),
    LE("le"),
    EQ("eq"),
    NE("ne"),
    
    PLUS("+"),
    MINUS("-"),
    NEG("neg"),
    MULT("*"),
    DIV("/"),
    EXP("**"),
    AT("@"),
    
    GAMMA("gamma"),
    TRUE("<true>"),
    FALSE("<false>"),
    NIL("<nil>"),
    DUMMY("<dummy>"),
    
    WITHIN("within"),
    SIMULTDEF("and"),
    REC("rec"),
    EQUAL("="),
    FCNFORM("function_form"),
    
    PAREN("<()>"),
    COMMA(","),
    
    YSTAR("<Y*>"),
    
    BETA(""),
    DELTA(""),
    ETA(""),
    TUPLE("");

    private String type;

    private NodeCSCategory(String obj_type) {
        type = obj_type;
    }

    public String getType() {
        return type;
    }

}
