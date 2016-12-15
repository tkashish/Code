package LeetCode.DynamicPrograming;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by kashishtayal on 12/8/16.
 */
public class UniqueSubstringsInWraparoundString {
    public static int findSubstringInWraproundString(String p) {
        int len = p.length();
        if(len == 0)return 0;
        int start = 0;
        int[] count = new int[26];
        for(int i = 1; i < len ; i++){
            int curr = p.charAt(i);
            int diff = curr - p.charAt(i-1);
            count[p.charAt(i-1)-'a'] = Math.max(count[p.charAt(i-1)-'a'],i-start);
            if(diff != 1 && diff != -25){
                start = i;
            }
        }
        count[p.charAt(len-1)-'a'] = Math.max(count[p.charAt(len-1)-'a'],len-1-start+1);
        int ways = 0;
        for(int i : count){
            ways+=i;
        }
        return ways;
    }

    public static void main(String[] args) {
        System.out.println(UniqueSubstringsInWraparoundString.findSubstringInWraproundString("zabdgh") == 10);
        System.out.println(UniqueSubstringsInWraparoundString.findSubstringInWraproundString("zab") == 6);
        System.out.println(UniqueSubstringsInWraparoundString.findSubstringInWraproundString("zabcabc") == 10);
        System.out.println(UniqueSubstringsInWraparoundString.findSubstringInWraproundString("zabcf") == 11);
        System.out.println(UniqueSubstringsInWraparoundString.findSubstringInWraproundString("zabcfghabc") == 16);
        System.out.println(UniqueSubstringsInWraparoundString.findSubstringInWraproundString("abcabcd") == 10);
    }
}
