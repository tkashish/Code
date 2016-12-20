package Algorithms.UCSD;

import java.util.*;

public class DotProduct {
    private static long maxDotProduct(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        //write your code here
        long result = 0;
        for (int i = 0; i < a.length; i++) {
            result += a[i] * b[i];
        }
        return result;
    }

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int[] a = new int[n];
//        for (int i = 0; i < n; i++) {
//            a[i] = scanner.nextInt();
//        }
//        int[] b = new int[n];
//        for (int i = 0; i < n; i++) {
//            b[i] = scanner.nextInt();
//        }
        int[] a = {1,3,-5};
        int[] b = {-2,4,1};
        System.out.println(maxDotProduct(a, b));
    }
}
