package LeetCode.misc;

/**
 * Created by tayalka on 3/6/2017.
 */
public class MaxConsecutiveOnesII {
    public int findMaxConsecutiveOnes(int[] nums) {
        if(nums.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        int prevSum = -1;
        int currSum = 0;
        for(int num : nums){
            if(num == 0){
                max = Math.max(max,prevSum+currSum+1);
                prevSum = currSum;
                currSum = 0;
            }else{
                currSum++;
            }
        }
        max = Math.max(max,prevSum+currSum+1);
        return max;
    }

}
