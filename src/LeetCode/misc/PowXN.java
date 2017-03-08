package LeetCode.misc;

/**
 * Created by tayalka on 3/7/2017.
 */
public class PowXN {
    public static double myPow(double x, int n) {
        double result = helper(x,Math.abs(n));
        return n>0?result:1/result;
    }
    private static double helper(double x, int n){
        if (n == 0) {
            return 1;
        } else {
            double pownby2 = helper(x, n / 2);
            double result = pownby2 * pownby2;
            if (n % 2 == 0) {
                return result;
            } else {
                result *= x;
                return result;
            }
        }
    }
    public static void main(String[] args) {
        System.out.println(myPow(2.00000 ,- 2147483648));
    }
}
