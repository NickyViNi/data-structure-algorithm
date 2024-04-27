package tree;
//96 medium
//Given an integer n, return the number of structurally unique BST's (binary search trees)
//which has exactly n nodes of unique values from 1 to n.
public class UniqueBinarySearchTree {
    //Memoization method O(n^2)
    int[] memoization = new int[20];
    public int numTrees(int n) {
        if (n <= 1) return 1;
        if (memoization[n] != 0) return memoization[n];
        int result = 0;
        for (int i = 1; i <= n; i++) {
            result += numTrees(i - 1) * numTrees(n - i);
        }
        return memoization[n] = result;
    }

    //Tabulation method O(n^2)
    public int numTrees2(int n) {
        int[] bstCount = new int[n + 1];
        bstCount[0] = 1;
        bstCount[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                bstCount[i] += bstCount[j - 1] * bstCount[i - j];
            }
        }
        return bstCount[n];
    }

    //basic recursive method: O(2^n)
    public int numTrees3(int n) {
        if (n == 0 || n == 1) return 1;
        int result = 0;
        for (int i = 1; i <= n; i++) {
            result += numTrees(i - 1) * numTrees(n - i);
        }
        return result;
    }
}
