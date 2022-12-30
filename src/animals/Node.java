package animals;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Node {
    private String value;
    private Node parent;
    private Node left;
    private Node right;

    public Node() {
    }

    public Node(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @JsonIgnore
    public Node getParent() {
        return parent;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
        if (this.left != null) {
            this.left.parent = this;
        }
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
        if (this.left != null) {
            this.right.parent = this;
        }
    }

    @JsonIgnore
    public int getNodeDepth () {
        return this.getNodeDepth(this, 0);
    }

    @JsonIgnore
    private int getNodeDepth (Node current, int depth) {
        if (current.getParent() == null) {
            return depth;
        }
        return getNodeDepth(current.getParent(), ++depth);
    }

    @JsonIgnore
    public boolean hasChildren() {
        return this.left != null && this.right != null;
    }
}
