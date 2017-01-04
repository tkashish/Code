package LeetCode.LinkedList;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by kashishtayal on 1/3/17.
 */
public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> heap = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                if(o1.val > o2.val) return 1;
                else if(o1.val < o2.val) return -1;
                else return 0;
            }
        });
        for(ListNode node : lists){
            if(node == null) continue;
            heap.add(node);
        }
        ListNode head = null;
        ListNode prev = null;

        while(!heap.isEmpty()){
            ListNode curr = heap.remove();
            if(head == null){
                head = curr;
                prev = curr;
            }else{
                prev.next = curr;
                prev = prev.next;
            }
            if(curr.next != null) heap.add(curr.next);
        }
        return head;
    }

}
