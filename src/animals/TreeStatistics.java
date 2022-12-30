package animals;

import java.util.Arrays;
import java.util.List;

public class TreeStatistics {
    private final BinaryTree tree;
    private final int[] leafDepths;
    private final int numberOfLeafs;
    private final int numberOfNodes;

    public TreeStatistics(BinaryTree tree) {
        this.tree = tree;
        this.leafDepths = getLeafsDepths();
        this.numberOfLeafs = this.tree.getLeafs().size();
        this.numberOfNodes = this.tree.getNodes().size();
    }

    public String getRootValue() {
        return this.tree.root().getValue();
    }

    public int getNumberOfLeafs() {
        return this.numberOfLeafs;
    }

    public int getNumberOfNodes() {
        return this.numberOfNodes;
    }

    public int getNumberOfParentNodes() {
        return this.numberOfNodes - this.numberOfLeafs;
    }

    public int getHeight() {
        int max = 0;
        for (int depth : this.leafDepths) {
            if (depth > max) {
                max = depth;
            }
        }
        return max;
    }

    public int minimumDepth() {
        int min = Integer.MAX_VALUE;
        for (int depth : this.leafDepths) {
            if (depth < min) {
                min = depth;
            }
        }
        return min;
    }

    public double avgDepth() {
        return Arrays.stream(this.leafDepths).average().getAsDouble();
    }

    private int[] getLeafsDepths() {
        List<Node> leafs = this.tree.getLeafs();
        int[] depths = new int[leafs.size()];
        for (int i = 0; i < leafs.size(); i++) {
            depths[i] = leafs.get(i).getNodeDepth();
        }
        return depths;
    }
}
