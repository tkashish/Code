package LeetCode.misc;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * do BFS from all the empty lots
 * Created by tayalka on 2/14/2017.
 */
public class ShortestDistanceFromAllBuildings {
    int ROWS;
    int COLS;
    int numOfBuildings;
    public int shortestDistance(int[][] grid) {
        ROWS = grid.length;
        if(ROWS > 0){
            COLS = grid[0].length;
        }
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if(grid[i][j] == 1){
                    numOfBuildings++;
                }
            }
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (grid[i][j] == 0) {
                    int subResult = getShortest(grid,i,j);
                    if(subResult > 0)result = Math.min(result,subResult);
                }
            }
        }

        return (result == Integer.MAX_VALUE)? -1 : result;
    }

    public int getShortest(int[][] grid , int row, int col){
        int totalDistanceSum = 0;
        Queue<Integer> queue = new LinkedList<>();
        int inLoc = row* COLS + col;
        queue.add(inLoc);
        queue.add(null);
        int distance = 1;
        Set<Integer> visited = new HashSet<>();
        int buildingsVisited = 0;
        while(queue.size() > 1){
            if(queue.peek() == null){
                queue.remove();
                distance++;
                queue.add(null);
            }else {
                int curr = queue.remove();
                int r = curr / COLS;
                int c = curr % COLS;
                visited.add(curr);
                if(r-1 > -1){
                    int nextLoc = (r - 1) * COLS + c;
                    if (!visited.contains(nextLoc)) {
                        if (grid[r - 1][c] == 1) {
                            totalDistanceSum += distance;
                            visited.add(nextLoc);
                            buildingsVisited++;
                        } else if (grid[r - 1][c] == 0) {
                            queue.add(nextLoc);
                        }
                    }
                }
                if(r+1 < ROWS){
                    int nextLoc = (r + 1) * COLS + c;
                    if (!visited.contains(nextLoc)) {
                        if (grid[r + 1][c] == 1) {
                            totalDistanceSum += distance;
                            visited.add(nextLoc);
                            buildingsVisited++;
                        } else if (grid[r + 1][c] == 0) {
                            queue.add(nextLoc);
                        }
                    }
                }
                if(c-1 > -1){
                    int nextLoc = (r) * COLS + c-1;
                    if (!visited.contains(nextLoc)) {
                        if (grid[r][c - 1] == 1) {
                            totalDistanceSum += distance;
                            visited.add(nextLoc);
                            buildingsVisited++;
                        } else if (grid[r][c - 1] == 0) {
                            queue.add(nextLoc);
                        }
                    }
                }
                if(c+1 < COLS){
                    int nextLoc = (r) * COLS + c+1;
                    if(!visited.contains(nextLoc)){
                        if(grid[r][c+1] == 1){
                            totalDistanceSum += distance;
                            visited.add(nextLoc);
                            buildingsVisited++;
                        }else if(grid[r][c+1] == 0){
                            queue.add(nextLoc);
                        }
                    }
                }
            }
            if(buildingsVisited == numOfBuildings) return totalDistanceSum;
        }
        return buildingsVisited == numOfBuildings ? totalDistanceSum : -1;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                new int[]{1, 1, 1, 1, 1, 0},
                new int[]{0, 0, 0, 0, 0, 1},
                new int[]{0, 1, 1, 0, 0, 1},
                new int[]{1, 0, 0, 1, 0, 1},
                new int[]{1, 0, 1, 0, 0, 1},
                new int[]{1, 0, 0, 0, 0, 1},
                new int[]{0, 1, 1, 1, 1, 0}
        };
        ShortestDistanceFromAllBuildings sd = new ShortestDistanceFromAllBuildings();
        System.out.println(sd.shortestDistance(grid));
    }
}