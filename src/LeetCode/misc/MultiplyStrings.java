package LeetCode.misc;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 43 https://leetcode.com/problems/multiply-strings/
 */
public class MultiplyStrings {
    public static String multiply(String num1, String num2) {
        String ret = "";
        num2 = new StringBuilder(num2).reverse().toString();
        int counter = 0;
        for(Character c : num2.toCharArray()){
            StringBuilder sb = new StringBuilder(singleMultiply(num1,c));
            for(int i = 0; i< counter; i++){
                sb.append("0");
            }
            ret = add(ret, sb.toString());
            counter++;
        }
        return ret;
    }
    public static String singleMultiply(String num1, Character num2){
        num1 = new StringBuilder(num1).reverse().toString();
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int num = Integer.valueOf(num2.toString());
        for(Character c : num1.toCharArray()){
            int curr = (Integer.valueOf(c.toString()))*num + carry;
            carry = curr>9?curr/10:0;
            sb.append(curr>9?curr%10:curr);
        }
        if(carry>0){
            sb.append(carry);
        }
        return sb.reverse().toString();
    }

    public static String add(String num1, String num2){
        num1 = new StringBuilder(num1).reverse().toString();
        num2 = new StringBuilder(num2).reverse().toString();
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        int carry = 0;
        while(counter < num1.length() || counter < num2.length()){
            int n1 = counter<num1.length()?Integer.valueOf(num1.charAt(counter))-48:0;
            int n2 = counter<num2.length()?Integer.valueOf(num2.charAt(counter))-48:0;
            int currSum = n1+n2+carry;
            carry = currSum>9?1:0;
            sb.append(currSum>9?currSum%10:currSum);
            counter++;
        }
        if(carry>0){
           sb.append(1);
        }
        for(Character c: sb.toString().toCharArray()){
            if(c!='0'){
                return sb.reverse().toString();
            }
        }
        return "0";
    }

    public static void main(String[] args) {
        System.out.println(multiply("223", "80"));
    }
}
