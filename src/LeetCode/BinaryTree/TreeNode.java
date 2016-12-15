package LeetCode.BinaryTree;

/**
 * Created by kashishtayal on 11/9/16.
 */
public class TreeNode {
    public int val;
    public TreeNode left = null;
    public TreeNode right = null;
    public TreeNode(int x) { val = x; }

    @Override
    public String toString() {
        return ""+val;
    }
}
