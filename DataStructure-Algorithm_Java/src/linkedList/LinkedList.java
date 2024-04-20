package linkedList;
public class LinkedList {

    private Node head;
    private Node tail;
    private int length;

    class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }


    public LinkedList(int value) {
        Node newNode = new Node(value);
        this.head = newNode;
        this.tail = newNode;
        this.length = 1;
    }

    public void append(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        length++;
    }

    public void makeEmpty() {
        head = null;
        tail = null;
        length = 0;
    }

    public Node removeLast() {
        if (length == 0) return null;
        Node removeNode = tail;
        if (length == 1) {
            head = null;
            tail = null;
            length--;
            return removeNode;
        }

        Node temp = head;
        Node pre = head;
        while (temp.next != null) {
            pre = temp;
            temp = temp.next;
        }
        tail = pre;
        tail.next = null;
        length--;
        return removeNode;
    }

    public Node removeFirst() {
        if (this.length == 0) return null;
        Node removedNode = this.head;
        this.head = this.head.next;
        this.length--;
        if (this.length == 0) this.tail = null;
        removedNode.next = null;
        return removedNode;
    }

    public Node get(int index) {
        if (this.length == 0 || index < 0 || index >= this.length) return null;
        Node temp = this.head;
        int i = 0;
        while (i < index) {
            temp = temp.next;
            i++;
        }
        return temp;
    }

    public boolean set(int index, int val) {
        Node targetNode = get(index);
        if (targetNode != null) {
            targetNode.value = val;
            return true;
        }
        return false;
    }

    public boolean insert (int index, int val) {
        if (index < 0 || index > this.length) return false;
        Node newNode = new Node(val);
        Node currNode = this.head.next;
        Node preNode = this.head;
        int i = 0;
        while (i < index) {
            currNode = currNode.next;
            preNode = preNode.next;
            i++;
        }
        preNode.next = newNode;
        newNode.next = currNode;
        this.length++;
        return true;
    }

    public Node remove (int index) {
        if (this.length == 0 || index > this.length - 1) return null;
        Node currNode = this.head.next;
        Node preNode = this.head;
        int i = 0;
        while (i < index) {
            currNode = currNode.next;
            preNode = preNode.next;
            i++;
        }
        preNode.next = currNode.next;
        currNode.next = null;
        this.length--;
        return currNode;
    }

    public void reverse() {
        Node currNode = this.head;
        this.head = this.tail;
        this.tail = currNode;
        Node preNode = null;
        Node nextNode = null;
        while (currNode != null) {
            nextNode = currNode.next;
            currNode.next = preNode;
            preNode = currNode;
            currNode = nextNode;
        }
    }

    public void prepend(int value) {
        //solution 1
        // Node newNode = new Node(value);
        // if (length == 0) {
        //     head = newNode;
        //     tail = newNode;
        // } else {
        //     newNode.next = head;
        //     head = newNode;
        // }
        // length++;

        //solution 2
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
        length++;
        if (length == 1) tail = newNode;
    }


    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public int getLength() {
        return length;
    }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    public void printAll() {
        if (length == 0) {
            System.out.println("Head: null");
            System.out.println("Tail: null");
        } else {
            System.out.println("Head: " + head.value);
            System.out.println("Tail: " + tail.value);
        }
        System.out.println("Length:" + length);
        System.out.println("\nLinked List:");
        if (length == 0) {
            System.out.println("empty");
        } else {
            printList();
        }
    }

    public void bubbleSort() {
        // If list length is less than 2, no need to sort
        if (this.length < 2) return;

        // Initialize sortedUntil as null (nothing is sorted initially)
        Node sortedUntil = null;
        // Outer loop continues until sorted part reaches the second node
        while (sortedUntil != this.head.next) {
            // Start with the head node
            Node current = this.head;
            // Inner loop for each pass
            while (current.next != sortedUntil) {
                // Compare current node with next node
                Node nextNode = current.next;
                // If current node is greater, swap values
                if (current.value > nextNode.value) {
                    int temp = current.value;
                    current.value = nextNode.value;
                    nextNode.value = temp;
                }
                // Move to the next node
                current = current.next;
            }
            // After each pass, the largest element is bubbled to the end
            // Thus, update sortedUntil to point to the last sorted element
            sortedUntil = current;
        }
    }

    public void selectionSort() {
        // If list length is less than 2, no need to sort
        if (this.length < 2) return;
        // Initialize current node as head
        Node current = this.head;
        // Iterate over the list until the second last element
        while (current.next != null) {
            // Assume current node is the smallest
            Node smallest = current;
            // Start checking from the next node
            Node innerCurrent = current.next;
            // Inner loop to find smallest node in unsorted part
            while (innerCurrent != null) {
                // If a smaller node is found, update smallest
                if (innerCurrent.value < smallest.value) {
                    smallest = innerCurrent;
                }
                // Move to the next node
                innerCurrent = innerCurrent.next;
            }
            // Swap current node and smallest node if they're not the same
            if (smallest != current) {
                int temp = current.value;
                current.value = smallest.value;
                smallest.value = temp;
            }
            // Move to next node in the list
            current = current.next;
        }
        // this.tail = current;
    }

    public void insertionSort() {
        // If the list has less than 2 elements, it is already sorted
        if (length < 2) return;

        // Start with a sorted list containing only the first element
        Node sortedListHead = head;
        // Start with the second element in the unsorted list
        Node unsortedListHead = head.next;
        // Mark the end of the sorted list
        sortedListHead.next = null;
        // Iterate over the unsorted list
        while (unsortedListHead != null) {
            // Take the first element in the unsorted list
            Node current = unsortedListHead;
            // Move to the next element in the unsorted list
            unsortedListHead = unsortedListHead.next;
            // If the current element is smaller than the first element of the sorted list
            if (current.value < sortedListHead.value) {
                // Insert the current element at the beginning of the sorted list
                current.next = sortedListHead;
                // Update the sorted list head
                sortedListHead = current;
            } else {
                // Start at the beginning of the sorted list
                Node searchPointer = sortedListHead;
                // Search for the correct insertion point
                while (searchPointer.next != null && current.value > searchPointer.next.value) {
                    // Move to the next element in the sorted list
                    searchPointer = searchPointer.next;
                }
                // Insert the current element after searchPointer
                current.next = searchPointer.next;
                searchPointer.next = current;
            }
        }
        // Update the head of the sorted list
        head = sortedListHead;
        // Update the tail of the sorted list
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        tail = temp;
    }

}
