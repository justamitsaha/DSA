import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class B_InsertionComparison {
    public static void main(String[] args) {
        int iterations = 100_000;

        // 1. Benchmark ArrayList (Array-based)
        List<Integer> arrayList = new ArrayList<>();
        long startArray = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            arrayList.add(0, i); // Insert at the very front
        }
        long endArray = System.nanoTime();
        System.out.println(arrayList.get(24));

        arrayList = new ArrayList<>();
        long arrayAddStart = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            arrayList.add(i); // Insert at the end (for comparison)
        }
        long arrayAddEnd = System.nanoTime();
        System.out.println(arrayList.get(24));

        // 2. Benchmark LinkedList (Node-based)
        List<Integer> linkedList = new LinkedList<>();
        long startLinked = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            linkedList.add(0, i); // Insert at the very front
        }
        long endLinked = System.nanoTime();

        System.out.println("ArrayList Front Insertion: " + (endArray - startArray) / 1_000_000.0 + " ms");
        System.out.println("ArrayList End Insertion: " + (arrayAddEnd - arrayAddStart) / 1_000_000.0 + " ms");        
        System.out.println("LinkedList Front Insertion: " + (endLinked - startLinked) / 1_000_000.0 + " ms");
    }
}

/*
When you insert at the front of an Array/ArrayList:
- The computer has to move the item at index 0 to index 1.
- Then index 1 to index 2... and so on for all $n$ elements.
- This is $O(n)$. If you do this $100,000$ times, you are essentially performing $O(n^2)$ work.
When you insert at the front of a Linked List:
- The computer creates a node.
- It points the new node to the old head.
- This is $O(1)$, no matter how big the list is.

Why the gap is so huge?
If you run these, you might see results like this:
- Linked List: ~5 to 10 ms (It just creates pointers).
- Array List: ~500 to 2,000 ms (It has to copy millions of elements in memory).
The "Deep Dive" Observation
Notice that in both Java and Python, the Array-based version gets exponentially slower as the list grows. The first 10 insertions are fast, but the 99,990th insertion requires shifting 99,990 items. This is why we say the "Average Time" per insertion for an array is $O(n)$, while for a linked list, it is a rock-solid $O(1)$.


*/