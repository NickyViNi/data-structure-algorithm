import java.util.Scanner;

public class DataSA {
    public static void main (String[] args) {
//        swap(12, 35);
//        acceptUserInput();
        DataSA data = new DataSA();
        System.out.println(data.binarySearch(new int[]{1, 3, 5, 6, 7, 9, 10}, 6));
//        System.out.println(data.add(12, 34));
//        System.out.println(data.combineStr("Nicky, I love you!"));
    }

   public static void swap (int a, int b)  {
        int temp = a;
        a = b;
        b = temp;
        System.out.println("a = " + a + ", b = " + b);
   }

   public static void acceptUserInput () {
        String str;
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your string: ");
        str = input.nextLine();
        System.out.println("Your entered string is: " + str);
   }

   public int add(int a, int b) {
        int c = a + b;
        return c;
   }

   String combineStr(String a) {
        String combine = "Hello, dear " + a;
        return combine;
   }

   int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left +  (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            }
//            arr[mid] < target ? left = mid + 1 : right = mid - 1;
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
   }
}
