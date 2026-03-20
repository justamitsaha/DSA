
import java.util.LinkedList;
import java.util.Queue;

/**
 * Custom Stack Implementation using two Queues.
 * 
 * @param <T> the type of elements in this stack
 */
public class StackUsingTwoQueuesCustom<T> {
    private Queue<T> q1 = new LinkedList<>();
    private Queue<T> q2 = new LinkedList<>();

    public void push(T data) {
        q2.add(data);
        while (!q1.isEmpty()) {
            q2.add(q1.remove());
        }
        Queue<T> temp = q1;
        q1 = q2;
        q2 = temp;
    }

    public T pop() {
        if (isEmpty()) return null;
        return q1.remove();
    }

    public T peek() {
        if (isEmpty()) return null;
        return q1.peek();
    }

    public boolean isEmpty() {
        return q1.isEmpty();
    }

    public int size() {
        return q1.size();
    }

    public static void main(String[] args) {
        StackUsingTwoQueuesCustom<Integer> s = new StackUsingTwoQueuesCustom<>();
        s.push(1);
        s.push(2);
        s.push(3);
        System.out.println("Popped: " + s.pop());
        System.out.println("Peek: " + s.peek());
        s.push(4);
        System.out.println("Popped: " + s.pop());
    }
}
