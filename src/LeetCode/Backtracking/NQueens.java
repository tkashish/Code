package LeetCode.Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;

/**
 * Created by kashishtayal on 10/22/16.
 */
public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        backtrack(result,n,0,board);
        return result;
    }

    public void backtrack(List<List<String>> result, int n, int k, char[][] board){
//        print(board);
//        System.out.println(k);
        if(k == n){
            List<String> list = new ArrayList<>();
            for(char[] arr : board){
                list.add(new String(arr));
            }
            result.add(list);
        } else{
            for(int i = 0; i < n; i++){
                if(board[k][i] == Character.MIN_VALUE) {
                    char[][] boardI = new char[n][n];
                    for(int j = 0; j < n; j++){
                        boardI[j] = Arrays.copyOf(board[j],n);
                    }
                    updateBoard(boardI, k, i, n);
                    boardI[k][i] = 'Q';
                    backtrack(result, n, k + 1, boardI);
                }
            }
        }
    }
    public void print(char[][] board){
        System.out.println("---------");
        for(char[] c : board){
            System.out.println(new String(c));
        }
    }
    public char[][] updateBoard(char[][] board, int i, int j, int n){
        for (int m = 0; m < n; m++){
            board[i][m] = '.';
            board[m][j] = '.';
            int x = j-i;
            if(m+x >= 0 && m+x <n){
                board[m][m+x]='.';
            }
            int jj = n-1-j;
            x = i-jj;
            int mm = n-1-m;
            if(mm+x >= 0 && mm+x < n){
                board[m][mm+x] = '.';
            }
        }
        return board;
    }

    public static void main(String[] args) {
        NQueens nq = new NQueens();
        System.out.println(nq.solveNQueens(5));
        System.out.println(nq.solveNQueensI(5).size());
    }

    public List<List<String>> solveNQueensI(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for(int i = 0; i < n; i++){
            Arrays.fill(board[i],'.');
        }
        backtrackI(result,n,0,board);
        return result;
    }

    private void backtrackI(List<List<String>> result, int n, int k, char[][] board) {
        if(k == n){
            List<String> list = new ArrayList<>();
            for(char[] c : board){
                list.add(new String(c));
            }
            result.add(list);
        }else{
            for(int i = 0; i < n; i++){
                if(validate(board,k,i,n)){
                    board[k][i] = 'Q';
                    backtrackI(result,n,k+1,board);
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
