package LeetCode.LinkedList;

/**
 * Created by kashishtayal on 1/3/17.
 */
public class RemovingNthNodeFromTheEndOfList {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode firstNode = head;
        ListNode secondNode = head;
        while(n > 0){
            secondNode = secondNode.next;
            n--;
        }
        while (secondNode != null && secondNode.next != null){
            firstNode = firstNode.next;
            secondNode = secondNode.next;
        }
        if(secondNode == null){
            head = head.next;
            return head;
        }
        ListNode temp = firstNode.next.next;
        firstNode.next.next = null;
        firstNode.next = temp;
        return head;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.getList(new int[]{1,2,3,4,5});
        ListNode.print(RemovingNthNodeFromTheEndOfList.removeNthFromEnd(head,5));
    }
}
