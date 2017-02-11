package LeetCode.UnionFind;

import java.util.Arrays;

/**
 * Created by kashishtayal on 2/10/17.
 */
public class SurroundedRegions {
    int rows;
    int cols;
    private static final char CHAR = 'L';
    public void solve(char[][] board) {
        rows = board.length;
        if(rows <= 2 ){
            return;
        }
        cols = board[0].length;
        for(int i = 0; i < rows; i++){
            if(board[i][0] == 'O'){
                computeLocsNotToConvert(i,0,board);
            }
            if(board[i][cols-1] == 'O'){
                computeLocsNotToConvert(i,cols-1,board);
            }
        }

        for(int j = 0; j < cols; j++){
            if(board[0][j] == 'O'){
                computeLocsNotToConvert(0,j,board);
            }
            if(board[rows-1][j] == 'O'){
                computeLocsNotToConvert(rows-1,j,board);
            }
        }

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(board[i][j] == CHAR){
                    board[i][j] = 'O';
                }
                if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
            }
        }

    }

    public void computeLocsNotToConvert(int r, int c, char[][] inBoard){
        int loc = r*cols+c;
        inBoard[r][c] = CHAR;
        if(r-1 >= 0 && inBoard[r-1][c] == 'O'){
            computeLocsNotToConvert(r-1,c,inBoard);
        }
        if(r+1 < rows && inBoard[r+1][c] == 'O'){
            computeLocsNotToConvert(r+1,c,inBoard);
        }
        if(c-1 >= 0 && inBoard[r][c-1] == 'O'){
            computeLocsNotToConvert(r,c-1,inBoard);
        }
        if(c+1 < cols && inBoard[r][c+1] == 'O'){
            computeLocsNotToConvert(r,c+1,inBoard);
        }
    }
    public static void main(String[] args) {
        char[][] board = new char[][]{
                "XXXX".toCharArray(),
                "XOOX".toCharArray(),
                "XXOX".toCharArray(),
                "XOXX".toCharArray()
        };
        Arrays.stream(board).forEach(System.out::println);
        SurroundedRegions regions = new SurroundedRegions();
        regions.solve(board);
        System.out.println();
        Arrays.stream(board).forEach(System.out::println);
    }
}