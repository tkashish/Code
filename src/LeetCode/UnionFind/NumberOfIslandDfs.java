package LeetCode.UnionFind;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by kashishtayal on 2/9/17.
 */
public class NumberOfIslandDfs {
    int rows;
    int cols;
    Set<Integer> locs;
    public int numIslands(char[][] grid) {
        rows = grid.length;
        if(rows == 0) return 0;
        cols = grid[0].length;
        locs = new HashSet<>();
        init(grid);
        int count = 0;
        Set<Integer> visited = new HashSet<>();
        for(Integer loc : locs){
            if(!visited.contains(loc)){
                dfs(loc/cols,loc%cols,visited);
                count++;
            }
        }
        return count;
    }
    private void dfs(int r, int c, Set<Integer> inVisitedLocs){
        int loc = r*cols+c;
        if(r < 0 || r >= rows || c < 0 || c >= cols || inVisitedLocs.contains(loc) || !locs.contains(loc)){
            return;
        }
        inVisitedLocs.add(loc);
        dfs(r-1,c,inVisitedLocs);
        dfs(r+1,c,inVisitedLocs);
        dfs(r,c-1,inVisitedLocs);
        dfs(r,c+1,inVisitedLocs);
    }
    private void init(char[][] grid){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(grid[i][j] == '1'){
                    int loc = i*cols + j;
                    locs.add(loc);
                }
            }
        }
    }

    public static void main(String[] args) {
        NumberOfIslandDfs num = new NumberOfIslandDfs();
        char[][] grid = new char[][]{
                "11000".toCharArray(),
                "11000".toCharArray(),
                "00100".toCharArray(),
                "00011".toCharArray(),
        };
        System.out.println(num.numIslands(grid));
    }
}
