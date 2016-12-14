package LeetCode.DynamicPrograming;

/**
 * Created by tayalka on 11/17/2016.
 */
public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        if(nums.length == 0) return 0;
        int prev = nums[0];
        int max = prev;
        for (int i = 1; i < nums.length; i++){
//            prev = Math.max()
            max = Math.max(max, nums[i]*prev);
        }
        return max;
    }

    public static void main(String[] args) {
        MaximumProductSubarray mps = new MaximumProductSubarray();
        System.out.println(mps.maxProduct(new int[]{2,3,-2,4}));
    }
}
