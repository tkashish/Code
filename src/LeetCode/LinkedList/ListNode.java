package LeetCode.LinkedList;

/**
 * Created by kashishtayal on 1/3/17.
 */
public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
    public static ListNode getList(int[] arr){
        ListNode head = null;
        ListNode curr = null;
        ListNode prev = null;
        for (int i = 0; i < arr.length; i++) {
            curr = new ListNode(arr[i]);
            if(i == 0){
                head = curr;
            }else{
                prev.next = curr;
            }
            prev = curr;
        }
        return head;
    }
    public static void print(ListNode head){
        ListNode curr = head;
        while(curr != null){
            System.out.print(curr.val+" ");
            curr = curr.next;
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                '}';
    }
}
