package LeetCode.prac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tayalka on 7/8/2016.
 */
public class KnapSack {
    static class Work{
        String id;
        int time;
        int value;

        public Work(String id, int time, int value){
            this.id = id;
            this.time = time;
            this.value= value;
        }
    }

    public static int findMax(List<Work> inList, int maxTime){
        if(inList.size() == 0 || maxTime <= 0) return 0;
        int max = 0;
        for(int i = 0; i < inList.size(); i++){
            List<Work> subList = new ArrayList<>(inList);
            subList.remove(inList.get(i));
            max = Math.max(findMax(subList,maxTime-inList.get(0).time)+inList.get(0).value, findMax(subList,maxTime));
        }
        return max;
    }
    static List<Work> arrayWork;
    public static int findMaxDp(int n, int S){
        int[][] array = new int[n+1][S+1];
        Arrays.fill(array[0],0);
        for(int i = 1 ; i < n+1; i++){
            Arrays.fill(array[i],-1);
            array[i][0] = 0;
        }
        for(int i = 1; i < n+1; i++){
            for(int j = 1; j < S+1; j++){
                int time = arrayWork.get(i-1).time;
                int val = arrayWork.get(i-1).value;
                if( time > j) array[i][j] = array[i-1][j];
                else array[i][j] = Math.max(val+array[i-1][j-time],array[i-1][j]);
            }
        }
        print(array);
        return array[n][S];
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
        List<Work> list = new ArrayList<>();
        list.add(new Work("A",3,7));
        list.add(new Work("B",4,9));
        list.add(new Work("C",2,5));
        list.add(new Work("D",6,12));
        list.add(new Work("E",7,14));
        list.add(new Work("F",3,6));
        list.add(new Work("G",5,12));
        System.out.println(findMax(list,15));
        arrayWork = list;
        System.out.println(findMaxDp(list.size(), 15));
    }
}
