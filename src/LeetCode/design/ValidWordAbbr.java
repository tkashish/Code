package LeetCode.design;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kashishtayal on 11/29/16.
 */
public class ValidWordAbbr {
    HashMap<String, String> abbrMap;
    public ValidWordAbbr(String[] dictionary) {
        abbrMap = new HashMap<>();
        for(String word : dictionary){
            if(word.length() > 2) {
                String abr = getAbbr(word);
                if(abbrMap.containsKey(abr)) abbrMap.put(abr,"");
                else abbrMap.put(abr,word);
            }
        }
    }

    private String getAbbr(String word){
        int size = word.length();
        StringBuilder sb = new StringBuilder();
        sb.append(word.charAt(0));
        sb.append(Integer.toString(size-2));
        sb.append(word.charAt(size-1));
        return sb.toString();
    }

    public boolean isUnique(String word) {
        if(word.length() < 3) return true;
        String abbr = getAbbr(word);
        if(abbrMap.containsKey(abbr)){
            String mapping = abbrMap.get(abbr);
            if(mapping.equals(word)) return true;
            else return false;
        }
        return true;
    }

    public static void main(String[] args) {
        ValidWordAbbr abbr = new ValidWordAbbr(new String[]{"deer", "door", "cake", "card"});
        System.out.println(abbr.isUnique("dear"));
        System.out.println(abbr.isUnique("cart"));
        System.out.println(abbr.isUnique("cane"));
        System.out.println(abbr.isUnique("make"));
    }
}
