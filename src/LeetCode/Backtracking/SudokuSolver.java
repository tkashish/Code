package LeetCode.Backtracking;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by kashishtayal on 10/22/16.
 */
public class SudokuSolver {
    public void solveSudoku(char[][] board) {
        backtrack(board,0,0);
    }
    public boolean backtrack(char[][] board, int i, int j){
        if(i == board.length || j == board[0].length){
            return true;
        }else{
            if(board[i][j] == '.'){
                Set<Character> set = getUnUsedNumbers(board,i,j);
                if(set.isEmpty()) return false;
                for(char c : set){
                    board[i][j] = c;
                    if(!backtrack(board,i+1,j)){
                        board[i][j] = '.';
                        continue;
                    }
                    if(!backtrack(board,i+1,j+1)){
                        board[i][j] = '.';
                        continue;
                    }
                    if(!backtrack(board,i,j+1)){
                        board[i][j] = '.';
                        continue;
                    }
                    break;
                }
            }else{
                backtrack(board,i+1,j);
                backtrack(board,i+1,j+1);
                backtrack(board,i,j+1);
            }
            return true;
        }
    }
    public Set<Character> getUnUsedNumbers(char[][] board,int x, int y){
        Set<Character> result = new HashSet<>();
        int n = x/3;
        int m = y/3;
        for(char i = '1'; i <= '9'; i++){
            result.add(i);
        }
        for(int i = 0; i < 9; i++){
            if(board[x][i] != '.') result.remove(board[x][i]);
            if(board[i][y] != '.') result.remove(board[i][y]);
        }
        for(int i = n*3; i < (n+1)*3; i++){
            for (int j = m*3; j < (m+1)*3; j++) {
                if(board[i][j] != '.') result.remove(board[i][j]);
            }
        }
        return result;
    }
    public void print(char[][] board){
        for(char[] c : board){
            System.out.println(new String(c));
        }
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                    "..9748...".toCharArray(),
                    "7........".toCharArray(),
                    ".2.1.9...".toCharArray(),
                    "..7...24.".toCharArray(),
                    ".64.1.59.".toCharArray(),
                    ".98...3..".toCharArray(),
                    "...8.3.2.".toCharArray(),
                    "........6".toCharArray(),
                    "...2759..".toCharArray()
        };
        SudokuSolver ss = new SudokuSolver();
        ss.solveSudoku(board);
        ss.print(board);
    }
}
