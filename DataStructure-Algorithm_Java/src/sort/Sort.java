package sort;
import java.util.Arrays;

public class Sort {
    // (1): Bubble Sort:
    public static void bubbleSort(int[] nums) {
	    for (int i = nums.length - 1; i > 0 ; i--) {
	        for (int j = 0; j < i; j++) {
	            if (nums[j] > nums[j + 1]) {
	                int temp = nums[j];
	                nums[j] = nums[j + 1];
	                nums[j + 1] = temp;
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
                int temp = arr[minIdx];
                arr[minIdx] = arr[i];
                arr[i] = temp;
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

    // (4) merge sort:
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

    public static void main(String[] args) {
        int[] arr = {5, 21, 41, 1, 6, 3};
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
