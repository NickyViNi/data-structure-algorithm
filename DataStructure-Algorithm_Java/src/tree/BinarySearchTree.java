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
}
