package queue;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.LinkedList;

public class QueueExample {
    public static void main(String[] args) {
        Queue<Integer> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new ArrayDeque<>();

        queue1.add(12);
        queue1.offer(13);
        queue1.offer(14);
        queue1.offer(15);
        System.out.println(queue1);
        System.out.println(queue1.remove());
        System.out.println(queue1.poll());
        System.out.println(queue1.element());
        System.out.println(queue1.peek());
        System.out.println(queue1);

        queue2.add(22);
        queue2.offer(23);
        queue2.offer(24);
        queue2.offer(25);
        System.out.println(queue2);
        System.out.println(queue2.remove());
        System.out.println(queue2.poll());
        System.out.println(queue2.element());
        System.out.println(queue2.peek());
        System.out.println(queue2);
    }
}
