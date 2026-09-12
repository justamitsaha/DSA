package leetcode.C_LinkedList;

import leetcode.common.ListNode;

/**
 * LeetCode 142: Linked List Cycle II
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/linked-list-cycle-ii/
 *
 * Algorithm:
 * - Floyd's Cycle Detection & Cycle Entrance Locator (Two-Phase Algorithm)
 *
 * Mathematical Proof:
 * - Let:
 *     L = Distance from head to cycle start node.
 *     C = Length of the cycle.
 *     d = Distance from cycle start node to collision point.
 * - When slow and fast collide:
 *     Distance(slow) = L + d
 *     Distance(fast) = L + d + k * C  (fast has looped through the cycle k times)
 * - Since fast travels at twice the speed of slow:
 *     2 * (L + d) = L + d + k * C
 *     L + d = k * C
 *     L = k * C - d = (k - 1) * C + (C - d)
 * - KEY INSIGHT: The distance from `head` to the cycle entrance (L) is EXACTLY equal to
 *   the distance from the `collision point` to the cycle entrance (C - d)!
 * - Therefore: Place one pointer at `head` and one at `collisionPoint`. Advance both by 1 step.
 *   They will meet precisely at the entry node of the cycle!
 *
 * Complexity:
 * - Time Complexity:  O(N) - Linear scan.
 * - Space Complexity: O(1) - Constant memory.
 */
public class LC0142_LinkedListCycleII {

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;
        ListNode collision = null;

        // Phase 1: Detect whether a cycle exists and find the collision node
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                collision = slow;
                break;
            }
        }

        // If no cycle was detected
        if (collision == null) {
            return null;
        }

        // Phase 2: Find the cycle start node
        ListNode p1 = head;
        ListNode p2 = collision;

        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return p1; // Both pointers now point to the start of the cycle
    }

    public static void main(String[] args) {
        LC0142_LinkedListCycleII solver = new LC0142_LinkedListCycleII();

        // Scenario 1: Cycle starting at node 2 [3 -> 2 -> 0 -> -4 -> back to 2]
        ListNode n1 = new ListNode(3);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(0);
        ListNode n4 = new ListNode(-4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n2; // Loop back to n2

        ListNode cycleStart1 = solver.detectCycle(n1);
        System.out.println("Scenario 1 - Cycle Entrance Node Val: " + (cycleStart1 != null ? cycleStart1.val : "null"));
        System.out.println("Expected: 2\n");

        // Scenario 2: Two nodes in a loop [1 -> 2 -> back to 1]
        ListNode nA = new ListNode(1);
        ListNode nB = new ListNode(2);
        nA.next = nB;
        nB.next = nA;

        ListNode cycleStart2 = solver.detectCycle(nA);
        System.out.println("Scenario 2 - Cycle Entrance Node Val: " + (cycleStart2 != null ? cycleStart2.val : "null"));
        System.out.println("Expected: 1\n");

        // Scenario 3: No cycle [1 -> 2 -> 3]
        ListNode noCycle = ListNode.of(1, 2, 3);
        ListNode cycleStart3 = solver.detectCycle(noCycle);
        System.out.println("Scenario 3 - No Cycle: " + (cycleStart3 != null ? cycleStart3.val : "null"));
        System.out.println("Expected: null");
    }
}
