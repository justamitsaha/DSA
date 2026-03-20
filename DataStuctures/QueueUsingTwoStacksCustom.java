
import java.util.Stack;

/**
 * Custom Queue Implementation using two Stacks.
 * 
 * @param <T> the type of elements in this queue
 */
public class QueueUsingTwoStacksCustom<T> {
    private Stack<T> stack1 = new Stack<>();
    private Stack<T> stack2 = new Stack<>();

    public void enqueue(T data) {
        stack1.push(data);
    }

    public T dequeue() {
        if (isEmpty()) return null;
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public T peek() {
        if (isEmpty()) return null;
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.peek();
    }

    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

    public int size() {
        return stack1.size() + stack2.size();
    }

    public static void main(String[] args) {
        QueueUsingTwoStacksCustom<Integer> q = new QueueUsingTwoStacksCustom<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        System.out.println("Dequeued: " + q.dequeue());
        q.enqueue(4);
        System.out.println("Dequeued: " + q.dequeue());
        System.out.println("Peek: " + q.peek());
    }
}
