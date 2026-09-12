# DSA Master Study Plan & Progress Tracker

This document tracks the journey through Data Structures and Algorithms, following the roadmap in `README.md`.

## Legend
- [x] **Implemented**: Data structure written from scratch in Java & Python (in `DataStructures/`).
- [x] **Problem Solving**: LeetCode solutions added in `leetcode/<topic>/`.
- [ ] **Contrast**: Documented language-specific differences (Java vs Python).

---

## Phase 1: The Essentials
| Topic | Implementation | Problems | Theory/Note |
| :--- | :---: | :---: | :--- |
| **Complexity Analysis** | [x] | [ ] | See `Basics/` and `docs/notes/` |
| **Language Basics (Java/Python)** | [x] | [ ] | Focus on Collections vs Built-ins (`Basics/`) |

---

## Phase 2: Linear Data Structures
| Topic | Implementation | Problems | Key Files |
| :--- | :---: | :---: | :--- |
| **Arrays** | [ ] | [x] | `leetcode/A_Array/`: LC 36 (Sudoku), LC 53 (Max Subarray), LC 121 (Stock), LC 152 (Max Product), LC 189 (Rotate), LC 238 (Product), LC 271 (Encode/Decode), LC 485 (Max Ones), LC 973 (K Closest) |
| **Strings** | [ ] | [x] | `leetcode/F_SlidingWindow/`: LC 3, LC 239, LC 424; `leetcode/G_Two_pointer_methods/`: LC 11, LC 42, LC 125, LC 167 |
| **Linked Lists (Singly)** | [ ] | [x] | `leetcode/C_LinkedList/`: LC 2 (Add Two), LC 19 (Remove Nth), LC 21 (Merge), LC 141 (Cycle I), LC 142 (Cycle II), LC 143 (Reorder), LC 160 (Intersection), LC 206 (Reverse), LC 234 (Palindrome), LC 876 (Middle) |
| **Linked Lists (Doubly)** | [x] | [ ] | `DataStructures/B_DoublyLinkedListCustom` |
| **Linked Lists (Circular)** | [ ] | [ ] | |
| **Stack** | [x] | [x] | `leetcode/E_Stacks/`: LC 20 (Valid Parens), LC 150 (RPN), LC 155 (Min Stack), LC 503 (Next Greater II); `DataStructures/A_StackCustom` |
| **Queue (Circular)** | [x] | [ ] | `DataStructures/C_CircularQueueCustom` |
| **Deque** | [x] | [ ] | `DataStructures/D_DequeCustom` |
| **Stack/Queue Inter-impl** | [x] | [ ] | `DataStructures/E_QueueUsingTwoStacksCustom`, `F_StackUsingTwoQueuesCustom` |

---

## Phase 3: Non-Linear Data Structures
| Topic | Implementation | Problems | Key Files |
| :--- | :---: | :---: | :--- |
| **Hashing** | [x] | [x] | `leetcode/D_HashTable/`: LC 1 (Two Sum), LC 15 (3Sum), LC 49 (Group Anagrams), LC 128 (Longest Consecutive), LC 217 (Contains Duplicate), LC 242 (Valid Anagram); `DataStructures/J_HashMapCustom` |
| **Trees (Binary Tree)** | [x] | [x] | `leetcode/M_BinaryTree/`: LC 94, LC 100, LC 101, LC 102, LC 104, LC 106, LC 110, LC 112, LC 113, LC 124, LC 199, LC 208, LC 211, LC 226, LC 297, LC 404, LC 543, LC 572, LC 993, LC 1161, LC 1448; `DataStructures/H_BinaryTreeCustom` |
| **Trees (BST)** | [x] | [x] | `leetcode/N_BinarySearchTree/`: LC 98 (Validate BST), LC 230 (Kth Smallest in BST), LC 235 (LCA of BST); `DataStructures/I_BinarySearchTreeCustom` |
| **Heaps / Priority Queue** | [x] | [x] | `leetcode/O_Heap/`: LC 23 (Merge K Lists), LC 215 (Kth Largest in Array), LC 253 (Meeting Rooms II), LC 295 (Median Stream), LC 347 (Top K Frequent), LC 703 (Kth Largest in Stream), LC 1046 (Last Stone Weight); `DataStructures/G_PriorityQueueCustom` |
| **Graphs (Adjacency List/Matrix)** | [x] | [x] | `leetcode/R_GraphTheory/`: LC 133 (Clone Graph), LC 207 (Course Schedule), LC 261 (Graph Valid Tree), LC 269 (Alien Dict), LC 997 (Town Judge); `DataStructures/L_GraphCustom` |
| **Graph Traversal (BFS/DFS)** | [x] | [x] | `leetcode/R_GraphTheory/`: LC 130 (Surrounded Regions), LC 200 (Islands), LC 323 (Connected Components), LC 417 (Pacific Atlantic), LC 733 (Flood Fill), LC 994 (Rotting Oranges), LC 1020 (Enclaves); `DataStructures/L_GraphCustom` |

