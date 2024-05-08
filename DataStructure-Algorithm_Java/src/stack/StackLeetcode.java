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
}
