package LeetCode.misc;

/**
 * Created by kashishtayal on 2/27/17.
 */
public class SuperWashingMachines {
    public static int findMinMoves(int[] machines) {
        int len = machines.length;
        if(len == 0 || len == 1) return 0;
        int[] sumArr = new int[len];
        sumArr[0] = machines[0];
        for(int i = 1; i < len; i++){
            sumArr[i] = sumArr[i-1]+machines[i];

        }
        if(sumArr[len-1]%len != 0) return -1;
        int perCell = sumArr[len-1]/len;
        int count = 0;
        for(int i = 0; i < len; i++){
            if(sumArr[i] <= perCell) continue;
            int sumLeft = -1;
            int sumRight = -1;
            if(i > 0)sumLeft = (perCell*i)-sumArr[i-1];
            if(i < len-1) sumRight = ((perCell*(len-i-1)))-(sumArr[len-1]-sumArr[i]);
            if(sumRight < 0)sumRight = 0;
            if(sumLeft < 0)sumLeft = 0;
            count = Math.max(count,sumLeft+sumRight);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] machines = new int[]{0,3,0};
        System.out.println(findMinMoves(machines));
    }
}
