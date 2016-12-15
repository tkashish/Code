package LeetCode.DynamicPrograming;

/**
 * Created by kashishtayal on 11/16/16.
 */
public class ArithmeticSlices {
    public int numberOfArithmeticSlices(int[] A) {
        int len = A.length;
        int count = 0;
        boolean[][] dp = new boolean[len][len];
        for(int seqLen = 3; seqLen <= len; seqLen++){
            for(int start = 0; start < len-seqLen+1; start++){
                if((seqLen == 3 || dp[start+1][start+seqLen-1])  && A[start]-A[start+1] == A[start+1]-A[start+2]){
                    count++;
                    dp[start][start+seqLen-1] = true;
                }
            }
        }
        return count;
    }
    public int numberOfArithmeticSlicesFaster(int[] A){
        if(A.length < 3) return 0;
        int count = 0;
        int currCount = 1;
        for(int i = 1; i < A.length-1; i++){
            if(A[i-1]-A[i] == A[i]-A[i+1]){
                currCount++;
            }else{
                if(currCount > 1){
                    count += currCount*(currCount-1)/2;
                }
                currCount = 1;
            }
        }
        if(currCount > 1){
            int n = currCount-1;
            count += n*(n+1)/2;
        }
        return count;
    }
    public static void main(String[] args) {
        ArithmeticSlices as = new ArithmeticSlices();
//        System.out.println(as.numberOfArithmeticSlices(new int[]{1, 3, 5, 7, 9}));
//        System.out.println(as.numberOfArithmeticSlices(new int[]{7, 7, 7, 7}));
//        System.out.println(as.numberOfArithmeticSlices(new int[]{3, -1, -5, -9}));
//        System.out.println(as.numberOfArithmeticSlices(new int[]{1, 1, 2, 5, 7}));
        System.out.println(as.numberOfArithmeticSlicesFaster(new int[]{1, 3, 5, 7, 9}));
        System.out.println(as.numberOfArithmeticSlicesFaster(new int[]{7, 7, 7, 7}));
        System.out.println(as.numberOfArithmeticSlicesFaster(new int[]{3, -1, -5, -9}));
        System.out.println(as.numberOfArithmeticSlicesFaster(new int[]{1, 1, 2, 5, 7}));
        System.out.println(as.numberOfArithmeticSlicesFaster(new int[]{1,2,3,8,9,10}));
    }
}
