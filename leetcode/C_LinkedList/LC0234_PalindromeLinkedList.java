package leetcode.C_LinkedList;

import leetcode.common.ListNode;

/**
 * LeetCode 234: Palindrome Linked List
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/palindrome-linked-list/
 *
 * Algorithm:
 * - Floyd's Tortoise & Hare (Find Middle) + In-Place Sublist Reversal + Two-Pointer Check
 *
 * Concepts:
 * - A naive check copies all values to an ArrayList and uses two pointers ($O(n)$ space).
 * - The optimal $O(1)$ space algorithm operates in three distinct phases:
 *     1. Find Middle: Use slow and fast pointers. When fast reaches the end, slow is at the middle.
 *     2. Reverse Second Half: Reverse the sublist starting from slow.
 *     3. Compare Halves: Walk from head and reversed second-half head simultaneously, comparing values.
 *
 * Complexity:
 * - Time Complexity:  O(n) - Single pass to find middle, half pass to reverse, half pass to compare.
 * - Space Complexity: O(1) - Modifies pointers in-place without extra collections.
 */
public class LC0234_PalindromeLinkedList {

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        // Phase 1: Find middle using slow and fast pointers
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Phase 2: Reverse the second half starting from slow
        ListNode secondHalf = reverse(slow);

        // Phase 3: Compare first half and reversed second half
        ListNode p1 = head;
        ListNode p2 = secondHalf;
        boolean palindrome = true;

        while (p2 != null) {
            if (p1.val != p2.val) {
                palindrome = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        return palindrome;
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
        LC0234_PalindromeLinkedList solver = new LC0234_PalindromeLinkedList();

        // Scenario 1: Even length palindrome [1 -> 2 -> 2 -> 1]
        ListNode l1 = ListNode.of(1, 2, 2, 1);
        System.out.println("Scenario 1: [1 -> 2 -> 2 -> 1]");
        System.out.println("  Is Palindrome: " + solver.isPalindrome(l1) + " | Expected: true\n");

        // Scenario 2: Odd length palindrome [1 -> 2 -> 3 -> 2 -> 1]
        ListNode l2 = ListNode.of(1, 2, 3, 2, 1);
        System.out.println("Scenario 2: [1 -> 2 -> 3 -> 2 -> 1]");
        System.out.println("  Is Palindrome: " + solver.isPalindrome(l2) + " | Expected: true\n");

        // Scenario 3: Non-palindrome [1 -> 2]
        ListNode l3 = ListNode.of(1, 2);
        System.out.println("Scenario 3: [1 -> 2]");
        System.out.println("  Is Palindrome: " + solver.isPalindrome(l3) + " | Expected: false\n");

        // Scenario 4: Single element [7]
        ListNode l4 = ListNode.of(7);
        System.out.println("Scenario 4: [7]");
        System.out.println("  Is Palindrome: " + solver.isPalindrome(l4) + " | Expected: true");
    }
}
