package stack;

public class StackLeet {
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

    public static void main(String[] args) {

        String myString = "hello World";
        String reversedString = reverseString(myString);
        System.out.println(reversedString);
    }
}
