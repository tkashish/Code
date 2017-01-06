package LeetCode.LinkedList;

import java.util.Stack;

/**
 * Created by kashishtayal on 1/5/17.
 */
public class AddTwoNumbersII {
//    int carry = 0;
//    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//        ListNode head = addHelper(l1,l2);
//        if(carry > 0){
//            ListNode newHead = new ListNode(carry);
//            newHead.next = head;
//            return newHead;
//        }
//        return head;
//    }
//    private ListNode addHelper(ListNode l1, ListNode l2){
//        if(l1 == null && l2 == null) return null;
//        ListNode prevSumList = addHelper(l1.next, l2.next);
//        int sumCurr = l1.val + l2.val + carry;
//        carry = sumCurr > 9 ? 1 : 0;
//        if(carry > 0) sumCurr-=10;
//        ListNode node  = new ListNode(sumCurr);
//        node.next = prevSumList;
//        return node;
//    }
//    private int getListLength(ListNode list){
//        int count = 0;
//        while(list != null){
//            count++;
//            list = list.next;
//        }
//        return count;
//    }
//    private ListNode getZeroList(int len1, int len2){
//        int count = Math.abs(len1-len2);
//        ListNode head = null;
//        while(count > 0){
//            if(head == null){
//                head = new ListNode(0);
//            }
//        }
//        return head;
//    }

    public static void main(String[] args) {
        ListNode n1 = ListNode.getList(new int[]{7,2,4,3});
        ListNode n2 = ListNode.getList(new int[]{4});
        AddTwoNumbersII a2N = new AddTwoNumbersII();
        ListNode.print(a2N.addTwoNumbers(n1,n2));
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        int carry = 0;
        Stack<Integer> s1 = convertListToStack(l1);
        Stack<Integer> s2 = convertListToStack(l2);
        while(!s1.isEmpty() || !s2.isEmpty()){
            int sum = carry;
            if(!s1.isEmpty())sum +=s1.pop();
            if(!s2.isEmpty())sum +=s2.pop();
            carry = sum > 9 ? 1 : 0;
            if(sum > 9) sum -= 10;
            ListNode node = new ListNode(sum);
            node.next = head;
            head = node;
        }
        if(carry > 0){
            ListNode node = new ListNode(carry);
            node.next = head;
            head = node;
        }
        return head;
    }
    private Stack<Integer> convertListToStack(ListNode list){
        Stack<Integer> stack = new Stack<>();
        while(list != null){
            stack.add(list.val);
            list = list.next;
        }
        return stack;
    }
}
