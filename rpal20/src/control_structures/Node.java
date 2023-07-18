package control_structures;

/**
 *
 * @author OshadiPC
 */

public class Node {
    private String value;
    private Node child;
    private Node sibling;

    public Node(String type) {
        value = type;
        child = null;
        sibling = null;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setChild(Node child) {
        this.child = child;
    }

    public void setSibling(Node sibling) {
        this.sibling = sibling;
    }

    public String getValue() {
        return value;
    }

    public Node getChild() {
        return child;
    }

    public Node getSibling() {
        return sibling;
    }

}
