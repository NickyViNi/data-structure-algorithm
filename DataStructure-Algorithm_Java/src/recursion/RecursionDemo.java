package recursion;

public class RecursionDemo {
    // recursion is a method calling iitself until it doesn't.
    //base case : must have return statement to avoid stack overflow
    //recursive case
    public static void main(String[] argskk) {
        System.out.println(fibonacci3(15) + " count: " + count);
        // System.out.println("5: " + factorial(6));
    }

    public static int factorial(int n) {
        if (n == 1) return 1;
        return n * factorial(n - 1);
    }

    static int count = 0;
    // inefficient  time: O(2^n)
    public static int fibonacci1(int n) {
        count++; // compare test => when n = 15, count = 1973
        if (n == 0 || n == 1) return n;
        return fibonacci1(n - 1) + fibonacci1(n - 2);
    }

    static Integer[] memoization = new Integer[30];
    //efficient O(2n - 1) => time: O(n) Memoization
    public static int fibonacci2(int n) {
        // count++; // compare test => when n = 15, count = 29
        if(memoization[n] != null) return memoization[n];
        if(n == 0 || n == 1) return n;
        return memoization[n] = fibonacci2(n - 1) + fibonacci2(n - 2);
        // return memoization[n];
    }

    //time: O(n - 1) Bottom Up
    public static int fibonacci3(int n) {
        int[] fib = new int[n + 1];
        fib[0] = 0;
        fib[1] = 1;
        for (int i = 2; i <= n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib[n];
    }
}
