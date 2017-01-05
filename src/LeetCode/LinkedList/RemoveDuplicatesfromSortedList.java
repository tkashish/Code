package LeetCode.LinkedList;

/**
 * Created by kashishtayal on 1/4/17.
 */
public class RemoveDuplicatesfromSortedList {
    public static ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null)return head;
        ListNode lastNode = head;
        ListNode currNode = head.next;
        while(currNode != null){
            if(lastNode.val == currNode.val){
                ListNode temp = currNode.next;
                lastNode.next = temp;
                currNode.next = null;
                currNode = temp;
            }else{
                lastNode = currNode;
                currNode = currNode.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode node = ListNode.getList(new int[]{1,2,3,1});
        ListNode.print(RemoveDuplicatesfromSortedList.deleteDuplicates(node));
    }
}
