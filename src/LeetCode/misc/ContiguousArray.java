package LeetCode.misc;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tayalka on 2/24/2017.
 */
public class ContiguousArray {
    public static int findMaxLength(int[] nums) {
        Map<Integer, Integer> sumIndexMap = new HashMap<>();
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == 0){
                nums[i] = -1;
            }
        }
        int sum = 0;
        sumIndexMap.put(0,-1);
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            if(sumIndexMap.containsKey(sum)){
                max = Math.max(max,i - sumIndexMap.get(sum));
            }else{
                sumIndexMap.put(sum,i);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(findMaxLength(new int[]{ 0, 1}));
    }
}
