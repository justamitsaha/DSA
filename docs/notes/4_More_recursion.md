# Time Complexity of Recursive Fibonacci

## 1\. Fibonacci Function

Consider the recursive Fibonacci function:

```
int f(int n) {
    if (n == 1 || n == 2)
        return 1;

    return f(n - 1) + f(n - 2);
}
```

For every `n > 2`, the function makes **two recursive calls**:

-   `f(n - 1)`
-   `f(n - 2)`

* * *

## 2\. Recursion Tree

For example, `f(6)` produces a tree like:

```
f(6)
├── f(5)
│   ├── f(4)
│   │   ├── f(3)
│   │   │   ├── f(2)  ← stops
│   │   │   └── f(1)  ← stops
│   │   └── f(2)      ← stops
│   └── f(3)
│       ├── f(2)      ← stops
│       └── f(1)      ← stops
└── f(4)
    ├── f(3)
    │   ├── f(2)      ← stops
    │   └── f(1)      ← stops
    └── f(2)          ← stops
```

The important observation is that the tree **branches into two calls** at most non-base-case nodes.

* * *

## 3\. Estimating the Number of Function Calls

We don't need the exact number of nodes. For Big-O analysis, an approximation is sufficient.

At each level, the number of nodes can grow approximately as:

```
Level 0 → 2⁰
Level 1 → 2¹
Level 2 → 2²
Level 3 → 2³
...
```

The recursion depth is approximately `n`.

Therefore, the total number of nodes is approximately:

**2⁰ + 2¹ + 2² + ... + 2ⁿ**

This geometric series is dominated by its largest term, giving approximately:

**O(2ⁿ)**

* * *

## 4\. Work Per Function Call

Each function call performs only constant work apart from its recursive calls:

```
f(n - 1) + f(n - 2)
```

The addition itself is:

**O(1)**

Therefore:

```
Number of calls × Work per call
≈ 2ⁿ × O(1)
```

So the overall time complexity is:

# **O(2ⁿ)**

This is an **exponential** time complexity and is very inefficient for large `n`.

* * *

## 5\. Important Takeaways

-   Fibonacci recursion creates a **branching recursion tree**.
-   Each non-base-case call creates up to **two recursive calls**.
-   The tree grows exponentially.
-   Approximate number of function calls: **O(2ⁿ)**
-   Work per call: **O(1)**
-   **Time complexity: O(2ⁿ)**

The exact number of function calls is not necessary for Big-O analysis; the approximate growth rate is what matters.