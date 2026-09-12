package leetcode.C_LinkedList;

import leetcode.common.ListNode;

/**
 * LeetCode 19: Remove Nth Node From End of List
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 *
 * Algorithm:
 * - Two-Pointer One-Pass Sliding Gap (with Dummy Head Node)
 *
 * Concepts:
 * - Using a `dummy` node pointing to `head` simplifies edge cases where the head itself must be removed.
 * - Maintain two pointers: `fast` and `slow`, both starting at `dummy`.
 * - Advance `fast` forward by n + 1 steps so the gap between `fast` and `slow` is exactly n nodes.
 * - Move both `fast` and `slow` forward one node at a time until `fast` reaches null.
 * - At that moment, `slow` is standing directly BEFORE the node that needs to be deleted!
 * - Delete the target node: `slow.next = slow.next.next`.
 *
 * Complexity:
 * - Time Complexity:  O(L) where L is the number of nodes (single pass).
 * - Space Complexity: O(1) - In-place pointer modifications.
 */
public class LC0019_RemoveNthNodeFromEndOfList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy;
        ListNode slow = dummy;

        // Advance fast by n + 1 steps to establish the gap
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // Walk both until fast reaches null
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // Remove the target node
        slow.next = slow.next.next;

        return dummy.next;
    }

    public static void main(String[] args) {
        LC0019_RemoveNthNodeFromEndOfList solver = new LC0019_RemoveNthNodeFromEndOfList();

        // Scenario 1: Standard removal from middle [1 -> 2 -> 3 -> 4 -> 5], n = 2 -> [1 -> 2 -> 3 -> 5]
        ListNode l1 = ListNode.of(1, 2, 3, 4, 5);
        System.out.println("Scenario 1: Original: " + l1);
        System.out.println("  Remove 2nd from end: " + solver.removeNthFromEnd(l1, 2));
        System.out.println("  Expected:           [1 -> 2 -> 3 -> 5]\n");

        // Scenario 2: Single node list, n = 1 -> null
        ListNode l2 = ListNode.of(1);
        System.out.println("Scenario 2 - Single node: " + l2);
        System.out.println("  Remove 1st from end:    " + solver.removeNthFromEnd(l2, 1));
        System.out.println("  Expected:              null\n");

        // Scenario 3: Remove the head node [1 -> 2], n = 2 -> [2]
        ListNode l3 = ListNode.of(1, 2);
        System.out.println("Scenario 3 - Remove Head: " + l3);
        System.out.println("  Remove 2nd from end:    " + solver.removeNthFromEnd(l3, 2));
        System.out.println("  Expected:              [2]");
    }
}
