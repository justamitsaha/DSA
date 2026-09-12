package leetcode.C_LinkedList;

import leetcode.common.ListNode;

/**
 * LeetCode 21: Merge Two Sorted Lists
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/merge-two-sorted-lists/
 *
 * Algorithms:
 * 1. Iterative with Dummy Head (In-Place Splice) -> Optimal Space O(1)
 * 2. Recursive Merging -> Space O(n + m) call stack
 *
 * Concepts:
 * - We create a dummy head node to simplify edge cases (avoids special-casing the real head).
 * - A `curr` pointer compares list1.val and list2.val:
 *     - If list1.val <= list2.val: splice list1 node (curr.next = list1, list1 = list1.next).
 *     - Else: splice list2 node (curr.next = list2, list2 = list2.next).
 *     - Advance curr = curr.next.
 * - Once one list becomes null, append the entire remaining non-null list in O(1).
 *
 * Complexity:
 * - Time Complexity:  O(n + m) where n and m are the lengths of the two lists.
 * - Space Complexity: O(1) auxiliary space (splices existing nodes in-place).
 */
public class LC0021_MergeTwoSortedLists {

    /**
     * Merges two sorted linked lists in-place using a dummy head.
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }

        // Attach whichever list has remaining elements
        curr.next = (list1 != null) ? list1 : list2;

        return dummy.next;
    }

    public static void main(String[] args) {
        LC0021_MergeTwoSortedLists solver = new LC0021_MergeTwoSortedLists();

        // Scenario 1: Standard overlapping sorted lists
        ListNode l1 = ListNode.of(1, 2, 4);
        ListNode l2 = ListNode.of(1, 3, 4);
        System.out.println("Scenario 1:");
        System.out.println("  List 1: " + l1);
        System.out.println("  List 2: " + l2);
        System.out.println("  Merged: " + solver.mergeTwoLists(l1, l2));
        System.out.println("  Expected: [1 -> 1 -> 2 -> 3 -> 4 -> 4]\n");

        // Scenario 2: One empty list
        ListNode l3 = null;
        ListNode l4 = ListNode.of(0);
        System.out.println("Scenario 2 - One empty list:");
        System.out.println("  Merged: " + solver.mergeTwoLists(l3, l4));
        System.out.println("  Expected: [0]\n");

        // Scenario 3: Both empty lists
        System.out.println("Scenario 3 - Both empty lists:");
        System.out.println("  Merged: " + solver.mergeTwoLists(null, null));
        System.out.println("  Expected: null");
    }
}
