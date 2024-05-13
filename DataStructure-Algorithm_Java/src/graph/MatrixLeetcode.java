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
    //48 medium -> rotate matrix 90 degree
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // Swap the elements on both sides of the diagonal of the matrix
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        // Swap columns
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n-j-1];
                matrix[i][n-j-1] = temp;
            }
        }
    }
    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        //step1: swap rows
        int top = 0, buttom = n - 1;
        while (top < buttom) {
            int[] temp = matrix[top];
            matrix[top] = matrix[buttom];
            matrix[buttom] = temp;
            top++;
            buttom--;
        }

        //step2: swap the elements on both sides of the diagonal of the matrix
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    //73 medium -> Set matrix zeroes
    public void setZeroes(int[][] matrix) {
        List<int[]> list = new LinkedList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    list.add(new int[] {i, j});
                }
            }
        }
        for (int[] arr : list) {
            int i = arr[0];
            int j = arr[1];
            for (int col = 0; col < matrix[0].length; col++) {
                matrix[i][col] = 0;
            }
            for (int row = 0; row < matrix.length; row++) {
                matrix[row][j] = 0;
            }
        }
    }
    //73 O(1) speace
    public void setZeroes2(int[][] matrix) {
        boolean rowHasZero = false;
        boolean colHasZero = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0) rowHasZero = true;
                    if (j == 0) colHasZero = true;

                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int r = 1; r < matrix.length; r++) {
            if (matrix[r][0] == 0) {
                for (int c = 1; c < matrix[0].length; c++) {
                    matrix[r][c] = 0;
                }
            }
        }

        for (int c = 1; c < matrix[0].length; c++) {
            if (matrix[0][c] == 0) {
                for (int r = 1; r < matrix.length; r++) {
                    matrix[r][c] = 0;
                }
            }
        }

        if (rowHasZero) {
            for (int n = 0; n < matrix[0].length; n++) {
                matrix[0][n] = 0;
            }
        }

        if (colHasZero) {
            for (int m = 0; m < matrix.length; m++) {
                matrix[m][0] = 0;
            }
        }
    }

    //289 medium -> game of life
    public void gameOfLife(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 1) {
                    int count = liveHelper(board, i, j);
                    if (count == 2 || count == 3) {
                        board[i][j] += 2;
                    }
                }
                if (board[i][j] == 0) {
                    int count = liveHelper(board, i, j);
                    if (count == 3) {
                        board[i][j] += 2;
                    }
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] >>= 1;
            }
        }
    }

    private int liveHelper(int[][] board, int i, int j) {
        int count = 0;
        for (int r = Math.max(0, i - 1); r <= Math.min(i + 1, board.length - 1); r++) {
            for (int c = Math.max(0, j - 1); c <= Math.min(j + 1, board[0].length - 1); c++) {
                if (r == i && c == j) continue;
                // 3 & 1 = 1 -> 11 & 01 = 01;
                // 2 & 1 = 0 -> 10 & 01 = 00;
                if ((board[r][c] & 1) == 1) count++;
            }
        }
        return count;
    }

    //200 medium -> number of islands
    class Solution {
        private int row;
        private int col;
        public int numIslands(char[][] grid) {
            row = grid.length;
            if (row == 0 ) return 0;
            col = grid[0].length;
            int islandNum = 0;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (grid[i][j] == '1') {
                        dfs(grid, i, j);
                        islandNum++;
                    }
                }
            }
            return islandNum;
        }
        private void dfs(char[][] matrix, int r, int c) {
            if (r < 0 || r >= row || c < 0 || c >= col || matrix[r][c] == '0') return;
            matrix[r][c] = '0';
            dfs(matrix, r + 1, c);
            dfs(matrix, r - 1, c);
            dfs(matrix, r, c + 1);
            dfs(matrix, r, c - 1);
        }
    }

    //130 medium -> surrounded regions
    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;
        int r = board.length - 1;
        int c = board[0].length - 1;
        //top and bottom border
        for (int i = 0; i <= c; i++) {
            if (board[0][i] == 'O') dfs(board, 0, i);
            if (board[r][i] == 'O') dfs(board, r, i);
        }
        //left and right border
        for (int j = 0; j <= r; j++) {
            if (board[j][0] == 'O') dfs(board, j, 0);
            if (board[j][c] == 'O') dfs(board, j, c);
        }
        //let 'O' = 'X', 'A' = 'O'
        for (int n = 0; n <= r; n++) {
            for (int m = 0; m <= c; m++) {
                if (board[n][m] == 'O') board[n][m] = 'X';
                else if (board[n][m] == 'A') board[n][m] = 'O';
            }
        }
    }
    private void dfs(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != 'O') return;
        board[i][j] = 'A';
        dfs(board, i+1, j);
        dfs(board, i-1, j);
        dfs(board, i, j+1);
        dfs(board, i, j-1);
    }
}
