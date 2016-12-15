package LeetCode.misc;

/**
 * 58 https://leetcode.com/problems/length-of-last-word/
 */
public class LengthOfLastWord {
    public static int lengthOfLastWord(String s) {
        String[] list = s.split(" ");
        return list.length==0?0:list[list.length-1].length();
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("Hello World"));
        System.out.println(lengthOfLastWord(""));
        System.out.println(lengthOfLastWord("Hello "));
        System.out.println(lengthOfLastWord(" World"));
        System.out.println(lengthOfLastWord(" "));
    }
}
