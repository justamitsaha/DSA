/*
Queue / FIFO using singly linked list

Structure:
head -> first element to be removed
tail -> last element to be inserted

Example:
head
 ↓
[ 1 | next ] → [ 2 | next ] → [ 3 | next ] → null
                                      ↑
                                     tail
*/

public class FIFOCustom {

    private static class Node {
        private final int value;
        private Node next;

        private Node(int value) {
            this.value = value;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    // Add element to rear - O(1) also called enqueue
    public void push(int value) {
        Node newNode = new Node(value);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    // Remove element from front - O(1) also called dequeue
    public Integer pop() {
        if (head == null) {
            return null;
        }

        int removedValue = head.value;
        head = head.next;

        if (head == null) {
            tail = null;
        }

        size--;
        return removedValue;
    }

    // View front element without removing it - O(1)
    public Integer peek() {
        return head == null ? null : head.value;
    }

    // Check whether queue is empty - O(1)
    public boolean isEmpty() {
        return head == null;
    }

    // Number of elements in queue - O(1)
    public int size() {
        return size;
    }

    // Remove all elements - O(1)
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    // Print all elements from head to tail - O(n)
    public void traverse() {
        Node current = head;

        while (current != null) {
            System.out.println(current.value);
            current = current.next;
        }
    }

    // Optional helper: view front node value and rear node value
    public Integer front() {
        return peek();
    }

    public Integer rear() {
        return tail == null ? null : tail.value;
    }

    public static void main(String[] args) {
        FIFOCustom queue = new FIFOCustom();

        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        queue.push(5);

        System.out.println("Traverse:");
        queue.traverse();

        System.out.println("Front: " + queue.front());
        System.out.println("Rear: " + queue.rear());
        System.out.println("Size: " + queue.size());
        System.out.println("Is Empty: " + queue.isEmpty());

        System.out.println("Dequeued: " + queue.pop());
        System.out.println("Dequeued: " + queue.pop());

        System.out.println("After dequeue:");
        queue.traverse();

        System.out.println("Front: " + queue.front());
        System.out.println("Rear: " + queue.rear());
        System.out.println("Size: " + queue.size());

        queue.clear();
        System.out.println("After clear, Is Empty: " + queue.isEmpty());
    }
}