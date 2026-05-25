# DSA Master Study Plan & Progress Tracker

This document tracks the journey through Data Structures and Algorithms, following the roadmap in `README.md`.

## Legend
- [x] **Implemented**: Data structure written from scratch in Java & Python.
- [ ] **Problem Solving**: Solved 5-10 classic problems for this topic.
- [ ] **Contrast**: Documented language-specific differences (Java vs Python).

---

## Phase 1: The Essentials
| Topic | Implementation | Problems | Theory/Note |
| :--- | :---: | :---: | :--- |
| **Complexity Analysis** | [x] | [ ] | See `Time-Complexity/` |
| **Language Basics (Java/Python)** | [x] | [ ] | Focus on Collections vs Built-ins |

---

## Phase 2: Linear Data Structures
| Topic | Implementation | Problems | Key Files |
| :--- | :---: | :---: | :--- |
| **Arrays** | [ ] | [ ] | Need custom traversal/rotation logic |
| **Strings** | [ ] | [ ] | Need Palindrome/Anagram implementations |
| **Linked Lists (Singly)** | [ ] | [ ] | |
| **Linked Lists (Doubly)** | [x] | [ ] | `b_doubly_linked_list_custom` |
| **Linked Lists (Circular)** | [ ] | [ ] | |
| **Stack** | [x] | [ ] | `a_stack_custom` |
| **Queue (Circular)** | [x] | [ ] | `c_circular_queue_custom` |
| **Deque** | [x] | [ ] | `d_deque_custom` |
| **Stack/Queue Inter-impl** | [x] | [ ] | `e_queue_using_two_stacks`, `f_stack_using_two_queues` |

---

## Phase 3: Non-Linear Data Structures
| Topic | Implementation | Problems | Key Files |
| :--- | :---: | :---: | :--- |
| **Hashing** | [x] | [ ] | `j_hash_map_custom` |
| **Trees (Binary Tree)** | [x] | [ ] | `h_binary_tree_custom` |
| **Trees (BST)** | [x] | [ ] | `i_binary_search_tree_custom` |
| **Heaps / Priority Queue** | [x] | [ ] | `g_priority_queue_custom` |
| **Graphs (Adjacency List/Matrix)** | [x] | [ ] | `l_graph_custom` |
| **Graph Traversal (BFS/DFS)** | [x] | [ ] | Included in `l_graph_custom` |

---

## Phase 4: Advanced Algorithms & Structures
| Topic | Implementation | Problems | Status |
| :--- | :---: | :---: | :--- |
| **Recursion** | [ ] | [ ] | `e_fibonacciOptimization` (partial) |
| **Sorting & Searching** | [ ] | [ ] | Need Merge/Quick Sort, Binary Search |
| **Backtracking** | [ ] | [ ] | N-Queens, Permutations |
| **Dynamic Programming** | [ ] | [ ] | Knapsack, LCS, Coin Change |
| **Greedy Algorithms** | [ ] | [ ] | Activity Selection, Huffman |
| **Graph Algorithms (Dijkstra)** | [ ] | [ ] | |
| **Trie** | [x] | [ ] | `k_trie_custom` |
| **Union-Find (DSU)** | [ ] | [ ] | |
| **Segment Tree / Fenwick** | [ ] | [ ] | |

---

## Immediate Next Steps
1. **Arrays & Strings**: Implement basic operations (Rotation, Reverse, Sliding Window) to complete Phase 2.
2. **Problem Solving**: Create a `Problems/` directory and start solving 5 classic problems for each "DONE" data structure.
3. **Complexity Practice**: Add more examples to `Time-Complexity/` for recursion and nested loops.

## Study Workflow (Per Topic)
1. **Theory**: Read concept in `README.md`.
2. **Code**: Implement `_custom` in Java and Python.
3. **Compare**: Update `Contrast` notes.
4. **Solve**: Add 5+ problems in `Problems/`.
