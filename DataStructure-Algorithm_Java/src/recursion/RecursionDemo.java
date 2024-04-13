package recursion;

public class RecursionDemo {
    // recursion is a method calling iitself until it doesn't.
    //base case : must have return statement to avoid stack overflow
    //recursive case
    public static void main(String[] argskk) {
        System.out.println(fibonacci(15) + " count: " + count);
        // System.out.println("5: " + factorial(6));
    }

    public static int factorial(int n) {
        if (n == 1) return 1;
        return n * factorial(n - 1);
    }

    static Integer[] memoization = new Integer[30];
    static int count = 0;

    // inefficient  time: O(2^n)
    public static int fibonacci(int n) {
        count++;
        if (n == 0 || n == 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }


}
