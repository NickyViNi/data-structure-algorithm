import java.util.Random;

import array.PermutationString;
import array.ReplaceSpace;
import array.UniqueChar;
import heap.Heap;

public class Main {
    public static void main(String[] args) {
        Heap heap = new Heap();
        System.out.println(heap.getHeap());
        heap.insert(12);
        System.out.println(heap.getHeap());
        heap.insert(25);
        System.out.println(heap.getHeap());
        heap.insert(8);
        System.out.println(heap.getHeap());
        heap.insert(56);
        System.out.println(heap.getHeap());
        heap.insert(99);
        System.out.println(heap.getHeap());
        // System.out.println(ReplaceSpace.replaceSpace("Mr John Smith   ", 13));
        // PermutationString perStr = new PermutationString();
        // System.out.println(perStr.permutation2("happy!", "hapoy!"));
        // UniqueChar uniqueString = new UniqueChar();
        // System.out.println(uniqueString.uniqueChar("howareyou"));
        // Random random = new Random();
        // random.ints().limit(10).forEach(System.out::println);
        // GenericArray<String> strArr = new GenericArray<>(5);
        // strArr.setElement(0, "Hey!");
        // strArr.setElement(1, "How are you?");
        // strArr.printArray();
        // LinkedList ll = new LinkedList(99);
        // ll.append(100);
        // ll.append(200);
        // ll.printAll();
        // System.out.println("Coding exercise 2: LL: Constructor");
        // LinkedList myLinkedList = new LinkedList(4);
        // myLinkedList.printAll();
        // /*
        //  * EXPECTED OUTPUT:
        //  * ----------------
        //  * Head: 4
        //  * Tail: 4
        //  * Length: 1
        //  *
        //  * Linked List:
        //  * 4
        //  *
        //  */
        // System.out.println("\n\nCoding exercise 3: LL: Append");
        // LinkedList myLinkedList2 = new LinkedList(1);
        // myLinkedList2.makeEmpty();
        // myLinkedList2.append(1);
        // myLinkedList2.append(2);
        // myLinkedList2.printAll();
        // /*
        //  * EXPECTED OUTPUT:
        //  * ----------------
        //  * Head: 1
        //  * Tail: 2
        //  * Length: 2
        //  *
        //  * Linked List:
        //  * 1
        //  * 2
        //  *
        //  */
        // System.out.println("\n\nCoding exercise 4: LL: Remove Last");
        // LinkedList myLinkedList3 = new LinkedList(1);
        // myLinkedList3.append(2);
        // // (2) Items - Returns 2 Node
        // System.out.println(myLinkedList3.removeLast());
        // // (1) Item - Returns 1 Node
        // System.out.println(myLinkedList3.removeLast());
        // // (0) Items - Returns null
        // System.out.println(myLinkedList3.removeLast());
        // System.out.println("\n\nCoding exercise 5: LL: Prepend");
        // LinkedList myLinkedList4 = new LinkedList(2);
        // myLinkedList4.append(3);
        // System.out.println("Before prepend():");
        // System.out.println("-----------------");
        // myLinkedList4.printAll();
        // myLinkedList4.prepend(1);
        // System.out.println("\n\nAfter prepend():");
        // System.out.println("----------------");
        // myLinkedList4.printAll();
        // /*
        //  * EXPECTED OUTPUT:
        //  *
        //  * Before prepend():
        //  * -----------------
        //  * Head: 2
        //  * Tail: 3
        //  * Length: 2
        //  *
        //  * Linked List:
        //  * 2
        //  * 3
        //  *
        //  *
        //  * After prepend():
        //  * ----------------
        //  * Head: 1
        //  * Tail: 3
        //  * Length: 3
        //  *
        //  * Linked List:
        //  * 1
        //  * 2
        //  * 3
        //  *
        //  */
    }

}
