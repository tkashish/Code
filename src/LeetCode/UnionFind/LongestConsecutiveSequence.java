package LeetCode.UnionFind;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by kashishtayal on 2/9/17.
 */
public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> _bucketMap = new HashMap<>();
        Set<Integer> listEnderSet = new HashSet<>();
        for(int num : nums){
            if(_bucketMap.containsKey(num)){
                continue;
            }
            boolean containsPrev = _bucketMap.containsKey(num-1);
            boolean containsNext = _bucketMap.containsKey(num+1);
            if(containsNext && containsPrev){
                _bucketMap.put(num,num-1);
                _bucketMap.put(num+1,num);
                listEnderSet.remove(num-1);
            }else if(containsNext){
                _bucketMap.put(num, num);
                _bucketMap.put(num+1,num);
            }else if(containsPrev){
                _bucketMap.put(num,num-1);
                listEnderSet.remove(num-1);
                listEnderSet.add(num);
            }else{
                _bucketMap.put(num,num);
                listEnderSet.add(num);
            }
        }
        int count = 0;
        for(int end : listEnderSet){
            int currCount = 1;
            int prev = end;
            while((prev = _bucketMap.get(end)) != end){
                currCount++;
                end = prev;
            }
            count = Math.max(count,currCount);
        }
        return count;
    }

    public static void main(String[] args) {
        LongestConsecutiveSequence lcs = new LongestConsecutiveSequence();
        System.out.println(lcs.longestConsecutive(new int[]{11, 4, 12, 1, 3,14, 2,0,-1,13}));
    }
}
