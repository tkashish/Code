package LeetCode.misc;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by kashishtayal on 2/19/17.
 */
public class WiggleSort {
    public static void wiggleSort(int[] nums) {
        for(int i = 1; i < nums.length; i++){
            if((i%2 == 0 && nums[i-1] > nums[i]) || (i%2 == 1 && nums[i-1] < nums[i])){
                int  temp = nums[i-1];
                nums[i-1] = nums[i];
                nums[i] = temp;
            }
        }
    }
    public static void wiggleSortI(int[] nums) {
        Arrays.sort(nums);
        Deque<Integer> dq = new LinkedList<>();
        for(int num : nums){
            dq.add(num);
        }
        boolean first = true;
        int i = 0;
        while(!dq.isEmpty()){
            if(first){
                nums[i] = dq.removeFirst();
                first = false;
            }else{
                nums[i] = dq.removeLast();
                first = true;
            }
            i++;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 5, 2, 1, 6, 4};
        WiggleSort.wiggleSort(nums);
        Arrays.stream(nums).forEach(System.out::println);
    }
}
