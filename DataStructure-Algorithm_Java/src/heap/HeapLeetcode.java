package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class HeapLeetcode {
    //1046 easy -> Last Stone Weight
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int stone : stones) {
            maxHeap.add(stone);
        }

        while (maxHeap.size() > 1) {
            int first = maxHeap.poll();
            int second = maxHeap.poll();
            if (second != first) {
                maxHeap.add(first - second);
            }
        }

        return maxHeap.size() > 0 ? maxHeap.poll() : 0;
    }
    public int lastStoneWeight2(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        for (int stone : stones) {
            maxHeap.add(stone);
        }
        while (maxHeap.size() > 1) {
            maxHeap.add(maxHeap.poll() - maxHeap.poll());
        }
        return maxHeap.poll();
    }
}
