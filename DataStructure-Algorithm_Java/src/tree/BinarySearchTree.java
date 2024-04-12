package tree;

public class BinarySearchTree {
    	// CREATE CLASS VARIABLE (ROOT) AND NODE CLASS
        private Node root;

        class Node {
            Node left;
            Node right;
            int value;

            public Node(int value) {
                this.value = value;
            }
        }

        public Node getRoot() {
            return root;
        }

        public boolean insert(int value) {

            Node newNode = new Node(value);

            if (this.root == null) {
                this.root = newNode;
                return true;
            }

            Node temp = this.root;
            while(true) {
                if (newNode.value == temp.value) return false;
                else if (newNode.value < temp.value) {
                    if (temp.left == null) {
                        temp.left = newNode;
                        return true;
                    } else {
                        temp = temp.left;
                    }
                } else {
                    if (temp.right == null) {
                        temp.right = newNode;
                        return true;
                    } else {
                        temp = temp.right;
                    }
                }
            }
        }

        //recursion method for Inserting Node
        private Node rInsert(Node currNode, int value) {
            if (currNode == null) {
                return new Node(value);
            }

            if (value < currNode.value) {
                currNode.left = rInsert(currNode.left, value);
            } else if (value > currNode.value) {
                currNode.right = rInsert(currNode.right, value);
            }

            return currNode;

        }

        public void rInsert(int value) {
            if (root == null) root = new Node(value);
            rInsert(root, value);
        }

        public boolean contains(int value) {
            if (this.root == null) return false;
            Node temp = this.root;
            while(temp != null) {
                if(value < temp.value) {
                    temp = temp.left;
                } else if (value > temp.value) {
                    temp = temp.right;
                } else {
                    return true;
                }
            }
            return false;
        }

        // THE RECURSIVE CONTAINS METHOD
        private boolean rContains(Node currNode, int value) {
            if (currNode == null) return false;
            if (currNode.value == value) return true;
            if (currNode.value > value) {
                return rContains(currNode.left, value);
            } else {
                return rContains(currNode.right, value);
            }
        }

        public boolean rContains(int value) {
            return rContains(root, value);
        }

        //In a BST, the leftmost node from any given node is always the smallest.
        public int minValue(Node currNode) {
            while (currNode.left != null) {
                currNode = currNode.left;
            }
            return currNode.value;
        }
}
