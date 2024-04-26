package tree;

public class BuildTree {
    //105 medium -> using preorder and inorder array to build a tree
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(preorder, inorder, 0,0,inorder.length-1);
    }
    private TreeNode helper(int[] preorder, int[] inorder, int preStart, int inStart, int inEnd){
        if(preStart > preorder.length-1 || inStart > inEnd){ //base case
            return null;
        }
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);// The first node in preorder is always the root of the tree.
        int inIndex = inStart;
        for(int i = inStart; i <= inEnd; i++){// Loop to find the root in the inorder array. This helps us determine the start and end of both the left and the right subtree.
            if(inorder[i] == rootVal){
                inIndex = i;
                break;
            }
        }
        // Recursively build the left and right subtrees.
        int leftTreeSize = inIndex - inStart; // Find the size of the left subtree.
        // Pass slices (start and end indices) of preorder and inorder arrays to recursively build the tree.
        // preStart + 1: After the first element of the preorder array is used to construct the root node, move onto the next element by incrementing the index by 1.
        // inIndex - 1: inIndex is the index of the root in the inorder array, so the left tree in the inorder array is from 0 to inIndex - 1.
        root.left = helper(preorder, inorder, preStart+1, inStart, inIndex-1);
        // preStart + leftTreeSize + 1: The right subtree in the preorder array will start from preStart + length of the left subtree.
        // inIndex + 1: inIndex is the index of the root in the inorder array, the right tree in the inorder starts from inIndex + 1 to the end.
        root.right = helper(preorder,inorder, preStart+leftTreeSize+1, inIndex+1, inEnd);
        return root;
    }
}
