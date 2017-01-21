package LeetCode.DivideAndConquer;

/**
 * Created by kashishtayal on 1/20/17.
 */
public class Search2DMatrixII {
    public boolean searchMatrix(int[][] matrix, int target) {
        int wid = matrix.length;
        if(wid == 0) return false;
        int len = matrix[0].length;
        if(len == 0) return false;
        int column = binarySearch(matrix[0],0,len-1,target);
        if(column < 0) return false;
        if (matrix[0][column] == target) return true;
        int startRow = 0;
        for(int i = column ; i > -1 ; i--){
            startRow = binarySearch(matrix,i, startRow, wid-1, target);
            if(startRow < 0)continue;
            if(matrix[startRow][i] == target){
                return true;
            }
        }
        return false;
    }
    private int binarySearch(int[] nums, int start, int end, int num){
        if(start > end) return start-1;
        if(start == end) return start;
        int mid = start + (end-start)/2;
        if(nums[mid] < num){
            if(mid == start){
                if (mid == end || nums[mid+1] > num){
                    return mid;
                }else {
                    return binarySearch(nums,mid+1,end,num);
                }
            }
            return binarySearch(nums,mid,end,num);
        }else if(nums[mid] > num){
            return binarySearch(nums,start,mid-1, num);
        }else{
            return mid;
        }
    }
    private int binarySearch(int[][] nums, int column, int start, int end, int num){
        if(end < start) return start-1;
        if(start == end) return start;
        int mid = start + (end-start)/2;
        if(nums[mid][column] < num){
            if(mid == end || nums[mid+1][column] > num){
                return mid;
            }else{
                return binarySearch(nums,column,mid+1,end,num);
            }
        }else if(nums[mid][column] > num){
            return binarySearch(nums,column,start,mid-1, num);
        }else{
            return mid;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                new int[]{1,   4,  7, 11, 15},
                new int[]{2,   5,  8, 12, 19},
                new int[]{3,   6,  9, 17, 22},
                new int[]{10, 13, 14, 17, 24},
                new int[]{18, 21, 23, 26, 30}
        };
        Search2DMatrixII search = new Search2DMatrixII();
        System.out.println(search.searchMatrix(matrix,17));
    }
}
