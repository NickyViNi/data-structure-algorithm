package array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PermutationCombination {
    //77 medium -> combination
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = i + 1;
        combineHelper(result, nums, k, 0, new ArrayList<Integer>());
        return result;
    }
    private void combineHelper(List<List<Integer>> result, int[] nums, int k, int idx, List<Integer> arr) {
        if (k == 0) {
            result.add(new ArrayList<>(arr)); //have to create a new arraylist, arr is mutable
            return;
        }
        for (int i = idx; i < nums.length; i++) {
            arr.add(nums[i]);
            combineHelper(result, nums, k - 1, i + 1, arr);
            arr.remove(arr.size() - 1);
        }
    }

    //46 medium -> permutation
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backTracking(result, nums, new ArrayList<Integer>());
        return result;
    }
    private void backTracking(List<List<Integer>> result, int[] nums, List<Integer> arr) {
        if (arr.size() == nums.length) {
            result.add(new ArrayList<Integer>(arr));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (arr.contains(nums[i])) continue;
            arr.add(nums[i]);
            backTracking(result, nums, arr);
            arr.remove(arr.size() - 1);
        }
    }

    //39 medium -> combination sum
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        combinationBackTracking(candidates, target, result, 0, new ArrayList<Integer>());
        return result;
    }
    private void combinationBackTracking(int[] candidates, int target, List<List<Integer>> result, int start, List<Integer> arr) {
        if (target < 0) return;
        if (target == 0) {
            result.add(new ArrayList<>(arr));
        }
        for (int i = start; i < candidates.length; i++) {
            arr.add(candidates[i]);
            combinationBackTracking(candidates, target - candidates[i], result, i, arr);
            arr.remove(arr.size() - 1);
        }
    }

    //22 medium -> generate parenthesis
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        parenthesisHelper("", n, n, result);
        return result;
    }
    private void parenthesisHelper(String str, int left, int right, List<String> result) {
        if (left == 0 && right == 0) { // if (str.length() == 2*n)
            result.add(str);
            return;
        }
        if (left > 0) parenthesisHelper(str + "(", left - 1, right, result);
        if (right > left) parenthesisHelper(str + ")", left, right - 1, result);
    }

    //79 medium -> word search
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (wordDfs(board, word, i, j, 0, new HashSet<String>())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private boolean wordDfs(char[][] board, String word, int i, int j, int idx, HashSet<String> visited) {
        if (idx == word.length()) return true;
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length
            || board[i][j] != word.charAt(idx)
            || visited.contains(i + "-" + j)) return false;

        visited.add(i + "-" + j);
        if (wordDfs(board, word, i + 1, j, idx + 1, visited)
            || wordDfs(board, word, i - 1, j, idx + 1, visited)
            || wordDfs(board, word, i, j + 1, idx + 1, visited)
            || wordDfs(board, word, i, j - 1, idx + 1, visited)) return true;

        visited.remove(i + "-" + j);
        return false;
    }
}
