import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by kashishtayal on 6/20/16.
 */
public class ReverseString {

    public static String reverseString(String s) {
        return reverseString2(s);
    }

    public static String reverseString1(String s) {
        if(s == null){
            return null;
        }
        int len = s.length();
        StringBuilder sb = new StringBuilder();
        for(int i = len-1; i>=0; i--){
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
    // slower : WTFFF
    public static String reverseString2(String s) {
        if(s == null){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int len = s.length();
        IntStream.range(0,len).forEach(e->sb.append(s.charAt(len-e-1)));
        return sb.toString();
    }

    public static void main(String[] args){
        System.out.println(ReverseString.reverseString("k"));
    }
}
