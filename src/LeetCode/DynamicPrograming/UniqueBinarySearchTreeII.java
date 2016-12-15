package LeetCode.DynamicPrograming;

import LeetCode.BinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by kashishtayal on 11/9/16.
 */
public class UniqueBinarySearchTreeII {
    public List<TreeNode> generateTrees(int n) {
        if(n == 0) return new ArrayList<>();
        Queue<TreeNode> dp = new LinkedList<>();
        dp.add(new TreeNode(1));
        dp.add(null);
        for(int i = 2; i <= n ; i++){
            TreeNode prev = null;
            while((prev = dp.remove())!=null){
                TreeNode leftTree = new TreeNode(i);
                leftTree.left = clone(prev);
                dp.add(leftTree);
                int max = getNumberOfNodesAtRight(prev);
                int num = 0;
                while(num < max){
                    TreeNode prevCopy = clone(prev);
                    TreeNode parent = prevCopy;
                    int k = num;
                    while(k > 0){
                        parent = parent.right;
                        k--;
                    }
                    TreeNode temp = parent.right;
                    TreeNode newNode = new TreeNode(i);
                    parent.right = newNode;
                    newNode.left = temp;
                    dp.add(prevCopy);
                    num++;
                }
            }
            dp.add(null);
        }
        List<TreeNode> result = new ArrayList<>(dp);
        result.remove(null);
        return result;
    }
    public int getNumberOfNodesAtRight(TreeNode node){
        if(node == null) return 0;
        return 1+getNumberOfNodesAtRight(node.right);
    }
    public TreeNode clone(TreeNode node){
        if(node == null) return null;
        TreeNode newNode = new TreeNode(node.val);
        newNode.left = clone(node.left);
        newNode.right = clone(node.right);
        return newNode;
    }

    public static void main(String[] args) {
        UniqueBinarySearchTreeII ubst = new UniqueBinarySearchTreeII();
        List<TreeNode> result =ubst.generateTrees(2);
        System.out.println(result.size());
    }
}
