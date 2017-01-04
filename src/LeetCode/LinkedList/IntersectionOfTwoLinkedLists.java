package LeetCode.LinkedList;

/**
 * Created by kashishtayal on 1/3/17.
 */
public class IntersectionOfTwoLinkedLists {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int len1 = length(headA);
        int len2 = length(headB);
        ListNode hA = headA;
        ListNode hB = headB;
        if(len1 > len2){
            int count = len1-len2;
            while(count > 0){
                hA = hA.next;
                count--;
            }
        }else{
            int count = len2-len1;
            while(count > 0){
                hB = hB.next;
                count--;
            }
        }
        while(hA != hB){
            hA = hA.next;
            hB = hB.next;
        }
        return hA;
    }
    private int length(ListNode head){
        ListNode curr = head;
        int count = 0;
        while(curr != null){
            count++;
            curr = curr.next;
        }
        return count;
    }
}
