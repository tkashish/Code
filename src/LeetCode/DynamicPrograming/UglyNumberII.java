package LeetCode.DynamicPrograming;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Created by kashishtayal on 11/14/16.
 */
public class UglyNumberII {
    public int nthUglyNumber(int n) {
        Set<Long> numsVisited = new HashSet<>();
        PriorityQueue<Long> queue = new PriorityQueue<>();
        queue.add(1L);
        numsVisited.add(1L);
        int num = 0;
        while(true){
            long curr = queue.remove();
            num++;
            if(num == n) {
                return Math.toIntExact(curr);
            }
            long num2 = curr*2;
            long num3 = curr*3;
            long num5 = curr*5;
            if(!numsVisited.contains(num2)){
                queue.add(num2);
                numsVisited.add(num2);
            }
            if(!numsVisited.contains(num3)){
                queue.add(num3);
                numsVisited.add(num3);
            }
            if(!numsVisited.contains(num5)){
                queue.add(num5);
                numsVisited.add(num5);
            }
        }
    }


    public int nthUglyNumberI(int n) {
        long uglyNumber = 1;
        long count = 1;
        long num2 = Integer.MAX_VALUE;
        long num3 = Integer.MAX_VALUE;
        long num5 = Integer.MAX_VALUE;
        while(count < n){
            num2 = Math.min(num2,uglyNumber*2);
            num3 = Math.min(num3,uglyNumber*3);
            num5 = Math.min(num5,uglyNumber*5);
            System.out.println(num2+" "+num3+" "+num5);
            if(num2 < num3 && num2 < num5){
                uglyNumber = num2;
                num2 = Integer.MAX_VALUE;
            }else if(num3 < num2 && num3 < num5){
                uglyNumber = num3;
                num3 = Integer.MAX_VALUE;
            }else if(num5 < num3 && num5 < num2){
                uglyNumber = num5;
                num5 = Integer.MAX_VALUE;
            }
            count++;
        }
        return Math.toIntExact(uglyNumber);
    }

    public static void main(String[] args) {
        UglyNumberII uglyNumber = new UglyNumberII();
//        System.out.println(uglyNumber.nthUglyNumber(1407));
        System.out.println(uglyNumber.nthUglyNumberI(10));

//        for (int i = 0; i < 10; i++) {
//            System.out.println(uglyNumber.nthUglyNumberI(i));
//        }
    }
}
