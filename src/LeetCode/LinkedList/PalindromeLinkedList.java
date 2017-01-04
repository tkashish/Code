package LeetCode.LinkedList;

/**
 * Created by kashishtayal on 1/3/17.
 */
public class PalindromeLinkedList {
    public static boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) return true;
        ListNode node1Prev = head;
        ListNode node1next = head.next;
        ListNode node2 = head.next;
        ListNode secondHalfStart = null;
        if(node2 == null || node2.next == null){
            node1Prev.next = null;
            secondHalfStart = node2;
        }
        boolean first = true;
        while(secondHalfStart == null){
            node2 = node2.next.next;
            ListNode temp = node1next.next;
            node1next.next = node1Prev;
            if(first){
                node1Prev.next = null;
                first = false;
            }
            node1Prev = node1next;
            node1next = temp;
            if(node2 == null || node2.next == null){
                secondHalfStart = temp;
            }
        }
        if(node2 == null){
            node1Prev = node1Prev.next;
        }
        while(node1Prev != null && secondHalfStart != null){
            if(node1Prev.val != secondHalfStart.val) return false;
            node1Prev = node1Prev.next;
            secondHalfStart = secondHalfStart.next;
        }
        return node1Prev == null && secondHalfStart == null;
    }

    public static void main(String[] args) {
        ListNode node = ListNode.getList(new int[]{1,2,2});
        System.out.println(PalindromeLinkedList.isPalindrome(node));
    }
}
