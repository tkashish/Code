import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 257 https://leetcode.com/problems/binary-tree-paths/
 */
public class BinaryTreePaths {
    public static List<String> binaryTreePaths(TreeNode root) {
        if(root == null) return new ArrayList<>();
        List<String> right = binaryTreePaths(root.right);
        List<String> left = binaryTreePaths(root.left);
        List<String> ret = new ArrayList<>();
        if(right.size()!= 0) {
            ret.addAll(append(right, root.val));
        }
        if(left.size()!= 0) {
            ret.addAll(append(left, root.val));
        }
        if(right.size() == 0 && left.size() == 0) {
            ret.add(String.valueOf(root.val));
        }
        return ret;
    }
    public static List<String> append (List<String> inList, int val){
        return inList.stream().map(e->val+"->"+e).collect(Collectors.toList());
    }

    public static List<String> paths(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        Stack<String> stringStack = new Stack<>();
        List<String> ret = new ArrayList<>();
        String next = null;
        while(stack.size()!=0 || root!=null){
            if(root != null){
                stack.add(root);
                if(stringStack.isEmpty() && next == null){
                    stringStack.add(String.valueOf(root.val));
                }else if(next == null){
                    stringStack.add(stringStack.peek()+"->"+root.val);
                }else{
                    stringStack.add(next+"->"+root.val);
                    next= null;
                }
                root = root.left;
            }else{
                TreeNode node = stack.pop();
                String curr = stringStack.pop();
                if(isLeaf(node)){
                    ret.add(curr);
                }else{
                    next = curr;
                }
                root = node.right;
            }
        }
        return ret;
    }

    static boolean isLeaf(TreeNode inNode){
        return (inNode.left==null&&inNode.right==null)?true:false;
    }
    public static void main(String[] args) {
        TreeNode root = TreeNode.sampleTree();
        binaryTreePaths(root).stream().forEach(e-> System.out.println(e));
        paths(root).stream().forEach(e -> System.out.println(e));
    }
}
