
# 1. Complexity Analysis

**Goal:** Understand performance before writing algorithms.

**Study**

-   Time Complexity: O(1), O(log n), O(n), O(n log n), O(n²)
    
-   Space Complexity
    
-   Best / Average / Worst case
    
-   Amortized analysis
    

**Practice**

-   Analyze loops
    
-   Nested loops
    
-   Recursion complexity
    

**Outcome**

-   Able to estimate complexity of any simple code snippet.
    

----------

# 2. Arrays

**Study**

-   Static vs dynamic arrays
    
-   Traversal
    
-   Insert / delete
    
-   Prefix sum
    
-   Sliding window
    
-   Two pointer technique
    

**Problems**

-   Two Sum
    
-   Maximum subarray
    
-   Move zeroes
    
-   Container with most water
    

**Outcome**

-   Recognize when problems reduce to array scanning or window techniques.
    

----------

# 3. Strings

**Study**

-   Character arrays
    
-   String matching basics
    
-   Palindromes
    
-   Frequency counting
    

**Advanced**

-   KMP algorithm
    
-   Rabin-Karp
    

**Problems**

-   Valid palindrome
    
-   Longest substring without repeating characters
    
-   Anagram detection
    

----------

# 4. Recursion

**Study**

-   Base case
    
-   Stack behavior
    
-   Recursion tree
    
-   Tail recursion
    

**Problems**

-   Factorial
    
-   Fibonacci
    
-   Generate subsets
    
-   Tower of Hanoi
    

**Outcome**

-   Ability to convert recursion ↔ iteration.
    

----------

# 5. Linked Lists

**Study**

-   Singly linked list
    
-   Doubly linked list
    
-   Circular list
    

**Operations**

-   Insert
    
-   Delete
    
-   Reverse
    
-   Detect cycle (Floyd)
    

**Problems**

-   Reverse linked list
    
-   Merge two lists
    
-   Middle node
    
-   Remove nth node
    

----------

# 6. Stack

**Study**

-   LIFO principle
    
-   Implementation using array and linked list
    

**Applications**

-   Expression evaluation
    
-   Parentheses validation
    
-   Monotonic stack
    

**Problems**

-   Valid parentheses
    
-   Next greater element
    
-   Largest rectangle in histogram
    

----------

# 7. Queue

**Study**

-   FIFO
    
-   Circular queue
    
-   Deque
    
-   Priority queue
    

**Problems**

-   Sliding window maximum
    
-   Implement queue using stacks
    

----------

# 8. Hashing

**Study**

-   HashMap / HashTable
    
-   HashSet
    
-   Collision handling
    
-   Load factor
    

**Problems**

-   Two Sum (hash version)
    
-   Group anagrams
    
-   Longest consecutive sequence
    

----------

# 9. Trees (Core Section)

**Study**

-   Binary Tree
    
-   Binary Search Tree
    

**Traversals**

-   Inorder
    
-   Preorder
    
-   Postorder
    
-   Level order
    

**Problems**

-   Height
    
-   Diameter
    
-   Lowest common ancestor
    

----------

# 10. Heap / Priority Queue

**Study**

-   Min heap
    
-   Max heap
    
-   Heapify
    
-   Heap sort
    

**Problems**

-   Top K elements
    
-   Kth largest element
    
-   Merge k sorted lists
    

----------

# 11. Graphs

**Study**

-   Graph representation
    
    -   Adjacency list
        
    -   Adjacency matrix
        

**Traversal**

-   BFS
    
-   DFS
    

**Problems**

-   Number of islands
    
-   Detect cycle
    
-   Shortest path
    

----------

# 12. Graph Algorithms

**Study**

-   Dijkstra
    
-   Bellman Ford
    
-   Floyd Warshall
    
-   Topological sort
    

----------

# 13. Backtracking

**Study**

-   Decision trees
    
-   Constraint exploration
    

**Problems**

-   N queens
    
-   Sudoku solver
    
-   Permutations
    
-   Subsets
    

----------

# 14. Dynamic Programming

**Study**

-   Memoization
    
-   Tabulation
    
-   Optimal substructure
    
-   Overlapping subproblems
    

**Classic problems**

-   Knapsack
    
-   Longest common subsequence
    
-   Coin change
    
-   LIS
    

----------

# 15. Greedy Algorithms

**Study**

-   Greedy choice property
    

**Problems**

-   Activity selection
    
-   Huffman coding
    
-   Minimum coins
    

----------

