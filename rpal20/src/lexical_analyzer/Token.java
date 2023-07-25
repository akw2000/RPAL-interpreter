package lexical_analyzer;

public class Token {
    // Token is simply a pair of type and value(eg: <IDENTIFIER: function>)
    private String type; // eg: IDENTIFIER
    private String value; // eg: function
    
    
    /** 
     * @return String
     */
    public String getType() {
        return type;
    }
    
    
    /** 
     * @return String
     */
    public String getValue() {
        return value;
    }
    

    /** 
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    
    /** 
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }
    
    
    /** 
     * for debugging purposes
     * @return String
     */
    @Override
    public String toString() {
        return "<" + type + ": " + value + ">";
    }
    
}
