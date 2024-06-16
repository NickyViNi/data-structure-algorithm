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

    //2542 medium ->  Maximum Subsequence Score
    public long maxScore(int[] nums1, int[] nums2, int k) { // time: O(nlog(n)), space: O(n)
        long max = 0, sum = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int[][] pairs = new int[nums1.length][2];
        for (int i = 0; i < nums1.length; i++) {
            pairs[i][0] = nums1[i];
            pairs[i][1] = nums2[i];
        }
        Arrays.sort(pairs, (a, b) -> b[1] - a[1]);
        for (int i = 0; i < nums1.length; i++) {
            if (i >= k - 1) {
                max = Math.max(max, (sum + pairs[i][0]) * pairs[i][1]);
            }
            if (i < k - 1) {
                pq.add(pairs[i][0]);
                sum += pairs[i][0];
            } else if (!pq.isEmpty() && pairs[i][0] > pq.peek()) {
                sum = sum - pq.poll() + pairs[i][0];
                pq.add(pairs[i][0]);
            }
        }
        return max;
    }
    //2462 medium -> Total Cost to Hire K Workers
    public long totalCost(int[] costs, int k, int candidates) { // time: O(nlog(n)), space: O(n)
        int left = 0, right = costs.length - 1;
        long hireCosts = 0;
        PriorityQueue<Integer> leftPq = new PriorityQueue<>();
        PriorityQueue<Integer> rightPq = new PriorityQueue<>();
        while (k-- > 0) { //This loop runs k times.
            while (leftPq.size() < candidates && left <= right) { // This loop run O(candidates*log(candidates))
                leftPq.add(costs[left++]); // Adding an element to a priority queue (min-heap) has a time complexity of O(log(candidates)).
            }
            while (rightPq.size() < candidates && left <= right) {
                rightPq.add(costs[right--]);
            }
            int leftNum = leftPq.size() > 0 ? leftPq.peek() : Integer.MAX_VALUE;
            int rightNum = rightPq.size() > 0 ? rightPq.peek() : Integer.MAX_VALUE;
            if (leftNum <= rightNum) {
                hireCosts += leftNum;
                leftPq.poll(); // One removal from a priority queue:O(log(candidates))
            } else {
                hireCosts += rightNum;
                rightPq.poll();
            }
        }
        return hireCosts;
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

    public void addBack(int num) { //time: O(n)
        if (!smallestSet.contains(num)) { // O(n)
            smallestSet.add(num); // O(logN)
        }
    }
}
