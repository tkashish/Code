package LeetCode.DynamicPrograming;

import java.util.*;

/**
 * Created by kashishtayal on 11/9/16.
 */
public class LargestDivisibleSubset {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Set<List<Integer>> set = new HashSet<>();
        List<Integer> result = new ArrayList<>();
        int max = 0;
        for(int i = 0; i < nums.length; i++){
            int num = nums[i];
            Set<List<Integer>> next = new HashSet<>();
            for(List<Integer> list : set){
                if(canAddToList(list,num)){
                    List<Integer> newList = new ArrayList<>(list);
                    newList.add(num);
                    next.add(newList);
                    if(newList.size() > max){
                        max = newList.size();
                        result = newList;
                    }
                }
            }
            List<Integer> individualList = new ArrayList<>();
            individualList.add(num);
            if(individualList.size() > max){
                max = individualList.size();
                result = individualList;
            }
            set.add(individualList);
            set.addAll(next);
        }
        return result;
    }
    public boolean canAddToList(List<Integer> list, int num){
        return !list.stream().filter(i->(i%num != 0 && num%i != 0)).findFirst().isPresent();
    }

    public List<Integer> largestDivisibleSubsetI(int[] nums) {
        Arrays.sort(nums);
        List<Integer>[] dp = new List[nums.length+1];
        dp[0] = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        int maxInResult = 0;
        for(int i = 1; i < nums.length+1 ; i++){
            int num = nums[i-1];
            dp[i] = new ArrayList<>();
            dp[i].add(num);
            if(maxInResult > 0 && num % maxInResult == 0){
                dp[i].addAll(result);
                result = dp[i];
                maxInResult = num;
                continue;
            }
            for (int j = i - 1; j > 0; j--) {
                if (num % nums[j-1] == 0) {
                    List<Integer> curr = dp[j];
                    dp[i].addAll(curr);
                    break;
                }
            }
            if(dp[i].size() >= result.size()){
                maxInResult = num;
                result = dp[i];
            }

        }
        return result;
    }

    public static void main(String[] args) {
        LargestDivisibleSubset lds = new LargestDivisibleSubset();
        int[] in = new int[]{1,2,4,8,9,72};
        System.out.println(lds.largestDivisibleSubset(in));
        System.out.println(lds.largestDivisibleSubsetI(in));
    }
}
