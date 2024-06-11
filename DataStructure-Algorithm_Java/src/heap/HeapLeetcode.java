package heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
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

    //215 medium -> Kth largest element in an array
    public int findKthLargest(int[] nums, int k) {
        if (nums.length == 1) return nums[0];
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        for (int num : nums) {
            maxHeap.add(num);
        }
        int count = 0;
        while (maxHeap.size() > 0) {
            int knum = maxHeap.poll();
            count++;
            if (count == k) return knum;
        }
        return -1;
    }

    //373 medium -> find k pairs with smallest sums
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> pairs = new ArrayList<>();
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] + a[1] - b[0] - b[1]);
        //let nums1 as col, nums2 as row, get 2D pairs matrix, matrix first col element sum will be the smallest in each row;
        //add first col pairs into minHeap
        for (int i = 0; i < nums1.length && i < k; i++) {
            minHeap.offer(new int[] {nums1[i], nums2[0], 0}); //last one is nums2 index
        }
        while (!minHeap.isEmpty() && k-- > 0) {
            int[] curr = minHeap.poll();
            pairs.add(Arrays.asList(curr[0], curr[1]));
            if (curr[2] == nums2.length - 1) continue;
            minHeap.offer(new int[] {curr[0], nums2[curr[2] + 1], curr[2] + 1});
        }
        return pairs;
    }
}

//2336 medium -> Smallest Number in Infinite Set
class SmallestInfiniteSet {
    PriorityQueue<Integer> smallestSet;
    public SmallestInfiniteSet() {
        this.smallestSet = new PriorityQueue<>();
        for (int i = 1; i <= 1000; i++) {
            smallestSet.add(i);
        }
    }

    public int popSmallest() {
        return smallestSet.poll();
    }

    public void addBack(int num) {
        if (!smallestSet.contains(num)) {
            smallestSet.add(num);
        }
    }
}
