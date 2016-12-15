package LeetCode.design;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by kashishtayal on 12/1/16.
 */
public class SnakeGame {
    Deque<Integer> snake;
    int maxWidth;
    int maxHeight;
    int[][] food;
    int foodCounter = 0;
    /** Initialize your data structure here.
     @param width - screen width
     @param height - screen height
     @param inFood - A list of food positions
     E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public SnakeGame(int width, int height, int[][] inFood) {
        snake = new LinkedList<>();
        snake.addLast(0);
        maxWidth = width;
        maxHeight = height;
        food = inFood;
    }

    /** Moves the snake.
     @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     @return The game's score after the move. Return -1 if game over.
     Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        int head = snake.peekFirst();
        int nextW = head/maxWidth;
        int nextH = head%maxWidth;
        if(direction.equals("U")){
            nextH--;
        }else if(direction.equals("L")){
            nextW--;
        }else if(direction.equals("R")){
            nextW++;
        }else if(direction.equals("D")){
            nextH++;
        }else{
            return -1;
        }
        if(!isInBoard(nextW,nextH)){
            return -1;
        }
        if(isHeadingToItself(nextW,nextH)){
            return -1;
        }
        snake.addFirst(nextH*maxWidth+nextW);
        if(!hasFoodinLoc(nextW,nextH)){
            snake.removeLast();
        }else{
            foodCounter++;
        }
        return snake.size()-1;
    }
    private boolean isInBoard(int inW, int inH){
        return (inW > -1 && inW < maxWidth) && (inH > -1 && inH < maxHeight);
    }

    private boolean hasFoodinLoc(int inW, int inH){
        if(foodCounter >= food.length) return false;
        int[] foddLoc = food[foodCounter];
        return foddLoc[0] == inH && foddLoc[1] == inW;
    }
    private boolean isHeadingToItself(int inW, int inH){
        int tail = snake.removeLast();
        boolean result = snake.contains(inH*maxWidth+inW);
        snake.addLast(tail);
        return result;
    }
//    class Loc{
//        int w;
//        int h;
//        public Loc(int inW, int inH){
//            w = inW;
//            h = inH;
//        }
//    }

    public static void main(String[] args) {
        int[][] food = new int[][]{
                new int[]{0,1}
        };
        SnakeGame snake = new SnakeGame(2,2,food);
        System.out.println(snake.move("R") == 1);
        System.out.println(snake.move("D") == 1);
    }
}
