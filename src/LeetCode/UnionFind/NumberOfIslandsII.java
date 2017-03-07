package LeetCode.UnionFind;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by kashishtayal on 2/10/17.
 */
public class NumberOfIslandsII {
    Integer[][] map;
    int maxRows;
    int maxCols;
    private static final int HEAD = -1;
    private int count = 0;
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        maxRows = m;
        maxCols = n;
        map = new Integer[m][n];
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < positions.length; i++){
            int row = positions[i][0];
            int col = positions[i][1];
            map[row][col] = HEAD;
            connect(row,col);
            result.add(count);
        }
        return result;
    }
    private void connect(int row, int col){
        int pos = row*maxCols+col;
        count++;
        Set<Integer> visitedHead = new HashSet<>();
        visitedHead.add(pos);
        if(row-1 > -1 && map[row-1][col] != null){
            findHeadAndSet(row-1,col,visitedHead,pos);
        }
        if(row+1 < maxRows && map[row+1][col] != null){
            findHeadAndSet(row+1,col,visitedHead,pos);
        }
        if(col+1 < maxCols && map[row][col+1] != null){
            findHeadAndSet(row,col+1,visitedHead,pos);
        }
        if(col-1 > -1 && map[row][col-1] != null){
            findHeadAndSet(row,col-1,visitedHead,pos);
        }
    }
    private void findHeadAndSet(int row, int col, Set<Integer> visited, int val){
        int curr = map[row][col];
        while(curr != HEAD){
            row = curr/maxCols;
            col = curr%maxCols;
            curr = map[row][col];
        }
        if(!visited.contains(row*maxCols+col)){
            map[row][col] = val;
            count--;
        }
    }
    public static void main(String[] args) {
        int[][] positions = new int[][]{
                new int[]{0,1},
                new int[]{1,2},
                new int[]{2,1},
                new int[]{1,0},
                new int[]{0,2},
                new int[]{0,0},
                new int[]{1,1}
        };
        NumberOfIslandsII num = new NumberOfIslandsII();
        System.out.println(num.numIslands2(3,3,positions));
    }
}
