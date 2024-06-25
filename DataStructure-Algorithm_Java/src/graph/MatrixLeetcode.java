package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

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

    //207 medium -> courses schedule
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //calculate each course has how many prerequisites
        int[] courses = new int[numCourses];
        for (int[] pre : prerequisites) {
            courses[pre[0]]++;
        }

        //add the courses which don't need prerequisites
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (courses[i] == 0) queue.offer(i);
        }

        //iterate the prerequisites, queue and courses
        while (!queue.isEmpty()) {
            int course = queue.poll();
            for (int[] re : prerequisites) {
                if (courses[re[0]] == 0) continue;
                if (re[1] == course) courses[re[0]]--;
                if (courses[re[0]] == 0) queue.offer(re[0]);
            }
        }

        //check whether the courses element greater than 0
        for (int course : courses) {
            if (course != 0) return false;
        }
        return true;
    }

    //210 medium -> courses scheduleII
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];
        int count = 0;
        //calculate each course has how many prerequisites
        int[] courses = new int[numCourses];
        for (int[] pre : prerequisites) {
            courses[pre[0]]++;
        }

        //add the courses which don't need prerequisites
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (courses[i] == 0)  {
                queue.offer(i);
                res[count++] = i;
            }
        }

        //iterate the prerequisites, queue, if the precourse is in the queue, decrease the courses
        while (!queue.isEmpty()) {
            int course = queue.poll();
            for (int[] re : prerequisites) {
                if (re[1] == course) {
                    courses[re[0]]--;
                    if (courses[re[0]] == 0) {
                    queue.offer(re[0]);
                    res[count++] = re[0];
                    }
                }
            }
        }

        return count == numCourses ? res : new int[0];

        //check whether the courses element greater than 0
        // for (int course : courses) {
        //     if (course != 0) return new int[0];
        // }

        // return res;
    }

    //74 medium -> search a 2D matrix: binary search in a 2D matrix
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        int start = 0;
        int end = row * col - 1;
        while (start <= end) {
            int mid = (end - start) / 2 + start;
            int value = matrix[mid / col][mid % col];
            if (target == value) return true;
            else if (target > value) start = mid + 1;
            else end = mid - 1;
        }
        return false;
    }

    //433 medium -> minimum genetic mutation
    //BFS
    public int minMutation(String startGene, String endGene, String[] bank) {
        if (startGene.equals(endGene)) return 0;
        HashSet<String> bankSet = new HashSet<>();
        for (String str : bank) {
            bankSet.add(str);
        }

        if (!bankSet.contains(endGene)) return -1;
        char[] choices = {'A', 'C', 'G', 'T'};
        Queue<String> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        queue.offer(startGene);
        visited.add(startGene);
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                for (int j = 0; j < curr.length(); j++) {
                    char[] currArray = curr.toCharArray();
                    for (char gene : choices) {
                        if (gene == currArray[j]) continue; // Skip if it's the same gene
                        currArray[j] = gene;
                        String nextGene = new String(currArray);
                        if (nextGene.equals(endGene)) return step + 1;
                        if (bankSet.contains(nextGene) && !visited.contains(nextGene)) {
                            queue.offer(nextGene);
                            visited.add(nextGene);
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    //     while (!queue.isEmpty()) {
    //         int size = queue.size();
    //         for (int i = 0; i < size; i++) {
    //             String curr = queue.poll();
    //             for (int j = 0; j < 8; j++) {
    //                 StringBuilder str = new StringBuilder(curr);
    //                 for (char gene : choices) {
    //                     str.setCharAt(j, gene);
    //                     String nextGene = str.toString();
    //                     if (nextGene.equals(endGene)) return step + 1;
    //                     if (bankSet.contains(nextGene) && !visited.contains(nextGene)) {
    //                         queue.offer(nextGene);
    //                         visited.add(nextGene);
    //                     }
    //                 }
    //             }
    //         }
    //         step++; //count a set of mutations as one step. This would require keeping track of all possible mutations at each step and incrementing the step count accordingly.
    //     }
    //     return -1;
    }

    //841 medium -> Keys and Rooms
    //Time: O(Nkeys + Nrooms)
    //way1: recursive
    public boolean canVisitAllRooms1(List<List<Integer>> rooms) {
        Set<Integer> set = new HashSet<>();
        visitRoomHelper(rooms, set, 0);
        return set.size() == rooms.size();
    }
    private void visitRoomHelper(List<List<Integer>> rooms, Set<Integer> set, int visit) {
        if (set.contains(visit)) return;
        set.add(visit);
        for (int key : rooms.get(visit)) {
            visitRoomHelper(rooms, set, key);
        }
    }
    //way2: iterative
    public boolean canVisitAllRooms2(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        Queue<Integer> queue = new LinkedList<>();
        visited[0] = true;
        queue.add(0);
        while (!queue.isEmpty()) {
            int room = queue.poll();
            for (int key : rooms.get(room)) {
                if (visited[key] == false) {
                    queue.add(key);
                    visited[key] = true;
                }
            }
        }

        for (boolean vi : visited) {
            if (vi == false) return false;
        }
        return true;
    }

    //547 medium -> number of provinces
    //dfs
    public int findCircleNum1(int[][] isConnected) {
        int size = isConnected.length;
        int provinces = 0;
        boolean[] visited = new boolean[size];
        for (int i = 0; i < size; i++) {
            if (!visited[i]) {
                dfsProvinces(isConnected, visited, i);
                provinces++;
            }
        }
        return provinces;
    }
    private void dfsProvinces(int[][] isConnected, boolean[] visited, int row) {
        for (int j = 0; j < isConnected.length; j++) {
            if (isConnected[row][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfsProvinces(isConnected, visited, j);
            }
        }
    }
    //bfs
    public int findCircleNum(int[][] isConnected) {
        int size = isConnected.length;
        int provinces = 0;
        boolean[] visited = new boolean[size];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            if (!visited[i]) {
                visited[i] = true;
                queue.offer(i);
                while(!queue.isEmpty()) {
                    int row = queue.poll();
                    for (int j = 1; j < size; j++) {
                        if (isConnected[row][j] == 1 && !visited[j]) {
                            queue.offer(j);
                            visited[j] = true;
                        }
                    }
                }
                provinces++;
            }
        }
        return provinces;
    }

    //994 medium -> rotting oranges
    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int freshOranges = 0;
        int minutes = 0;
        List<int[]> rottenOranges = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) rottenOranges.add(new int[]{i, j});
                if (grid[i][j] == 1) freshOranges++;
            }
        }

        if (freshOranges == 0) return 0;

        while (rottenOranges.size() > 0) {
            List<int[]> newRotten = new ArrayList<>();
            for (int[] rotten : rottenOranges) {
                int i = rotten[0], j = rotten[1];
                if (i - 1 >= 0 && grid[i - 1][j] == 1) {
                    grid[i - 1][j] = 2;
                    newRotten.add(new int[]{i - 1, j});
                    freshOranges--;
                }
                if (i + 1 < rows && grid[i + 1][j] == 1) {
                    grid[i + 1][j] = 2;
                    newRotten.add(new int[]{i + 1, j});
                    freshOranges--;
                }
                if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                    grid[i][j - 1] = 2;
                    newRotten.add(new int[]{i, j - 1});
                    freshOranges--;
                }
                if (j + 1 < cols && grid[i][j + 1] == 1) {
                    grid[i][j + 1] = 2;
                    newRotten.add(new int[]{i, j + 1});
                    freshOranges--;
                }
            }
            minutes++;
            rottenOranges = newRotten;
        }

        return freshOranges == 0 ? minutes - 1 : -1;
    }

    //1926 medium -> Nearest Exit from Entrance in Maze
    //BFS
    public int nearestExit(char[][] maze, int[] entrance) {
        int rows = maze.length, cols = maze[0].length;
        int steps = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(entrance);
        maze[entrance[0]][entrance[1]] = '+';

        int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while (!queue.isEmpty()) {
            steps++;
            int len = queue.size();
            for (int x = 0; x < len; x++) {
                int[] cell = queue.poll();
                for (int[] dir : directions) {
                    int i = cell[0] + dir[0];
                    int j = cell[1] + dir[1];
                    if (i < 0 || i >= rows || j < 0 || j >= cols || maze[i][j] == '+') continue;
                    if (i == 0 || i == rows - 1 || j == 0 || j == cols - 1) return steps;
                    maze[i][j] = '+';
                    queue.offer(new int[]{i, j});
                }
            }
        }

        return -1;
    }

    //1466 medium -> Reorder Routes to Make All Paths Lead to the City Zero
    //dfs
    public int minReorder1(int n, int[][] connections) {
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }
        for (int[] c : connections) {
            list.get(c[0]).add(c[1]);
            list.get(c[1]).add(-c[0]);
        }
        return reorderDfs(list, new boolean[n], 0);
    }
    private int reorderDfs(List<List<Integer>> list, boolean[] visited, int start) {
        int reorder = 0;
        visited[start] = true;
        for (int li : list.get(start)) {
            if (!visited[Math.abs(li)]) {
                reorder += reorderDfs(list, visited, Math.abs(li)) + (li > 0 ? 1 : 0);
            }
        }
        return reorder;
    }

}
