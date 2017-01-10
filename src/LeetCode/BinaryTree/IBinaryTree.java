package LeetCode.BinaryTree;

/**
 * Created by kashishtayal on 1/9/17.
 */
public interface IBinaryTree {

    void add(int inKey);

    void delete(int inKey);

    boolean find(int inKey);

    TreeNode getRoot();
}
