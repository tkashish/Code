/**
 * 87 https://leetcode.com/problems/scramble-string/
 */
public class ScrambleString {
    public boolean isScramble(String s1, String s2) {
        if(s1.length() != s2.length()) return false;
        if(s1.length() == 1){
            if(s1.equals(s2)) return true;
            else return false;
        }
        if(s1.length() == 2){
            if(!new StringBuilder(s1).reverse().toString().equals(s2))return false;
            else return true;
        }
        int i = 0;
        char charS2 = s2.charAt(0);
        for(; i < s1.length(); i++){
            if(s1.charAt(i) == charS2)break;
        }
        return isScramble(s1.substring(0,i+1),s2.substring(0,i+1)) && isScramble(s1.substring(i+1,s1.length()),s2.substring(i+1,s2.length()));
    }

    public static void main(String[] args) {
        
    }
}
