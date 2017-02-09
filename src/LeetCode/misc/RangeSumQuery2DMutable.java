package LeetCode.misc;

/**
 * Created by kashishtayal on 2/7/17.
 */
public class RangeSumQuery2DMutable {
    int[][] dp;
    int[][] _matrix;
    boolean initialization = true;
    public RangeSumQuery2DMutable(int[][] matrix) {
        int rows = matrix.length;
        int cols = 0;
        if(rows > 0){
            cols = matrix[0].length;
        }
        dp = new int[rows][cols+1];
        _matrix = matrix;
//        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                update(0,j,_matrix[0][j]);
            }
//        }
        initialization = false;
    }

    private int getUpdateParent(int index){
        int i = index;
        i += i&(-i);
        return i;
    }
    private int getSumParent(int index){
        index -= index&(-index);
        return index;
    }

    public void update(int row, int col, int val) {
        int change = -_matrix[row][col]+val;
        for(int i = row; i < _matrix.length; i++){
            int parent = col+1;
            if(initialization) change+=_matrix[i][col];
            while(parent < dp[0].length){
                dp[i][parent] += change;
                parent += parent&(-parent);
            }
        }
        _matrix[row][col] = val;
    }

    private int sum(int row, int col){
        int sum = 0;
        int parent = col+1;
        while(parent > 0){
            sum += dp[row][parent];
            parent -= parent&(-parent);
        }
        return sum;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int result = sum(row2,col2);
        if(row1 == 0 && col1 == 0){
            return result;
        }else if(row1 == 0){
            result -= sum(row2,col1-1);
        }else if(col1 == 0){
            result -= sum(row1-1,col2);
        }else{
            result = result-sum(row2,col1-1) - sum(row1-1,col2) + sum(row1-1,col1-1);
        }
        return result;
    }
    public void print(int[][] m){
        for(int i = 0; i < m.length; i++){
            for (int j = 0; j < m[0].length; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("---");
    }
    public static void main(String[] args) {
        int[][] i = new int[][]{
                new int[]{0,-3,-8,3},
                new int[]{-9,3,5,3},
                new int[]{2,3,-5,3}
        };
        RangeSumQuery2DMutable r = new RangeSumQuery2DMutable(i);
        r.print(i);
        r.print(r.dp);
        r.update(0,1,9);
        r.print(r._matrix);
        r.print(r.dp);
        r.update(1,0,-7);
        r.print(r._matrix);
        r.print(r.dp);
        System.out.println(r.sumRegion(2,2,2,3));
        System.out.println(r.sumRegion(1,3,1,3));
    }
}
