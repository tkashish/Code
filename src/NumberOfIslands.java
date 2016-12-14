import java.util.Arrays;

/**
 * 200 https://leetcode.com/problems/number-of-islands/
 */
public class NumberOfIslands {
    public static int numIslands(char[][] grid) {
        int x = grid.length;
        if(x == 0)return 0;
        int y = grid[0].length;
        char counter = 'A';
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                if(grid[i][j] == '1'){
                    grid[i][j]= counter;
                    fillAdjacent(grid,i,j,counter);
                    counter++;
                }
            }
        }
        return counter-'A';
    }
    static void fillAdjacent(char[][] grid, int x, int y, char counter){
        if(x-1>=0 && grid[x-1][y] == '1'){
            grid[x-1][y]= counter;
            fillAdjacent(grid,x-1,y, counter);
        }
        if(y-1 >= 0 && grid[x][y-1] == '1'){
            grid[x][y-1]= counter;
            fillAdjacent(grid,x,y-1, counter);
        }
        if(x+1 < grid.length && grid[x+1][y] == '1'){
            grid[x+1][y]= counter;
            fillAdjacent(grid,x+1,y,counter);
        }
        if(y+1 < grid[0].length && grid[x][y+1] == '1'){
            grid[x][y+1]= counter;
            fillAdjacent(grid,x,y+1,counter);
        }
    }
    static void print(char[][] grid){
        System.out.println("---------------");
        for(int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                System.out.print(grid[i][j] + " ");
            }
            System.out.println("");
        }
    }
    public static void main(String[] args) {
        char[][] grid = new char[][]{{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','1','0'}};
        print(grid);
        System.out.println(numIslands(grid));
    }
}
