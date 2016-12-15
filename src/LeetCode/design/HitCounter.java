package LeetCode.design;

import sun.reflect.generics.tree.Tree;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by kashishtayal on 12/3/16.
 */
public class HitCounter {
    private static final double CUT_OFF_TIME = 300d;
    TreeMap<Double, Double> hitTimeline;
    double counter = 0;
    /** Initialize your data structure here. */
    public HitCounter() {
        hitTimeline = new TreeMap<>();
    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        counter++;
        double stamp = (double) timestamp;
        hitTimeline.put(stamp,counter);

    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        double currTimeStamp = (double) timestamp;
        double cutOffTimeStamp = currTimeStamp-CUT_OFF_TIME;
        double hitsBeforeTimeStamp = 0;
        double hitsBeforeCutOff = 0;
        boolean foundCutOff = false;
        boolean foundTimeStamp = false;
        if(hitTimeline.containsKey(currTimeStamp)){
            hitsBeforeTimeStamp = hitTimeline.get(currTimeStamp);
            foundTimeStamp = true;
        }
        if(hitTimeline.containsKey(cutOffTimeStamp)){
            hitsBeforeCutOff = hitTimeline.get(cutOffTimeStamp);
            foundCutOff = true;
        }
        if(!foundCutOff || ! foundTimeStamp) {
            for (Map.Entry<Double, Double> time : hitTimeline.entrySet()) {
                Double key = time.getKey();
                Double value = time.getValue();
                if(!foundCutOff && key <= cutOffTimeStamp){
                    hitsBeforeCutOff = value;
                }
                if(!foundTimeStamp && key <= currTimeStamp){
                    hitsBeforeTimeStamp = value;
                }
                if(key > currTimeStamp){
                    break;
                }
            }
        }
        return (int) (hitsBeforeTimeStamp - hitsBeforeCutOff);
    }

    public static void main(String[] args) {
        HitCounter counter = new HitCounter();
        // hit at timestamp 1.
        counter.hit(1);

// hit at timestamp 2.
        counter.hit(2);

// hit at timestamp 3.
        counter.hit(3);

// get hits at timestamp 4, should return 3.
        System.out.println(counter.getHits(4));

// hit at timestamp 300.
        counter.hit(300);

// get hits at timestamp 300, should return 4.

        System.out.println(counter.getHits(300));

// get hits at timestamp 301, should return 3.
        System.out.println(counter.getHits(301));
        counter.hit(400);
        counter.hit(400);
        counter.hit(400);
        counter.hit(400);
        counter.hit(400);
        System.out.println(counter.getHits(500));
    }
}
