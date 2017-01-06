package LeetCode.LinkedList;

import java.util.Stack;

/**
 * Created by kashishtayal on 1/5/17.
 */
public class PlusOneLinkedList {
    public static ListNode plusOne(ListNode head) {
        if(head == null) return head;
        Stack<ListNode> stack = new Stack<>();
        ListNode node = head;
        while(node != null){
            stack.push(node);
            node = node.next;
        }
        int carry = 1;
        while(carry == 1 && !stack.isEmpty()){
            ListNode curr = stack.pop();
            int val = curr.val + carry;
            carry = val > 9 ? 1:0;
            if(val > 9) val-=10;
            curr.val = val;
        }
        if(carry == 1){
            ListNode temp = new ListNode(carry);
            temp.next = head;
            head = temp;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode node = ListNode.getList(new int[]{1,9});
        ListNode.print(PlusOneLinkedList.plusOne(node));
    }
}
