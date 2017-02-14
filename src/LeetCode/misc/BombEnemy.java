package LeetCode.misc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kashishtayal on 2/13/17.
 */
public class BombEnemy {
    private int[][] rowSum;
    private int[][] colSum;
    private char[][] grid;
    private int rows;
    private int cols;
    public int maxKilledEnemies(char[][] inGrid) {
        grid = inGrid;
        rows = grid.length;
        cols = 0;
        if(rows > 0){
            cols = grid[0].length;
        }
        rowSum = new int[rows][cols];
        colSum = new int[rows][cols];
        int result = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(grid[i][j] == '0'){
                    int curr = getFatalities(i,j);
                    result = Math.max(result,curr);
                }
            }
        }
        return result;
    }

    private int getFatalities(int row, int col){
        int rSum = rowSum[row][col];
        int cSum = colSum[row][col];
        if(rSum == 0){
            List<Integer> rowIndexWithInWalls = new ArrayList<>();
            for(int i = row+1; i < rows; i++){
                if(grid[i][col] == 'W'){
                    break;
                }else if(grid[i][col] == 'E'){
                    rSum++;
                }else{
                    rowIndexWithInWalls.add(i);
                }
            }
            for(int i = row-1; i > -1; i--){
                if(grid[i][col] == 'W'){
                    break;
                }else if(grid[i][col] == 'E'){
                    rSum++;
                }else{
                    rowIndexWithInWalls.add(i);
                }
            }
            for(int rIndex : rowIndexWithInWalls){
                rowSum[rIndex][col] = rSum == 0 ? -1 : rSum;
            }
        }else{
            rSum = (rSum == -1) ? 0 : rSum;
        }
        if(cSum == 0){
            List<Integer> colIndexWithInWalls = new ArrayList<>();
            for(int i = col+1; i < cols; i++){
                if(grid[row][i] == 'W'){
                    break;
                }else if(grid[row][i] == 'E'){
                    cSum++;
                }else{
                    colIndexWithInWalls.add(i);
                }
            }
            for(int i = col-1; i > -1; i--){
                if(grid[row][i] == 'W'){
                    break;
                }else if(grid[row][i] == 'E'){
                    cSum++;
                }else{
                    colIndexWithInWalls.add(i);
                }
            }
            for(int cIndex : colIndexWithInWalls){
                colSum[row][cIndex] = cSum == 0 ? -1 : cSum;
            }
        }else{
            cSum = (cSum == -1) ? 0 : cSum;
        }
        return rSum+cSum;
    }

    public static void main(String[] args) {
        char[][] grid = new char[][]{
                "0E00".toCharArray(),
                "E0WE".toCharArray(),
                "0E00".toCharArray()
        };
        BombEnemy be = new BombEnemy();
        System.out.println(be.maxKilledEnemies(grid));
    }
}
