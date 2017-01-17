package LeetCode.LinkedList;

/**
 * Created by tayalka on 12/28/2016.
 */
public class DeletNodeInLinkedList {
    public void deleteNode(ListNode node) {
        ListNode prev = null;
        ListNode j = node;
        ListNode i = node.next;
        while (i != null){
            j.val = i.val;
            prev = j;
            j = j.next;
            i = i.next;
        }
        prev.next = null;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
