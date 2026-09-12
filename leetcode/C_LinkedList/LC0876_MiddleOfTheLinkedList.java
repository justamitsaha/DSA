package leetcode.C_LinkedList;

import leetcode.common.ListNode;

/**
 * LeetCode 876: Middle of the Linked List
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/middle-of-the-linked-list/
 *
 * Algorithm:
 * - Floyd's Tortoise and Hare (Fast & Slow Pointers)
 *
 * Concepts:
 * - By advancing `slow` by 1 step and `fast` by 2 steps:
 *     - When `fast` reaches the end (or goes beyond), `slow` will be exactly halfway through the list.
 * - For odd-length lists (e.g., 5 nodes): `fast.next == null` stops with `slow` at node 3.
 * - For even-length lists (e.g., 6 nodes): `fast == null` stops with `slow` at node 4 (the second middle node, as required).
 *
 * Complexity:
 * - Time Complexity:  O(n) - Half pass through the list.
 * - Space Complexity: O(1) - Constant auxiliary memory.
 */
public class LC0876_MiddleOfTheLinkedList {

    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static void main(String[] args) {
        LC0876_MiddleOfTheLinkedList solver = new LC0876_MiddleOfTheLinkedList();

        // Scenario 1: Odd length list [1, 2, 3, 4, 5] -> middle is 3
        ListNode l1 = ListNode.of(1, 2, 3, 4, 5);
        System.out.println("Scenario 1 (Odd: 5 nodes):   " + l1);
        System.out.println("  Middle:                   " + solver.middleNode(l1));
        System.out.println("  Expected:                 [3 -> 4 -> 5]\n");

        // Scenario 2: Even length list [1, 2, 3, 4, 5, 6] -> second middle is 4
        ListNode l2 = ListNode.of(1, 2, 3, 4, 5, 6);
        System.out.println("Scenario 2 (Even: 6 nodes):  " + l2);
        System.out.println("  Middle:                   " + solver.middleNode(l2));
        System.out.println("  Expected:                 [4 -> 5 -> 6]\n");

        // Scenario 3: Single node [42]
        ListNode l3 = ListNode.of(42);
        System.out.println("Scenario 3 (Single node):   " + l3);
        System.out.println("  Middle:                   " + solver.middleNode(l3));
        System.out.println("  Expected:                 [42]");
    }
}
