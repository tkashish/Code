package LeetCode.LinkedList;

/**
 * Created by kashishtayal on 1/3/17.
 */
public class OddEven {
    public static ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null || head.next.next == null) return head;
        ListNode lastOdd = head;
        final ListNode firstEven = head.next;
        ListNode currNode = head.next.next;
        ListNode prevEven = firstEven;
        while(currNode != null){
            ListNode nextEven = currNode.next;
            /**
             * put the currNode before first Even
             */
            lastOdd.next = currNode;
            currNode.next = firstEven;
            prevEven.next = nextEven;
            lastOdd = currNode;
            if(nextEven == null)break;
            currNode = nextEven.next;
            prevEven = nextEven;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.getList(new int[]{1,2,3,4,5,6});
        ListNode.print(head);
        ListNode.print(OddEven.oddEvenList(head));
    }
}