---

## Phase 4: Advanced Algorithms & Structures
| Topic | Implementation | Problems | Status |
| :--- | :---: | :---: | :--- |
| **Maths & Geometry** | [ ] | [x] | `leetcode/H_MathsGeometry/`: LC 48 (Rotate Image), LC 50 (Pow(x, n)), LC 54 (Spiral Matrix), LC 73 (Set Matrix Zeroes) |
| **Bit Manipulation** | [ ] | [x] | `leetcode/Q_BitManipulation/`: LC 7 (Reverse Int), LC 29 (Divide Ints), LC 136 (Single Number), LC 191 (Number of 1 Bits), LC 268 (Missing Number), LC 287 (Duplicate Number), LC 338 (Counting Bits), LC 371 (Sum of Two Integers) |
| **Intervals** | [ ] | [x] | `leetcode/I_Intervals/`: LC 35 (Search Insert), LC 56 (Merge Intervals), LC 57 (Insert Interval), LC 252 (Meeting Rooms), LC 435 (Non-overlapping Intervals) |
| **Sorting & Searching** | [ ] | [x] | `leetcode/B_Sorting/`: LC 75 (Colors), LC 169 (Majority), LC 283 (Move Zeroes); `leetcode/J_BinarySearch_1/`: LC 33 (Rotated Search), LC 34 (First/Last Pos), LC 153 (Min in Rotated); `leetcode/K_BinarySearch_2/`: LC 875 (Koko Eating Bananas), LC 1552 (Magnetic Force Between Two Balls) |
| **Recursion & Backtracking** | [ ] | [x] | `leetcode/L_RecursionBacktracking/`: LC 17 (Phone Letters), LC 39 (Combination Sum), LC 40 (Combination Sum II), LC 46 (Permutations), LC 51 (N-Queens), LC 78 (Subsets), LC 79 (Word Search), LC 90 (Subsets II), LC 212 (Word Search II); `Basics/E_FibonacciOptimization` |
| **Dynamic Programming** | [ ] | [x] | `leetcode/P_DynamicPrograming/`: LC 5 (Palindromic Substring), LC 55 (Jump Game), LC 62 (Unique Paths), LC 70 (Climb Stairs), LC 91 (Decode Ways), LC 139 (Word Break), LC 198 (House Robber), LC 213 (House Robber II), LC 300 (LIS), LC 322 (Coin Change), LC 397 (Integer Replacement), LC 416 (Partition Equal Subset Sum), LC 494 (Target Sum), LC 542 (01 Matrix), LC 647 (Palindromic Substrings), LC 746 (Min Cost Climb Stairs), LC 1143 (LCS) |
| **Greedy Algorithms** | [ ] | [x] | `leetcode/S_GreedyAlgorithm/`: LC 921 (Min Add Parentheses); `leetcode/I_Intervals/`: LC 435 (Non-overlapping Intervals) |
| **Graph Algorithms (Dijkstra)** | [ ] | [x] | `leetcode/R_GraphTheory/`: LC 743 (Network Delay Time) |
| **Trie** | [x] | [x] | `leetcode/M_BinaryTree/`: LC 208 (Trie), LC 211 (Word Dict); `leetcode/L_RecursionBacktracking/`: LC 212 (Word Search II); `DataStructures/K_TrieCustom` |
| **Union-Find (DSU)** | [ ] | [x] | `leetcode/R_GraphTheory/`: LC 261 (Graph Valid Tree), LC 323 (Connected Components) |
| **Segment Tree / Fenwick** | [ ] | [ ] | |

---

## Immediate Next Steps
1. [x] **Import Udemy Problems**: Completed migration of all 132 problems across 19 categories into `leetcode/` with clean naming (`LC<4-digit-number>_<ProblemTitle>.java`), complete Javadoc, complexity analyses, and runnable test suites.
2. **Arrays & Strings**: Complete scratch implementations for rotations, reversal, and sliding window.
3. **Problem Solving**: Add 5 classic problems for each implemented data structure in `leetcode/`.

## Study Workflow (Per Topic)
1. **Theory**: Read concept in `README.md` and `docs/notes/`.
2. **Code**: Implement `_custom` in Java and Python in `DataStructures/`.
3. **Compare**: Update `Contrast` notes.
4. **Solve**: Add problems in `leetcode/<topic>/`.
