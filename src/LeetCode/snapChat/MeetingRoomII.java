package LeetCode.snapChat;

import java.util.*;

/**
 * Created by kashishtayal on 12/6/16.
 */
public class MeetingRoomII {
    public int minMeetingRooms(Interval[] intervals) {
        PriorityQueue<Interval> heap = new PriorityQueue<>(intervals.length,new comparatorI());
        for(Interval i  : intervals){
            heap.add(i);
        }
        List<Interval> rooms = new ArrayList<>();
        while(!heap.isEmpty()){
            Interval currInterval = heap.remove();
            System.out.println(currInterval.start);
            boolean needNewRoom = true;
            for(int i = 0; i < rooms.size(); i++){
                Interval interval = rooms.get(i);
                if(interval.end <= currInterval.start){
                    needNewRoom = false;
                    rooms.set(i,currInterval);
                    break;
                }
            }
            if(needNewRoom)rooms.add(currInterval);
        }
        return rooms.size();
    }
    class comparatorI implements Comparator<Interval> {
        @Override
        public int compare(Interval i1, Interval i2) {
            if(i1.start < i2.start){
                return -1;
            }else if(i1.start > i2.start){
                return 1;
            }else{
                return 0;
            }
        }
    }
    static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

    public static void main(String[] args) {
        MeetingRoomII meetingRoomII = new MeetingRoomII();
        Interval[] intervals = new Interval[3];
        intervals[0] = new Interval(0,30);
        intervals[1] = new Interval(5,10);
        intervals[2] = new Interval(15,20);
        System.out.println(meetingRoomII.minMeetingRooms(intervals));
    }
}
