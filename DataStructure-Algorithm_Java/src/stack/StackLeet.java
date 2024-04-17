package stack;

public class StackLeet {
    public static void main(String[] args) {

        String myString = "hello World";
        String reversedString = reverseString(myString);
        System.out.println(reversedString);
    }

    public static String reverseString(String str) {
        String reversed = "";
        StackArrayList<String> stack = new StackArrayList<>();
        String[] arr = str.split("");
        for (String i : arr ) {
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            reversed += stack.pop();
        }

        return reversed;
    }

    public static boolean isBalancedParentheses(String str) {
        StackArrayList<Character> stack = new StackArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            char ps = str.charAt(i);
            if (ps == '(') {
                stack.push(ps);
            } else if (ps == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void sortStack(StackArrayList<Integer> stack) {
        if (stack.isEmpty()) return;
        StackArrayList<Integer> sorted = new StackArrayList<>();
        sorted.push(stack.pop());
        while (!stack.isEmpty()) {
            int sk = stack.pop();
            while (!sorted.isEmpty() && sk < sorted.peek()) {
                int sd = sorted.pop();
                stack.push(sd);

            }
            sorted.push(sk);
        }

        while (!sorted.isEmpty()) {
            stack.push(sorted.pop());
        }
    }
}
