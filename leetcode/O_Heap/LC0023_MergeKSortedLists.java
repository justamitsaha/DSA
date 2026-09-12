package leetcode.O_Heap;

import leetcode.common.ListNode;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LeetCode 23: Merge k Sorted Lists
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/merge-k-sorted-lists/
 *
 * Algorithm:
 * - Min-Heap / Priority Queue Multi-Way Merge
 *
 * Concepts:
 * - Given an array of `k` linked-lists `lists`, each linked-list is sorted in ascending order.
 * - Merge all the linked-lists into one sorted linked-list and return it.
 * - Heap Optimization:
 *   - Insert the head node of each of the `k` lists into a Min-Heap of size at most `k`.
 *   - The root of the heap always holds the globally smallest available node.
 *   - Poll the minimum node, append it to the merged list's tail, and if `minNode.next != null`,
 *     push `minNode.next` into the heap.
 *   - Repeat until the heap is empty.
 *
 * Complexity:
 * - Time Complexity:  O(N log k) - Where N is the total number of nodes across all lists, and k is the number of lists.
 *                     Each node is inserted and extracted from the size-k heap once.
 * - Space Complexity: O(k)       - For the priority queue holding at most k node pointers.
 */
public class LC0023_MergeKSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        // Min-heap ordering nodes by their integer values
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(
            lists.length,
            Comparator.comparingInt(a -> a.val)
        );

        // Add the head of each non-empty list to the heap
        for (ListNode listHead : lists) {
            if (listHead != null) {
                minHeap.offer(listHead);
            }
        }

        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        while (!minHeap.isEmpty()) {
            ListNode minNode = minHeap.poll();
            tail.next = minNode;
            tail = tail.next;

            // If the extracted node has a next element, add it to the min-heap
            if (minNode.next != null) {
                minHeap.offer(minNode.next);
            }
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        LC0023_MergeKSortedLists solver = new LC0023_MergeKSortedLists();

        // Scenario 1: [[1->4->5], [1->3->4], [2->6]]
        ListNode l1 = ListNode.fromArray(new int[]{1, 4, 5});
        ListNode l2 = ListNode.fromArray(new int[]{1, 3, 4});
        ListNode l3 = ListNode.fromArray(new int[]{2, 6});

        ListNode merged1 = solver.mergeKLists(new ListNode[]{l1, l2, l3});
        System.out.println("Merged 3 lists: " + ListNode.toList(merged1) + " (Expected: [1, 1, 2, 3, 4, 4, 5, 6])");

        // Scenario 2: Empty lists array []
        ListNode merged2 = solver.mergeKLists(new ListNode[]{});
        System.out.println("Merged empty: " + ListNode.toList(merged2) + " (Expected: [])");

        // Scenario 3: Array with empty list [null]
        ListNode merged3 = solver.mergeKLists(new ListNode[]{null});
        System.out.println("Merged [null]: " + ListNode.toList(merged3) + " (Expected: [])");
    }
}
