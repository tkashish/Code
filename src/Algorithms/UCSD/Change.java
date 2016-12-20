package Algorithms.UCSD;

public class Change {
    private static int getChange(int m) {
        int count = 0;
        while(m > 0){
            if(m >= 10){
                m -= 10;
            }else if (m >= 5){
                m -= 5;
            }else if(m >= 1){
                m-= 1;
            }
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(getChange(28));
    }
}

