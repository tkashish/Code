package LeetCode.DynamicPrograming;

import java.util.*;

/**
 * Created by kashishtayal on 12/8/16.
 */
public class AndroidUnlockPatterns {
    Map<Integer,Set<Integer>> adjList;
    private void init(){
        adjList = new HashMap<>();
        adjList.put(1,new HashSet<Integer>(Arrays.asList(2,4,5)));
        adjList.put(2,new HashSet<Integer>(Arrays.asList(5)));
        adjList.put(3,new HashSet<Integer>(Arrays.asList(2,5,6)));
        adjList.put(4,new HashSet<Integer>(Arrays.asList(5)));
        adjList.put(5,new HashSet<Integer>(Arrays.asList()));
        adjList.put(6,new HashSet<Integer>(Arrays.asList(5)));
        adjList.put(7,new HashSet<Integer>(Arrays.asList(4,5,8)));
        adjList.put(8,new HashSet<Integer>(Arrays.asList(5)));
        adjList.put(9,new HashSet<Integer>(Arrays.asList(5,6,8)));
    }
    public int numberOfPatterns(int m, int n) {
        init();
        int count1 = 0;
        int count2 = 0;
        int count5 = 0;
        for(int i = m; i <= n; i++) {
            count1+= dfs(1,i-1,new HashSet<>(Arrays.asList(1)));
            count2+= dfs(2,i-1,new HashSet<>(Arrays.asList(2)));
            count5+= dfs(5,i-1,new HashSet<>(Arrays.asList(5)));
        }
        return count1*4+count2*4+count5;
    }
    private int dfs(int start, int itr, Set<Integer> explored){
        if(itr == 0)return 1;
        int ways = 0;
        for(int i = 1; i < 10; i++){
            if(explored.contains(i))continue;
            Set<Integer> newExplored = new HashSet<>(explored);
            newExplored.add(i);
            Set<Integer> wall = adjList.get(start);
            int mid = (start + i) / 2;
            if((start+i)%2 == 0 && wall.contains(mid)){
                if(explored.contains(mid)) {
                    ways+=dfs(i,itr-1,newExplored);
                }
            }else{
                ways += dfs(i,itr-1, newExplored);
            }
        }
        return ways;
    }

    public static void main(String[] args) {
        AndroidUnlockPatterns unlockPatterns = new AndroidUnlockPatterns();
//        System.out.println(unlockPatterns.numberOfPatterns(1,1) == 9);
//        System.out.println(unlockPatterns.numberOfPatterns(2,2) == 56);
//        System.out.println(unlockPatterns.numberOfPatterns(1,2) == 65);
//        System.out.println(unlockPatterns.numberOfPatterns(3,3) == 320);
        System.out.println(unlockPatterns.numberOfPatterns(4,4) == 320);
    }
}
