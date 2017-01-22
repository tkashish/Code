package LeetCode.DivideAndConquer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kashishtayal on 1/21/17.
 */
public class DifferentWaysToAddParentheses {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> result = new ArrayList<>();
        if(!input.contains("+") && !input.contains("-") && !input.contains("*")){
            result.add(Integer.parseInt(input));
            return result;
        }
        char[] chars = input.toCharArray();
        for(int i = 0; i < chars.length; i++){
            char c = chars[i];
            if(c == '+' || c == '-' || c == '*'){
                List<Integer> left = diffWaysToCompute(input.substring(0,i));
                List<Integer> right = diffWaysToCompute(input.substring(i+1,chars.length));
                for(int num1 : left){
                    for(int num2 : right){
                        result.add(compute(num1,num2,c));
                    }
                }
            }
        }
        return result;
    }
    private int compute(int num1, int num2, char operator){
        if(operator == '+') return num1+num2;
        else if(operator == '-') return num1-num2;
        else return num1*num2;
    }
    public static void main(String[] args) {
        DifferentWaysToAddParentheses diff = new DifferentWaysToAddParentheses();
        List<Integer> result = diff.diffWaysToCompute("2*3-4*5");
        System.out.println(result);
    }
}
