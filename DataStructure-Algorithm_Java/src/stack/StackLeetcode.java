package stack;
import java.util.Stack;

public class StackLeetcode {
    //71 medium -> simplify path
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] pathArr = path.split("/");
        for (String pa : pathArr) {
            if (pa == null || pa.length() == 0 || pa.equals(".")) continue;
            if (pa.equals("..")) {
                if (!stack.empty()) stack.pop();
            } else {
                stack.push(pa);
            }
        }
        String result = "";
        while (!stack.empty()) {
            result = "/" + stack.pop() + result;
        }
        return result.length() == 0? "/" : result;
    }

    //150 medium -> Evaluate reverse polish notation
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String str : tokens) {
            if (str.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (str.equals("-")) {
                int num2 = stack.pop();
                int num1 = stack.pop();
                stack.push(num1 - num2);
            } else if (str.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (str.equals("/")) {
                int num2 = stack.pop();
                int num1 = stack.pop();
                stack.push(num1 / num2);
            } else {
                stack.push(Integer.parseInt(str));
            }
        }
        return stack.pop();
    }

    //2390 medium -> Removing Stars From a String
    public String removeStars(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder str = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (ch == '*') {
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        while (!stack.empty()) str.append(stack.pop());
        return str.reverse().toString();
    }

    // way2 is too slow
    public String removeStars2(String s) {
        Stack<Character> stack = new Stack<>();
        String res = "";
        for (char ch : s.toCharArray()) stack.push(ch);
        int stars = 0;
        while (!stack.empty()) {
            while (stack.peek() == '*') {
                stack.pop();
                stars++;
            }
            while (stars > 0 && stack.peek() != '*') {
                stack.pop();
                stars--;
            }
            if (!stack.empty() && stack.peek() != '*') res = stack.pop() + res;
        }
        return res;
    }
}
