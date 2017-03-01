package LeetCode.misc;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by kashishtayal on 2/28/17.
 */
public class RelativeRanks {
    public static String[] findRelativeRanks(int[] nums) {
        String[] result = new String[nums.length];
        PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] < o2[1])return 1;
                else if(o1[1] > o2[1])return -1;
                else return 0;
            }
        });
        for(int i = 0; i < nums.length; i++){
            int[] curr = new int[]{i,nums[i]};
            heap.add(curr);
        }
        int rank = 1;
        while(!heap.isEmpty()){
            int[] curr = heap.remove();
            if(rank == 1){
                result[curr[0]] = "Gold Medal";
            }else if(rank == 2){
                result[curr[0]] = "Silver Medal";
            }else if(rank == 3){
                result[curr[0]] = "Bronze Medal";
            }else{
                result[curr[0]] = String.valueOf(rank);
            }
            rank++;
        }
        return result;
    }

    public static void main(String[] args) {
        findRelativeRanks(new int[]{5,4,3,2,1});
    }
}
