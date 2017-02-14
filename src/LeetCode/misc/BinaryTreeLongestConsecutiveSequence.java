package LeetCode.misc;

import LeetCode.BinaryTree.Codec;
import LeetCode.BinaryTree.TreeNode;

/**
 * Created by kashishtayal on 2/13/17.
 */
public class BinaryTreeLongestConsecutiveSequence {
    private int max = 0;
    public int longestConsecutive(TreeNode root) {
        helper(root, 0);
        return max;
    }

    private void helper(TreeNode root, int currCount){
        if(root == null){
            return;
        }
        currCount++;
        max = Math.max(max, currCount);
        TreeNode right = root.right;
        TreeNode left = root.left;
        if(right != null){
            if(right.val - root.val == 1){
                helper(right,currCount);
            }else{
                helper(right,0);
            }
        }
        if(left != null){
            if(left.val - root.val == 1){
                helper(left,currCount);
            }else{
                helper(left,0);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = Codec.deserialize("[1,null,3,2,4,null,null,null,5]");
        BinaryTreeLongestConsecutiveSequence lc = new BinaryTreeLongestConsecutiveSequence();
        System.out.println(lc.longestConsecutive(root));
    }
}
