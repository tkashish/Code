package LeetCode.LinkedList;

/**
 * Created by tayalka on 12/28/2016.
 */
public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        ListNode resultHead = null;
        while(head != null){
            ListNode node = head;
            head = head.next;
            node.next = resultHead;
            resultHead = node;
        }
        return resultHead;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
