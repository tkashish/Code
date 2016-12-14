package LeetCode.DynamicPrograming;

/**
 * Created by tayalka on 11/3/2016.
 */
public class UniquePath {
    public int uniquePaths(int m, int n) {
        int[][] uniquePaths = new int[m][n];
        for(int i = m-1; i>-1; i--){
            uniquePaths[i][n-1] = 1;
        }
        for(int i = n-1; i>-1; i--){
            uniquePaths[m-1][i] = 1;
        }
        for (int i = m-2; i > -1 ; i--) {
            for (int j = n-2; j > -1 ; j--) {
                uniquePaths[i][j] = uniquePaths[i][j+1] + uniquePaths[i+1][j];
            }
        }
        return uniquePaths[0][0];
    }

}
