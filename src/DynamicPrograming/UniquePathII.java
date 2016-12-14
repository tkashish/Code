package DynamicPrograming;

/**
 * Created by tayalka on 11/3/2016.
 */
public class UniquePathII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if(obstacleGrid[m-1][n-1] == 1 || obstacleGrid[0][0] == 1) return 0;
        int[][] uniquePaths = new int[m][n];
        uniquePaths[m-1][n-1] = 1;
        for (int i = m - 2; i > -1; i--) {
            if(obstacleGrid[i+1][n-1] == 0) {
                uniquePaths[i][n - 1] = uniquePaths[i+1][n-1];
            }else{
                uniquePaths[i][n - 1] = 0;
            }
        }
        for (int i = n - 2; i > -1; i--) {
            if(obstacleGrid[m-1][i+1] == 0) {
                uniquePaths[m - 1][i] = uniquePaths[m - 1][i + 1];
            }else{
                uniquePaths[m - 1][i] = 0 ;
            }
        }
        for (int i = m - 2; i > -1; i--) {
            for (int j = n - 2; j > -1; j--) {
                if(obstacleGrid[i][j+1] == 0) {
                    uniquePaths[i][j] += uniquePaths[i][j + 1];
                }
                if(obstacleGrid[i+1][j] == 0) {
                    uniquePaths[i][j] += uniquePaths[i+1][j];
                }
            }
        }
        return uniquePaths[0][0];
    }
}
