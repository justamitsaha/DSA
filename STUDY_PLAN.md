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
| **Arrays** | [ ] | [x] | `leetcode/A_Array/`: LC 53 (Max Subarray), LC 121 (Stock), LC 189 (Rotate), LC 238 (Product) |
| **Strings** | [ ] | [x] | `leetcode/F_SlidingWindow/`: LC 3 (Longest Substring) |
| **Linked Lists (Singly)** | [ ] | [x] | `leetcode/C_LinkedList/`: LC 2 (Add Two Numbers) |
| **Linked Lists (Doubly)** | [x] | [ ] | `DataStructures/B_DoublyLinkedListCustom` |
| **Linked Lists (Circular)** | [ ] | [ ] | |
| **Stack** | [x] | [ ] | `DataStructures/A_StackCustom` |
| **Queue (Circular)** | [x] | [ ] | `DataStructures/C_CircularQueueCustom` |
| **Deque** | [x] | [ ] | `DataStructures/D_DequeCustom` |
| **Stack/Queue Inter-impl** | [x] | [ ] | `DataStructures/E_QueueUsingTwoStacksCustom`, `F_StackUsingTwoQueuesCustom` |

---

## Phase 3: Non-Linear Data Structures
| Topic | Implementation | Problems | Key Files |
| :--- | :---: | :---: | :--- |
| **Hashing** | [x] | [ ] | `DataStructures/J_HashMapCustom` |
| **Trees (Binary Tree)** | [x] | [ ] | `DataStructures/H_BinaryTreeCustom` |
| **Trees (BST)** | [x] | [ ] | `DataStructures/I_BinarySearchTreeCustom` |
| **Heaps / Priority Queue** | [x] | [ ] | `DataStructures/G_PriorityQueueCustom` |
| **Graphs (Adjacency List/Matrix)** | [x] | [ ] | `DataStructures/L_GraphCustom` |
| **Graph Traversal (BFS/DFS)** | [x] | [ ] | Included in `DataStructures/L_GraphCustom` |

---

## Phase 4: Advanced Algorithms & Structures
| Topic | Implementation | Problems | Status |
| :--- | :---: | :---: | :--- |
| **Recursion** | [ ] | [ ] | `Basics/E_FibonacciOptimization` |
| **Sorting & Searching** | [ ] | [ ] | Binary Search in `Basics/binary_search_manual.py` |
| **Backtracking** | [ ] | [ ] | N-Queens, Permutations |
| **Dynamic Programming** | [ ] | [ ] | Knapsack, LCS, Coin Change |
| **Greedy Algorithms** | [ ] | [ ] | Activity Selection, Huffman |
| **Graph Algorithms (Dijkstra)** | [ ] | [ ] | |
| **Trie** | [x] | [ ] | `DataStructures/K_TrieCustom` |
| **Union-Find (DSU)** | [ ] | [ ] | |
| **Segment Tree / Fenwick** | [ ] | [ ] | |

---

## Immediate Next Steps
1. **Import Udemy Problems**: Place your Java LeetCode solutions into the appropriate `leetcode/<topic>/` directory following the naming pattern `LC<number>_<Name>.java`.
2. **Arrays & Strings**: Complete scratch implementations for rotations, reversal, and sliding window.
3. **Problem Solving**: Add 5 classic problems for each implemented data structure in `leetcode/`.

## Study Workflow (Per Topic)
1. **Theory**: Read concept in `README.md` and `docs/notes/`.
2. **Code**: Implement `_custom` in Java and Python in `DataStructures/`.
3. **Compare**: Update `Contrast` notes.
4. **Solve**: Add problems in `leetcode/<topic>/`.
