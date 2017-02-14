package LeetCode.misc;

/**
 * Created by kashishtayal on 2/13/17.
 */
public class DecodeString {
    public String decodeString(String s) {
        if (s.length() == 0) return "";
        StringBuilder sb = new StringBuilder();
        int intStartIndex = -1;
        int intEndIndex = -1;
        int repeatCount = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c <= 'z' && c >= 'a') {
                sb.append(c);
            } else if (c <= '9' && c >= '0') {
                intStartIndex = i;
                intEndIndex = i;
                for (int j = i + 1; j < s.length(); j++) {
                    if (s.charAt(j) <= '9' && s.charAt(j) >= '0') {
                        intEndIndex = j;
                    } else {
                        break;
                    }
                }
                repeatCount = Integer.parseInt(s.substring(intStartIndex, intEndIndex + 1));
                String nextSubString ="";
                int openBracketCount = 0;
                int j = intEndIndex+1;
                for(; j < s.length(); j++){
                    if(s.charAt(j) == ']'){
                        openBracketCount--;
                    }else if(s.charAt(j) == '['){
                        openBracketCount++;
                    }
                    if(openBracketCount == 0){
                        nextSubString = s.substring(intEndIndex+2,j);
                        break;
                    }
                }
                String stringToAppend = decodeString(nextSubString);
                for(int count = 0; count < repeatCount; count++){
                    sb.append(stringToAppend);
                }
                i = j;
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        DecodeString decodeString = new DecodeString();
//        System.out.println(decodeString.decodeString("3[a]2[bc]"));
//        System.out.println(decodeString.decodeString("3[a2[c]]"));
//        System.out.println(decodeString.decodeString("2[abc]3[cd]ef"));
        System.out.println(decodeString.decodeString("100[leetcode]"));
    }
}
