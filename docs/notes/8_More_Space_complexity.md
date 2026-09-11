# Space Complexity of Recursive Fibonacci

## 1\. Key Idea

For recursive functions, space complexity is primarily determined by the **maximum recursion depth**.

Unlike time complexity, we do not need to count every node in the recursion tree. We only need to determine the **maximum number of function calls active at the same time**.

* * *

## 2\. Fibonacci Recursion

For Fibonacci:

```
int f(int n) {
    if (n == 1 || n == 2)
        return 1;

    return f(n - 1) + f(n - 2);
}
```

The recursion tree branches into `f(n-1)` and `f(n-2)`.

However, the program does **not** execute all branches simultaneously.

For example, starting with `f(6)`, it first explores a path like:

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

Then it returns and explores the other branches.

* * *

## 3\. Maximum Call Stack Depth

The deepest path is approximately:

```
f(n)
 ↓
f(n-1)
 ↓
f(n-2)
 ↓
...
 ↓
f(2)
```

So the maximum recursion depth is approximately:

**n - 1**

That means the call stack can contain approximately `n - 1` active function calls at its maximum.

* * *

## 4\. Space Used by the Call Stack

Each function call stores a constant amount of information.

If each stored value requires `k` bytes:

```
Space ≈ (n - 1) × k
```

Here:

-   `n - 1` grows with `n`
-   `k` is a constant

Ignoring constants:

**Space Complexity = O(n)**

* * *

## 5\. Important Difference: Time vs. Space

This is an important distinction for recursive Fibonacci:

| Complexity | Result | Reason |
| --- | --- | --- |
| **Time** | **O(2ⁿ)** | Number of function calls grows exponentially |
| **Space** | **O(n)** | Maximum call-stack depth grows linearly |

Even though the recursion tree contains exponentially many nodes, **they are not all stored in memory at the same time**.

The call stack only needs to hold the **current active path**.

### Key Takeaway

For recursive space complexity:

> **Look at the maximum recursion depth, not the total number of nodes in the recursion tree.**

For recursive Fibonacci:

**Time = O(2ⁿ)**  
**Space = O(n)**