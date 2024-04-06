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

    public int removeLast() {
        if (length == 0) return -1;
        Node removeNode = tail;
        if (length == 1) {
            head = null;
            tail = null;
            length--;
            return removeNode.value;
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
        return removeNode.value;
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
        if (this.length == 0 || index > this.length - 1) return null;
        Node temp = this.head;
        int i = 0;
        while (i < index) {
            temp = temp.next;
            i++;
        }
        // temp.next = null; do we need this line?
        return temp;
    }

    public boolean set(int index, int val) {
        if (this.length == 0 || index > this.length - 1) return false;
        Node temp = this.head;
        int i = 0;
        while (i < index) {
            temp = temp.next;
            i++;
        }
        temp.value = val;
        return true;
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

}
