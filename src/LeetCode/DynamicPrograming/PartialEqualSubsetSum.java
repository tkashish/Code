package LeetCode.DynamicPrograming;

import java.util.*;

/**
 * Created by kashishtayal on 11/2/16.
 */
public class PartialEqualSubsetSum {
    public static boolean canPartition(int[] nums) {
        int total = Arrays.stream(nums).reduce(0,Integer::sum);
        if(total%2 != 0)return false;
        int mid = total/2;
        Set<Integer> set = new HashSet<>();
        set.add(0);
        int i = 0;
        while(i < nums.length){
            Iterator<Integer> it = set.iterator();
            Set<Integer> setNext = new HashSet<>(set);
            while(it.hasNext()){
                int diff = it.next()+nums[i];
                if(diff == mid) return true;
                if(diff >= mid){
                    setNext.add(diff);
                }
            }
            i++;
            set = setNext;
        }
        return false;
    }
    public static boolean canPartitionDp(int[] nums) {
        int total = Arrays.stream(nums).reduce(0,Integer::sum);
        if(total%2 != 0)return false;
        int mid = total/2;
        return can(0,nums,total,mid);
    }
    public static boolean can(int index, int[] nums, int sum, int target){
        if(sum < target || index >= nums.length)return false;
        if(sum - nums[index] == target) return true;
        return can(index+1,nums,sum,target) || can(index+1, nums, sum-nums[index], target);
    }
    public static void main(String[] args) {
        System.out.println(PartialEqualSubsetSum.canPartition(new int[]{1,5,4,5,7}));
        System.out.println(PartialEqualSubsetSum.canPartition(new int[]{1,5,4,5}));
        System.out.println(PartialEqualSubsetSum.canPartition(new int[]{2,6,8,2}));
        System.out.println(PartialEqualSubsetSum.canPartition(new int[]{1,2,3,5}));

        System.out.println("===");

        System.out.println(PartialEqualSubsetSum.canPartitionDp(new int[]{1,5,4,5,7}));
        System.out.println(PartialEqualSubsetSum.canPartitionDp(new int[]{1,5,4,5}));
        System.out.println(PartialEqualSubsetSum.canPartitionDp(new int[]{2,6,8,2}));
        System.out.println(PartialEqualSubsetSum.canPartitionDp(new int[]{1,2,3,5}));
    }
}
