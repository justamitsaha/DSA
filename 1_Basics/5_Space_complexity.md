# Space Complexity: Fundamentals

## 1\. What Is Space Complexity?

**Space complexity** describes how the memory used by an algorithm grows with respect to the input size.

For example:

**O(n)** space complexity means the memory requirement grows proportionally with `n`.

Just as time complexity measures the growth of execution time, space complexity measures the growth of **memory usage**.

* * *

## 2\. Why Space Complexity Matters

An algorithm may be logically correct but still fail if it requires too much memory.

Therefore, in coding interviews and real applications, we need to consider both:

-   **Time complexity** → How execution time grows
-   **Space complexity** → How memory usage grows

* * *

# 3\. Three Sources of Space Usage

When calculating space complexity, consider three main factors:

### 1\. Variables

Variables consume memory.

For example:

```
int i = 0;
```

An `int` occupies memory, so the variable contributes to the algorithm's space usage.

However, a fixed number of variables contributes **O(1)** space because their memory requirement does not grow with `n`.

* * *

### 2\. External Data Structures

Algorithms often create additional data structures, such as:

-   Arrays
-   Stacks
-   Queues
-   Graphs
-   Other collections

The memory occupied by these structures must be included in the space complexity.

For example, creating an array containing `n` elements requires memory proportional to `n`:

**O(n)**

* * *

### 3\. Recursion

Recursion is an important source of space usage because recursive calls use an internal **call stack**.

For example:

```
factorial(n)
 → factorial(n - 1)
   → factorial(n - 2)
     → ...
```

Each active function call occupies space on the call stack.

Therefore, when analyzing recursive algorithms, we must consider the **maximum depth of the recursion/call stack**.

This is sometimes called an **implicit data structure** because the stack is created automatically by the program's execution rather than explicitly by the programmer.

* * *

# 4\. Explicit vs. Implicit Data Structures

### Explicit Data Structures

These are structures that we deliberately create.

Examples:

-   Array
-   Stack
-   Queue
-   Graph

### Implicit Data Structures

These are created automatically as a consequence of the algorithm.

The most important example is the **call stack created by recursion**.

Therefore, recursion must always be considered when calculating space complexity.

* * *

# 5\. Formula for Space Complexity

A useful way to think about space complexity is:

**Total Space = Variable Space + Data Structure Space + Recursion Space**

We consider the contribution of all three and express the resulting growth using Big-O notation.

* * *

# 6\. Key Takeaways

-   Space complexity measures **memory growth**, not execution time.
-   Variables contribute to space usage.
-   Additional data structures contribute to space usage.
-   Recursion uses the **call stack**, which must be included.
-   Recursion creates an **implicit** data structure.
-   Explicit data structures are deliberately created by the programmer.
-   To calculate space complexity, consider:

**Variables + Data Structures + Recursion**