
/**
 * Custom Circular Queue Implementation.
 * 
 * @param <T> the type of elements in this queue
 */
public class CircularQueueCustom<T> {
    private T[] array;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    @SuppressWarnings("unchecked")
    public CircularQueueCustom(int capacity) {
        this.capacity = capacity;
        this.array = (T[]) new Object[capacity];
        this.front = -1;
        this.rear = -1;
        this.size = 0;
    }

    public boolean enqueue(T value) {
        if (isFull()) {
            System.out.println("Queue is Full");
            return false;
        }
        if (isEmpty()) {
            front = 0;
        }
        rear = (rear + 1) % capacity;
        array[rear] = value;
        size++;
        return true;
    }

    public T dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is Empty");
            return null;
        }
        T value = array[front];
        array[front] = null; // Clear reference
        if (front == rear) {
            front = -1;
            rear = -1;
        } else {
            front = (front + 1) % capacity;
        }
        size--;
        return value;
    }

    public T peek() {
        if (isEmpty()) return null;
        return array[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int size() {
        return size;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        System.out.print("Circular Queue: ");
        int i = front;
        while (true) {
            System.out.print(array[i] + " ");
            if (i == rear) break;
            i = (i + 1) % capacity;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CircularQueueCustom<Integer> q = new CircularQueueCustom<>(5);
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.enqueue(40);
        q.display();
        System.out.println("Dequeued: " + q.dequeue());
        q.enqueue(50);
        q.enqueue(60);
        q.display();
    }
}
