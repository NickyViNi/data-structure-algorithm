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
