/*
Structure:
Last In First Out (LIFO)

Example:
[ 3 ] <- Top
[ 2 ]
[ 1 ]
*/

import java.util.EmptyStackException;

/**
 * Custom Stack Implementation including both Array and Linked List versions.
 * 
 * @param <T> the type of elements in this stack
 */
public class A_StackCustom<T> {

    /**
     * Array-based implementation of Stack.
     */
    public static class ArrayStack<T> {
        private T[] array;
        private int top;
        private int capacity;

        @SuppressWarnings("unchecked")
        public ArrayStack(int size) {
            this.capacity = size;
            this.array = (T[]) new Object[size];
            this.top = -1;
        }

        public void push(T value) {
            if (isFull()) {
                throw new StackOverflowError("Stack is full");
            }
            array[++top] = value;
        }

        public T pop() {
            if (isEmpty()) {
                throw new EmptyStackException();
            }
            T value = array[top];
            array[top--] = null; // Clear reference for GC
            return value;
        }

        public T peek() {
            if (isEmpty()) {
                throw new EmptyStackException();
            }
            return array[top];
        }

        public boolean isEmpty() {
            return top == -1;
        }

        public boolean isFull() {
            return top == capacity - 1;
        }

        public int size() {
            return top + 1;
        }

        public void display() {
            System.out.print("Array Stack: ");
            for (int i = 0; i <= top; i++) {
                System.out.print(array[i] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Linked List-based implementation of Stack.
     */
    public static class LinkedStack<T> {
        private static class Node<T> {
            T data;
            Node<T> next;

            Node(T data) {
                this.data = data;
            }
        }

        private Node<T> top;
        private int size;

        public LinkedStack() {
            this.top = null;
            this.size = 0;
        }

        public void push(T value) {
            Node<T> newNode = new Node<>(value);
            newNode.next = top;
            top = newNode;
            size++;
        }

        public T pop() {
            if (isEmpty()) {
                throw new EmptyStackException();
            }
            T value = top.data;
            top = top.next;
            size--;
            return value;
        }

        public T peek() {
            if (isEmpty()) {
                throw new EmptyStackException();
            }
            return top.data;
        }

        public boolean isEmpty() {
            return top == null;
        }

        public int size() {
            return size;
        }

        public void display() {
            System.out.print("Linked Stack: ");
            Node<T> current = top;
            while (current != null) {
                System.out.print(current.data + " -> ");
                current = current.next;
            }
            System.out.println("null");
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Testing Array Stack ---");
        ArrayStack<Integer> arrayStack = new ArrayStack<>(5);
        arrayStack.push(10);
        arrayStack.push(20);
        arrayStack.push(30);
        arrayStack.display();
        System.out.println("Popped: " + arrayStack.pop());
        arrayStack.display();

        System.out.println("\n--- Testing Linked Stack ---");
        LinkedStack<String> linkedStack = new LinkedStack<>();
        linkedStack.push("A");
        linkedStack.push("B");
        linkedStack.push("C");
        linkedStack.display();
        System.out.println("Peek: " + linkedStack.peek());
        System.out.println("Popped: " + linkedStack.pop());
        linkedStack.display();
    }
}
