package LeetCode.LinkedList;

/**
 * Created by kashishtayal on 1/3/17.
 */
public class MergeTwoSortedLists {
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        ListNode head = null;
        ListNode prev = null;
        if(l1.val > l2.val){
            head = l2;
            l2 = l2.next;
        }else{
            head = l1;
            l1 = l1.next;
        }
        prev = head;
        while(l1 != null && l2 != null){
            if(l1.val > l2.val){
                prev.next = l2;
                l2 = l2.next;
            }else{
                prev.next = l1;
                l1 = l1.next;
            }
            prev = prev.next;
        }
        if(l1 == null) prev.next = l2;
        if(l2 == null) prev.next = l1;
        return head;
    }

    public static void main(String[] args) {
        ListNode l1 = ListNode.getList(new int[]{});
        ListNode l2 = ListNode.getList(new int[]{2,4,6,8});
        ListNode.print(MergeTwoSortedLists.mergeTwoLists(l1,l2));
    }
}
