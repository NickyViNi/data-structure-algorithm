package tree;

public class QuadTreeProblem {
    public QuadTree construct(int[][] grid) {
        return buildQuadTree(grid, 0, 0, grid.length - 1,  grid[0].length - 1);
    }

    private QuadTree buildQuadTree(int[][] grid, int x1, int y1, int x2, int y2) {
        if (x1 > x2 || y1 > y2) return null;
        int value = grid[x1][y1];
        boolean same = true;
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if (grid[i][j] != value) {
                    same = false;
                    break;
                }
            }
        }
        QuadTree node = new QuadTree();
        // same == true
        if(same) {
            node.isLeaf = true;
            node.val = value == 1 ? true : false;
            return node;
        }
        // same == false
        int midRow = (x1 + x2) / 2;
        int midCol = (y1 + y2) / 2;
        node.isLeaf = false;
        //node.val = value == 1 ? true : false;
        node.topLeft = buildQuadTree(grid, x1, y1, midRow, midCol);
        node.topRight = buildQuadTree(grid, x1, midCol+1, midRow, y2);
        node.bottomLeft = buildQuadTree(grid, midRow+1, y1, x2, midCol);
        node.bottomRight = buildQuadTree(grid, midRow+1, midCol+1, x2, y2);
        return node;
    }
}
