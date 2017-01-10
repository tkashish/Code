package LeetCode.BinaryTree;

import com.sun.istack.internal.NotNull;

import java.util.*;

/**
 * Created by kashishtayal on 1/9/17.
 */
public class BinaryTreeTraversalUtils {
    private BinaryTreeTraversalUtils(){}

    @NotNull
    public static List<Integer> inOrderRecursiveNotNull(final TreeNode inRoot){
        List<Integer> traversal = new ArrayList<>();
        if(inRoot == null) return traversal;
        List<Integer> leftList = inOrderRecursiveNotNull(inRoot.left);
        List<Integer> rightList = inOrderRecursiveNotNull(inRoot.right);
        if(leftList != null && !leftList.isEmpty())traversal.addAll(leftList);
        traversal.add(inRoot.val);
        if(rightList != null && !rightList.isEmpty())traversal.addAll(rightList);
        return traversal;
    }

    @NotNull
    public static List<Integer> inOrderIterativeNotNull(final TreeNode inRoot){
        List<Integer> traversal = new ArrayList<>();
        if(inRoot == null){
            return traversal;
        }
        Stack<TreeNode> stack = new Stack<>();
        Set<TreeNode> nodesCovered = new LinkedHashSet<>();
        stack.push(inRoot);
        while(!stack.isEmpty()){
            TreeNode currNode = stack.peek();
            TreeNode nextLeftNode = currNode.left;
            if(nextLeftNode == null || nodesCovered.contains(nextLeftNode)){
                stack.pop();
                nodesCovered.add(currNode);
                if(currNode.right != null)stack.push(currNode.right);
            }else{
                stack.push(nextLeftNode);
            }
        }
        nodesCovered.stream().forEach(tn->traversal.add(tn.val));
        return traversal;
    }

    @NotNull
    public static List<Integer> preOrderRecursiveNotNull(final TreeNode inRoot){
        List<Integer> traversal = new ArrayList<>();
        if(inRoot == null){
            return traversal;
        }
        traversal.add(inRoot.val);
        List<Integer> left = preOrderRecursiveNotNull(inRoot.left);
        List<Integer> right = preOrderRecursiveNotNull(inRoot.right);
        if(!left.isEmpty()) traversal.addAll(left);
        if(!right.isEmpty()) traversal.addAll(right);
        return traversal;
    }

    @NotNull
    public static List<Integer> preOrderIterativeNotNull(final TreeNode inRoot){
        List<Integer> traversal = new ArrayList<>();
        if(inRoot == null){
            return traversal;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.add(inRoot);
        while(!stack.isEmpty()){
            TreeNode currNode = stack.pop();
            traversal.add(currNode.val);
            if(currNode.right != null) stack.add(currNode.right);
            if(currNode.left != null) stack.add(currNode.left);
        }
        return traversal;
    }
    @NotNull
    public static List<Integer> postOrderRecursiveNotNull(final TreeNode inRoot){
        List<Integer> traversal = new ArrayList<>();
        if(inRoot == null){
            return traversal;
        }
        List<Integer> left = postOrderRecursiveNotNull(inRoot.left);
        List<Integer> right = postOrderRecursiveNotNull(inRoot.right);
        traversal.addAll(left);
        traversal.addAll(right);
        traversal.add(inRoot.val);
        return traversal;
    }

    public static List<Integer> postOrderIterativeNotNull(final TreeNode inRoot){
        List<Integer> traversal = new ArrayList<>();
        if(inRoot == null){
            return traversal;
        }
        Stack<TreeNode> stack = new Stack<>();
        Set<TreeNode> visited = new HashSet<>();
        stack.add(inRoot);
        while(!stack.isEmpty()){
            TreeNode node = stack.peek();
            if(node.left == null || visited.contains(node.left)){
                if(node.right == null || visited.contains(node.right)){
                    stack.pop();
                    traversal.add(node.val);
                    visited.add(node);
                }else{
                    stack.push(node.right);
                }
            }else{
                stack.push(node.left);
            }
        }
        return traversal;
    }
}
