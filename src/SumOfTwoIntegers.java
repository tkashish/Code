/**
 * 371
 */
public class SumOfTwoIntegers {
    public static int getSum(int a, int b) {
        if(a==0)return b;
        return getSum((a&b)<<1,a^b);
    }

    public static void main(String[] args) {
        System.out.println(getSum(0,Integer.MAX_VALUE));
    }
}
