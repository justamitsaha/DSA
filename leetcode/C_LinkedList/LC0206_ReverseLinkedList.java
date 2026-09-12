package leetcode.C_LinkedList;

import leetcode.common.ListNode;

/**
 * LeetCode 206: Reverse Linked List
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/reverse-linked-list/
 *
 * Algorithms:
 * 1. Iterative (Three Pointers: prev, curr, next) -> Optimal Space O(1)
 * 2. Recursive (Divide and Conquer)               -> Space O(n) Call Stack
 *
 * Iterative Intuition:
 * - As we walk forward through the list:
 *     1. Cache the next node: `next = curr.next`
 *     2. Invert the pointer:  `curr.next = prev`
 *     3. Advance prev:        `prev = curr`
 *     4. Advance curr:        `curr = next`
 * - When `curr` becomes null, `prev` points to the new head of the reversed list.
 *
 * Complexity:
 * - Iterative: Time O(n), Space O(1).
 * - Recursive: Time O(n), Space O(n) call stack.
 */
public class LC0206_ReverseLinkedList {

    /**
     * Approach 1: Iterative Three-Pointer In-Place Reversal (Optimal).
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next; // Cache next node
            curr.next = prev;          // Reverse direction
            prev = curr;               // Advance prev
            curr = next;               // Advance curr
        }

        return prev;
    }

    /**
     * Approach 2: Recursive Reversal.
     */
    public ListNode reverseListRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = reverseListRecursive(head.next);
        head.next.next = head; // Point the next node's next back to head
        head.next = null;      // Cut old forward pointer

        return newHead;
    }

    public static void main(String[] args) {
        LC0206_ReverseLinkedList solver = new LC0206_ReverseLinkedList();

        // Scenario 1: Standard 5-node list [1 -> 2 -> 3 -> 4 -> 5]
        ListNode l1 = ListNode.of(1, 2, 3, 4, 5);
        System.out.println("Scenario 1 - Iterative:");
        System.out.println("  Original: " + l1);
        System.out.println("  Reversed: " + solver.reverseList(l1));
        System.out.println("  Expected: [5 -> 4 -> 3 -> 2 -> 1]\n");

        // Scenario 2: Two-node list using recursive approach [1 -> 2]
        ListNode l2 = ListNode.of(1, 2);
        System.out.println("Scenario 2 - Recursive:");
        System.out.println("  Original: " + l2);
        System.out.println("  Reversed: " + solver.reverseListRecursive(l2));
        System.out.println("  Expected: [2 -> 1]\n");

        // Scenario 3: Empty list
        System.out.println("Scenario 3 - Empty list: " + solver.reverseList(null) + " | Expected: null");
    }
}
