package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

    //129 medium -> Sum root to leaf numbers
    int sum = 0;
    public int sumNumbers(TreeNode root) {
        if (root.left == null && root.right == null) return root.val;
        traversal(root, 0);
        return sum;
    }
    private void traversal(TreeNode node, int value) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            sum += value * 10 + node.val;
            return;
        }
        traversal(node.left, value * 10 + node.val);
        traversal(node.right, value * 10 + node.val);
    }

    //116 medium -> Populating next right pointer in each node: perfect binary tree
    public TreeNode connect(TreeNode root) {
        if (root == null) return root;
        TreeNode curr = root;
        while (curr != null) {
            TreeNode head = curr;
            while (curr != null) {
                if (curr.left != null) {
                    curr.left.next = curr.right;
                }
                if (curr.right != null && curr.next != null) {
                    curr.right.next = curr.next.left;
                }
                curr = curr.next;
            }
            curr = head.left;
        }
        return root;
    }
    public TreeNode connect2 (TreeNode root) {
        if (root == null) return root;
        if (root.left != null) root.left.next = root.right;
        if (root.right != null && root.next != null) root.right.next = root.next.left;
        connect(root.left);
        connect(root.right);
        return root;
    }

    //117 medium -> Populating next right pointer in each node: general binary tree
    public TreeNode connectII(TreeNode root) { // O(1) space
        if (root == null) return root;
        TreeNode head = null;
        TreeNode pre = null;
        TreeNode curr = root;
        while (curr != null) {
            while (curr != null) {
                if (curr.left != null) {
                    if (head == null) {
                        head = curr.left;
                        pre = curr.left;
                    } else {
                        pre.next = curr.left;
                        pre = curr.left;
                    }
                }
                if (curr.right != null) {
                    if (head == null) {
                        head = curr.right;
                        pre = curr.right;
                    } else {
                        pre.next = curr.right;
                        pre = curr.right;
                    }
                }
                curr = curr.next;
            }
            curr = head;
            head = null;
            pre = null;
        }
        return root;
    }
    public TreeNode connectII2(TreeNode root) { // O(n) space
        if (root == null) return root;
        // Queue<Node> queue = new LinkedList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (queue.size() != 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i < size - 1) node.next = queue.peek();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return root;
    }

    //173 medium -> binary search tree iterator
    class BSTIterator {
        Stack<TreeNode> stack;
        TreeNode curr;
        public BSTIterator(TreeNode root) {
            this.stack = new Stack<>();
            this.curr = root;
        }

        public int next() {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            int value = curr.val;
            curr = curr.right;
            return value;
        }

        public boolean hasNext() {
            if (stack.empty() && curr == null) return false;
            return true;
        }
    }

    //235 medium -> Lowest Common Ancestor of Binary Search Tree
    public TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }

    //236 medium -> Lowest Common Ancestor of Binary Tree
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        return left == null ? right : left;
    }

    //199 medium -> Binary Tree right side view
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) return result;
        queue.offer(root);
        while (queue.size() > 0) { // !queue.isEmpty()
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == 0) result.add(node.val);
                if (node.right != null) queue.offer(node.right);
                if (node.left != null) queue.offer(node.left);
            }
        }
        return result;
    }

    //102 medium -> Binary Tree level order traversal
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            result.add(list);
        }
        return result;
    }
    //103 medium -> Binary tree zigzag level order traversal
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean LtoR = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (LtoR) {
                    list.add(node.val);
                } else {
                    list.add(0, node.val);
                }
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.add(node.right);
            }
            LtoR = !LtoR;
            result.add(list);
        }
        return result;
    }

    //872 easy -> Leaf-Similar Trees
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        getLeaf(root1, list1);
        getLeaf(root2, list2);
        return list1.equals(list2);
    }
    private void getLeaf(TreeNode root, List<Integer> list) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            list.add(root.val);
            return;
        }
        getLeaf(root.left, list);
        getLeaf(root.right, list);
    }
    //700 easy -> Search in a Binary Search Tree
    //recursion
    public TreeNode searchBST1(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) return root;
        if (root.val > val) return searchBST(root.left, val);
        else return searchBST(root.right, val);
    }
    //iterative
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        while (root != null) {
            if (root.val > val) {
                root = root.left;
            } else if (root.val < val) {
                root = root.right;
            } else {
                return root;
            }
        }
        return root;
    }

    //1161 medium -> Maximum Level Sum of a Binary Tree
    public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>(List.of(root));
        int maxSum = Integer.MIN_VALUE;
        int level = 0;
        int currLevel = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int currSum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                currSum += node.val;
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            currLevel++;
            if (currSum > maxSum) {
                maxSum = currSum;
                level = currLevel;
            }
        }
        return level;
    }

    //recursive
    public int maxLevelSum2(TreeNode root) {
        if(root == null) return 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        findSum(root, map, 0);
        int maxSum = Integer.MIN_VALUE;
        int level = 0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getValue() > maxSum){
                maxSum = entry.getValue();
                level = entry.getKey();
            }
        }
        return level+1;
    }
    private void findSum(TreeNode root,  HashMap<Integer, Integer>map, Integer level){
        if(root == null) return;
        int prev = map.getOrDefault(level, 0);
        map.put(level, prev + root.val);
        findSum(root.left, map, level + 1);
        findSum(root.right, map, level + 1);
    }

    //113 medium -> path sum II
    List<List<Integer>> paths;
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        this.paths = new ArrayList<>();
        pathSumHelper(root, targetSum, new ArrayList<>());
        return paths;
    }
    private void pathSumHelper(TreeNode root, int targetSum, List<Integer> road) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            if (root.val == targetSum) {
                road.add(root.val);
                paths.add(new ArrayList<>(road));
                road.remove(road.size() - 1);
            }
            return;
        }

        road.add(root.val);
        pathSumHelper(root.left, targetSum - root.val, road);
        pathSumHelper(root.right, targetSum - root.val, road);
        road.remove(road.size() - 1);
    }
    //437 medium -> path sum III, T: O(n^2), S: O(h)
    public int pathSumIII(TreeNode root, int targetSum) {
        if (root == null) return 0;
        return (int)pathSumHelperIII(root, (long)targetSum) + pathSumIII(root.left, targetSum) + pathSumIII(root.right, targetSum);
    }
    private long pathSumHelperIII(TreeNode node, long sum) {
        if (node == null) return 0;
        return (node.val == sum ? 1 : 0) + pathSumHelperIII(node.left, sum - node.val) + pathSumHelperIII(node.right, sum - node.val);
    }

    //1448 medium -> Count Good Nodes in Binary Tree
    static int count1;
    public int goodNodes(TreeNode root) {
        count1 = 0;
        int max = root.val;
        goodHelper(root, max);
        return count1;
    }
    private void goodHelper(TreeNode root, int max) {
        if (root == null) return;
        if (root.val >= max) {
            max = root.val;
            count1++;
        }
        goodHelper(root.left, max);
        goodHelper(root.right, max);
    }
}
