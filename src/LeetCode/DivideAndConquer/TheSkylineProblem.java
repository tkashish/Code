package LeetCode.DivideAndConquer;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by kashishtayal on 1/20/17.
 */
public class TheSkylineProblem {
    public List<int[]> getSkyline(int[][] buildings) {
//        Arrays.sort(buildings, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                if(o1[0] < o2[0])return -1;
//                else if(o1[0] > o2[0])return 1;
//                else return 0;
//            }
//        });
        List<int[]> skyline = new ArrayList<>();
        TreeMap<Integer, int[]> rightWithLeftFinishedMap = new TreeMap<>();
        int buildingIndex = 0;
        while(buildingIndex < buildings.length || !rightWithLeftFinishedMap.isEmpty()){
            int[] keyPoint = null;
            int[] curr = null;
            int[] building = null;
            if(buildingIndex < buildings.length) building = buildings[buildingIndex];
            boolean isCurrStart = true;
            boolean isRightEdgeBigger = false;
            int[] startedBuilding = null;
            if(rightWithLeftFinishedMap.size() > 0){
                int right = rightWithLeftFinishedMap.firstKey();
                startedBuilding = rightWithLeftFinishedMap.get(right);
                if(building != null && building[0] < startedBuilding[1]){
                    curr = building;
                    buildingIndex++;
                }else if(building == null || building[0] > startedBuilding[1]){
                    curr = startedBuilding;
                    isCurrStart = false;
                    rightWithLeftFinishedMap.remove(right);
                }else{
                    curr = building;
                    buildingIndex++;
                    if(building[2] < startedBuilding[2]){
                        isRightEdgeBigger = true;
                    }
                    rightWithLeftFinishedMap.remove(right);
                }
            }else{
                curr = buildings[buildingIndex];
                buildingIndex++;
            }
            int[] currCopy = curr;
            /**
             * do some logic
             */
            boolean foundEq = false;
            boolean foundGr = false;
            for(int[] e : rightWithLeftFinishedMap.values()){
                if(e[2] > curr[2]){
                    foundGr = true;
                    if(isCurrStart && isRightEdgeBigger){
                        if(startedBuilding[2] > e[2]){
                            curr[2] = e[2];
                            foundGr = false;
                        }
                    }
                    if(foundGr) break;
                }else if (e[2] < curr[2]){
                    if(!isCurrStart){
                        if(keyPoint == null) {
                            keyPoint = new int[2];
                            keyPoint[0] = curr[1];
                            keyPoint[1] = e[2];
                        }else{
                            keyPoint[1] = Math.max(keyPoint[1], e[2]);
                        }
                    }
                    continue;
                }else{
                    foundEq = true;
                }
            }
            if(!foundGr){
                if(foundEq){
                    if(isCurrStart && isRightEdgeBigger){
                        keyPoint = new int[2];
                        keyPoint[0] = curr[0];
                        keyPoint[1] = curr[2];
                    }else{
                        keyPoint = null;
                    }
                }else{
                    if(isCurrStart){
                        keyPoint = new int[2];
                        keyPoint[0] = curr[0];
                        keyPoint[1] = curr[2];
                    }else if(!isCurrStart && rightWithLeftFinishedMap.isEmpty()){
                        keyPoint = new int[2];
                        keyPoint[0] = curr[1];
                    }
                }
            }else{
                keyPoint = null;
            }
            if(keyPoint != null){
                if(skyline.isEmpty() || skyline.get(skyline.size()-1)[1] != keyPoint[1]){
                    if(!skyline.isEmpty() && skyline.get(skyline.size()-1)[0] == keyPoint[0]){
                        skyline.set(skyline.size()-1,keyPoint);
                    }else {
                        skyline.add(keyPoint);
                    }
                }
            }
            /**
             * if !isCurrStart then the right end of a previously started building is removed
             * so remove the first entry from the rightWithLeftFinishedMap
             * is isCurrStart add right end to the map
             */
            if(isCurrStart){
                if(rightWithLeftFinishedMap.containsKey(currCopy[1])){
                    if(rightWithLeftFinishedMap.get(currCopy[1])[2] < currCopy[2]){
                        rightWithLeftFinishedMap.put(currCopy[1], currCopy);
                    }
                }else {
                    rightWithLeftFinishedMap.put(currCopy[1], currCopy);
                }
            }
        }
        return skyline;
    }

    public static void main(String[] args) {
        int[][] buildings = new int[][]{
                 new int[]{1, 2, 1},
                 new int[]{1, 2, 2},
                 new int[]{1, 2, 3}
        };
        TheSkylineProblem prob = new TheSkylineProblem();
        prob.getSkyline(buildings);
    }
}
