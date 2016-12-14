package LeetCode.DynamicPrograming;

/**
 * Created by tayalka on 11/3/2016.
 */
public class MinPathSum {
    public int minPathSum(int[][] grid) {
        int len = grid.length;
        int wid = grid[0].length;
        for(int i = len-2; i > -1; i--){
            grid[i][wid-1] += grid[i+1][wid-1];
        }
        for(int i = wid-2; i > -1; i--){
            grid[len-1][i] += grid[len-1][i+1];
        }
        for (int i = len-2; i > -1 ; i--) {
            for (int j = wid-2; j > -1 ; j--) {
                grid[i][j] += Math.min(grid[i+1][j], grid[i][j+1]);
            }
        }
        return grid[0][0];
    }

    public static void main(String[] args) {
        int[][] nums = new int[][]{
          new int[]{1,2,3},
          new int[]{4,5,6},
          new int[]{7,8,9}
        };
        MinPathSum mps = new MinPathSum();
        System.out.println(mps.minPathSum(nums));
    }
}
