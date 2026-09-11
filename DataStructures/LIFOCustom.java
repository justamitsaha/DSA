
public class LIFOCustom {

    // The 'Head' is the latest node in the list
    private Node head;

    // A Node is just a small object that holds two things:
    // Data: The value you want to store (e.g., an integer).
    // Next: A reference (pointer) to the next Node in the sequence. If there is no
    // next node, this will be null.
    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // O(1) Operation: Adding to the very front
    public void addAtFront(int value) {
        Node newNode = new Node(value);
        newNode.next = head; // Point new node to current head
        head = newNode; // Move head to the new node
    }

    public void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        LIFOCustom list = new LIFOCustom();
        list.addAtFront(10);
        list.addAtFront(20);
        list.addAtFront(30);
        list.display(); // Output: 30 -> 20 -> 10 -> null
    }
}

/*
If the goal was just to use a list, you would absolutely use java.util.LinkedList or list() in Python and move on with your day.

However, since your goal is a Deep Dive, we build them from scratch for three specific reasons:

1. Understanding the "Black Box"
The built-in java.util.LinkedList is a Doubly Linked List. It’s full of extra features, edge-case handling, and optimizations that hide the core logic. By building a Node yourself, you see exactly how memory references work. You learn:

How an object "points" to another object.

Why a Linked List doesn't need to resize like an ArrayList.

What happens to "orphaned" nodes (Garbage Collection).

2. Contiguous vs. Non-Contiguous Memory
Your int[] arr benchmark worked because all 400MB of data was in one solid block. A Linked List is the opposite.

Array: Imagine a row of lockers. If you have locker #1, you know exactly where locker #100 is.

Linked List: Imagine a scavenger hunt. The note in your pocket tells you the address of the first item. At that address, you find the item and the address for the next one.

3. Coding Interview "Standard"
In technical interviews (at companies like Google, Amazon, or Microsoft), they almost never ask you to use the built-in LinkedList. Instead, they give you a custom ListNode class and ask you to reverse it, detect a cycle, or merge two lists. If you haven't built one from scratch, those problems are incredibly difficult to visualize.

Array vs. Linked List (The Trade-offs)
Since you just finished benchmarking Arrays, look at how the "building material" changes the performance:



Operation,           Array (ArrayList),   Singly Linked List,     Why?
Access by Index,        O(1),                O(n),                   Arrays use math to jump to a spot; Lists must walk node-by-node.
Insert at Front,        O(n),                O(1),                   Arrays must shift everyone; Lists just update one pointer.
Search (Unsorted),      O(n),                O(n),                   Both have to check every item in the worst case.
Memory Usage,           Fixed/Lower,        Higher,                  Lists use extra memory to store the ""Next"" pointers.

 */