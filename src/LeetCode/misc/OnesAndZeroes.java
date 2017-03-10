package LeetCode.misc;

/**
 * Created by tayalka on 3/9/2017.
 */
public class OnesAndZeroes {
    /*
    The end result will be max at some M,N combination
    so increment value (m,n) where we can go to using str starting from (m-zerocount,n-onecount)
     */
    public static int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m+1][n+1];
        int max = 0;
        for(String str : strs){
            int oneCount = 0;
            int zeroCount = 0;
            for (char c : str.toCharArray()) {
                if (c == '1') oneCount++;
                else zeroCount++;
            }
            for(int i = 0; i <= m-zeroCount; i++){
                for(int j = 0; j <= n-oneCount; j++){
                    dp[i][j] = Math.max(1+dp[i+zeroCount][j+oneCount],dp[i][j]);
                    max = Math.max(max,dp[i][j]);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"10", "0001", "111001", "1", "0"};
        System.out.println(findMaxForm(strs,5,3));
    }
}
