package LeetCode.snapChat;

/**
 * Created by kashishtayal on 12/6/16.
 */
public class GameOfLife {
    public void gameOfLife(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int live = 0;
                for(int r = i-1; r <= i+1; r++){
                    for(int c = j-1; c <= j+1; c++){
                        if(r < 0 || r >= board.length) continue;
                        if(c < 0 || c >= board[0].length) continue;
                        if(r == i && c == j)continue;
                        if(board[r][c] == 1 || board[r][c] == 2)live++;
                    }
                }
                if(board[i][j] == 1){
                    if(live < 2 || live > 3) board[i][j] = 2;
                }else{
                    if(live == 3) board[i][j] = -1;
                }
            }
        }
        print(board);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = (board[i][j] == 1 || board[i][j] == -1) ? 1:0;
            }
        }
    }
    public void print(int[][] board){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        GameOfLife life = new GameOfLife();
        int[][] board = new int[][]{
                new int[]{0,0,0,0,0},
                new int[]{0,0,1,0,0},
                new int[]{0,0,1,0,0},
                new int[]{0,0,1,0,0},
                new int[]{0,0,0,0,0},
        };
        life.gameOfLife(board);
        System.out.println("  -----");
        life.print(board);
    }
}
