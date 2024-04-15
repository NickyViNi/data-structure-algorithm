package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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

        private Node deleteNode(Node currNode, int value) {
            //base case
            if (currNode == null) return null;

            //recurse down the tree
            if (value < currNode.value) {
                // If less, go left
                currNode.left = deleteNode(currNode.left, value);
            } else if (value > currNode.value) {
                // if more, go right
                currNode.right = deleteNode(currNode.right, value);
            } else { // Value is same as current's value, node to delete
                if (currNode.left == null && currNode.right == null) { //Node is a leaf node
                    return null;
                } else if (currNode.left == null) { // Node has only right child
                    currNode = currNode.right;
                } else if (currNode.right == null) { // Node has only left child
                    currNode = currNode.left;
                } else { // Node has only right child
                    int subTreeMinVal = minValue(currNode.right);
                    currNode.value = subTreeMinVal; // Replace with min in right subtree
                    currNode.right = deleteNode(currNode.right, subTreeMinVal); // Delete the minimum in right subtree
                }
            }
            // Return the modified tree
            return currNode;
        }

        public void deleteNode(int value) {
            this.root = deleteNode(this.root, value);
        }

        // Breadth First Search
        public ArrayList<Integer> BFS() {
            Node currNode = this.root;
            Queue<Node> queue = new LinkedList<>();
            ArrayList<Integer> results = new ArrayList<>();
            queue.add(currNode);

            while(queue.size() > 0) {
                currNode = queue.remove();
                results.add(currNode.value);
                if(currNode.left != null) {
                    queue.add(currNode.left);
                }
                if(currNode.right != null) {
                    queue.add(currNode.right);
                }
            }

            return results;
        }
        // Depth First Search: preOrder using Stack
        public ArrayList<Integer> DFSPreOrder1() {
            Node currNode = this.root;
            Stack<Node> stack = new Stack<>();
            ArrayList<Integer> results = new ArrayList<>();
            stack.add(currNode);

            while(!stack.isEmpty()) {
                currNode = stack.pop();
                results.add(currNode.value);
                if (currNode.right != null) {
                    stack.push(currNode.right);
                }
                if (currNode.left != null) {
                    stack.push(currNode.left);
                }
            }
            return results;
        }

        public ArrayList<Integer> DFSPreOrder() {
            ArrayList<Integer> results = new ArrayList<>();

            class Traverse {
                Traverse(Node currentNode) {
                    results.add(currentNode.value);

                    if(currentNode.left != null) {
                        new Traverse(currentNode.left);
                    }
                    if(currentNode.right != null) {
                        new Traverse(currentNode.right);
                    }

                }
            }

           new Traverse(this.root);
           return results;
        }

        public ArrayList<Integer> DFSInOrder() {
            ArrayList<Integer> results = new ArrayList<>();

            class Traverse {
                Traverse(Node currentNode) {

                    if(currentNode.left != null) {
                        new Traverse(currentNode.left);
                    }
                    results.add(currentNode.value);
                    if(currentNode.right != null) {
                        new Traverse(currentNode.right);
                    }

                }
            }

           new Traverse(this.root);
           return results;
        }

        public ArrayList<Integer> DFSPostOrder() {
            ArrayList<Integer> results = new ArrayList<>();

            class Traverse {
                Traverse(Node currentNode) {

                    if(currentNode.left != null) {
                        new Traverse(currentNode.left);
                    }
                    if(currentNode.right != null) {
                        new Traverse(currentNode.right);
                    }

                    results.add(currentNode.value);
                }
            }

           new Traverse(this.root);
           return results;
        }
}
