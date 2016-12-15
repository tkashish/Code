package LeetCode.design;

import java.util.*;

/**
 * Created by kashishtayal on 12/3/16.
 */
public class PhoneDirectory {
    Set<Integer> notAvailable;
    Queue<Integer> queue;
    int max;
    /** Initialize your data structure here
     @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        notAvailable = new HashSet<>();
        queue = new LinkedList<>();
        for(int i = 0; i < maxNumbers; i++){
            queue.add(i);
        }
        max = maxNumbers;
    }

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if(queue.isEmpty()){
            return -1;
        }else{
            int result = queue.remove();
            notAvailable.add(result);
            return result;
        }
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        if(number < 0 || number >= max)return false;
        return !notAvailable.contains(number);
    }

    /** Recycle or release a number. */
    public void release(int number) {
        if(notAvailable.contains(number)) {
            queue.add(number);
            notAvailable.remove(number);
        }
    }
}

