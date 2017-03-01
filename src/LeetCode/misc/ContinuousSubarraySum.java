package LeetCode.misc;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kashishtayal on 2/27/17.
 */
public class ContinuousSubarraySum {
    public static boolean checkSubarraySum(int[] nums, int k) {
        if(nums == null || nums.length <2) return false;
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for(int i = 1; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                int currSum = nums[i] + sum[j];
                if(currSum%k == 0)return true;
                sum[j] += nums[i];
            }
            sum[i] = nums[i];
        }
        return false;
    }


    public static boolean checkSubarraySumI(int[] nums, int k) {
        if(nums == null || nums.length <2) return false;
        k = Math.abs(k);
        Map<Integer, Integer> map = new HashMap<>();
        int sum = nums[0];
        if(k == 0){
            map.put(-sum,0);
        }else{
            map.put(sum%k,0);
        }
        for(int i = 1; i < nums.length; i++){
            sum += nums[i];
            if(k == 0){
                if(sum == 0 || map.containsKey(sum))return true;
                map.put(-sum,i);
            }else {
                if (sum == k || map.containsKey(sum % k)) {
                    return true;
                }
                map.put(sum % k, i);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(checkSubarraySumI(new int[]{0,1,0}, 0));
    }
}
