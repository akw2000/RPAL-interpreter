package lexical_analyzer;

public class Token {
    // Token is simply a pair of type and value(eg: <id: fun>)
    private String type;
    private String value;
    
    public String getType() {
        return type;
    }
    public String getValue() {
        return value;
    }
    public void setType(String type) {
        this.type = type;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return "<" + type + ": " + value + ">";
    }
    
}
