package LeetCode.misc;

import LeetCode.BinaryTree.Codec;
import LeetCode.BinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kashishtayal on 2/28/17.
 */
public class FindModeInBinarySearchTree {
    int prev = -1;
    int count = 0;
    int max = Integer.MIN_VALUE;
    List<Integer> result = new ArrayList<>();
    public int[] findMode(TreeNode root) {
        inorder(root);
        update(-1);
        int[] resultArray = new int[result.size()];
        for(int i = 0; i < resultArray.length; i++){
            resultArray[i] = result.get(i);
        }
        return resultArray;
    }
    private void inorder(TreeNode root){
        if(root == null) return;
        inorder(root.left);
        int val  = root.val;
        if(prev == -1){
            prev = val;
            count = 1;
        }else{
            update(val);
        }
        inorder(root.right);
    }

    private void update(int currVal){
        if(prev == currVal){
            count++;
        }else{
            if(max < count){
                result = new ArrayList<>();
                result.add(prev);
                max = count;
            }else if(max == count){
                result.add(prev);
            }
            prev = currVal;
            count = 1;
        }
    }

    public static void main(String[] args) {
        TreeNode root = Codec.deserialize("[1,1,2,null,null,null,2]");
        FindModeInBinarySearchTree fd = new FindModeInBinarySearchTree();
        Arrays.stream(fd.findMode(root)).forEach(System.out::println);
    }
}
