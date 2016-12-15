package LeetCode.DynamicPrograming;

/**
 * Created by kashishtayal on 12/8/16.
 */
public class PaintFence {
    public static int numWays(int n, int k) {
        if(k == 0 || n == 0) return 0;
        if(n == 1) return k;
        int countSame = k;
        int countDiff = k*(k-1);
        for(int i = 2; i < n; i++){
            int tempCountSame = countDiff;
            int tempCountDiff = countSame*(k-1) + countDiff*(k-1);
            countSame = tempCountSame;
            countDiff = tempCountDiff;
        }
        return countSame + countDiff;
    }

    public static void main(String[] args) {
        System.out.println(PaintFence.numWays(1,2));
    }
}

