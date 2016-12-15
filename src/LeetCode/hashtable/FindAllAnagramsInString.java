package LeetCode.hashtable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kashishtayal on 10/25/16.
 */
public class FindAllAnagramsInString {
    public List<Integer> findAnagrams(String s, String p) {
        System.out.println(s.length()+"   "+p.length());
        List<Integer> result = new ArrayList<>();
        int[] array = new int[26];
        for(char c : p.toCharArray()){
            int i = (int) c - 97;
            array[i]++;
        }
        for(int i = 0; i <= s.length()-p.length();){
            System.out.println(i);
            int c = s.charAt(i);
            if(array[(int)c-97] == 0) {
                i++;
                continue;
            }
            int[] arrcopy = Arrays.copyOf(array,array.length);
            arrcopy[(int)c-97]--;
            boolean found = true;
            for(int j = 1; j < p.length(); j++){
                if(arrcopy[(int)s.charAt(i+j)-97] == 0){
                    if(array[(int)s.charAt(i+j)-97] == 0){
                        System.out.println(s.charAt(i+j)+"  "+(i+j+1));
                        i = i+j+1;
                    }else{
                        i++;
                    }
                    found = false;
                    break;
                }else{
                    arrcopy[(int)s.charAt(i+j)-97]--;
                }
            }
            if(found){
                result.add(i);
                i++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        FindAllAnagramsInString ana = new FindAllAnagramsInString();
        System.out.println(ana.findAnagrams("aa","bb"));
    }
}
