package queue;

import java.util.Queue;
import java.util.LinkedList;

public class QueueLeetCode {

    //933 easy -> Number of Recent Calls
    Queue<Integer> calls;
    public QueueLeetCode() {
        calls = new LinkedList<>();
    }
    public int ping(int t) {
        calls.offer(t);
        while (t- 3000 > calls.peek()) {
            calls.poll();
        }
        return calls.size();
    }
}
