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

        System.out.println("Max Heap:");
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
