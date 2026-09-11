package leetcode.C_LinkedList;

import leetcode.common.ListNode;

/**
 * LeetCode 2: Add Two Numbers
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/add-two-numbers/
 *
 * Problem:
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order, and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 *
 * Examples:
 * l1 = [2,4,3], l2 = [5,6,4] -> [7,0,8] (342 + 465 = 807)
 * l1 = [0], l2 = [0]         -> [0]
 * l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9] -> [8,9,9,9,0,0,0,1]
 *
 * NOTE ON OVERFLOW:
 * Converting the entire list to a primitive long/int and adding will overflow when
 * lists have more than 18 digits (LeetCode lists can have up to 100 nodes).
 * The optimal approach simulates elementary math column-by-column with a carry variable.
 *
 * Time Complexity:  O(max(N, M))
 * Space Complexity: O(max(N, M)) for the new result list
 */
public class LC0002_AddTwoNumbers {

    /**
     * Optimal Solution: Elementary digit-by-digit addition with carry.
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;
            int sum = carry + x + y;

            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {
        LC0002_AddTwoNumbers solver = new LC0002_AddTwoNumbers();

        // Example 1: 342 + 465 = 807 -> [7, 0, 8]
        ListNode l1 = ListNode.of(2, 4, 3);
        ListNode l2 = ListNode.of(5, 6, 4);
        System.out.println("l1: " + l1);
        System.out.println("l2: " + l2);
        System.out.println("Sum: " + solver.addTwoNumbers(l1, l2));

        // Example 2: Large numbers with carry (prevents overflow)
        ListNode l3 = ListNode.of(9, 9, 9, 9, 9, 9, 9);
        ListNode l4 = ListNode.of(9, 9, 9, 9);
        System.out.println("\nl3: " + l3);
        System.out.println("l4: " + l4);
        System.out.println("Sum: " + solver.addTwoNumbers(l3, l4));
    }
}
