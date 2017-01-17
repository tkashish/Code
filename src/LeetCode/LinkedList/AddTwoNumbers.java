package LeetCode.LinkedList;


/**
 * Created by tayalka on 12/28/2016.
 */
public class AddTwoNumbers {
    ListNode result = null;
    int carry = 0;
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode i = l1;
        ListNode j = l2;
        ListNode node = null;
        while(i != null && j != null){
            int sum = getSum(i.val, j.val);
            node = addToResult(sum, node);
            i = i.next;
            j = j.next;
        }
        if(i == null){
            while(j != null){
                int sum = getSum(0,j.val);
                node = addToResult(sum, node);
                j = j.next;
            }
        }
        if(j == null){
            while (i != null) {
                int sum = getSum(i.val,0);
                node = addToResult(sum, node);
                i = i.next;
            }
        }
        if(carry > 0)addToResult(carry,node);
        return result;
    }

    private int getSum(int numL1, int numL2){
        int sum = numL1 + numL2 + carry;
        if (sum > 9) {
            carry = 1;
            sum -= 10;
        } else {
            carry = 0;
        }
        return sum;
    }
    public ListNode addToResult(int sum, ListNode node){
        if (node == null) {
            node = new ListNode(sum);
            result = node;
        } else {
            node.next = new ListNode(sum);
            node = node.next;
        }
        return node;
    }
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    public static void main(String[] args) {
        AddTwoNumbers a2N = new AddTwoNumbers();
        ListNode node = null;
        node = a2N.addToResult(10,node);
        System.out.println(node.val);
    }
}