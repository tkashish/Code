package LeetCode.misc;

import java.util.Stack;

/**
 * Created by kashishtayal on 1/15/17.
 */
public class LongestAbsoluteFilePath {
    public static int lengthLongestPath(String input) {
        String[] filesAndDirs = input.split("\n");
        Stack<String> stack = new Stack<>();
        int len = 0;
        int lastDepth = 0;
        int max = 0;
        for(String s : filesAndDirs){
            String[] currDirList = s.split("\t");
            int currDepth = currDirList.length;
            String currDir = currDirList[currDepth-1];
            if(currDepth <= lastDepth){
                int numPops = lastDepth - currDepth + 1;
                while(numPops > 0){
                    String popedDir = stack.pop();
                    len -= (popedDir.length() + 1);
                    numPops--;
                }
            }
            stack.push(currDir);
            len += currDir.length()+1;
            lastDepth = currDepth;
            if(stack.peek().contains(".")) {
                max = Math.max(max, len);
            }
        }
        if(max > 0) max--;
        return max;
    }

    public static void main(String[] args) {
        System.out.println(LongestAbsoluteFilePath.lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"));
    }
}
