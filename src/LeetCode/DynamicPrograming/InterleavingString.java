package LeetCode.DynamicPrograming;

/**
 * Created by kashishtayal on 10/25/16.
 */
public class InterleavingString {

    public boolean isInterleavingRecursive(String str1, String str2, String target){
        if(str1.length() == 0) return target.equals(str2);
        if(str2.length() == 0) return target.equals(str1);
        if(str1.length() == 0 && str2.length() == 0) return target.length()==0;
        char c1 = str1.charAt(0);
        char c2 = str2.charAt(0);
        char t = target.charAt(0);
        if(c1 == t){
            return isInterleaving(str1.substring(1),str2,target.substring(1));
        }
        return ((c1 == t)&isInterleaving(str1.substring(1),str2,target.substring(1)))|
        ((c2 == t)&isInterleaving(str1,str2.substring(1),target.substring(1)));
    }

    public boolean isInterleaving(String str1, String str2, String target){
        int len1 = str1.length();
        int len2 = str2.length();
        Boolean[][] matrix = new Boolean[len1+1][len2+1];
        matrix[len1][len2] = true;
        char[] targetArr = target.toCharArray();
        char[] str1Arr = str1.toCharArray();
        char[] str2Arr = str2.toCharArray();
        for (int i = len1; i >= 0 ; i--) {
            for (int j = len2; j >= 0; j--) {
                if(i == len1 && j == len2) continue;
                int index = target.length()-(len1-i + len2-j);
                boolean currVal = false;
                if(i+1 > len1){
                    currVal = matrix[i][j+1]&&(str2Arr[j] == targetArr[index]);
                }else if(j+1 > len2){
                    currVal = matrix[i+1][j]&&(str1Arr[i] == targetArr[index]);
                }else{
                    currVal = (matrix[i][j+1]&&(str2Arr[j]==targetArr[index]))|(matrix[i+1][j]&&(str1Arr[i]==targetArr[index]));
                }
                matrix[i][j] = currVal;
            }
        }
        this.<Boolean>print(matrix);
        return matrix[0][0];
    }

    public <T> void print(T[][] matrix){
        for(T[] m : matrix){
            for (T b: m) {
                System.out.print(b+" ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        InterleavingString is = new InterleavingString();
        System.out.println(is.isInterleaving("chips","chocolate", "cchocohilaptes"));
        System.out.println(is.isInterleaving("chips","chocolate", "chocochilatspe"));
    }
}
