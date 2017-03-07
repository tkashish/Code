package LeetCode.misc;

import java.util.*;

/**
 * Created by kashishtayal on 2/19/17.
 */
public class ThreeSumSmaller {
    public static int threeSumSmaller(int[] nums, int target) {
        int count = 0;
        Map<Integer, List<List<Integer>>> currSum = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            int num = nums[i];
            Map<Integer, List<List<Integer>>> temp = new HashMap<>();
            List<List<Integer>> numList = new ArrayList<>();
            List<Integer> numSubList = new ArrayList<>();
            numSubList.add(num);
            numList.add(numSubList);
            temp.put(num,numList);
            for(Map.Entry<Integer, List<List<Integer>>> entry : currSum.entrySet()){
                int sum = entry.getKey();
                List<List<Integer>> indexeList = entry.getValue();
                int nextSum = sum+num;
                List<List<Integer>> newNextSumList = new ArrayList<>();
                for(List<Integer> indexs: indexeList){
                    if(indexs.size() == 2){
                        if(nextSum < target){
                            System.out.println(indexs+" "+num);
                            count++;
                        }
                    }else{
                        List<Integer> subList = new ArrayList<>(indexs);
                        subList.add(num);
                        newNextSumList.add(subList);
                    }
                }
                if(temp.containsKey(nextSum)){
                    temp.get(nextSum).addAll(newNextSumList);
                }else {
                    temp.put(nextSum, newNextSumList);
                }
            }
            for(int sum : temp.keySet()){
                if(currSum.containsKey(sum)){
                    List<List<Integer>> list = currSum.get(sum);
                    list.addAll(temp.get(sum));
                }else{
                    currSum.put(sum,temp.get(sum));
                }
            }
        }
        return count;
    }

    public static int threeSumSmallerI(int[] nums, int target){
        Arrays.sort(nums);
        int count = 0;
        for(int i = 0; i < nums.length; i++ ){
            int num = nums[0];
            int l = i+1;
            int h = nums.length-1;
            while(l < h){
                if(nums[i] + nums[l] + nums[h] < target){
                    count+= h-l;
                    l++;
                }else{
                    h--;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,0,0,2,-2};
        System.out.println(threeSumSmallerI(nums,2));
    }
}
