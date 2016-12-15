package LeetCode.design;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by kashishtayal on 11/29/16.
 */
public class MovingAverage {
    int windowSize;
    Queue<Integer> queue;
    double currSum;
    double currSize;
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        windowSize = size;
        queue = new LinkedList<Integer>();
        currSum = 0;
        currSize = 0;
    }

    public double next(int val) {
        System.out.println(queue);
        currSum += val;
        queue.add(val);
        if(currSize < windowSize){
            currSize++;
            return currSum/(currSize);
        }else{
            currSum -= queue.remove();
            return (currSum)/windowSize;
        }
    }

    public static void main(String[] args) {
        MovingAverage ma = new MovingAverage(3);
        System.out.println(ma.next(1) == 1);
        System.out.println(ma.next(2) == 1.5);
        System.out.println(ma.next(3) == 2);
        System.out.println(ma.next(4) == 3);
        System.out.println(ma.next(5) == 4);
        System.out.println(ma.next(6) == 5);
    }
}
