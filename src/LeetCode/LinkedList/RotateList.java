package LeetCode.LinkedList;

/**
 * Created by kashishtayal on 1/5/17.
 */
public class RotateList {
    public static ListNode rotateRight(ListNode head, int k) {
        if(head == null || k == 0) return head;
        ListNode first = head;
        ListNode second = head;
        int len = 0;
        int num = k;
        while(k > 0){
            if(second == null){
                second = head;
                k = num%len;
                if(k == 0) return head;
            }
            second = second.next;
            len++;
            k--;
        }
        if(second == null) return head;
        while(second.next != null){
            first = first.next;
            second = second.next;
        }
        second.next = head;
        head = first.next;
        first.next = null;
        return head;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.getList(new int[]{1,2,3,4,5});
        ListNode.print(RotateList.rotateRight(head,9002));
    }
}
