package LeetCode.misc;

/**
 * Created by tayalka on 3/6/2017.
 */
public class FreedomTrail {
    public int findRotateSteps(String ring, String key) {
        int ringLength = ring.length();
        int keyLength = key.length();
        int[][] dp = new int[keyLength+1][ringLength];
        for(int i = keyLength-1; i > 0; i--){
            char prevChar = key.charAt(i-1);
            char currChar = key.charAt(i);
            for(int j = 0; j < ringLength; j++){
                if(ring.charAt(j) == prevChar) {
                    dp[i][j] = Integer.MAX_VALUE;
                    for (int k = 0; k < ringLength; k++) {
                        if(ring.charAt(k) == currChar){
                            int diff = Math.abs(k-j);
                            int minSteps = Math.min(diff,ringLength-diff);
                            dp[i][j] = Math.min(dp[i][j], minSteps+dp[i+1][k]);
                        }
                    }
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < ringLength; i++){
            if(ring.charAt(i) == key.charAt(0)){
                int diff = i;
                int minSteps = Math.min(i,ringLength-i);
                min = Math.min(min,minSteps+dp[1][i]);
            }
        }
        return keyLength+min;
    }

    public static void main(String[] args) {
        FreedomTrail freedom = new FreedomTrail();
        System.out.println(freedom.findRotateSteps("godding","gndng"));
    }
}
