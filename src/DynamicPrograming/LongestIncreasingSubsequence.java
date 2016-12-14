package DynamicPrograming;

/**
 * Created by tayalka on 11/15/2016.
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length == 1) return 1;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = 1;
        for(int i = 1; i < nums.length; i++){
            dp[i] = 1;
            max = Math.max(max, findMaxN2(nums,i,dp));
        }
        return max;
    }

    public int findMax(int[] nums, int currIndex, int[] dp){
        return 0;
    }
    public int findMaxN2(int[] nums, int currIndex, int[] dp){
        for (int j = 0; j < currIndex; j++) {
            if (nums[j] < nums[currIndex]) {
                dp[currIndex] = Math.max(1 + dp[j], dp[currIndex]);
            }
        }
        return dp[currIndex];
    }


    public static void main(String[] args) {
        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
        System.out.println(lis.lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
        System.out.println(lis.lengthOfLIS(new int[]{}));
        System.out.println(lis.lengthOfLIS(new int[]{1}));
        System.out.println(lis.lengthOfLIS(new int[]{1,2}));
        System.out.println(lis.lengthOfLIS(new int[]{2,1}));
    }
}
