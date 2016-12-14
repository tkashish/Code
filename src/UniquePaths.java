import java.util.Arrays;

/**
 * 62  https://leetcode.com/problems/unique-paths/
 */
public class UniquePaths {
    public static int uniquePaths(int m, int n) {
        int[][] sq = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 && j == 0) sq[i][j] = 1;
                if(i-1 >=0)sq[i][j]+=sq[i-1][j];
                if(j-1 >=0)sq[i][j]+=sq[i][j-1];
            }
        }
        print(sq);
        return sq[m-1][n-1];
    }
    static void print(int[][] grid){
        for(int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                System.out.print(grid[i][j] + " ");
            }
            System.out.println("");
        }
    }
    public static void main(String[] args) {
        System.out.println(uniquePaths(10,10));
    }
}
