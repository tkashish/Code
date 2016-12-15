package LeetCode.design;

import java.util.*;

/**
 * Created by kashishtayal on 12/1/16.
 */
public class WordDistance {
    Map<String,List<Integer>> wordLocation;
    public WordDistance(String[] inWords) {
        wordLocation = new HashMap<>();
        for(int i = 0; i < inWords.length; i++){
            List<Integer> location = wordLocation.get(inWords[i]);
            if(location == null) location = new ArrayList<>();
            location.add(i);
            wordLocation.put(inWords[i],location);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> listWord1 = wordLocation.get(word1);
        List<Integer> listWord2 = wordLocation.get(word2);
        int min = Integer.MAX_VALUE;
        for(int i = 0, j = 0; i < listWord1.size() && j < listWord2.size();){
            int word1Loc = listWord1.get(i);
            int word2Loc = listWord2.get(j);
            min = Math.min(min, Math.abs(word1Loc-word2Loc));
            int val = word1Loc > word2Loc ? j++ : i++;
        }
        return min;
    }

    public static void main(String[] args) {
        WordDistance wd = new WordDistance(new String[]{ "makes","practice", "perfect", "coding","makes"});
        System.out.println(wd.shortest("makes","coding"));
    }
}
