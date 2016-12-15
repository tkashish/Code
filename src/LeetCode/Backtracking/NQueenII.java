package LeetCode.Backtracking;

import java.util.Arrays;

/**
 * Created by kashishtayal on 10/22/16.
 */
public class NQueenII {
    int num = 0;
    public int totalNQueens(int n) {
        char[][] board = new char[n][n];
        for(int i = 0; i < n; i++){
            Arrays.fill(board[i],'.');
        }
        backtrackI(n,0,board);
        return num;
    }

    private void backtrackI(int n, int k, char[][] board) {
        if(k == n){
            num++;
        }else{
            for(int i = 0; i < n; i++){
                if(validate(board,k,i,n)){
                    board[k][i] = 'Q';
                    backtrackI(n,k+1,board);
                    board[k][i] = '.';
                }
            }
        }
    }
    private boolean validate(char[][] board, int i, int j, int n){
        for (int m = 0; m < n; m++){
            if(board[i][m] == 'Q') return false;
            if(board[m][j] == 'Q') return false;
            int x = j-i;
            if(m+x >= 0 && m+x <n){
                if(board[m][m+x]=='Q') return false;
            }
            int jj = n-1-j;
            x = i-jj;
            int mm = n-1-m;
            if(mm+x >= 0 && mm+x < n){
                if(board[m][mm+x]=='Q') return false;
            }
        }
        return true;
    }
}
