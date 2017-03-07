package LeetCode.misc;

import java.util.List;
import java.util.Stack;

/**
 * Created by kashishtayal on 2/20/17.
 */
public class ConvexPolygon {
    public boolean isConvex(List<List<Integer>> points) {
        boolean isClockwise = false;
        boolean first = true;
        int len = points.size();
        for(int i = 0; i < len; i++){
            List<Integer> x1y1 = points.get(i);
            List<Integer> x2y2 = points.get((i+1)%len);
            List<Integer> x3y3 = points.get((i+2)%len);
            int rotation = (x2y2.get(1)-x1y1.get(1))*(x3y3.get(0)-x2y2.get(0)) - (x2y2.get(0)-x1y1.get(0))*(x3y3.get(1)-x2y2.get(1));
            if(first){
                isClockwise = rotation > 0 ? true : false;
                if(rotation != 0) first = false;
            }else{
                if(isClockwise && rotation < 0)return false;
                if(!isClockwise && rotation > 0)return false;
            }
        }
        return true;
    }
    public static int largestRectangleArea(int[] height) {
        int len = height.length;
        Stack<Integer> s = new Stack<Integer>();
        int maxArea = 0;
        for(int i = 0; i <= len; i++){
            int h = (i == len ? 0 : height[i]);
            if(s.isEmpty() || h >= height[s.peek()]){
                s.push(i);
            }else{
                int tp = s.pop();
                maxArea = Math.max(maxArea, height[tp] * (s.isEmpty() ? i : i - 1 - s.peek()));
                i--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] hist = new int[]{2,1,2,1};
        System.out.println(largestRectangleArea(hist));
    }
}
