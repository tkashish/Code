package LeetCode.misc;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by tayalka on 3/2/2017.
 */
public class TargetSum {
    int[] sum;
    Set<Integer>[] setOfPossibleSums;
    public int findTargetSumWays(int[] nums, int S) {
        sum = new int[nums.length];
        setOfPossibleSums = new Set[nums.length+1];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = nums[i]+sum[i-1];
        }
        return helper(nums, nums.length - 1, S);
    }

    private int helper(int[] nums, int lastIndex, int target) {
        System.out.println(target+" "+lastIndex);
        if (lastIndex == -1) {
            return Math.abs(target) == 0 ? 1 : 0;
        }
        int leftTarget = target - nums[lastIndex];
        int rightTarget = target + nums[lastIndex];
        int left = 0;
        int right = 0;
        if(sum[lastIndex] >= Math.abs(leftTarget)) {
            if (setOfPossibleSums[lastIndex] != null && setOfPossibleSums[lastIndex].contains(leftTarget)) {
                left = 1;
            } else {
                left = helper(nums, lastIndex - 1, leftTarget);
                Set<Integer> sums = setOfPossibleSums[lastIndex];
                if(sums == null) sums = new HashSet<>();
                sums.add(left);
                setOfPossibleSums[lastIndex] = sums;
            }
        }
        if (sum[lastIndex] >= Math.abs(rightTarget)){
            if (setOfPossibleSums[lastIndex] != null && setOfPossibleSums[lastIndex].contains(rightTarget)) {
                right = 1;
            } else {
                right = helper(nums, lastIndex - 1, rightTarget);
                Set<Integer> sums = setOfPossibleSums[lastIndex];
                if (sums == null) sums = new HashSet<>();
                sums.add(right);
                setOfPossibleSums[lastIndex] = sums;
            }
        }
        return left > 0 || right > 0 ? left + right : 0;
    }

    public static void main(String[] args) {
        TargetSum sum = new TargetSum();
        System.out.println(sum.findTargetSumWays(new int[]{0,0,1}, 1));
    }
}
