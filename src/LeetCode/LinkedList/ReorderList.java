package LeetCode.LinkedList;

/**
 * Created by kashishtayal on 1/4/17.
 */
public class ReorderList {
    public static void reorderList(ListNode head) {
        if(head == null || head.next == null)return;
        ListNode firstCurr = null;
        ListNode second = head;
        ListNode firstPrev = null;
        ListNode first = head;
        while(second != null && second.next != null){
            second = second.next.next;
            firstPrev = firstCurr;
            firstCurr = first;
            first = first.next;
            firstCurr.next = firstPrev;
        }
        ListNode lastNode = null;
        if(second == null){
            second = first;
            first = firstCurr;
        }else if(second.next == null){
            ListNode node1 = firstCurr;
            ListNode node2 = first;
            ListNode node3 = first.next;
            second = first.next.next;
            first = firstCurr.next;
            node1.next = node3;
            node3.next = node2;
            node2.next = null;
            lastNode = node1;
        }
        while(first != null && second != null){
            ListNode node1 = first;
            ListNode node2 = second;
            first = first.next;
            second = second.next;
            node1.next = node2;
            node2.next = lastNode;
            lastNode = node1;
        }
        ListNode.print(head);
    }

    public static void main(String[] args) {
        ListNode head = ListNode.getList(new int[]{1,2});
        ReorderList.reorderList(head);
    }
}
