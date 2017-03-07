package LeetCode.misc;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kashishtayal on 2/19/17.
 */
public class StrobogrammaticNumber {
    public static boolean isStrobogrammatic(String num) {
        Map<Character, Character> map= new HashMap<>();
        map.put('0','0');
        map.put('1','1');
        map.put('6','9');
        map.put('8','8');
        map.put('9','6');
        for(int i = 0; i < num.length()/2 + 1; i++){
            System.out.println(i);
            if(!map.containsKey(num.charAt(i))){
                return false;
            }
            char mapping  = map.get(num.charAt(i));
            if(mapping != num.charAt(num.length()-i-1)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isStrobogrammatic("086980"));
    }
}
