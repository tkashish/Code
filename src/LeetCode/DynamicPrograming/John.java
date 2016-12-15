package LeetCode.DynamicPrograming;

import java.util.*;

/**
 * Created by kashishtayal on 12/6/16.
 */
public class John {
    public Set<String> getAllPath(int start, int end, String hops){
        int diff = end-start;
        HashMap<Integer, Set<String>> map = new HashMap<>();
        char[] chars = hops.toCharArray();
        for(char c : chars){
            int currHop = c=='l'?-1:1;
            HashMap<Integer, Set<String>> currMap = new HashMap<>(map);
            for(Map.Entry<Integer, Set<String>> entry : map.entrySet()){
                int posWithHop = entry.getKey()+currHop;
                Set<String> ways = entry.getValue();
                Set<String> set = currMap.get(posWithHop);
                if(set == null) set = new HashSet<>();
                for(String s: ways){
                    set.add(s+c);
                }
                currMap.put(posWithHop,set);
            }
            Set<String> set = currMap.get(currHop);
            if(set == null) set = new HashSet<>();
            set.add(String.valueOf(c));
            currMap.put(currHop,set);
            map = currMap;
        }
        return map.get(diff);
    }

    public static void main(String[] args) {
        John john = new John();
        System.out.println(john.getAllPath(0,3,"lrlrlrlrlrlrlrlrlr"));
    }
}
