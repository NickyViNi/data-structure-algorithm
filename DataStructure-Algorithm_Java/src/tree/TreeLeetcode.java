package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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

    //100 easy -> same tree
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if ((p == null || q == null)) return false;
        return p.val == q.val ? isSameTree(p.left, q.left) && isSameTree(p.right, q.right) : false;
    }
    public boolean isSameTree2(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if ((p == null || q == null)) return false;
        TreeNode currp = p;
        TreeNode currq = q;
        Queue<TreeNode> queueP = new LinkedList<>();
        Queue<TreeNode> queueQ = new LinkedList<>();
        queueP.add(currp);
        queueQ.add(currq);
        while (queueP.size() > 0 && queueQ.size() > 0) {
            currp = queueP.remove();
            currq = queueQ.remove();
            if (currp.val != currq.val || (currp.left == null && currq.left != null) ||
                (currp.left != null && currq.left == null) ||
                (currp.right == null && currq.right != null) ||
                (currp.right != null && currq.right == null) ||
                (currp.left != null && currq.left != null && currp.left.val != currq.left.val) ||
                (currp.right != null && currq.right != null && currp.right.val != currq.right.val)) return false;
            if (currp.left != null) queueP.add(currp.left);
            if (currp.right != null) queueP.add(currp.right);
            if (currq.left != null) queueQ.add(currq.left);
            if (currq.right != null) queueQ.add(currq.right);
        }
        return true;
    }

    //101 easy -> Symmetric Tree
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }
    private boolean isMirror(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if ((a == null || b == null)) return false;
        return a.val == b.val ? isMirror(a.left, b.right) && isMirror(a.right, b.left) : false;
    }

    //114 medium -> Flatten Binary tree into linkedlist
    public void flatten(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return;
        TreeNode dummy = new TreeNode(0);
        TreeNode pointer = dummy;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode curr = stack.pop();
            pointer.right = curr;
            pointer = curr;
            if (curr.right != null) stack.push(curr.right);
            if (curr.left != null) stack.push(curr.left);
            curr.left = null;
        }

        root = dummy.right;
    }
    public void flatten2(TreeNode root) { // O(1) space
        if (root == null) return;
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode rightMost = curr.left;
                while(rightMost.right != null) {
                    rightMost = rightMost.right;
                }
                rightMost.right = curr.right;
                curr.right = curr.left;
                curr.left = null;
            }
            curr = curr.right;
        }
    }

    //222 easy -> count complete tree nodes
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    public int countNodes2(TreeNode root) {
        if (root == null) return 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int count = 0;
        while (!stack.empty()) {
            TreeNode curr = stack.pop();
            count++;
            if(curr.left != null) stack.push(curr.left);
            if(curr.right != null) stack.push(curr.right);
        }
        return count;
    }

    //112 easy -> path sum
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    //637 easy -> average of levels in binary tree
    public List<Double> averageOfLevels(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>(List.of(root));
        List<Double> result = new ArrayList<>();
        while(queue.size() > 0) {
            int len = queue.size();
            double sum = 0;
            for (int i = 0; i < len; i++) {
                TreeNode curr = queue.poll();
                sum += curr.val;
                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
            result.add(sum / len);
        }
        return result;
    }
    public List<Double> averageOfLevels2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Double> result = new ArrayList<>();
        queue.add(root);
        queue.add(null);
        while (queue.peek() != null) {
            double sum = 0;
            int count = 0;
            while (queue.peek() != null) {
                TreeNode node = queue.poll();
                sum += node.val;
                count++;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            queue.add(queue.poll());
            result.add( sum / count);
        }
        return result;
    }

    //530 easy -> Minimum Absolute Difference in BST
    int minDif = Integer.MAX_VALUE;
    int prevVal = Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        inorder(root);
        return minDif;
    }
    private void inorder(TreeNode root) {
        if (root.left != null) inorder(root.left);
        minDif = Math.min(minDif, Math.abs(root.val - prevVal));
        prevVal = root.val;
        if (root.right != null) inorder(root.right);
    }
}
