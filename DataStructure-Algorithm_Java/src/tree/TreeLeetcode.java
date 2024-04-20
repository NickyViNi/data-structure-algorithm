package tree;

import java.util.ArrayList;
import java.util.Stack;

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

    //98 medium -> validate BST
    private boolean isValidBST(TreeNode currentNode, double low, double high) {
        if (currentNode == null) return true;
        if (currentNode.val <= low || currentNode.val >= high) return false;
        return isValidBST(currentNode.left, (double) low, (double) currentNode.val) &&
            isValidBST(currentNode.right,(double) currentNode.val, (double) high);
    }

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    //230 medium -> Kth smallest element in BST => solution1
    private ArrayList<Integer> DFSInOrder(TreeNode node) {
        ArrayList<Integer> result = new ArrayList<>();

        class Traverse {
            Traverse(TreeNode curr) {
                if (curr.left != null) {
                    new Traverse(curr.left);
                }
                result.add(curr.val);
                if (curr.right != null) {
                    new Traverse(curr.right);
                }
            }
        }

        new Traverse(node);
        return result;
    }

    public int kthSmallest(TreeNode root, int k) {
        ArrayList<Integer> arr = DFSInOrder(root);
        return arr.get(k - 1);
    }

    //230 medium -> Kth smallest element in BST => solution2
    int count = 1;
    int ksmall = Integer.MAX_VALUE;
    public int kthSmallest2(TreeNode root, int k) {
        if (root == null) return -1;
        kthSmallest2(root.left, k);
        if (k == count) ksmall = root.val;
        count++;
        kthSmallest2(root.right, k);
        return ksmall;
    }

    //230 medium -> Kth smallest element in BST => solution3
    public Integer kthSmallest3(TreeNode node, int k) {
        Stack<TreeNode> stack = new Stack<>();
        // TreeNode node = this.root;

        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            k -= 1;
            if (k == 0) {
                return node.val;
            }
            node = node.right;
        }
        return null;
    }

}
