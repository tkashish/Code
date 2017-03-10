package LeetCode.misc;

import java.util.Arrays;

/**
 * Created by tayalka on 3/9/2017.
 */
public class Heaters {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < houses.length; i++){
            int min = Math.abs(houses[i]-find(houses[i],heaters));
            max = Math.max(max,min);
        }
        return max;
    }

    private int find(int houseIndex, int[] heaters){
        int start = 0;
        int end = heaters.length-1;

        while(start <= end){
            int mid = start + (end-start)/2;
            if(houseIndex == heaters[mid]){
                return heaters[mid];
            }else if(houseIndex > heaters[mid]){
                start = mid+1;
            }else{
                end = mid-1;
            }
        }
        if(end < 0) return heaters[start];
        if(start >= heaters.length) return heaters[end];
        return Math.abs(houseIndex-heaters[start]) > Math.abs(houseIndex - heaters[end]) ? heaters[end] : heaters[start];
    }

    public static void main(String[] args) {
        Heaters heaters = new Heaters();
        System.out.println(heaters.findRadius(new int[]{1,2,3,4}, new int[]{1,4}));
    }
}
