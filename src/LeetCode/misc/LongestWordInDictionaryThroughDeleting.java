package LeetCode.misc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kashishtayal on 2/28/17.
 */
public class LongestWordInDictionaryThroughDeleting {
    public static String findLongestWord(String s, List<String> d) {
        String result = "";
        for(String poss : d){
            if(poss.length() < result.length()){
                continue;
            }else if(poss.length() == result.length()){
                if(poss.compareToIgnoreCase(result) >= 0){
                    continue;
                }
            }
            int i = 0;
            int j = 0;
            while (i < poss.length()) {
                boolean found = false;
                while(j < s.length()){
                    char pp = poss.charAt(i);
                    char ss = s.charAt(j);
                    j++;
                    if(pp == ss){
                        found = true;
                        break;
                    }
                }
                if(!found)break;
                i++;
            }
            if(i == poss.length()){
                result = poss;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("aa");
        list.add("a");
        System.out.println(findLongestWord("aaa", list));
    }
}
