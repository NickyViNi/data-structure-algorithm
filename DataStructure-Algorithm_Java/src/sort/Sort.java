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

    public static void main(String[] args) {
        int[] arr = {5, 2, 4, 1, 6, 3};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