# 16. Advanced Structures (Optional)

**Study**

-   Trie
    
-   Segment tree
    
-   Fenwick tree
    
-   Union-Find (Disjoint Set)
    

----------

# How to Study Each Topic (Recommended Loop)

For **each topic**:

1.  Learn concept (theory)
    
2.  Implement **from scratch**
    
    -   Java
        
    -   Python
        
3.  Solve **10–20 problems**
    
4.  Re-implement without looking
    
5.  Move to next topic


## Phase 1: The Essentials (Language & Complexity)

Before touching a Linked List, you must understand how to measure efficiency and how your languages handle data.

-   **Big O Notation:** Time and Space complexity. Understand why $O(n)$ is better than $O(n^2)$.
    
-   **Memory Management:** How Java's JVM manages heap/stack vs. how Python handles dynamic typing and references.
    
-   **Java Focus:** Master the Collections Framework (`ArrayList`, `HashMap`, `HashSet`).
    
-   **Python Focus:** Master built-in types (`list`, `dict`, `set`, `tuple`) and list comprehensions.
    

----------

## Phase 2: Linear Data Structures

These are the building blocks. Start here to understand how data is stored sequentially.

### 1. Arrays & Strings

-   **Concepts:** Multi-dimensional arrays, sliding window technique, two-pointer approach.
    
-   **Deep Dive:** Implement common operations (reverse, rotate, search) in both languages. Notice how Python's slicing `arr[::-1]` compares to Java's manual loops.
    

### 2. Linked Lists

-   **Concepts:** Singly, Doubly, and Circular Linked Lists.
    
-   **Deep Dive:** Since neither language has a "built-in" pointer like C++, you must implement a `Node` class. Practice reversing a list and detecting cycles (Floyd’s Cycle-Finding Algorithm).
    

### 3. Stacks & Queues

-   **Concepts:** LIFO vs. FIFO.
    
-   **Deep Dive:** Implement a stack using an Array and a Queue using a Linked List.
    
-   **Language Note:** In Python, use `collections.deque` for queues. In Java, use the `Deque` interface.
    

----------

## Phase 3: Non-Linear Data Structures

This is where technical interviews usually "begin."

### 4. Trees

-   **Concepts:** Binary Trees, Binary Search Trees (BST), AVL Trees.
    
-   **Algorithms:** Traversals (In-order, Pre-order, Post-order, Level-order).
    
-   **Deep Dive:** Implement a BST from scratch and write a recursive function for height and leaf count.
    

### 5. Heaps & Priority Queues

-   **Concepts:** Min-Heap and Max-Heap.
    
-   **Deep Dive:** Understand the "Heapify" process.
    
-   **Language Note:** Use `PriorityQueue` in Java and `heapq` in Python.
    

### 6. Graphs

-   **Concepts:** Adjacency Matrix vs. Adjacency List.
    
-   **Algorithms:** Breadth-First Search (BFS) and Depth-First Search (DFS).
    
-   **Deep Dive:** Use DFS to find a path and BFS to find the shortest path in an unweighted graph.
    

----------

## Phase 4: Advanced Algorithms

Once you have the structures, you need the logic to manipulate them efficiently.

### 7. Recursion & Backtracking

-   **Concepts:** Base cases, recursive calls, and the "choice, constraint, goal" framework.
    
-   **Deep Dive:** Solve the N-Queens problem or generate all permutations of a string.
    

### 8. Sorting & Searching

-   **Algorithms:** Merge Sort, Quick Sort, Binary Search.
    
-   **Deep Dive:** Understand why Quick Sort is often faster in practice but Merge Sort is stable.
    

### 9. Dynamic Programming (DP)

-   **Concepts:** Memoization (Top-down) vs. Tabulation (Bottom-up).
    
-   **Deep Dive:** Start with Fibonacci, move to the 0/1 Knapsack problem, then Longest Common Subsequence.
    

----------

## Strategy for Each Topic

To follow your "pick one and deep dive" approach, use this workflow for every item above:

1.  **Theory:** Read the concept and watch a visualization.
    
2.  **Implementation:** Write the data structure from scratch in **Java** (focus on types and structure).
    
3.  **Porting:** Re-write it in **Python** (focus on brevity and idiomatic code).
    
4.  **Practice:** Solve 5-10 LeetCode/HackerRank problems specifically for that topic.
    
5.  **Contrast:** Write down one thing Java did better (e.g., "type safety helped catch errors") and one thing Python did better (e.g., "shorter code for logic").