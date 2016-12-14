package DynamicPrograming;

/**
 * Created by tayalka on 12/7/2016.
 */
public class WayToDestinationSubstring {
    public int getList(int inStart, int inEnd, String inString, int n){
        int diff = inEnd-inStart;
        int[][] dp = new int[n][3];
        dp[inStart][1] = 1;
        dp[inStart][2] = 1;
        char[] chars = inString.toCharArray();
        for(char c : chars){
            int[][] nextStage = new int[n][3];
            for(int i = 0; i < n; i++){
                if(c == 'l' && dp[i][1] > 0 && i-1 > -1){
                    int inc = dp[i][1];
                    nextStage[i-1][0] = inc;
                    nextStage[i-1][1] = inc;
                    nextStage[i-1][2] = inc;
                    dp[i][1] -= inc;
                }else if(c == 'r' && dp[i][2] > 0 && i+1 < n){
                    int inc = dp[i][2];
                    nextStage[i + 1][1] = inc;
                    nextStage[i + 1][2] = inc;
                    nextStage[i + 1][0] = inc;
                    dp[i][2] -= inc;
                }
            }
            for(int i = 0; i < n; i++){
                dp[i][0] += nextStage[i][0];
                dp[i][1] += nextStage[i][1];
                dp[i][2] += nextStage[i][2];
            }
        }
        return dp[inEnd][0];
    }

    public static void main(String[] args) {
        WayToDestinationSubstring ways = new WayToDestinationSubstring();
        System.out.println(ways.getList(3,4,"lrlr",7) == 3);
        System.out.println(ways.getList(3,4,"lrr",7) == 2);
        System.out.println(ways.getList(3,4,"lrlrlrllrlrlrlrlrlrlllrllrllrllrlrllrlr",7));
    }
}
