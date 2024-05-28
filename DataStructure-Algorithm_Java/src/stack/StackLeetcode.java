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

    //735 medium -> asteroid collision
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int aster : asteroids) {
            if (aster > 0) {
                stack.push(aster);
            } else {
                while (!stack.empty() && stack.peek() > 0 && stack.peek() < -aster) {
                    stack.pop();
                }
                if (stack.empty() || stack.peek() < 0) {
                    stack.push(aster);
                }
                if (stack.peek() == -aster) {
                    stack.pop();
                }
            }
        }
        int[] result = new int[stack.size()];
        int idx = stack.size() - 1;
        while (!stack.empty()) result[idx--] = stack.pop();
        return result;
    }

    //394 medium -> Decode String
    public String decodeString(String s) {
        Stack<String> dict = new Stack<>();
        Stack<Integer> nums = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                int num = ch - '0';
                while(Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + (s.charAt(i + 1) - '0');
                    i++;
                }
                nums.push(num);
            } else if (ch == '[') {
                dict.push("[");
            } else if (Character.isLetter(ch)) {
                dict.push(ch + "");
            } else if (ch == ']') {
                String temp = "";
                while (dict.peek() != "[") {
                    temp += dict.pop();
                }
                int re= nums.pop();
                String letters = temp.repeat(re);
                dict.pop();
                dict.push(letters);
            }
        }
        StringBuilder res = new StringBuilder();
        while (!dict.empty()) res.append(dict.pop());
        return res.reverse().toString();
    }
}
