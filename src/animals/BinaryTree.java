package animals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record BinaryTree(Node root) {

    public List<String> getLeafsValues() {
        List<String> values = new ArrayList<>();
        List<Node> leafs = getLeafs();
        for (Node leaf : leafs) {
            values.add(leaf.getValue());
        }
        Collections.sort(values);
        return values;
    }

    public Node findNode(String value) {
        for (Node node : getLeafs()) {
            if (node.getValue().equals(value)) {
                return node;
            }
        }
        return null;
    }

    public List<Node> getNodes() {
        List<Node> nodes = new ArrayList<>();
        getNodes(root, nodes);
        return nodes;
    }

    public List<Node> getLeafs() {
        List<Node> leafs = new ArrayList<>();
        getLeafs(root, leafs);
        return leafs;
    }

    private void replaceNode(Node oldNode, Node newNode) {
        Node parent = oldNode.getParent();
        if (parent.getRight() == oldNode) {
            parent.setRight(newNode);
        } else {
            parent.setLeft(newNode);
        }
    }

    public void deleteLeaf(Node animal) {
        Node parent = animal.getParent();
        if (parent.getRight() == animal) {
            replaceNode(parent, parent.getLeft());
        } else {
            replaceNode(parent, parent.getRight());
        }
        parent.setLeft(null);
        parent.setRight(null);
    }

    private void getNodes(Node current, List<Node> nodes) {
        if (current == null) return;
        nodes.add(current);
        getNodes(current.getLeft(), nodes);
        getNodes(current.getRight(), nodes);
    }

    private void getLeafs(Node current, List<Node> leafs) {
        if (current == null) {
            return;
        }
        if (!current.hasChildren()) {
            leafs.add(current);
        }
        getLeafs(current.getLeft(), leafs);
        getLeafs(current.getRight(), leafs);
    }
}
