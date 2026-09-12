package leetcode.C_LinkedList;

import leetcode.common.ListNode;

/**
 * LeetCode 143: Reorder List
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/reorder-list/
 *
 * Algorithm:
 * - Three-Step In-Place Reordering:
 *     1. Find Middle (Fast & Slow Pointers)
 *     2. Split & Reverse Second Half
 *     3. Interweave / Alternating Merge of First and Second Halves
 *
 * Problem:
 * Reorder L0 -> L1 -> ... -> Ln-1 -> Ln to:
 * L0 -> Ln -> L1 -> Ln-1 -> L2 -> Ln-2 -> ...
 * You may not modify values in the list's nodes, only nodes themselves may be changed.
 *
 * Example trace [1 -> 2 -> 3 -> 4 -> 5]:
 *   1. Middle is 3. Split into [1 -> 2 -> 3] and [4 -> 5].
 *   2. Reverse second half: [5 -> 4].
 *   3. Merge alternating: 1 -> 5 -> 2 -> 4 -> 3.
 *
 * Complexity:
 * - Time Complexity:  O(n) - Linear time across all three phases.
 * - Space Complexity: O(1) - Pointer adjustments strictly in-place.
 */
public class LC0143_ReorderList {

    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }

        // Step 1: Find middle using slow and fast pointers
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Split into two lists
        ListNode secondHalf = slow.next;
        slow.next = null;

        // Step 2: Reverse the second half
        secondHalf = reverse(secondHalf);

        // Step 3: Merge the two lists alternatively
        ListNode p1 = head;
        ListNode p2 = secondHalf;

        while (p2 != null) {
            ListNode nextP1 = p1.next;
            ListNode nextP2 = p2.next;

            p1.next = p2;
            p2.next = nextP1;

            p1 = nextP1;
            p2 = nextP2;
        }
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        LC0143_ReorderList solver = new LC0143_ReorderList();

        // Scenario 1: Odd length [1 -> 2 -> 3 -> 4 -> 5]
        ListNode l1 = ListNode.of(1, 2, 3, 4, 5);
        System.out.println("Scenario 1 - Original: " + l1);
        solver.reorderList(l1);
        System.out.println("  Reordered:           " + l1);
        System.out.println("  Expected:            [1 -> 5 -> 2 -> 4 -> 3]\n");

        // Scenario 2: Even length [1 -> 2 -> 3 -> 4]
        ListNode l2 = ListNode.of(1, 2, 3, 4);
        System.out.println("Scenario 2 - Original: " + l2);
        solver.reorderList(l2);
        System.out.println("  Reordered:           " + l2);
        System.out.println("  Expected:            [1 -> 4 -> 2 -> 3]\n");

        // Scenario 3: Two elements [1 -> 2]
        ListNode l3 = ListNode.of(1, 2);
        System.out.println("Scenario 3 - Two nodes: " + l3);
        solver.reorderList(l3);
        System.out.println("  Reordered:            " + l3);
        System.out.println("  Expected:             [1 -> 2]");
    }
}
