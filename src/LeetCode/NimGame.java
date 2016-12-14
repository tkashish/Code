package LeetCode;

import java.util.stream.IntStream;

/**
 * Created by kashishtayal on 6/20/16.
 */
public class NimGame {
    public static boolean canWinNim(int n) {
        return (n%4)==0?false:true;
    }

    public static void main(String... args){
        IntStream.rangeClosed(0,10).forEach(e->System.out.println(e + " " + canWinNim(e)));
//        System.out.println(canWinNim(1));
    }
}
