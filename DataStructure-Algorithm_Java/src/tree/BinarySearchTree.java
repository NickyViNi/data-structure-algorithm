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
}
