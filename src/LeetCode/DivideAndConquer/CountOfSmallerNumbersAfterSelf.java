package LeetCode.DivideAndConquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by kashishtayal on 1/18/17.
 */
public class CountOfSmallerNumbersAfterSelf {
    public List<Integer> countSmaller(int[] nums) {
        int len = nums.length;
        if(len == 0) return new ArrayList<>();
        Node head = new Node(Integer.MIN_VALUE);
        Node next = new Node(nums[len-1]);
        head.next = next;
        List<Integer> result = new ArrayList<>(len);
        result.add(0);
        for(int i = len-2; i > -1; i--){
            Node curr = new Node(nums[i]);
            Node node = head;
            int count = 0;
            while(node.next != null && node.next.val < curr.val){
                node = node.next;
                count += node.count;
            }
            if(node.next == null){
                node.next = curr;
            }else{
                if(node.next.val == curr.val){
                    node.next.count++;
                }else {
                    Node nextNode = node.next;
                    node.next = curr;
                    curr.next = nextNode;
                }
            }
            result.add(count);
        }
        Collections.reverse(result);
        return result;
    }
    class Node{
        int val;
        int count = 1;
        Node next;
        public Node(int num) {
            val = num;
        }
    }

    public static void main(String[] args) {
        CountOfSmallerNumbersAfterSelf c = new CountOfSmallerNumbersAfterSelf();
        System.out.println(c.countSmaller(new int[]{5,2,6,3,1}));
        System.out.println(c.countSmaller(new int[]{3,2,2,6,1}));
        System.out.println(c.countSmallerI(new int[]{5,2,6,3,1}));
        System.out.println(c.countSmallerI(new int[]{3,2,2,6,1}));
    }

    class Nodee{
        Nodee left;
        Nodee right;
        int count = 1;
        int val;
        public Nodee(int num) {
            val = num;
        }
    }

    public List<Integer> countSmallerI(int[] nums) {
        if(nums.length == 0) return new ArrayList<>();
        Nodee head = new Nodee(nums[nums.length-1]);
        Integer[] result = new Integer[nums.length];
        result[nums.length-1] = 0;
        for(int i = nums.length-2; i > -1; i--){
            Nodee nodee = new Nodee(nums[i]);
            Nodee curr = head;
            Nodee prev = head;
            int count = 0;
            while(curr != null){
                prev = curr;
                if(curr.val >= nums[i]){
                    curr.count++;
                    curr = curr.left;
                }else{
                    count += curr.count;
                    curr = curr.right;
                }
            }
            if(prev.val >= nums[i]){
                prev.left = nodee;
            }else{
                prev.right = nodee;
            }
            result[i] = count;
        }
        return Arrays.asList(result);
    }

}
