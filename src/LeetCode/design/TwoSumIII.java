package LeetCode.design;

import java.util.*;

/**
 * Created by kashishtayal on 11/29/16.
 */
public class TwoSumIII {
    Set<Integer> duplicates;
    Set<Integer> numbers;
    public TwoSumIII(){
        duplicates = new HashSet<>();
        numbers = new HashSet<>();
    }
    // Add the number to an internal data structure.
    public void add(int number) {
        if(numbers.contains(number)){
            duplicates.add(number);
        }else{
            numbers.add(number);
        }
    }

    // Find if there exists any pair of numbers which sum is equal to the value.
    public boolean find(int value) {
        for(int i : numbers){
            int diff = value - i;
            if(diff == i){
                if(duplicates.contains(diff)) return true;
            }else{
                if(numbers.contains(diff)) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        TwoSumIII ts = new TwoSumIII();
        ts.add(0);
        ts.add(0);
        ts.add(0);
        System.out.println(ts.find(0));
//        System.out.println(ts.find(7));
//        System.out.println(ts.find(6));
    }
}
