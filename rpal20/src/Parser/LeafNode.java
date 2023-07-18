package Parser;

public class LeafNode extends Node{
    private String value;

    public LeafNode(String type,String value){
        super(type);
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
    public void setValue(String val){
        this.value = val;
    }
    
}
