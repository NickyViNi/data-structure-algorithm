package heap;
import java.util.ArrayList;
import java.util.List;

//The MinHeap class represents a min heap, a complete binary tree in which the value in each internal node is less than or equal to the values in the children of that node.

public class MinHeap {
    private List<Integer> heap;

    public MinHeap() {
        this.heap = new ArrayList<>();
    }

    public List<Integer> getHeap() {
        return new ArrayList<>(heap);
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
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
        while(currIdx > 0 && heap.get(currIdx) < heap.get(parent(currIdx))) {
            swap(currIdx, parent(currIdx));
            currIdx = parent(currIdx);
        }
    }

    public Integer remove() {
        if(heap.size() == 0) return null;
        if(heap.size() == 1) return heap.remove(0);
        int minVal = heap.get(0);
        heap.set(0, heap.remove(heap.size() - 1));
        sinkDown(0);
        return minVal;
    }

    private void sinkDown(int index) {
        int minIdx = index;
        while(true) {
            int leftIdx = leftChild(minIdx);
            int rightIdx = rightChild(minIdx);
            if(leftIdx < heap.size() && heap.get(leftIdx) < heap.get(minIdx)) {
                minIdx = leftIdx;
            }
            if(rightIdx < heap.size() && heap.get(rightIdx) < heap.get(minIdx)) {
                minIdx = rightIdx;
            }
            if(minIdx != index) {
                swap(minIdx, index);
                index = minIdx;
            } else {
                return;
            }
        }
    }
}
