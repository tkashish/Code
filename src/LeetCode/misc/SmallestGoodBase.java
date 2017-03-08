package LeetCode.misc;

import java.math.BigInteger;

/**
 * Created by tayalka on 3/7/2017.
 */
public class SmallestGoodBase {
    private int MAX_A = 64;
    public String smallestGoodBaseI(String n) {
        BigInteger N = new BigInteger(n);
        for(BigInteger i = new BigInteger(String.valueOf(2)); N.compareTo(i) > 0 ; i=i.add(BigInteger.ONE)){
            BigInteger target = N.multiply(i.subtract(BigInteger.ONE)).add(BigInteger.ONE);
            int result = getA(target,i);
            if(result != -1){
                return i.toString();
            }
        }
        return null;
    }
    private int getA(BigInteger target, BigInteger base){
        int s = 0;
        int e = MAX_A;
        while(s <= e){
            int mid = s + (e-s)/2;
            BigInteger powMid = base.pow(mid);
            int comp = target.compareTo(powMid);
            if( comp == 0){
                return 1;
            }else if(comp < 0){
                e = mid-1;
            }else{
                s = mid+1;
            }
        }
        MAX_A = s;
        return -1;
    }



    public static void main(String[] args) {
        SmallestGoodBase smallestGoodBase = new SmallestGoodBase();
        System.out.println(smallestGoodBase.smallestGoodBase("14919921443713777"));
    }


    public String smallestGoodBase(String n) {
        long N = Long.valueOf(n);
        int minA = 2;
        int maxA = (int) (Math.log(N + 1) / Math.log(2));
        for(int a = maxA; a >= minA; a--){
            long kU = (long) (Math.pow(N, 1.0 / (a-1)));
            long kL = (long) (Math.pow(N + 1, 1.0 / a));

            while(kL <= kU){
                long mid = kL + (kU-kL)/2;
                long RHS = 0l;
                for (int i = 0; i < a; i++, RHS = RHS * mid + 1) ;
                int comp = Long.compare(N,RHS);
                if(comp == 0){
                    return String.valueOf(mid);
                }else if (comp < 1){
                    kU = mid-1;
                }else{
                    kL = mid+1;
                }
            }
        }
        return String.valueOf(N - 1.0);
    }

    public String smallestGoodBaseII(String n) {
        long num = Long.valueOf(n);

        for (int m = (int) (Math.log(num + 1) / Math.log(2)); m >= 2; m--) {
            long l = (long) (Math.pow(num + 1, 1.0 / m));
            long r = (long) (Math.pow(num, 1.0 / (m - 1)));

            while (l <= r) {
                long k = l + ((r - l) >> 1);
                long f = 0L;
                for (int i = 0; i < m; i++, f = f * k + 1) ;

                if (num == f) {
                    return String.valueOf(k);
                } else if (num < f) {
                    r = k - 1;
                } else {
                    l = k + 1;
                }
            }
        }

        return String.valueOf(num - 1);
    }
}
