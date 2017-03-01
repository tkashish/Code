package LeetCode.misc;

import java.util.*;

/**
 * Created by kashishtayal on 2/28/17.
 */
public class MinimumAbsoluteDifferenceInBST {
    /*
    Since this is a BST better approach would be to do a inorder traversal
    which will effectively give a sorted list
    and in that case there is no need to store it in a list.
     */
    public int getMinimumDifference(TreeNode root) {
        int min = Integer.MAX_VALUE;
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.remove();
            list.add(node.val);
            TreeNode left = node.left;
            TreeNode right = node.right;
            if(left != null) queue.add(left);
            if(right != null) queue.add(right);
        }
        Collections.sort(list);
        int prev = list.get(0);
        for (int i = 1; i < list.size(); i++){
            int curr = list.get(i);
            min = Math.min(min,curr-prev);
            prev = curr;
        }
        return min;
    }
}
