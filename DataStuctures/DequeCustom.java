
/**
 * Custom Deque (Double-ended Queue) Implementation using Doubly Linked List.
 * 
 * @param <T> the type of elements in this deque
 */
public class DequeCustom<T> {

    private static class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public T removeFirst() {
        if (isEmpty()) return null;
        T data = head.data;
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
        return data;
    }

    public T removeLast() {
        if (isEmpty()) return null;
        T data = tail.data;
        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return data;
    }

    public T peekFirst() {
        return isEmpty() ? null : head.data;
    }

    public T peekLast() {
        return isEmpty() ? null : tail.data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void display() {
        System.out.print("Deque: ");
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DequeCustom<Integer> dq = new DequeCustom<>();
        dq.addLast(10);
        dq.addLast(20);
        dq.addFirst(5);
        dq.display();
        System.out.println("Removed Last: " + dq.removeLast());
        System.out.println("Removed First: " + dq.removeFirst());
        dq.display();
    }
}
