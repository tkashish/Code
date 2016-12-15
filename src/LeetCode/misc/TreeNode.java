package LeetCode.misc;

/**
 * Created by tayalka on 7/5/2016.
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }

    static boolean isheightBalanced(TreeNode head){
        if(head == null)return true;
        if(!isheightBalanced(head.left)||!isheightBalanced(head.right))return false;
        if(maxHeight(head.left)-maxHeight(head.right) > 1 )return false;
        return true;
    }

    static int maxHeight(TreeNode head){
        if(head == null) return 0;
        return Math.max(maxHeight(head.left),maxHeight(head.right))+1;
    }

    static TreeNode sampleTree(){
        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);

        root.left = n2;
        root.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;
        return root;
    }
}
