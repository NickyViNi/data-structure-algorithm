package stack;
import java.util.Stack;

//155 medium -> min stack
//way1: using one stack
public class MinStack155 {
    int min;
    Stack<Integer> stack;
    public MinStack155() {
        this.stack = new Stack<>();
        this.min = Integer.MAX_VALUE;
    }

    public void push(int val) {
        if (val <= min) {
            stack.push(min);
            min = val;
        }
        stack.push(val);
    }

    public void pop() {
        if (stack.pop() == min) {
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}

////way2: using two stacks
class MinStack {
    Stack<Integer> stack;
    Stack<Integer> minstack;
    public MinStack() {
        this.stack = new Stack<>();
        this.minstack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        if (minstack.empty()) {
            minstack.push(val);
        } else {
            if (val <= minstack.peek()) minstack.push(val);
        }
    }

    public void pop() {
        int removed = stack.pop();
        if (removed == minstack.peek()) minstack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minstack.peek();
    }
}
