package LeetCode.DynamicPrograming;

/**
 * Created by tayalka on 11/17/2016.
 */
public class HouseRobber {
    public int rob(int[] nums) {
        if(nums.length == 0) return 0;
        int[] max = new int[nums.length+1];
        max[1] = nums[0];
        for(int i = 2; i < nums.length+1;i++){
            int houseIndex = i-1;
            max[i] = Math.max(nums[houseIndex]+max[i-2], max[i-1]);
        }
        return max[nums.length];
    }
}
