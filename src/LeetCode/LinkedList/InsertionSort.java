package LeetCode.LinkedList;

/**
 * Created by kashishtayal on 1/3/17.
 */
public class InsertionSort {
    public static ListNode insertionSortList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode newHead = head;
        ListNode tail = head;
        while(tail.next != null){
            ListNode nextNode = tail.next;
            ListNode temp = nextNode.next;
            /**
             * insert the nextNode in the sortedList
             * 1) at the start
             * 2) in the middle
             * 3) at the end
             */

            if(nextNode.val <= newHead.val){
                nextNode.next = newHead;
                newHead = nextNode;
            }else if(nextNode.val >= tail.val){
                tail = nextNode;
            }else{
                ListNode prev = newHead;
                ListNode curr = newHead;
                while(curr.val < nextNode.val){
                    prev = curr;
                    curr = curr.next;
                }
                prev.next = nextNode;
                nextNode.next = curr;
            }

            /**
             * adjust the tail
             */
            tail.next = temp;
        }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.getList(new int[]{1,9,2,8,3,7,4,6,5,9,9,1,1,2,4,5});
        ListNode.print(InsertionSort.insertionSortList(head));
    }
}
