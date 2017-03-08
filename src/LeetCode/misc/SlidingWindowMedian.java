package LeetCode.misc;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Created by tayalka on 3/7/2017.
 */
public class SlidingWindowMedian {
    PriorityQueue<Node> maxPq = new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Node> minPq = new PriorityQueue<>();
    int[] pqNumber;
    int maxPqCount;
    int minPqCount;
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] result = new double[nums.length-k+1];
        int minIndex = 0;
        maxPqCount = 0;
        minPqCount = 0;
        pqNumber = new int[nums.length];
        for(int i = 0; i < k; i++){
            maxPq.add(new Node(nums[i],i));
            maxPqCount++;
            pqNumber[i] = 1;
        }
        boolean bothTop = k%2 == 0;
        for(int i = 0; i < nums.length-k+1; i++){
            balance();
            if(bothTop){
                Node n1 = maxPq.peek();
                while(n1.index < i){
                    maxPq.poll();
                    n1 = maxPq.peek();
                }
                Node n2 = minPq.peek();
                while (n2.index < i) {
                    minPq.poll();
                    n2 = minPq.peek();
                }
                double mid = (n1.num)/2.0+(n2.num)/2.0;
                result[i] = mid;
            }else{
                Node n1 = null;
                if(maxPqCount > minPqCount){
                    n1 = maxPq.peek();
                }else{
                    n1 = minPq.peek();
                }
                while (n1.index < i) {
                    if (maxPqCount > minPqCount) {
                        maxPq.poll();
                        n1 = maxPq.peek();
                    } else {
                        minPq.poll();
                        n1 = minPq.peek();
                    }
                }
                double mid = n1.num;
                result[i] = mid;
            }
            int pqNum = pqNumber[i];
            if(pqNum == 1) maxPqCount--;
            else minPqCount--;
            if(i+k < nums.length) {
                if (nums[i + k] < result[i]) {
                    maxPq.add(new Node(nums[i + k], i + k));
                    pqNumber[i + k] = 1;
                    maxPqCount++;
                } else {
                    minPq.add(new Node(nums[i + k], i + k));
                    pqNumber[i + k] = 2;
                    minPqCount++;
                }
            }
        }
        return result;
    }

    private void balance(){
        while(Math.abs(maxPqCount-minPqCount) > 1){
            if(maxPqCount > minPqCount){
                Node node = maxPq.poll();
                maxPqCount--;
                minPqCount++;
                pqNumber[node.index] = 2;
                minPq.add(node);
            }else{
                Node node = minPq.poll();
                maxPqCount++;
                minPqCount--;
                pqNumber[node.index] = 1;
                maxPq.add(node);
            }
        }
    }

    private class Node implements Comparable<Node>{
        int num;
        int index;

        public Node(int num, int index) {
            this.num = num;
            this.index = index;
        }

        @Override
        public int compareTo(Node o) {
            if(num < o.num) return -1;
            else if(num == o.num) return 1;
            else return 1;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "num=" + num +
                    '}';
        }
    }

    public static void main(String[] args) {
        SlidingWindowMedian med = new SlidingWindowMedian();
        Arrays.stream(med.medianSlidingWindow(new int[]{1,1,1,1},2)).forEach(System.out::println);
    }
}
