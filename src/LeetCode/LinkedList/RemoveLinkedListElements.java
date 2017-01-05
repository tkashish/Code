package LeetCode.LinkedList;

/**
 * Created by kashishtayal on 1/4/17.
 */
public class RemoveLinkedListElements {
    public static ListNode removeElements(ListNode head, int val) {
        ListNode prevNode = null;
        ListNode currNode = head;
        while(currNode != null){
            ListNode next = currNode.next;
            if(currNode.val == val){
                if(prevNode == null){
                    head = next;
                }else{
                    prevNode.next = next;
                }
                currNode.next = null;
            }else{
                prevNode = currNode;
            }
            currNode = next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.getList(new int[]{1,2,3,4,2,6,2,7,2,1});
        ListNode.print(RemoveLinkedListElements.removeElements(head,3));
    }
}
