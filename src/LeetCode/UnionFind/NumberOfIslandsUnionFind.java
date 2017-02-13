package LeetCode.UnionFind;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by kashishtayal on 2/9/17.
 */
public class NumberOfIslandsUnionFind {
    Map<Integer,Integer> _locToIslandNumberMap;
    char[][] _grid;
    int cols;
    int rows;
    public int numIslands(char[][] grid) {
        if(grid.length == 0) return 0;
        _locToIslandNumberMap = new HashMap<>();
        _grid = grid;
        cols = _grid[0].length;
        rows = _grid.length;
        init();
        Set<Integer> visited = new HashSet<>();
        int count = 0;
        for(Integer loc : _locToIslandNumberMap.keySet()){
            if(!visited.contains(loc)){
                int r = loc/cols;
                int c = loc%cols;
                dfs(r,c,visited,_locToIslandNumberMap.get(loc));
                count++;
            }
        }

        return count;
    }
    private void dfs(int r, int c, Set<Integer> inVisitedLocs, int inParentIslandNumber){
        if(r < 0 || r >= rows || c < 0 || c >= cols){
            return;
        }
        int inParentLoc = r*cols+c;
        if(inVisitedLocs.contains(inParentLoc) || !_locToIslandNumberMap.containsKey(inParentLoc)){
            return;
        }
        inVisitedLocs.add(inParentLoc);
        _locToIslandNumberMap.put(inParentLoc,inParentIslandNumber);
        dfs(r-1,c,inVisitedLocs,inParentIslandNumber);
        dfs(r,c+1,inVisitedLocs,inParentIslandNumber);
        dfs(r+1,c,inVisitedLocs,inParentIslandNumber);
        dfs(r,c-1,inVisitedLocs,inParentIslandNumber);
    }
    private void init(){
        int islandCounter = 0;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(_grid[i][j] == '1'){
                    int loc = i*cols + j;
                    _locToIslandNumberMap.put(loc,islandCounter);
                    islandCounter++;
                }
            }
        }
    }

    public static void main(String[] args) {
        NumberOfIslandsUnionFind num = new NumberOfIslandsUnionFind();
        char[][] grid = new char[][]{
                "01001110000010000101".toCharArray(),
                "10100110010101011000".toCharArray(),
                "01000111100000111101".toCharArray(),
                "11000110001110010110".toCharArray(),
                "01011010001001000001".toCharArray(),
                "10010100011010010000".toCharArray(),
                "10001100000100100001".toCharArray(),
                "10001011101011110001".toCharArray(),
                "10010001000000000101".toCharArray(),
                "00010111111111000010".toCharArray(),
                "10101001110110011000".toCharArray(),
                "01001000000111100010".toCharArray(),
                "10001110100010101001".toCharArray(),
                "00001011010101111000".toCharArray(),
                "01100001001110011010".toCharArray(),
                "10111111011010010001".toCharArray(),
                "10001010010100100111".toCharArray(),
                "00100001001101110000".toCharArray(),
                "00100000011010100011".toCharArray(),
                "10001011100101011000".toCharArray()
        };
        System.out.println(num.numIslands(grid));
    }
}