package LeetCode.LinkedList;

/**
 * Created by kashishtayal on 1/5/17.
 */
public class RemoveDuplicatesfromSortedListII {
    public static ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode lastNode = null;
        while(true){
            ListNode node = head;
            if(lastNode != null) node = lastNode.next;
            int val = node.val;
            boolean isDup = false;
            while(node.next != null && node.next.val == val){
                node = node.next;
                isDup = true;
            }
            if(isDup){
                if(lastNode == null){
                    if(node.next == null){
                        return null;
                    } else{
                        head = node.next;
                        node.next = null;
                        continue;
                    }
                }else{
                    if(node.next == null){
                        lastNode.next = null;
                        return head;
                    }else{
                        lastNode.next = node.next;
                        node.next = null;
                    }
                }
            }else{
                lastNode = node;
            }
            if(lastNode == null || lastNode.next == null) break;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode node = ListNode.getList(new int[]{1});
        ListNode node5 = ListNode.getList(new int[]{1,2});
        ListNode node1 = ListNode.getList(new int[]{1,1});
        ListNode node2 = ListNode.getList(new int[]{1,1,2});
        ListNode node3 = ListNode.getList(new int[]{1,2,2});
        ListNode node4 = ListNode.getList(new int[]{1,2,2,3});
        ListNode node6 = ListNode.getList(new int[]{1,2,2,3,3,4,5,6,6,7});
        ListNode node7 = ListNode.getList(new int[]{1,1,2,2,3});
        ListNode.print(RemoveDuplicatesfromSortedListII.deleteDuplicates(node7));
    }
}
