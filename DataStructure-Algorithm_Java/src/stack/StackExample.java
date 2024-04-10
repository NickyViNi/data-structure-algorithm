package stack;
import java.util.Stack;

public class StackExample {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.add(12);
        stack.add(25);
        stack.push(26);
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        System.out.println(stack.search(25));
        System.out.println(stack.empty());
    }
}
