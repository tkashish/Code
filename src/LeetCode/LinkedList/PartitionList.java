package LeetCode.LinkedList;

/**
 * Created by kashishtayal on 1/4/17.
 */
public class PartitionList {
    public static ListNode partition(ListNode head, int x) {
        ListNode first = null;
        ListNode firstRunner = null;
        ListNode second = null;
        ListNode secondRunner = null;
        while(head != null) {
            if (head.val < x) {
                if(first == null) {
                    first = head;
                    firstRunner = head;
                }else{
                    firstRunner.next = head;
                    firstRunner = firstRunner.next;
                }
            } else {
                if(second == null){
                    second = head;
                    secondRunner = head;
                }else{
                    secondRunner.next = head;
                    secondRunner = secondRunner.next;
                }
            }
            head = head.next;
        }
        if(secondRunner != null)secondRunner.next = null;
        if(firstRunner != null)firstRunner.next = second;
        else first = second;
        return first;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.getList(new int[]{1,4,3,2,5,2});
        ListNode.print(PartitionList.partition(head,0));
    }
}
