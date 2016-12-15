package LeetCode.design;

/**
 * Created by kashishtayal on 12/3/16.
 */
public class TicTacToe {
    int[][] board;
    int moveCount1 = 0;
    int moveCount2 = 0;
    int N = 0;
    boolean won = false;
    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        board = new int[n][n];
        N = n;
    }

    /** Player {player} makes a move at ({row}, {col}).
     @param row The row of the board.
     @param col The column of the board.
     @param player The player, can be either 1 or 2.
     @return The current winning condition, can be either:
     0: No one wins.
     1: Player 1 wins.
     2: Player 2 wins. */
    public int move(int row, int col, int player) {
        if(won) return -1;
        board[row][col] = player;
        int result = 0;
        if(player == 1){
            moveCount1++;
            if(moveCount1 >= N){
                 result = checkWin(row,col,player) ? 1 : 0;
            }
        }else{
            moveCount2++;
            if(moveCount2 >= N){
                result = checkWin(row,col,player) ? 2 : 0;
            }
        }
        if(result == 1 ) won = true;
        return result;
    }
    private boolean checkWin(int row, int col, int player){
        boolean onDiagonal = (row == col) || (row == (N-1-col));
        boolean resultHorizontal = true;
        boolean resultVertical = true;
        boolean resultDiagonalMain = onDiagonal;
        boolean resultDiagonalAux = onDiagonal;
        for(int i = 0; i < N; i++){
            if(resultHorizontal && board[row][i] != player){
                resultHorizontal = false;
            }
            if(resultVertical && board[i][col] != player){
                resultVertical = false;
            }
            if(resultDiagonalMain && board[i][i] != player){
                resultDiagonalMain = false;
            }
            if(resultDiagonalAux && board[i][N-1-i] != player){
                resultDiagonalAux = false;
            }
        }
        return resultHorizontal || resultVertical || resultDiagonalMain || resultDiagonalAux;
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe(3);
        System.out.println(game.move(0,0,1));
        System.out.println(game.move(0,2,2));
        System.out.println(game.move(2,2,1));
        System.out.println(game.move(1,1,2));
        System.out.println(game.move(2,0,1));
        System.out.println(game.move(1,0,2));
        System.out.println(game.move(2,1,1));
        System.out.println(game.move(0,1,2));
    }
}
