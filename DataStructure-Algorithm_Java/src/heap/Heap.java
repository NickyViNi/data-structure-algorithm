package heap;
import java.util.ArrayList;
import java.util.List;

// The Heap class represents a max heap, a complete binary tree in which the value in each internal node is greater than or equal to the values in the children of that node.
// remove or insert time complexity: O(log n)
public class Heap {
    private List<Integer> heap;

    public Heap() {
        this.heap = new ArrayList<>();
    }

    public List<Integer> getHeap() {
        return new ArrayList<>(heap);
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return index * 2 + 2;
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private void swap(int index1, int index2) {
        int temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

    public void insert(int value) {
        heap.add(value);
        int currIdx = heap.size() - 1;

        while (currIdx > 0 && heap.get(currIdx) > heap.get(parent(currIdx))) {
            swap(currIdx, parent(currIdx));
            currIdx = parent(currIdx);
        }
    }

    public Integer remove() {
        if (heap.size() == 0) return null;
        if (heap.size() == 1) return heap.remove(0);

        int maxValue = heap.get(0);
        heap.set(0, heap.remove(heap.size() - 1));
        sinkDown(0);

        return maxValue;
    }

    private void sinkDown(int index) {
        int maxIdx = index;
        while (true) {
            int leftIdx = leftChild(index);
            int rightIdx = rightChild(index);

            if (leftIdx < heap.size() && heap.get(leftIdx) > heap.get(maxIdx)) {
                maxIdx = leftIdx;
            }

            if (rightIdx < heap.size() && heap.get(rightIdx) > heap.get(maxIdx)) {
                maxIdx = rightIdx;
            }

            if (maxIdx != index) {
                swap(index, maxIdx);
                index = maxIdx;
            } else {
                return;
            }
        }
    }
}
