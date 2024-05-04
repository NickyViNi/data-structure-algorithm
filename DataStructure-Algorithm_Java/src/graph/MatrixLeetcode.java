package graph;

import java.util.LinkedList;
import java.util.List;

public class MatrixLeetcode {
    //54 medium -> Spiral Matrix
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ll = new LinkedList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return ll;
        int row = matrix.length;
        int col = matrix[0].length;
        int top = 0, bottom = row - 1, left = 0, right = col - 1;
        while (top <= bottom && left <= right) {
            // Traverse top row
            for (int i = left; i <= right; i++) {
                ll.add(matrix[top][i]);
            }
            top++;
            // Traverse right column
            for (int i = top; i <= bottom; i++) {
                ll.add(matrix[i][right]);
            }
            right--;
            // Traverse bottom row
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    ll.add(matrix[bottom][i]);
                }
                bottom--;
            }
            // Traverse left column
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    ll.add(matrix[i][left]);
                }
                left++;
            }
        }
        return ll;
    }

}
