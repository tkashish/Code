package LeetCode.DynamicPrograming;

import java.util.*;

/**
 * Created by kashishtayal on 11/22/16.
 */
public class ArithmeticSlicesII {
    public int numberOfArithmeticSlices(int[] A) {
        int len = A.length;
        if(len < 3) return 0;
        int count = 0;
        List<Map<Long,List<Integer>>> dp = new ArrayList<>();
        dp.add(new HashMap<>());
        for(int i = 1; i < len; i++){
            Map<Long,List<Integer>> currMap = new HashMap<>();
            for(int j = 0; j < i; j++){
                long diff = ((long)A[i])-((long)A[j]);
                Map<Long,List<Integer>> mapJ = dp.get(j);
                List<Integer> prev = mapJ.get(diff);
                List<Integer> currList = currMap.get(diff);
                if(currList == null) currList = new ArrayList<>();
                if(prev!= null){
                    for(int m = 0; m < prev.size(); m++){
                        currList.add(1+prev.get(m));
                        count += prev.get(m)-1;
                    }
                    currMap.put(diff,currList);
                }else{
                    currList.add(2);
                    currMap.put(diff,currList);
                }
            }
            dp.add(currMap);
        }
        return count;
    }

    public static void main(String[] args) {
        ArithmeticSlicesII as = new ArithmeticSlicesII();
        int min = Integer.MIN_VALUE;
        System.out.println(as.numberOfArithmeticSlices(new int[]{2,4,6,8,10,12}));
        System.out.println(as.numberOfArithmeticSlices(new int[]{min,min+2,min+4,min+6,min+8,min+10}));
        System.out.println(as.numberOfArithmeticSlices(new int[]{Integer.MIN_VALUE,0,Integer.MAX_VALUE-1}));
        System.out.println(as.numberOfArithmeticSlices(new int[]{0,2000000000,-294967296}));
        System.out.println(as.numberOfArithmeticSlices(new int[]{1,2,3,4,3,2,1}));
        System.out.println(as.numberOfArithmeticSlices(new int[]{1,1,1}));
        System.out.println(as.numberOfArithmeticSlices(new int[]{1,2,3,3,4}));
        System.out.println(as.numberOfArithmeticSlices(new int[]{1,2,2,3,3}));
        System.out.println(as.numberOfArithmeticSlices(new int[]{1,2,3,4,4}));
        System.out.println(as.numberOfArithmeticSlices(new int[]{4,4,3,2,1}));
        System.out.println(as.numberOfArithmeticSlices(new int[]{3,4,3,2,1}));
        System.out.println(as.numberOfArithmeticSlices(new int[]{79,20,64,28,67,81,60,58,97,85,92,96,82,89,46,50,15,2,36,44,54,2,90,37,7,79,26,40,34,67,64,28,60,89,46,31,9,95,43,19,47,64,48,95,80,31,47,19,72,99,28,46,13,9,64,4,68,74,50,28,69,94,93,3,80,78,23,80,43,49,77,18,68,28,13,61,34,44,80,70,55,85,0,37,93,40,47,47,45,23,26,74,45,67,34,20,33,71,48,96}));
    }
}

//[79,20,64,28,67,81,60,58,97,85,92,96,82,89,46,50,15,2,36,44,54,2,90,37,7,79,26,40,34,67,64,28,60,89,46,31,9,95,43,19,47,64,48,95,80,31,47,19,72,99,28,46,13,9,64,4,68,74,50,28,69,94,93,3,80,78,23,80,43,49,77,18,68,28,13,61,34,44,80,70,55,85,0,37,93,40,47,47,45,23,26,74,45,67,34,20,33,71,48,96]