package LeetCode.DynamicPrograming;

import java.util.Arrays;

/**
 * Created by tayalka on 3/20/2017.
 */
public class DistinctSubsequences {
    public static int numDistinct(String s, String t) {
        int[] prev = new int[s.length()+1];
        Arrays.fill(prev,1);
        for(int i = 0; i < t.length(); i++){
            int[] curr = new int[s.length()+1];
            int count = 0;
            for(int j = 0; j < s.length(); j++) {
                curr[j + 1] = count;
                if(t.charAt(i) == s.charAt(j) && prev[j] > 0){
                    curr[j+1] += prev[j];
                }
                count = curr[j+1];
            }
            prev = curr;
        }
        return prev[s.length()];
    }

    public static void main(String[] args) {
        System.out.println(numDistinct("rabbbit", "rabbt"));
    }
}
