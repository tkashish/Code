package LeetCode.DynamicPrograming;

/**
 * Created by kashishtayal on 11/7/16.
 */
public class DecodeWays {
    public int numDecodings(String s) {
        int len = s.length();
        if(len == 0) return 0;
        char[] sArr = s.toCharArray();
        int[] dp = new int[len+1];
        dp[0] = 1;
        for(int i = 1; i <= len; i++ ){
            int currOne = (int)sArr[i-1]-48;
            if(currOne > 0) {
                dp[i] = dp[i - 1];
            }
            if(i>1){
                int currTwo = Integer.valueOf(s.substring(i-2,i));
                if(currTwo > 9 && currTwo < 27){
                    dp[i] += dp[i-2];
                }
            }
            if(dp[i] == 0) return 0;
        }
        return dp[len];
    }

    public static void main(String[] args) {
        DecodeWays dw = new DecodeWays();
        System.out.println(dw.numDecodings("") == 0);
        System.out.println(dw.numDecodings("0") == 0);
        System.out.println(dw.numDecodings("1") == 1);
        System.out.println(dw.numDecodings("12") == 2);
        System.out.println(dw.numDecodings("123") == 3);
        System.out.println(dw.numDecodings("01") == 0);
        System.out.println(dw.numDecodings("012") == 0);
        System.out.println(dw.numDecodings("0123") == 0);
        System.out.println(dw.numDecodings("102") == 1);
        System.out.println(dw.numDecodings("1002") == 0);
        System.out.println(dw.numDecodings("1020") == 1);
        System.out.println(dw.numDecodings("1030") == 0);
    }
    /**
     * Zero in itself will not have any meaning
     * but it can be paired with other numbers
     * like 10 or 01
     * now 01 = 1
     */
}
