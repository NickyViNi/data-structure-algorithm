package tree;

import java.util.HashMap;

//106 medium -> Construct a binary tree from postorder adn inorder traversal
/*Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree
and postorder is the postorder traversal of the same tree, construct and return the binary tree.
Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
Output: [3,9,20,null,null,15,7]*/
public class BuildTreePostorder {
    HashMap<Integer, Integer> inMap = new HashMap<>();
    int idx;
    int[] postorder;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int len = postorder.length;
        this.idx = len - 1;
        this.postorder = postorder;
        for (int i = 0; i < len; i++) {
            inMap.put(inorder[i], i);
        }
        return buildTreeHelper(0, len -1);
    }
    private TreeNode buildTreeHelper(int start, int end) {
        if (start > end) return null;
        int currVal = postorder[idx--];
        TreeNode currNode = new TreeNode(currVal);
        int mid = inMap.get(currVal);
        currNode.right = buildTreeHelper(mid + 1, end);
        currNode.left = buildTreeHelper(start, mid - 1);
        return currNode;
    }
}
