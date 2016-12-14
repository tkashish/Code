package LeetCode;

/**
 * 109 https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
 */
public class ConvertSortedListtoBinarySearchTree {
    public static TreeNode sortedListToBST(ListNode head) {
        if(head == null)return null;
        if(head.next == null)return new TreeNode(head.val);
        ListNode n1 = head;
        ListNode n2 = head;
        ListNode n3 = null;
        while(n2 != null && n2.next != null){
            n3 = n1;
            n1 = n1.next;
            n2 = n2.next.next;
        }
        n3.next = null;
        TreeNode treeHead = new TreeNode(n1.val);
        treeHead.left = sortedListToBST(head);
        treeHead.right = sortedListToBST(n1.next);
        return treeHead;
    }

    public static void main(String[] args) {

        System.out.println(  TreeNode.isheightBalanced(sortedListToBST(ListNode.getList(new int[]{}))));
        System.out.println(  TreeNode.isheightBalanced(sortedListToBST(ListNode.getList(new int[]{1}))));
        System.out.println(  TreeNode.isheightBalanced(sortedListToBST(ListNode.getList(new int[]{1,2}))));
        System.out.println(  TreeNode.isheightBalanced(sortedListToBST(ListNode.getList(new int[]{1,2,3}))));
        System.out.println(  TreeNode.isheightBalanced(sortedListToBST(ListNode.getList(new int[]{1,2,3,4}))));
        System.out.println(  TreeNode.isheightBalanced(sortedListToBST(ListNode.getList(new int[]{1,2,3,4,5}))));
        System.out.println(  TreeNode.isheightBalanced(sortedListToBST(ListNode.getList(new int[]{1,2,3,4,5,6}))));
    }

}
