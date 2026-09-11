# LeetCode Problem Repository

This directory contains Java solutions for LeetCode problems, categorized by topic and algorithmic pattern.

---

## 📁 Topic Directory Structure

| Folder | Package Name | Focus Topics & Patterns |
| :--- | :--- | :--- |
| [`common/`](file:///C:/Amit/work/code/DSA/leetcode/common/) | `leetcode.common` | Shared definitions (`ListNode`, `TreeNode`) for local test runs |
| [`arrays/`](file:///C:/Amit/work/code/DSA/leetcode/arrays/) | `leetcode.arrays` | Two Pointers, Sliding Window, Prefix Sums, Kadane's |
| [`strings/`](file:///C:/Amit/work/code/DSA/leetcode/strings/) | `leetcode.strings` | Palindromes, Substrings, Anagrams, String Matching |
| [`linked_lists/`](file:///C:/Amit/work/code/DSA/leetcode/linked_lists/) | `leetcode.linked_lists` | Fast & Slow Pointers, Reversals, Merging |
| [`stacks_queues/`](file:///C:/Amit/work/code/DSA/leetcode/stacks_queues/) | `leetcode.stacks_queues` | Monotonic Stack, Matching Parentheses, Min Stack |
| [`hashing/`](file:///C:/Amit/work/code/DSA/leetcode/hashing/) | `leetcode.hashing` | Frequency Counting, Hash Sets, Hash Maps |
| [`trees/`](file:///C:/Amit/work/code/DSA/leetcode/trees/) | `leetcode.trees` | Tree Traversals (In/Pre/Post/Level), BST, Path Sums |
| [`heaps/`](file:///C:/Amit/work/code/DSA/leetcode/heaps/) | `leetcode.heaps` | Top-K Elements, Min/Max Heap, Median of Stream |
| [`graphs/`](file:///C:/Amit/work/code/DSA/leetcode/graphs/) | `leetcode.graphs` | BFS, DFS, Topological Sort, Shortest Path |
| [`backtracking/`](file:///C:/Amit/work/code/DSA/leetcode/backtracking/) | `leetcode.backtracking` | Permutations, Subsets, N-Queens, Sudoku |
| [`dynamic_programming/`](file:///C:/Amit/work/code/DSA/leetcode/dynamic_programming/) | `leetcode.dynamic_programming` | 0/1 Knapsack, LCS, LIS, Coin Change, Grid Paths |
| [`greedy/`](file:///C:/Amit/work/code/DSA/leetcode/greedy/) | `leetcode.greedy` | Interval Scheduling, Jump Game, Gas Station |
| [`binary_search/`](file:///C:/Amit/work/code/DSA/leetcode/binary_search/) | `leetcode.binary_search` | Search in Rotated Array, Binary Search on Answer |

---

## 🏷️ File Naming Convention

Name files using 4-digit problem numbers followed by PascalCase problem name:

```text
LC<4-digit-number>_<ProblemTitle>.java
```

**Examples:**
- `LC0001_TwoSum.java`
- `LC0002_AddTwoNumbers.java`
- `LC0003_LongestSubstringWithoutRepeatingCharacters.java`
- `LC0053_MaximumSubarray.java`

---

## 📝 Problem Template

When adding solutions from your Udemy course, use this template:

```java
package leetcode.arrays; // Adjust package to folder

import java.util.*;

/**
 * LeetCode <Number>: <Title>
 * Difficulty: Easy | Medium | Hard
 * Link: https://leetcode.com/problems/<problem-slug>/
 *
 * Concepts: <e.g., Two Pointers, HashMap>
 * Time Complexity: O(...)
 * Space Complexity: O(...)
 */
public class LC0000_ProblemTitle {

    public int exampleSolution(int[] nums) {
        // Solution implementation
        return 0;
    }

    public static void main(String[] args) {
        LC0000_ProblemTitle solver = new LC0000_ProblemTitle();
        // Add sample tests
    }
}
```
