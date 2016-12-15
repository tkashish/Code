package LeetCode.snapChat;

import LeetCode.BinaryTree.Codec;
import LeetCode.BinaryTree.TreeNode;
import com.sun.org.apache.bcel.internal.classfile.Code;
import sun.reflect.generics.tree.Tree;

import java.util.*;

/**
 * Created by kashishtayal on 12/6/16.
 */
public class BinaryTreeVerticalOrderTraversal {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        Map<Integer, List<Integer>> map = new TreeMap<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        int level = 0;
        boolean left = true;
        while(true){
            System.out.println(node+" "+level);
            if(node != null){
                stack.push(node);
                List<Integer> temp = map.get(level);
                if(temp == null) temp = new ArrayList<>();
                temp.add(node.val);
                map.put(level,temp);
                node = node.left;
                level--;
                left = true;
            }else {
                if(stack.isEmpty()) break;
                node = stack.pop().right;

                left = false;
                if(left){
                    level+=2;
                }else{
                    level++;
                }
            }
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
//        TreeNode root = Codec.deserialize("[3,9,20,null,null,15,7]");
        TreeNode root = Codec.deserialize("[3,9,8,4,0,1,7]");
        BinaryTreeVerticalOrderTraversal v = new BinaryTreeVerticalOrderTraversal();
        System.out.println(v.verticalOrder(root));
    }
}
