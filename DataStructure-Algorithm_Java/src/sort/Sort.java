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

    public static void main(String[] args) {
        int[] arr = {5, 21, 4, 1, 6, 3};
        selectionSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
