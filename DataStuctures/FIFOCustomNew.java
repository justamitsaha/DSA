public class FIFOCustomNew<T> {

    private static class Node<T> {
        T val;
        Node<T> next;

        Node(T val) {
            this.val = val;
        }
    }

    private Node<T> head;   // front
    private Node<T> tail;   // rear
    private int size;       // O(1) size tracking

    /* Enqueue → O(1) */
    public void enqueue(T value) {
        Node<T> n = new Node<>(value);

        if (head == null) {
            head = n;
            tail = n;
        } else {
            tail.next = n;
            tail = n;
        }
        size++;
    }

    /* Dequeue → O(1) */
    public T dequeue() {
        if (head == null) return null;

        T val = head.val;
        head = head.next;

        if (head == null) {
            tail = null;
        }

        size--;
        return val;
    }

    /* Peek front → O(1) */
    public T peek() {
        return (head == null) ? null : head.val;
    }

    /* Check empty → O(1) */
    public boolean isEmpty() {
        return head == null;
    }

    /* Size → O(1) */
    public int size() {
        return size;
    }

    /* Clear queue */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /* Debug traversal */
    public void traverse() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }

    /* Optional: contains */
    public boolean contains(T value) {
        Node<T> current = head;
        while (current != null) {
            if ((value == null && current.val == null) ||
                    (value != null && value.equals(current.val))) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /* Optional: convert to array (Object[]) */
    public Object[] toArray() {
        Object[] arr = new Object[size];
        Node<T> current = head;
        int i = 0;

        while (current != null) {
            arr[i++] = current.val;
            current = current.next;
        }
        return arr;
    }
}