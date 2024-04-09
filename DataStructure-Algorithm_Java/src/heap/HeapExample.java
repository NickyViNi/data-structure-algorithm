package heap;
import java.util.PriorityQueue;
import java.util.Comparator;

public class HeapExample {
    public static void main(String[] args) {

        //(1) Max Heap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        maxHeap.add(3);
        maxHeap.add(7);
        maxHeap.add(1);
        maxHeap.add(4);
        maxHeap.add(2);
        maxHeap.offer(18);
        maxHeap.offer(7);

        System.out.println("Max Heap:");
        System.out.println(maxHeap.contains(41)); //Returns true if this queue contains the specified element.
        System.out.println(maxHeap.peek()); //Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
        System.out.println(maxHeap.poll()); // Retrieves and removes the head of this queue, or returns null if this queue is empty.
        System.out.println(maxHeap.remove(7)); //Removes a single instance of the specified element from this queue, if it is present.

        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.poll() + " ");
        }
        System.out.println();

        //(2) Min Heap (Default behavior)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.add(3);
        minHeap.add(7);
        minHeap.add(1);
        minHeap.add(4);
        minHeap.add(2);

        System.out.println("Min Heap:");
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.poll() + " ");
        }
        System.out.println();
    }
}
