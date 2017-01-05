package LeetCode.LinkedList;

/**
 * Created by kashishtayal on 1/4/17.
 */
public class SwapNodesinPairs {
    public static ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode node1 = head;
        ListNode node2 = head.next;
        while(node2 != null){
            int temp = node2.val;
            node2.val = node1.val;
            node1.val = temp;
            node1 = node2.next;
            if(node1 == null){
                break;
            }
            node2 = node1.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.getList(new int[]{1});
        ListNode.print(SwapNodesinPairs.swapPairs(head));
    }
}
