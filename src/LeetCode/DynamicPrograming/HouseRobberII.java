package LeetCode.DynamicPrograming;

/**
 * Created by tayalka on 11/17/2016.
 */
public class HouseRobberII {
    public int rob(int[] nums) {
        if(nums.length  == 0) return 0;
        if(nums.length == 1) return nums[0];
        int[] maxIncludingFirst = new int[nums.length];
        int[] maxExcludingFirst = new int[nums.length+1];
        maxIncludingFirst[0] = nums[0];
        maxIncludingFirst[1] = nums[0];
        for(int i = 2; i < nums.length+1; i++){
            if(i > 1 && i < nums.length-1){
                maxIncludingFirst[i] = Math.max(nums[i]+maxIncludingFirst[i-2], maxIncludingFirst[i-1]);
            }
            maxExcludingFirst[i] = Math.max(nums[i-1] + maxExcludingFirst[i - 2], maxExcludingFirst[i - 1]);
        }
        return Math.max(maxIncludingFirst[nums.length-2], maxExcludingFirst[nums.length]);
    }

    public static void main(String[] args) {
        HouseRobberII hr = new HouseRobberII();
        System.out.println(hr.rob(new int[]{1,2}));
    }
}
