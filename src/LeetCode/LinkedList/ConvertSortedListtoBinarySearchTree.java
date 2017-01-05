package LeetCode.LinkedList;

import LeetCode.BinaryTree.Codec;
import LeetCode.BinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kashishtayal on 1/4/17.
 */
public class ConvertSortedListtoBinarySearchTree {
    public TreeNode sortedListToBST(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode node = head;
        while(node != null){
            list.add(node.val);
            node = node.next;
        }
        int len = list.size();
        return getTree(list,0,len);
    }
    /*
       get height Balanced tree start and end position in list inclusive
     */
    private TreeNode getTree(List<Integer> list, int start, int end){
        if(start == (end-1)) return new TreeNode(list.get(start));
        if(start == end) return null;
        int mid = start + (end-start)/2;
        if((end-start) % 2 == 0) mid--;
        TreeNode root = new TreeNode(list.get(mid));
        root.left = getTree(list,start,mid);
        root.right = getTree(list,mid+1,end);
        return root;
    }

    public static void main(String[] args) {
        ListNode node = ListNode.getList(new int[]{1,2,3,4,5,6,7,8,9});
        ConvertSortedListtoBinarySearchTree convert = new ConvertSortedListtoBinarySearchTree();
        System.out.println(Codec.serialize(convert.sortedListToBST(node)));
    }
}
