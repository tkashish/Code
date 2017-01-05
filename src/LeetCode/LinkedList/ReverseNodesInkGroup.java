package LeetCode.LinkedList;

/**
 * Created by kashishtayal on 1/4/17.
 */
public class ReverseNodesInkGroup {
    ListNode HEAD = null;
    public ListNode reverseKGroup(ListNode head, int k) {
        if(k == 0 || k == 1) return head;
        int len = getLength(head);
        ListNode prev = null;
        int count = 0;
        while (count+k <= len){
            if(prev == null){
                prev = reverseHelper(head,prev,k);
            }else {
                prev = reverseHelper(prev.next, prev, k);
            }
            count += k;
        }
        if(HEAD == null)return head;
        return HEAD;
    }
    private int getLength(ListNode head){
        ListNode node = head;
        int count = 0;
        while(node != null){
            count++;
            node = node.next;
        }
        return count;
    }
    private ListNode reverseHelper(ListNode head, ListNode prev, int k){
        ListNode first = head;
        ListNode second = head.next;
        ListNode prevNUll = prev == null ? head : null;
        while(k > 1){
            ListNode temp = second.next;
            second.next = first;
            first = second;
            second = temp;
            k--;
        }
        if(prev == null){
            HEAD = first;
            prevNUll.next = second;
            return prevNUll;
        }else {
            ListNode nextPrev = prev.next;
            prev.next.next = second;
            prev.next = first;
            return nextPrev;
        }
    }

    public static void main(String[] args) {
        ReverseNodesInkGroup  reverse = new ReverseNodesInkGroup();
        ListNode head = ListNode.getList(new int[]{1,2,3,4,5});
        ListNode.print(reverse.reverseKGroup(head,6));
    }
}
