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
}
