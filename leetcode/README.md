# LeetCode Problem Repository

This directory contains Java solutions for LeetCode problems, categorized by topic and algorithmic pattern matching your Udemy course modules.

---

## 📁 Topic Directory Structure

| Folder | Package Name | Focus Topics & Course Module |
| :--- | :--- | :--- |
| [`common/`](file:///C:/Amit/work/code/DSA/leetcode/common/) | `leetcode.common` | Shared helper structures (`ListNode`, `TreeNode`) for local testing |
| [`A_Array/`](file:///C:/Amit/work/code/DSA/leetcode/A_Array/) | `leetcode.A_Array` | Basic array manipulations, Kadane's, Prefix/Suffix, Stock Trading |
| [`B_Sorting/`](file:///C:/Amit/work/code/DSA/leetcode/B_Sorting/) | `leetcode.B_Sorting` | Dutch National Flag, Counting Sort, Boyer-Moore, Move Zeroes |
| [`C_LinkedList/`](file:///C:/Amit/work/code/DSA/leetcode/C_LinkedList/) | `leetcode.C_LinkedList` | Fast/Slow pointers, Cycle detection, List reversal, Merge |
| [`D_HashTable/`](file:///C:/Amit/work/code/DSA/leetcode/D_HashTable/) | `leetcode.D_HashTable` | Frequency maps, Two Sum, Group Anagrams, Longest Consecutive |
| [`E_Stacks/`](file:///C:/Amit/work/code/DSA/leetcode/E_Stacks/) | `leetcode.E_Stacks` | Valid Parentheses, Min Stack, Reverse Polish Notation, Monotonic Stack |
| [`F_SlidingWindow/`](file:///C:/Amit/work/code/DSA/leetcode/F_SlidingWindow/) | `leetcode.F_SlidingWindow` | Fixed & Dynamic sliding window, Substring character counts |
| [`G_Two_pointer_methods/`](file:///C:/Amit/work/code/DSA/leetcode/G_Two_pointer_methods/) | `leetcode.G_Two_pointer_methods` | Container With Most Water, Trapping Rain Water, Palindromes |
| [`H_MathsGeometry/`](file:///C:/Amit/work/code/DSA/leetcode/H_MathsGeometry/) | `leetcode.H_MathsGeometry` | Rotate Image, Set Matrix Zeroes, Spiral Matrix |
| [`I_Intervals/`](file:///C:/Amit/work/code/DSA/leetcode/I_Intervals/) | `leetcode.I_Intervals` | Merge Intervals, Insert Interval, Non-overlapping Intervals |
| [`J_BinarySearch_1/`](file:///C:/Amit/work/code/DSA/leetcode/J_BinarySearch_1/) | `leetcode.J_BinarySearch_1` | Core Binary Search, First/Last Position, Peak Element |
| [`K_BinarySearch_2/`](file:///C:/Amit/work/code/DSA/leetcode/K_BinarySearch_2/) | `leetcode.K_BinarySearch_2` | Rotated sorted arrays, Binary search on answer space |
| [`L_RecursionBacktracking/`](file:///C:/Amit/work/code/DSA/leetcode/L_RecursionBacktracking/) | `leetcode.L_RecursionBacktracking` | Subsets, Permutations, Combinations, Word Search, N-Queens |
| [`M_BinaryTree/`](file:///C:/Amit/work/code/DSA/leetcode/M_BinaryTree/) | `leetcode.M_BinaryTree` | Traversals (DFS/BFS), Max Depth, Diameter, Tree Inversion |
| [`N_BinarySearchTree/`](file:///C:/Amit/work/code/DSA/leetcode/N_BinarySearchTree/) | `leetcode.N_BinarySearchTree` | Validate BST, LCA, Kth Smallest in BST |
| [`O_Heap/`](file:///C:/Amit/work/code/DSA/leetcode/O_Heap/) | `leetcode.O_Heap` | PriorityQueue, Kth Largest, Top K Frequent, Median Finder |
| [`P_DynamicPrograming/`](file:///C:/Amit/work/code/DSA/leetcode/P_DynamicPrograming/) | `leetcode.P_DynamicPrograming` | 1D/2D DP, Climbing Stairs, Coin Change, LCS, LIS, Knapsack |
| [`Q_BitManipulation/`](file:///C:/Amit/work/code/DSA/leetcode/Q_BitManipulation/) | `leetcode.Q_BitManipulation` | XOR tricks, Single Number, Counting Bits, Reverse Bits |
| [`R_GraphTheory/`](file:///C:/Amit/work/code/DSA/leetcode/R_GraphTheory/) | `leetcode.R_GraphTheory` | BFS, DFS, Number of Islands, Clone Graph, Topological Sort |
| [`S_GreedyAlgorithm/`](file:///C:/Amit/work/code/DSA/leetcode/S_GreedyAlgorithm/) | `leetcode.S_GreedyAlgorithm` | Jump Game, Gas Station, Greedy choices |

---

## 🏷️ File & Class Naming Convention

Every problem file follows:
```text
File Name:  LC<4-digit-number>_<ProblemTitle>.java
Class Name: public class LC<4-digit-number>_<ProblemTitle>
Package:    package leetcode.<Folder_Name>;
```

**Examples:**
- `leetcode/A_Array/LC0121_BestTimeToBuyAndSellStock.java` -> `package leetcode.A_Array;`
- `leetcode/B_Sorting/LC0075_SortColors.java` -> `package leetcode.B_Sorting;`
- `leetcode/D_HashTable/LC0001_TwoSum.java` -> `package leetcode.D_HashTable;`
- `leetcode/E_Stacks/LC0020_ValidParentheses.java` -> `package leetcode.E_Stacks;`

---

## 📝 Problem File Template

```java
package leetcode.A_Array; // Adjust package to current module folder

import java.util.*;

/**
 * LeetCode <Number>: <Title>
 * Difficulty: Easy | Medium | Hard
 * Link: https://leetcode.com/problems/<problem-slug>/
 *
 * Algorithm:
 * - <Algorithm Name, e.g., Kadane's Algorithm, Dutch National Flag>
 *
 * Concepts:
 * - <Explanation of approach and core intuition>
 *
 * Complexity:
 * - Time Complexity:  O(...)
 * - Space Complexity: O(...)
 */
public class LC0000_ProblemTitle {

    public int exampleSolution(int[] nums) {
        // Implementation with in-line comments
        return 0;
    }

    public static void main(String[] args) {
        LC0000_ProblemTitle solver = new LC0000_ProblemTitle();
        // Diverse test scenarios (standard, edge case, expected vs actual)
    }
}
```
