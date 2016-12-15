package LeetCode.design;

import BinaryTree.Codec;
import BinaryTree.TreeNode;
import sun.reflect.generics.tree.Tree;

import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Created by kashishtayal on 12/1/16.
 */
public class BSTIterator {
    Stack<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        doStuff(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }
    private void doStuff(TreeNode root){
        TreeNode curr;
        while((curr = root) != null){
            stack.push(curr);
            root = root.left;
        }
    }
    /** @return the next smallest number */
    public int next() {
        if(this.hasNext()){
            TreeNode result = stack.pop();
            doStuff(result.right);
            return result.val;
        }
        throw new NoSuchElementException();
    }

    public static void main(String[] args) {
        {
            TreeNode root = Codec.deserialize("[1,2,3,4,5,6,7,null,null,8,null,null,null,null,null]");
            BSTIterator itr = new BSTIterator(root);
            while(itr.hasNext()){
                System.out.println(itr.next());
            }
        }
        {
            BSTIterator itr = new BSTIterator(null);
            System.out.println(itr.hasNext());
        }
        {
            TreeNode root = Codec.deserialize("[1,null,2]");
            BSTIterator itr = new BSTIterator(root);
            while(itr.hasNext()){
                System.out.println(itr.next());
            }
        }

    }
}
