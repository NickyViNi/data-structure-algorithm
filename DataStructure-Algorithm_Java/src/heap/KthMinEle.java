package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KthMinEle {
        // WRITE THE FINDKTHSMALLEST METHOD HERE //
        public static boolean isSameEleArr(int[] nums) {
            for (int i = 0; i < nums.length - 2; i++) {
                if (nums[i] != nums[i + 1]) return false;
            }
            return true;
        }

        // public static int findKthSmallest(int[] nums, int k) {
        //     if(nums.length == 1 || isSameEleArr(nums)) return nums[0];
        //     Heap maxHeap = new Heap();
        //     int kNum = 0;
        //     for (int i : nums) {
        //         maxHeap.insert(i);
        //     }
        //     int targetIdx = maxHeap.getHeap().size() - k;
        //     for (int i = 0; i <= targetIdx; i++) {
        //         kNum = maxHeap.remove();
        //     }

        //     return kNum;
        // }

        public static int findKthSmallest2(int[] nums, int k) {
            if(nums.length == 1 || isSameEleArr(nums)) return nums[0];
            Heap maxHeap = new Heap();

            for (int i : nums) {
                maxHeap.insert(i);
                if(maxHeap.getHeap().size() > k){
                    maxHeap.remove();
                }
            }

            return maxHeap.remove();
        }

        public static Integer findKthSmallest(int[] nums, int k) {

            if(nums.length == 1 || isSameEleArr(nums)) return nums[0];

            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

            for (int i : nums) {
                maxHeap.add(i);
                if(maxHeap.size() > k){
                    maxHeap.poll();
                }
            }

            return maxHeap.poll();
        }

        public static void main(String[] args) {
            // Test case 1
            int[] nums1 = {7, 10, 4, 3, 20, 15};
            int k1 = 3;
            System.out.println("Test case 1:");
            System.out.println("Expected output: 7");
            System.out.println("Actual output: " + findKthSmallest(nums1, k1));
            System.out.println();

            // Test case 2
            int[] nums2 = {2, 1, 3, 5, 6, 4};
            int k2 = 2;
            System.out.println("Test case 2:");
            System.out.println("Expected output: 2");
            System.out.println("Actual output: " + findKthSmallest(nums2, k2));
            System.out.println();

            // Test case 3
            int[] nums3 = {9, 3, 2, 11, 7, 10, 4, 5};
            int k3 = 5;
            System.out.println("Test case 3:");
            System.out.println("Expected output: 7");
            System.out.println("Actual output: " + findKthSmallest(nums3, k3));
            System.out.println();

            /*
                EXPECTED OUTPUT:
                ----------------
                Test case 1:
                Expected output: 7
                Actual output: 7

                Test case 2:
                Expected output: 2
                Actual output: 2

                Test case 3:
                Expected output: 7
                Actual output: 7

            */

        }
}
