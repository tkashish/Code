package LeetCode.LinkedList;

/**
 * Created by tayalka on 12/28/2016.
 */
public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        ListNode first = head;
        if(head == null) return false;
        ListNode second = head.next;
        while(first != null && second != null){
            if(first == second)return true;
            first = first.next;
            second = second.next;
            if(second == null) return false;
            second = second.next;
        }
        return false;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
