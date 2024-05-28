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

    //649 medium -> Dota2 Senate
    public String predictPartyVictory(String senate) {
        int len = senate.length();
        Queue<Integer> queueR = new LinkedList<>();
        Queue<Integer> queueD = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            char ch = senate.charAt(i);
            if (ch == 'R') queueR.offer(i);
            if (ch == 'D') queueD.offer(i);
        }
        while (!queueD.isEmpty() && !queueR.isEmpty()) {
            int r = queueR.poll();
            int d = queueD.poll();
            if (r < d) queueR.offer(len++);
            else queueD.offer(len++);
        }
        return queueD.isEmpty() ? "Radiant" : "Dire";
    }
}
