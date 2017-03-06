package LeetCode.misc;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by tayalka on 3/6/2017.
 */
public class LonelyPixelII {
    public static int findBlackPixel(char[][] picture, int N) {
        int ROWS = picture.length;
        if (ROWS == 0) return 0;
        int COLS = picture[0].length;
        String[] rowPicture = new String[ROWS];
        int[] rowSum = new int[ROWS];
        int[] colSum = new int[COLS];
        Set<Integer> bLocs = new HashSet<>();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (picture[i][j] == 'B') {
                    bLocs.add(i * COLS + j);
                    rowSum[i]++;
                    colSum[j]++;
                }
            }
            rowPicture[i] = new String(picture[i]);
        }

        int count = 0;
        for(int bloc : bLocs){
            int r = bloc / COLS;
            int c = bloc % COLS;
            if (rowSum[r] == N && colSum[c] == N) {
                boolean allSameAsCurr = true;
                for(int i = 0; i < ROWS; i++){
                    if(i == r)continue;
                    if(picture[i][c] == 'B' && !rowPicture[i].equals(rowPicture[r])){
                        allSameAsCurr = false;
                        break;
                    }
                }
                if(allSameAsCurr)count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        char[][] picture = new char[][]{
                new char[]{'W', 'B', 'W', 'B', 'B', 'W'},
                new char[]{'W', 'B', 'W', 'B', 'B', 'W'},
                new char[]{'W', 'B', 'W', 'B', 'B', 'W'},
                new char[]{'W', 'W', 'B', 'W', 'B', 'W'}
        };
        System.out.println(findBlackPixel(picture, 3));
    }
}
