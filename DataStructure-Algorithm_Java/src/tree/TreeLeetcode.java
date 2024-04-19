package tree;

public class TreeLeetcode {

    //108 easy: convert sorted array into BST
    //Big-O: O(nlog(n)) : Since the function makes a recursive call on each half of the array at each step, and each recursive call has a time complexity of O(n), the overall time complexity of this function is O(nlog(n))
    public TreeNode sortedArrayToBST(int[] nums) {
        return createNode(nums, 0, nums.length - 1);
    }

    private static TreeNode createNode(int[] nums, int left, int right) {
        if (left > right) return null;
        int medium = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[medium]);
        root.left = createNode(nums, left, medium - 1);
        root.right = createNode(nums, medium + 1, right);
        return root;
    }

    //226 easy: invert binary tree
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode temp = root.right;
        root.right = invertTree(root.left);
        root.left = invertTree(temp);
        return root;
    }
}
