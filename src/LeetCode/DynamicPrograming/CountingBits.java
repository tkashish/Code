package LeetCode.DynamicPrograming;

import java.util.Arrays;

/**
 * Created by tayalka on 11/17/2016.
 */
public class CountingBits {
    public int[] countBits(int num) {
        int[] result = new int[num+1];
        for(int i = 1; i < num+1; i++){
            result[i] += i%2==0 ? result[i/2] : 1+result[(i-1)/2];
        }
        return result;
    }

    public static void main(String[] args) {
        CountingBits cb = new CountingBits();
        Arrays.stream(cb.countBits(5)).forEach(System.out::print);
    }
}
