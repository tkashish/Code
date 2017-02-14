package LeetCode.misc;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * BFS from buildings
 * Created by tayalka on 2/14/2017.
 */
public class ShortestDistanceFromAllBuildingsI {
    int[][] buildingsVisited;
    int[][] distanceFromBuildings;
    int rows;
    int cols;
    public int shortestDistance(int[][] grid) {
        rows = grid.length;
        cols = 0;
        if(rows > 0)cols = grid[0].length;
        buildingsVisited = new int[rows][cols];
        distanceFromBuildings = new int[rows][cols];
        int totalBuildings = 0;
        int result = Integer.MAX_VALUE;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(grid[i][j] == 1){
                    totalBuildings++;
                    searchAllReachableEmptyLotFrom(i,j,grid);
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0 && buildingsVisited[i][j]==totalBuildings) {
                    result = Math.min(result,distanceFromBuildings[i][j]);
                }
            }
        }
        return (result == Integer.MAX_VALUE || result == 0) ? -1 : result;
    }

    private void searchAllReachableEmptyLotFrom(int row, int col, int[][] grid){
        int buildingLocation = row*cols+col;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(buildingLocation);
        queue.add(null);
        int distance = 1;
        Set<Integer> visited = new HashSet<>();
        visited.add(buildingLocation);
        while(queue.size() > 1){
            if(queue.peek() == null){
                queue.remove();
                distance++;
                queue.add(null);
            }else{
                int currLoc = queue.remove();
                int r = currLoc/cols;
                int c = currLoc%cols;
                if(r-1 > -1 && !visited.contains((r-1)*cols + c) && grid[r-1][c] == 0){
                    visited.add((r - 1) * cols + c);
                    distanceFromBuildings[r - 1][c] += distance;
                    buildingsVisited[r-1][c]++;
                    queue.add((r - 1) * cols + c);
                }
                if(r+1 < rows && !visited.contains((r+1)*cols + c) && grid[r+1][c] == 0){
                    visited.add((r + 1) * cols + c);
                    distanceFromBuildings[r + 1][c] += distance;
                    buildingsVisited[r + 1][c]++;
                    queue.add((r + 1) * cols + c);
                }
                if(c+1 < cols && !visited.contains((r)*cols + c+1) && grid[r][c+1] == 0){
                    visited.add((r) * cols + c+1);
                    distanceFromBuildings[r][c+1] += distance;
                    buildingsVisited[r][c + 1]++;
                    queue.add((r) * cols + c+1);
                }
                if (c - 1 > -1 && !visited.contains((r) * cols + c-1) && grid[r][c-1] == 0) {
                    visited.add((r) * cols + c-1);
                    distanceFromBuildings[r][c-1] += distance;
                    buildingsVisited[r][c - 1]++;
                    queue.add((r) * cols + c-1);
                }
            }
        }
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
        ShortestDistanceFromAllBuildingsI sd = new ShortestDistanceFromAllBuildingsI();
        System.out.println(sd.shortestDistance(grid));
    }

}
