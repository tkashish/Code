package LeetCode.DynamicPrograming;

/**
 * Created by kashishtayal on 11/10/16.
 */
public class NumMatrix {
    int[][] _matrix;
    public NumMatrix(int[][] matrix) {
        _matrix = matrix;
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++) {
                if (i == 0 && j == 0) continue;
                if (i - 1 < 0) {
                    _matrix[i][j] += _matrix[i][j - 1];
                } else if (j - 1 < 0) {
                    _matrix[i][j] += _matrix[i - 1][j];
                } else {
                    _matrix[i][j] += _matrix[i - 1][j] + _matrix[i][j - 1] - _matrix[i - 1][j - 1];
                }
            }
        }
    }
    public int sumRegion(int row1, int col1, int row2, int col2) {

        if (col1 - 1 < 0 && row1 - 1 >= 0) {
            return _matrix[row2][col2] - _matrix[row1 - 1][col2];
        } else if (col1 - 1 >= 0 && row1 - 1 < 0) {
            return _matrix[row2][col2] - _matrix[row2][col1 - 1];
        } else if (col1 - 1 < 0 && row1 - 1 < 0) {
            return _matrix[row2][col2];
        } else {
            return _matrix[row2][col2] - _matrix[row2][col1 - 1] - _matrix[row1 - 1][col2] + _matrix[row1 - 1][col1 - 1];
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
          new int[]{3, 0, 1, 4, 2},
          new int[]{5, 6, 3, 2, 1},
          new int[]{1, 2, 0, 1, 5},
          new int[]{4, 1, 0, 1, 7},
          new int[]{1, 0, 3, 0, 5}
        };
        NumMatrix numMatrix = new NumMatrix(matrix);
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
        System.out.println(numMatrix.sumRegion(1, 1, 2, 2));
        System.out.println(numMatrix.sumRegion(1, 2, 2, 4));
    }
}

