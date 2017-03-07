package LeetCode.misc;

import java.util.Arrays;

/**
 * Created by kashishtayal on 2/28/17.
 */
public class NextGreaterElementII {
    public static int[] nextGreaterElements(int[] nums) {
        int[] maxIndexArray = new int[nums.length];
        Arrays.fill(maxIndexArray,-1);
        for(int i = 0; i < nums.length; i++){
            int j = (i+1)%(nums.length);
            while(j != i){
                if(nums[j] > nums[i]){
                    maxIndexArray[i] = j;
                    break;
                }else if(nums[j] == nums[i] && maxIndexArray[j] != -1){
                    maxIndexArray[i] = maxIndexArray[j];
                    break;
                }else if(nums[j] < nums[i]){
                    if(maxIndexArray[i] != -1){
                        if(nums[i] < nums[maxIndexArray[j]]){
                            maxIndexArray[i] = maxIndexArray[j];
                        }else{
                            j += maxIndexArray[j];
                        }
                    }else{
                        j++;
                    }
                }else{
                    j++;
                }
                j = j%nums.length;
            }
        }
        int[] result = new int[nums.length];
        for(int i = 0; i < maxIndexArray.length; i++){
            if(maxIndexArray[i] != -1) {
                result[i] = nums[maxIndexArray[i]];
            }else{
                result[i] = -1;
            }
        }
        return result;
    }

    public static int[] nextGreaterElementsI(int[] nums) {
        int[] nextElementIndex = new int[nums.length];
        Arrays.fill(nextElementIndex,-1);
        for(int i = nums.length-1; i > -1; i--){
            int j = (i+1)%nums.length;
            while(j != i){
                if(nums[j] > nums[i]){
                    nextElementIndex[i]  = j;
                    break;
                }else if(nums[j] == nums[i] && nextElementIndex[j] != -1){
                    nextElementIndex[i] = nextElementIndex[j];
                    break;
                }else if(nums[j] < nums[i] && nextElementIndex[j] != -1){
                    j = nextElementIndex[j];
                }else {
                    j++;
                }
                j = j%nums.length;
            }
        }
        int[] result = new int[nums.length];
        for(int i = 0; i < nextElementIndex.length; i++){
            if(nextElementIndex[i] != -1) {
                result[i] = nums[nextElementIndex[i]];
            }else{
                result[i] = -1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        nextGreaterElementsI(new int[]{1,2,1});
    }
}
