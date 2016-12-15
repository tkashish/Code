package LeetCode.design;

import java.util.HashMap;

/**
 * Created by kashishtayal on 11/29/16.
 */
public class Logger {
    HashMap<String,Integer> map;
    /** Initialize your data structure here. */
    public Logger() {
        map = new HashMap<>();
    }

    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
     If this method returns false, the message will not be printed.
     The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if(!map.containsKey(message)){
            map.put(message,timestamp);
            return true;
        }else{
            boolean result = false;
            if(timestamp - map.get(message) >= 10){
                result = true;
                map.put(message,timestamp);
            }
            return result;
        }
    }
}
