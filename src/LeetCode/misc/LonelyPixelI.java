package LeetCode.misc;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by tayalka on 3/6/2017.
 */
public class LonelyPixelI {
    public static int findLonelyPixel(char[][] picture) {
        int ROWS = picture.length;
        if(ROWS == 0) return 0;
        int COLS = picture[0].length;
        int[] rowSum = new int[ROWS];
        int[] colSum = new int[COLS];
        Set<Integer> bLocs = new HashSet<>();
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                if(picture[i][j] == 'B'){
                    bLocs.add(i*COLS+j);
                    rowSum[i]++;
                    colSum[j]++;
                }
            }
        }
        int count = 0;
        for(int bloc : bLocs){
            int r = bloc/COLS;
            int c = bloc%COLS;
            if(rowSum[r] == 1 && colSum[c] == 1){
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        char[][] picture = new char[][]{
                "WWB".toCharArray(),
                "WBW".toCharArray(),
                "BWB".toCharArray()
        };
        System.out.println(findLonelyPixel(picture));
    }
}
