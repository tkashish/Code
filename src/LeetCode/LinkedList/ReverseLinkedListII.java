package LeetCode.LinkedList;

/**
 * Created by kashishtayal on 1/4/17.
 */
public class ReverseLinkedListII {
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null || head.next == null || m == n) return head;
        ListNode last = m > 1 ? head:null;
        int count = n-m;
        while(m > 2){
            last = last.next;
            m--;
        }
        ListNode first = (last == null) ? head : last.next;
        ListNode second = first.next;
        while(count > 0){
            ListNode temp = second.next;
            second.next = first;
            first = second;
            second = temp;
            count--;
        }
        if(last == null) {
            head.next = second;
            return first;
        }
        last.next.next = second;
        last.next = first;
        return head;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.getList(new int[]{1,2,3,4,5});
        ListNode.print(ReverseLinkedListII.reverseBetween(head,2,3));
    }
}
