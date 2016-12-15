package LeetCode.DynamicPrograming;

import java.util.List;

/**
 * Created by kashishtayal on 11/9/16.
 */
public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        int min = triangle.get(0).get(0);
        List<Integer> prevLevel = triangle.get(0);
        for(int i = 1; i < triangle.size(); i++){
            min = Integer.MAX_VALUE;
            List<Integer> list = triangle.get(i);
            list.set(0,list.get(0)+prevLevel.get(0));
            if(list.get(0) < min) min = list.get(0);
            list.set(list.size()-1,list.get(list.size()-1)+prevLevel.get(prevLevel.size()-1));
            if(list.get(list.size()-1) < min) min = list.get(list.size()-1);
            for(int j = 1; j < list.size()-1; j++){
                list.set(j,list.get(j)+Math.min(prevLevel.get(j-1),prevLevel.get(j)));
                if(list.get(j) < min) min = list.get(j);
            }
            prevLevel = list;
        }
        return min;
    }

}
