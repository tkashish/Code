package LeetCode;

/**
 * 2  https://leetcode.com/problems/add-two-numbers/
 */
public class AddTwoNumbers {

    public static ListNode addTwo(ListNode l1, ListNode l2){
        ListNode ret = null;
        ListNode retNext = null;
        int carry = 0;
        boolean counter = true;
        while(l1 != null && l2 != null){
            int currSum = l1.val + l2.val + carry;
            carry = currSum>9?1:0;
            if(counter){
                ret = new ListNode(currSum>9?(currSum%10):currSum);
                retNext = ret;
                counter = false;
            }else{
                retNext.next = new ListNode(currSum>9?(currSum%10):currSum);
                retNext = retNext.next;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        ListNode n = l1==null?l2:l1;
        while(n != null){
            int currSum = n.val + carry;
            carry = currSum>9?1:0;
            retNext.next = new ListNode(currSum>9?(currSum%10):currSum);
            retNext = retNext.next;
            n = n.next;
        }
        if(carry!=0){
            retNext.next = new ListNode(carry);
            retNext = retNext.next;
        }
        retNext = null;
        return ret;
    }

    public static void main(String[] args) {
        ListNode.print(addTwo(ListNode.getList(new int[]{5}), ListNode.getList(new int[]{5})));
        ListNode.print(addTwo(ListNode.getList(new int[]{2}), ListNode.getList(new int[]{5})));
        ListNode.print(addTwo(ListNode.getList(new int[]{1,2}), ListNode.getList(new int[]{2,5})));
        ListNode.print(addTwo(ListNode.getList(new int[]{5}), ListNode.getList(new int[]{9,5})));
        ListNode.print(addTwo(ListNode.getList(new int[]{8,9}), ListNode.getList(new int[]{6,7})));
    }
}
