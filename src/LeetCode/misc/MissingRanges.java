package LeetCode.misc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kashishtayal on 2/13/17.
 */
public class MissingRanges {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> missingRanges = new ArrayList<>();
        if(nums.length == 0){
            String missingRange = getMissinRange(lower,upper);
            missingRanges.add(missingRange);
            return missingRanges;
        }
        if(lower < nums[0]){
            String missingRange = getMissinRange(lower,nums[0]-1);
            missingRanges.add(missingRange);
        }
        int lastNum = nums[0];
        for(int i = 1; i < nums.length; i++){
            long diff = (long)nums[i] - (long)lastNum;
            if(diff > 1){
                String missingRange = getMissinRange(lastNum+1,nums[i]-1);
                missingRanges.add(missingRange);
            }
            lastNum = nums[i];
        }
        if(nums[nums.length-1] < upper){
            String missingRange = getMissinRange(nums[nums.length-1]+1,upper);
            missingRanges.add(missingRange);
        }
        return missingRanges;
    }

    private String getMissinRange(int start, int end){
        String missingRange = end==start ? String.valueOf(start) : (start + "->" + end);
        return missingRange;
    }

    public static void main(String[] args) {
        MissingRanges mr = new MissingRanges();
        int[] nums = new int[]{-2147483648,2147483647};
        int lower = -2147483648;
        int upper = 2147483647;
        System.out.println(mr.findMissingRanges(nums,lower,upper));
    }
}