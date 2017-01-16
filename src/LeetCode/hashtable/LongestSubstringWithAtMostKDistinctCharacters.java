package LeetCode.hashtable;

import java.util.*;

/**
 * Created by kashishtayal on 1/15/17.
 */
public class LongestSubstringWithAtMostKDistinctCharacters {
    public static int lengthOfLongestSubstringKDistinct1(String s, int k) {
        if(k == 0) return 0;
        char[] charr = s.toCharArray();
        int length = 0;
        Map<Character, Integer> elementCountMap = null;
        int countCurr = 0;
        for(int i = 0; i < charr.length; ){
            elementCountMap = new HashMap<>();
            HashMap<Character, Integer> lastFoundMap = new HashMap<>();
            Set<Character> set = new HashSet<>();
            int j = i;
            char lastChar = '.';
            int lastMin = -1;
            for(; j < charr.length; j++){
                char c = charr[j];
                lastFoundMap.put(c, j);
                int newMin = lastFoundMap.values().stream().min(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        if(o1 < o2) return -1;
                        else if( o1 > o2) return 1;
                        else return 0;
                    }
                }).get();
                if(set.contains(c)){
                    if(newMin != lastMin){
                        elementCountMap.put(c, elementCountMap.get(c)+1);
                    }else{
                        elementCountMap.put(c, 1);
                    }
                    countCurr++;
                }else{
                    if(set.size() < k){
                        elementCountMap.put(c, 1);
                        countCurr++;
                        set.add(c);
                    }else{
                        break;
                    }
                }
                lastChar = c;
            }
            length = Math.max(length, countCurr);
            i = j;
            countCurr = elementCountMap.get(lastChar);
        }
        return length;
    }
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if(k == 0) return 0;
        char[] charr = s.toCharArray();
        int length = 0;
        Map<Character, Integer> lastFoundAtMap = null;
        for(int i = 0; i < charr.length; ){
            lastFoundAtMap = new HashMap<>();
            int count = 0;
            Set<Character> set = new HashSet<>();
            for(int j = i; j < charr.length; j++){
                char c = charr[j];
                if(set.contains(c)){
                    lastFoundAtMap.put(c,j);
                    count++;
                }else{
                    if(set.size() < k){
                        lastFoundAtMap.put(c,j);
                        count++;
                        set.add(c);
                    }else{
                        break;
                    }
                }
            }
            Collection<Integer> coll = lastFoundAtMap.values();
            if(lastFoundAtMap.size() == k){
                i = coll.stream().min(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        if(o1 < o2)return -1;
                        else if (o1 > o2) return 1;
                        else return 0;
                    }
                }).get() + 1;
            }else{
                i = charr.length;
            }
            length = Math.max(length, count);
        }
        return length;
    }
    public static void main(String[] args) {
        System.out.println(LongestSubstringWithAtMostKDistinctCharacters.lengthOfLongestSubstringKDistinct("ecebeaaa", 3));
    }
}
