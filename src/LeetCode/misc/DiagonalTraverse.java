package LeetCode.misc;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by kashishtayal on 3/1/17.
 */
public class DiagonalTraverse {
    int ROWS;
    int COLS;
    public int[] findDiagonalOrder(int[][] matrix) {
        ROWS = matrix.length;
        if(ROWS > 0) {
            COLS = matrix[0].length;
        }
        Deque<Integer> dq = new LinkedList<>();
        int[] result = new int[ROWS*COLS];
        boolean colIncrement = true;
        boolean inorder = false;
        int r = 0;
        int c = -1;
        for(int index = 0; index < result.length; ){
            if(colIncrement){
                c++;
                update(dq,matrix,r,c);
                if(c == COLS-1)colIncrement = false;
            }else{
                r++;
                update(dq,matrix,r,c);
            }
            while(!dq.isEmpty()){
                result[index] = inorder?dq.removeFirst():dq.removeLast();
                index++;
            }
            inorder = !inorder;
        }
        return result;
    }
    private void update(Deque<Integer> dq, int[][] matrix, int i, int j){
        while(j > -1 && i < ROWS){
            dq.add(matrix[i][j]);
            i++;
            j--;
        }
    }
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                new int[]{1,2,3},
                new int[]{4,5,6},
                new int[]{7,8,9},
                new int[]{10,11,12}
        };
        DiagonalTraverse dt = new DiagonalTraverse();
        Arrays.stream(dt.findDiagonalOrder(matrix)).forEach(System.out::println);
    }
}
