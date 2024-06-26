package linkedList;

public class DoublyLinkedList {
    private Node head;
    private Node tail;
    private int length;

    class Node {
        int value;
        Node next;
        Node prev;

        Node(int value) {
            this.value = value;
        }
    }

    public DoublyLinkedList(int value) {
        Node newNode = new Node(value);
        this.head = newNode;
        this.tail = newNode;
        this.length = 1;
    }

    public void append(int value) {
        Node newNode = new Node(value);
        if (this.length == 0) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.next = newNode;
            newNode.prev = this.tail;
            this.tail = newNode;
        }
        this.length++;
    }

    public Node removeLast() {
        if (this.length == 0) return null;
        Node removedNode = this.tail;
        if (this.length == 1) {
            this.head = null;
            this.tail = null;
        } else {
            this.tail = this.tail.prev;
            removedNode.prev = null;
            this.tail.next = null;
        }
        this.length--;
        return removedNode;
    }

    public void prepend(int value) {
        Node newNode = new Node(value);
        if (this.length == 0) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.head.prev = newNode;
            newNode.next = this.head;
            this.head = newNode;
        }
        this.length++;
    }

    public Node removeFirst() {
        if (this.length == 0) return null;
        Node firstNode = this.head;
        if (this.length == 1) {
            this.head = null;
            this.tail = null;
        } else {
            this.head = this.head.next;
            this.head.prev = null;
            firstNode.next = null;
        }
        this.length--;
        return firstNode;
    }

    public Node get(int index) {
        if (index < 0 || index >= this.length) return null;
        Node curr;
        if (index < this.length / 2) {
            int pointer = 0;
            curr = this.head;
            while (pointer < index)
            curr = curr.next;
            pointer++;
        } else {
            int pointer = this.length - 1;
            curr = this.tail;
            while (pointer > index) {
                curr = curr.prev;
                pointer--;
            }
        }
        return curr;
    }

    public boolean set(int index, int value) {
        Node temp = get(index);
        if (temp != null) {
            temp.value = value;
            return true;
        }
        return false;
    }

    public boolean insert(int index, int value) {
        if (index < 0 || index > this.length) return false;
        if (index == 0) {
            prepend(value);
            return true;
        }
        if (index == this.length) {
            append(value);
            return true;
        }

        Node newNode = new Node(value);
        Node prevNode = get(index - 1);
        Node nextNode = prevNode.next;
        prevNode.next = newNode;
        nextNode.prev = newNode;
        newNode.prev = prevNode;
        newNode.next = nextNode;
        this.length++;
        return true;
    }

    public Node remove(int index) {
        if (index < 0 || index >= this.length) return null;
        if (index == 0) {
            return removeFirst();
        }
        else if (index == this.length - 1) {
            return removeLast();
        }
        else {
            Node removedNode = get(index);
            removedNode.next.prev = removedNode.prev;
            removedNode.prev.next = removedNode.next;
            removedNode.prev = null;
            removedNode.next = null;
            this.length--;
            return removedNode;
        }
    }

    public void swapFirstLast() {
        if (length < 2) return;
        int temp = head.value;
        head.value = tail.value;
        tail.value = temp;
    }

    public void reverse() {
	    if (length <= 1) return;
	    Node temp = head;
	    head = tail;
	    tail = temp;
	    Node curr = tail;
	    Node prev = null;
	    while (curr != null) {
	        Node nextNode = curr.next;
	        curr.next = prev;
	        curr.prev = nextNode;
	        prev = curr;
	        curr = nextNode;
	    }
	}

    public boolean isPalindrome() {
        if (length <= 1) return true;
        Node left = head;
        Node right = tail;
        int idx = length / 2;
        while (idx > 0 && left.value == right.value) {
            left = left.next;
            right = right.prev;
            idx--;
        }
        return idx == 0;
    }

    public void swapPairs() {
        if (head == null || head.next == null) return;
        Node dummyNode = new Node(0);
        dummyNode.next = head;
        Node prevNode = dummyNode;
        while (head != null && head.next != null) {
            Node firstNode = head;
            Node secondNode = head.next;

            //swap nodes
            prevNode.next = secondNode;
            firstNode.next = secondNode.next;
            secondNode.next = firstNode;

            //update prevous node
            secondNode.prev = prevNode;
            firstNode.prev = secondNode;
            if (firstNode.next != null) firstNode.next.prev = firstNode;

            //update head
            head = firstNode.next;
            prevNode = firstNode;
        }
        head = dummyNode.next;
        head.prev = null;
    }


    public void getHead() {
        System.out.println("Head: " + this.head.value);
    }

    public void getTail() {
        System.out.println("Tail: " + this.tail.value);
    }

    public void getLength () {
        System.out.println("Length: " + this.length);
    }

    public void printList() {
        Node temp = this.head;
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
        System.out.println("\nDoubly Linked List:");
        if (length == 0) {
            System.out.println("empty");
        } else {
            printList();
        }
    }
}
