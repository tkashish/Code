package LeetCode.misc;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kashishtayal on 1/16/17.
 */
public class WordPatternII {

    public boolean wordPatternMatch(String pattern, String str) {
        if(pattern.length() == 1 && str.length() > 0) return true;
        if(pattern.length() > str.length()) return false;
        Map<Character, String> mapping = new HashMap<>();
        return isMatchingPattern(pattern,0,str,0,mapping);
    }
    private boolean isMatchingPattern(String pattern, int pIndex, String str, int sIndex, Map<Character, String> mapping){
        if(sIndex == str.length() && pIndex < pattern.length())return false;
        if(sIndex < str.length() && pIndex == pattern.length())return false;
        if(sIndex == str.length() && pIndex == pattern.length()) return true;
        char patternCurrChar = pattern.charAt(pIndex);
        if(mapping.containsKey(patternCurrChar)){
            String value = mapping.get(patternCurrChar);
            boolean isMatch = str.regionMatches(sIndex,value,0,value.length());
            if(isMatch){
                boolean nextMatchResult = isMatchingPattern(pattern,pIndex+1,str,sIndex+value.length(),mapping);
                return nextMatchResult;
            }else{
                return false;
            }
        }else{
            StringBuilder sb = new StringBuilder();
            for(int i = sIndex; i < str.length(); i++){
                Map<Character, String> mappingCopy = new HashMap<>();
                mappingCopy.putAll(mapping);
                sb.append(str.charAt(i));
                if(mapping.values().contains(sb.toString())){
                    continue;
                }
                mappingCopy.put(patternCurrChar, sb.toString());
                boolean nextMatchResult = isMatchingPattern(pattern,pIndex+1,str,i+1,mappingCopy);
                if(nextMatchResult){
                    System.out.println(mappingCopy);
                    return true;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        WordPatternII wp = new WordPatternII();
        System.out.println(wp.wordPatternMatch("abab","redblueredblue"));
        System.out.println(wp.wordPatternMatch("aaaa","asdasdasdasd"));
        System.out.println(wp.wordPatternMatch("aabb","xyzabcxzyabc"));
        System.out.println(wp.wordPatternMatch("ab","aa"));
    }
}
