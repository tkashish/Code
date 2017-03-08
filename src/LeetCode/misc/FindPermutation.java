package LeetCode.misc;

import java.util.*;

/**
 * Created by tayalka on 3/7/2017.
 */
public class FindPermutation {

    public int[] findPermutation(String s) {
        int n = s.length() + 1;
        int[] result = new int[n];
        for(int i = 1; i < n+1; i++){
            result[i-1] = i;
        }
        for(int i = 0; i < s.length();){
             if(s.charAt(i) == 'D'){
                 int j = i+1;
                 while(j < s.length() && s.charAt(j) == 'D') j++;
                 for(int k = 0; k < (j-i+1)/2; k++){
                     int temp = result[i+k];
                     result[i+k] = result[j-k];
                     result[j-k] = temp;
                 }
                 i = j;
             }else{
                 i++;
             }
        }
        return result;
    }

    public int[] findPermutationI(String s) {
        int n = s.length()+1;
        int[] result = new int[n];
        used = new HashSet<>();
        List<Integer> resultList = new ArrayList<>();
        helper(n,s,0,-1, resultList);
        for(int i = 0; i < n; i++){
            result[i] = resultList.get(n-1-i);
        }
        return result;
    }

    Set<Integer> used;
    private boolean helper(int n, String S, int index, int prev, List<Integer> result){
        if(index > S.length()){
            return true;
        }else{
            char prevChar = index > 0 ? S.charAt(index-1):'.';
            List<Integer> resultNew;
            for(int i = 1; i < n+1; i++){
                boolean bool = false;
                if(!used.contains(i)){
                    if(prevChar == '.'||
                        (prevChar == 'D' && prev > i) ||
                        (prevChar == 'I' && prev < i)){
                        used.add(i);
                        bool = helper(n,S,index+1,i,result);
                        if(bool)result.add(i);
                        used.remove(i);
                    }
                }
                if(bool){
                    return true;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        FindPermutation perm = new FindPermutation();
        Arrays.stream(perm.findPermutation("DII")).forEach(System.out::println);
    }
}
