package LeetCode.misc;

/**
 * Created by kashishtayal on 1/15/17.
 */
public class LicenseKeyFormatting {
    public static String licenseKeyFormatting(String S, int K) {
            if(K == 0)return S;
            StringBuilder sb = new StringBuilder();
            int start = S.length()-1;
            int count = 0;
            for(int i = start; i > -1; i--){
                if(S.charAt(i) == '-')continue;
                if(count == K){
                    sb.append("-");
                    i++;
                    count = 0;
                    continue;
                }
                sb.append(S.charAt(i));
                count++;
            }
            return sb.reverse().toString().toUpperCase();
    }

    public static void main(String[] args) {
        System.out.println(LicenseKeyFormatting.licenseKeyFormatting("--a-a-a-a--",2));
    }
}
