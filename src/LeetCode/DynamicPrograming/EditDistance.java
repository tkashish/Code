package LeetCode.DynamicPrograming;

import java.util.Arrays;

/**
 * Created by kashishtayal on 12/9/16.
 */
public class EditDistance {
    /*
    from word 1 to word 2
     */
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1+1][len2+1];
        for(int i = 0; i < len2+1; i++){
            dp[0][i] = i;
        }
        for(int i = 0; i < len1+1; i++){
            dp[i][0] = i;
        }
        for(int i = 1; i < len1+1; i++){
            for(int j = 1; j < len2+1; j++){
                int convertedFullAtLastChar = dp[i-1][j]+1;
                int convertLastAtLastChar = dp[i-1][j-1];
                if(word1.charAt(i-1) != word2.charAt(j-1)){
                    convertLastAtLastChar+=1;
                }
                int definiteInsert = dp[i][j-1]+1;
                dp[i][j] = min(convertedFullAtLastChar,convertLastAtLastChar,definiteInsert);
            }
        }
        return dp[len1][len2];
    }
    private int min(int...nums){
        int min = Integer.MAX_VALUE;
        for(int i : nums){
            min = Math.min(min,i);
        }
        return min;
    }

    public static void main(String[] args) {
        EditDistance ed = new EditDistance();
//        System.out.println(ed.minDistance("tayal","kashish"));
        System.out.println(ed.minDistance("tayal","tayal"));
    }
}
