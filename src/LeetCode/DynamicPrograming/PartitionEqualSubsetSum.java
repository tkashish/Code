package LeetCode.DynamicPrograming;

import java.util.Arrays;

/**
 * Created by tayalka on 11/3/2016.
 */
public class PartitionEqualSubsetSum {
    /*
    matrix values can be 0 = not visited
    1 = visited and true
    2 = visited and false
     */
    public boolean canPartition(int[] nums) {
        int total = Arrays.stream(nums).reduce(0,Integer::sum);
        if(total%2 != 0) return false;
        int target = total/2;
        int[][] matrix = new int[total+1][nums.length];
        return helper(matrix,nums,0,0,target);
    }
    public boolean helper(int[][] matrix, int[] nums, int index, int currVal, int target){
        if(currVal > target || index == nums.length) return false;
        if(currVal == target) return true;
        if(matrix[currVal][index] == 1) return true;
        if(matrix[currVal][index] == 2) return false;
        boolean result = helper(matrix,nums,index+1,currVal,target)|| helper(matrix, nums, index + 1, currVal+ nums[index], target);
        matrix[currVal][index] = result ? 1:2;
        return result;
    }
}
