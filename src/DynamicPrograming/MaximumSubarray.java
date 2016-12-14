package DynamicPrograming;

/**
 * Created by tayalka on 11/17/2016.
 */
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        if(nums.length == 0) return 0;
        int sumPrev = Integer.MIN_VALUE;
        int max = sumPrev;
        for(int i = 0; i < nums.length; i++){
            sumPrev = sumPrev > 0 ? (sumPrev +nums[i]) : nums[i];
            max = Math.max(max, sumPrev);
        }
        return max;
    }

    public static void main(String[] args) {
        MaximumSubarray ms = new MaximumSubarray();
        System.out.println(ms.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println(ms.maxSubArray(new int[]{-2}));
        System.out.println(ms.maxSubArray(new int[]{-2,1}));
        System.out.println(ms.maxSubArray(new int[]{}));
    }
}
