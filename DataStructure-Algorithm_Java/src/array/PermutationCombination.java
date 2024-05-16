package array;

import java.util.ArrayList;
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
}
