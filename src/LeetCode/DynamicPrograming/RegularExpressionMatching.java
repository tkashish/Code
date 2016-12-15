package LeetCode.DynamicPrograming;

import java.util.Arrays;

/**
 * Created by kashishtayal on 11/19/16.
 */
public class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        if(p.startsWith("*")) return false;
        if(!countEffLength(s,p)) return false;
        boolean[][] dp = new boolean[p.length()+1][s.length()+1];
        for(int i = 0; i < p.length()+1; i++){
            dp[i][0] = true;
        }
        for (int i = 1; i < p.length()+1; i++) {
            char c = p.charAt(i-1);
            boolean exists = false;
            if(c=='.'||c=='*'|| s.length() == 0) exists = true;
            for (int j = 1; j < s.length()+1; j++) {
                exists = false;
                if(dp[i][j-1]) exists = true;
                if(c == '*'){
                    dp[i][j] = dp[i-1][j];
                }else {
                    int count = 0;
                    for (int k = i + 1; k < p.length() + 1; k++) {
                        if (p.charAt(k - 1) == '*') count++;
                        else break;
                    }
                    if (c == '.') {
                        if (count % 2 == 0) {
                            dp[i][j] = dp[i - 1][j - 1];
                        } else {
                            dp[i][j] = (dp[i - 1][j - 1] || dp[i][j - 1]);
//                            if(!dp[i-1][j]) c = s.charAt(j-1);
                        }
                    } else {
                        if(count == 0 && c == s.charAt(j-1)) exists = true;
                        if (count % 2 == 0) {
                            if(count != 0)exists = true;
                            dp[i][j] = dp[i - 1][j - 1] && (dp[i - 1][j] || (c == s.charAt(j - 1)));
                        } else {
                            exists = true;
                            dp[i][j] = (dp[i - 1][j - 1] || dp[i][j - 1]) && (dp[i - 1][j] || (c == s.charAt(j - 1)));
                        }
                    }
                }
            }

            if(!exists) return false;
        }
        return dp[p.length()][s.length()];
    }

    public boolean countEffLength(String s, String str){
        int count = 0;
        boolean canAdd = false;
        for(int i = 0 ;i < str.length() ; i++){
            char c = str.charAt(i);
            if(c != '*'){
                int j = 0;
                for(int m = i+1; m < str.length(); m++){
                    if(str.charAt(m) == '*'){
                        j++;
                    }else{
                        break;
                    }
                }
                if(j % 2 == 0){
                    count++;
                }else{
                    canAdd = true;
                }
            }
        }
        return (s.length() > count && (canAdd) ) || (s.length() == count);
    }

    public static void main(String[] args) {
        RegularExpressionMatching rem = new RegularExpressionMatching();
        System.out.println(rem.isMatch("aab", "") == false);
        System.out.println(rem.isMatch("", "dads") == false);
        System.out.println(rem.isMatch("", "") == true);
        System.out.println(rem.isMatch("", "*") == false);
        System.out.println(rem.isMatch("", ".") == false);
        System.out.println(rem.isMatch("aab", "a*b") == true);
        System.out.println(rem.isMatch("aab", "a**b") == false);
        System.out.println(rem.isMatch("kas", ".kas") == false);
        System.out.println(rem.isMatch("kas", "*kas") == false);
        System.out.println(rem.isMatch("", "k*") == true);
        System.out.println(rem.isMatch("", ".*") == true);
        System.out.println(rem.isMatch("ab", ".*") == true);
        System.out.println(rem.isMatch("ab", ".*c") == false);
        System.out.println(rem.isMatch("aaabbb", ".*") == true);
        System.out.println(rem.isMatch("aaa", ".*c") == false);
        System.out.println(rem.isMatch("aaa", "aaa") == true);
        System.out.println(rem.isMatch("aab", "c*a*b") == true);


    }
}
