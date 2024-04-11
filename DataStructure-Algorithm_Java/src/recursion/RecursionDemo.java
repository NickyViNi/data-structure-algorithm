package recursion;

public class RecursionDemo {
    // recursion is a method calling iitself until it doesn't.
    //base case : must have return statement to avoid stack overflow
    //recursive case
    public static void main(String[] argskk) {
        System.out.println("5: " + factorial(6));
    }

    public static int factorial(int n) {
        if (n == 1) return 1;
        return n * factorial(n - 1);
    }
}
