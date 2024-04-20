package sort;
import java.util.Arrays;
import java.util.Collections;

public class Sort {
    public static void main(String[] args) {

        int[] arr1 = {5, 21, 41, 1, 6, 3, 2, 0, -2, -99};
        Integer[] arr2 = {5, 21, 41, 1, 6, 3, 2, 0, -2, -99};
        Arrays.sort(arr1);
        Arrays.sort(arr2, Collections.reverseOrder()); // use 'Integer' instead of 'int', sort in asdending then reverse
        // quickSort(arr);
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
    }

    private static void swap(int[] array, int idx1, int idx2) {
        int temp = array[idx1];
        array[idx1] = array[idx2];
        array[idx2] = temp;
    }

    // (1): Bubble Sort:
    public static void bubbleSort(int[] nums) {
	    for (int i = nums.length - 1; i > 0 ; i--) {
	        for (int j = 0; j < i; j++) {
	            if (nums[j] > nums[j + 1]) {
	                swap(nums, j, j + 1); //bubble the largest number to the end
	            }
	        }
	    }
	}

    // (2): Selection Sort:
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIdx = i;
            for(int j = i + 1; j < arr.length; j++) {
                if (arr[minIdx] > arr[j]) {
                    minIdx = j;
                }
            }
            if (i != minIdx) {
                swap(arr, minIdx, i);
            }
        }
    }

    // (3): insertion sort
    public static void insertionSort(int[] arr) {
	    for (int i = 1; i < arr.length; i++) {
	        int j = i - 1;
	        int temp = arr[i];
	        while (j >= 0 && temp < arr[j]) {
	            arr[j+1] = arr[j];
	            arr[j] = temp;
	            j--;
	        }
	    }
	}

    // (4) merge sort: Time: O(nlog(n))
    public static int[] merge(int[] arr1, int[] arr2) {
        int[] combineArr = new int[arr1.length + arr2.length];
        int index = 0, i = 0, j = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                combineArr[index] = arr1[i];
                i++;
                index++;
            } else {
                combineArr[index] = arr2[j];
                j++;
                index++;
            }
        }

        while (i < arr1.length) {
            combineArr[index] = arr1[i];
            i++;
            index++;
        }

        while (j < arr2.length) {
            combineArr[index] = arr2[j];
            j++;
            index++;
        }

        return combineArr;
    }

    public static int[] mergeSort(int[] arr) {
	    if (arr.length == 1) return arr;
	    int minIdx = arr.length / 2;
	    int[] left = mergeSort(Arrays.copyOfRange(arr, 0, minIdx));
	    int[] right = mergeSort(Arrays.copyOfRange(arr, minIdx, arr.length));
	    return merge(left, right);
	}

    //(5) quick sort: O(nlog(n), if the list already sorted, time is O(n^2)
    private static int pivot(int[] arr, int pivotIdx, int endIdx) {
	    int swapIdx = pivotIdx;
	    for (int i = pivotIdx + 1; i <= endIdx; i++) {
	        if (arr[i] < arr[pivotIdx]) {
	            swapIdx++;
	            if (swapIdx != i) swap(arr, swapIdx, i);
	        }
	    }
	    swap(arr, swapIdx, pivotIdx);
	    return swapIdx;
	}

    private static void quickSortHelper(int[] arr, int left, int right) {
	    if (left < right) {
	        int pivotIndex = pivot(arr, left, right);
	        quickSortHelper(arr, left, pivotIndex - 1);
	        quickSortHelper(arr, pivotIndex + 1, right);
	    }
	}

    public static void quickSort(int[] arr) {
        quickSortHelper(arr, 0, arr.length - 1);
    }
}
