package LeetCode;

/**
 * Created by tayalka on 7/5/2016.
 */
public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
    static ListNode getList(int[] input){
        ListNode ret = null;
        ListNode node = null;
        for(int i = 0; i < input.length; i++){
            ListNode next = new ListNode(input[i]);
            if(i == 0){
                node = next;
                ret = next;
                continue;
            }
            ret.next = next;
            ret = next;
        }
        return node;
    }
    static void print(ListNode inNode){
        while (inNode != null) {
            System.out.print(inNode.val + " ");
            inNode = inNode.next;
        }
        System.out.println("");
    }
}
