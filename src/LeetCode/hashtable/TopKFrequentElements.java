package LeetCode.hashtable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by kashishtayal on 10/25/16.
 */
public class TopKFrequentElements {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        if(nums.length == 0) return result;
        Arrays.sort(nums);
        PriorityQueue<Num> heap = new PriorityQueue<>();
        Num curr = new Num(nums[0]);
        for(int i = 1; i< nums.length; i++){
            if(nums[i] != curr.num){
                heap.add(curr);
                curr = new Num(nums[i]);
            }else{
                curr.freq++;
            }
        }
        heap.add(curr);
        System.out.println(heap);
        for(int i = 0; i < k; i++){
            result.add(heap.remove().num);
        }
        return result;
    }
    class Num implements Comparable<Num>{
        int num;
        int freq;
        public Num(int num){
            this.num = num;
        }

        @Override
        public int compareTo(Num o) {
            if(o.freq > this.freq) return 1;
            if(o.freq < this.freq) return -1;
            return 0;

        }
    }

    public static void main(String[] args) {
        TopKFrequentElements top = new TopKFrequentElements();
        System.out.println(top.topKFrequent(new int[]{1,2},2));
    }
}
