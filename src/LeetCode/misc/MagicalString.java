package LeetCode.misc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tayalka on 3/7/2017.
 */
public class MagicalString {
    public static int magicalString(int n) {
        List<Integer> magicString = new ArrayList<>();
        int count = 1;
        magicString.add(1);
        int prev = 0;
        while(magicString.size() < n){
            int len = magicString.size();
            int next = 0;
            if(prev+1 == len){
                next = magicString.get(prev) == 1 ? 2 : 1;
            }else{
                next = magicString.get(prev+1);
            }
            int addition = magicString.get(len-1) == 1 ? 2 : 1;
            for(int i = 0; i < next; i++){
                magicString.add(addition);
                if(addition == 1 && magicString.size() <= n)count++;
            }
            prev++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(magicalString(4));
    }
}