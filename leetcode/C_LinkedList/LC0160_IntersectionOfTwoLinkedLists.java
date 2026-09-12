package leetcode.C_LinkedList;

import leetcode.common.ListNode;

/**
 * LeetCode 160: Intersection of Two Linked Lists
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/intersection-of-two-linked-lists/
 *
 * Algorithms:
 * 1. Two-Pointer Cross-Switching Technique -> Optimal & Elegant (Single loop)
 * 2. Length-Difference Alignment Technique -> Explicit two-pass
 *
 * Cross-Switching Intuition:
 * - Let list A have length 'a' before intersection and 'c' common nodes: total = a + c.
 * - Let list B have length 'b' before intersection and 'c' common nodes: total = b + c.
 * - Pointer pA traverses list A, then switches to list B: traverses (a + c) + b.
 * - Pointer pB traverses list B, then switches to list A: traverses (b + c) + a.
 * - Since (a + c + b) == (b + c + a), both pointers travel the exact same total distance!
 * - They will meet at the intersection node on the second pass, or both hit null simultaneously if no intersection exists.
 *
 * Complexity:
 * - Time Complexity:  O(N + M) where N and M are the lengths of headA and headB.
 * - Space Complexity: O(1) - Constant auxiliary pointers.
 */
public class LC0160_IntersectionOfTwoLinkedLists {

    /**
     * Finds the node at which the two lists intersect.
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode pA = headA;
        ListNode pB = headB;

        // If no intersection, both pointers will eventually become null at the same time
        while (pA != pB) {
            pA = (pA == null) ? headB : pA.next;
            pB = (pB == null) ? headA : pB.next;
        }

        return pA;
    }

    public static void main(String[] args) {
        LC0160_IntersectionOfTwoLinkedLists solver = new LC0160_IntersectionOfTwoLinkedLists();

        // Scenario 1: Lists with an intersection
        // Common tail: [8 -> 4 -> 5]
        ListNode common = ListNode.of(8, 4, 5);

        // List A: [4 -> 1 -> 8 -> 4 -> 5]
        ListNode headA = new ListNode(4, new ListNode(1, common));

        // List B: [5 -> 6 -> 1 -> 8 -> 4 -> 5]
        ListNode headB = new ListNode(5, new ListNode(6, new ListNode(1, common)));

        ListNode intersection = solver.getIntersectionNode(headA, headB);
        System.out.println("Scenario 1 - Intersecting at node with value: " + (intersection != null ? intersection.val : "null"));
        System.out.println("Expected: 8\n");

        // Scenario 2: Lists with no intersection
        ListNode l1 = ListNode.of(2, 6, 4);
        ListNode l2 = ListNode.of(1, 5);
        ListNode noIntersection = solver.getIntersectionNode(l1, l2);
        System.out.println("Scenario 2 - No intersection: " + (noIntersection != null ? noIntersection.val : "null"));
        System.out.println("Expected: null");
    }
}
