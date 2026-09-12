package leetcode.C_LinkedList;

import leetcode.common.ListNode;

/**
 * LeetCode 141: Linked List Cycle
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/linked-list-cycle/
 *
 * Algorithm:
 * - Floyd's Cycle-Finding Algorithm (Tortoise and Hare)
 *
 * Concepts:
 * - Maintain two pointers:
 *     - `slow` moves 1 step at a time.
 *     - `fast` moves 2 steps at a time.
 * - If there is NO cycle, `fast` will eventually encounter null and terminate.
 * - If there IS a cycle, `fast` enters the cycle and laps `slow` from behind.
 *   The distance between them decreases by 1 node per iteration, guaranteeing a collision in O(N).
 *
 * Complexity:
 * - Time Complexity:  O(N) where N is the number of nodes.
 * - Space Complexity: O(1) - Constant auxiliary memory.
 */
public class LC0141_LinkedListCycle {

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true; // Cycle detected
            }
        }

        return false; // Reached end of list without collision
    }

    public static void main(String[] args) {
        LC0141_LinkedListCycle solver = new LC0141_LinkedListCycle();

        // Scenario 1: Standard cycle [3 -> 2 -> 0 -> -4 -> points back to 2]
        ListNode n1 = new ListNode(3);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(0);
        ListNode n4 = new ListNode(-4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n2; // Creates cycle back to node 2
        System.out.println("Scenario 1 - Has Cycle: " + solver.hasCycle(n1) + " | Expected: true");

        // Scenario 2: No cycle [1 -> 2 -> 3 -> 4]
        ListNode l2 = ListNode.of(1, 2, 3, 4);
        System.out.println("Scenario 2 - No Cycle:  " + solver.hasCycle(l2) + " | Expected: false");

        // Scenario 3: Single node self-loop [1 -> 1]
        ListNode nSingle = new ListNode(1);
        nSingle.next = nSingle;
        System.out.println("Scenario 3 - Self Loop: " + solver.hasCycle(nSingle) + " | Expected: true");
    }
}
