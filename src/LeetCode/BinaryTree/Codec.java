package LeetCode.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by kashishtayal on 12/1/16.
 */
public class Codec {
    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        if(root == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        sb.append(root.val);
        while(!queue.isEmpty()){
            TreeNode curr = queue.remove();
            TreeNode left = curr.left;
            TreeNode right = curr.right;
            if(left == null){
                sb.append(",null");
            }else{
                sb.append(","+left.val);
                queue.add(left);
            }
            if(right == null){
                sb.append(",null");
            }else{
                sb.append(","+right.val);
                queue.add(right);
            }
        }
        sb.append("]");
        return sb.toString().replaceFirst("[,null]+[\\]]", "]");
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String inString) {
        if(!inString.contains("[") || !inString.contains("]") || inString.contains("[]"))
            return null;
        inString = inString.replaceAll("[\\[]*[\\]]*", "");
        String[] strArr = inString.split(",");
        return getTree(strArr);
    }
    public static TreeNode getTree(String[] inList){
        if(inList == null || inList.length == 0 || inList[0] == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.valueOf(inList[0]));
        queue.add(root);
        int i = 1;
        while(i < inList.length){
            TreeNode curr = queue.remove();
            String left = inList[i];
            if(!left.equals("null")){
                TreeNode leftNode = new TreeNode(Integer.valueOf(left));
                curr.left = leftNode;
                queue.add(leftNode);
            }
            i++;
            if(i == inList.length){
                break;
            }
            String right = inList[i];
            if(!right.equals("null")){
                TreeNode rightNode = new TreeNode(Integer.valueOf(right));
                curr.right = rightNode;
                queue.add(rightNode);
            }
            i++;
        }
        return root;
    }
}
