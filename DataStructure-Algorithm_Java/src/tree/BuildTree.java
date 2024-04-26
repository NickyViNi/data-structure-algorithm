package tree;

import java.util.HashMap;
import java.util.Map;
/*Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.
Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
Output: [3,9,20,null,null,15,7]
 */

public class BuildTree {
    //105 medium -> using preorder and inorder array to build a tree
    int rootIdx = 0;
    Map<Integer, Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return buildTreeHelper(preorder, 0, preorder.length - 1);
    }

    private TreeNode buildTreeHelper(int[] preorder, int leftIdx, int rightIdx) {
        if (leftIdx > rightIdx) return null;
        int rootVal = preorder[rootIdx];
        if (rootIdx < preorder.length - 1 ) rootIdx++;
        TreeNode root = new TreeNode(rootVal);
        root.left = buildTreeHelper(preorder, leftIdx, map.get(rootVal) - 1);
        root.right = buildTreeHelper(preorder, map.get(rootVal) + 1, rightIdx);
        return root;
    }
}
