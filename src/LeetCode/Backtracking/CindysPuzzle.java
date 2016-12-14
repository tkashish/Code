package LeetCode.Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * https://www.cis.upenn.edu/~matuszek/cit594-2012/Pages/backtracking.html
 *
 * I call the following puzzle "Cindy's puzzle" for historical reasons. You have some number n of black marbles and the same number of white marbles, and you have a playing board which consists simply of a line of 2n+1 spaces to put the marbles in. Start with the black marbles all at one end (say, the left), the white marbles all at the other end, and a free space in between.


 The goal is to reverse the positions of the marbles:


 The black marbles can only move to the right, and the white marbles can only move to the left (no backing up). At each move, a marble can either:

 Move one space ahead, if that space is clear, or
 Jump ahead over exactly one marble of the opposite color, if the space just beyond that marble is clear.

 * Created by tayalka on 7/28/2016.
 */
public class CindysPuzzle {
    public static boolean hasSolution(String[] board){
        print(board);
        if(check(board)){
            print(board);
            return true;
        }
        List<String[]> possibleMoves = generateAllPossibleMoves(board);
//        printList(possibleMoves);
        for(String[] newBoard : possibleMoves){
            if(hasSolution(newBoard)) return true;
        }
        return false;
    }
    private static boolean check(String[] board){
        int len = board.length;
        if(!board[len/2].equals("")) return false;
        for(int i = 0; i < len-1; i++) {
            if(i < len/2 && board[i].equals("B")){ return false; }
            else if(i > len/2 && board[i].equals("W")) return false;
        }
        return true;
    }
    private static List<String[]> generateAllPossibleMoves(String[] board){
        List<String[]> listOfPossibleMoves = new ArrayList<>();
        for(int i = 0; i <= board.length-2; i++){
            int j = board.length-1-i;
            if(board[i].equals("B")){
                if (board[i+1].equals("")){
                    listOfPossibleMoves.add(move(Arrays.copyOf(board,board.length),i,i+1));
                }else if(!board[i].equals(board[i+1])){
                    if(i+2 <= board.length-1){
                        if(board[i+2].equals("")){
                            listOfPossibleMoves.add(move(Arrays.copyOf(board,board.length),i,i+2));
                        }
                    }
                }
            }
            if(board[j].equals("W")){
                if(board[j-1].equals("")){
                    listOfPossibleMoves.add(move(Arrays.copyOf(board,board.length),j,j-1));
                }else if(!board[j].equals(board[j-1])){
                    if(j-2 >= 0){
                        if(board[j-2].equals("")){
                            listOfPossibleMoves.add(move(Arrays.copyOf(board,board.length),j,j-2));
                        }
                    }
                }
            }
        }
        return listOfPossibleMoves;
    }
    public static void printList(List<String[]> boards){
        for (String[] s : boards){
            System.out.print("  ");
            print(s);
        }
    }
    public static void print(String[] board){
        for (String s : board){
            if(s.equals("")) System.out.print("|_|");
            else System.out.print(s);
        }
        System.out.println();
    }
    private static String[] move(String[] board, int i, int j){
        if(!board[j].equals("")) System.out.println("invalid move");
        board[j] = board[i];
        board[i] = "";
//        System.out.print("      ");print(board);
        return board;
    }

    private static String[] gen(int n){
        int m = 2*n+1;
        String[] ret = new String[m];
        for(int i = 0; i < m; i++){
            if(i < m/2) ret[i] = "B";
            else if(i > m/2) ret[i] = "W";
            else ret[i] = "";
        }
        print(ret);
        return ret;
    }

    public static void main(String[] args) {
//        for (int i = 1; i < 100; i++) {
//            System.out.println(i);
//            System.out.println(hasSolution(gen(i)));
//        }
        System.out.println(Boolean.TRUE.equals(null));
    }
}
