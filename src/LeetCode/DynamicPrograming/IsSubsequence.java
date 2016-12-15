package LeetCode.DynamicPrograming;

/**
 * Created by kashishtayal on 11/5/16.
 */
public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        int tLength = t.length();
        int sLength = s.length();
        if(sLength == 0) return true;
        int lastFoundIndex = 0;
        for(int i = 0 ; i < tLength ; i++){
            if(s.charAt(lastFoundIndex) == t.charAt(i)){
                lastFoundIndex++;
            }
            if(lastFoundIndex == sLength){
                return true;
            }
        }
        return false;
    }

    public boolean isSubsequenceI(String s, String t) {
        int tLength = t.length();
        int sLength = s.length();
        if(sLength == 0) return true;
        int lastIndex = 0;
        for(int i = 0; i < sLength; i++){
            int index = t.indexOf(s.charAt(i),lastIndex);
            if(index < 0) return false;
            lastIndex = index+1;
        }
        return true;
    }
    public static void main(String[] args) {
        IsSubsequence is = new IsSubsequence();
        System.out.println(is.isSubsequenceI("abc","ahbgdc"));
        System.out.println(is.isSubsequenceI("aab","ahbgdc"));
        System.out.println(is.isSubsequenceI("axc","ahbgdc"));
        System.out.println(is.isSubsequenceI("acb","ahbgdc"));
        System.out.println(is.isSubsequenceI("acb","ahbgdcb"));
        System.out.println(is.isSubsequenceI("",""));
    }
}
