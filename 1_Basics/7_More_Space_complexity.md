The key idea here is: **recursive functions use memory even when you don't explicitly create variables or data structures.** That extra memory comes from the **call stack**.

### 1\. Why `O(1)` looks tempting

Consider:

```
int factorial(int n) {
    if (n == 1)
        return 1;

    return n * factorial(n - 1);
}
```

At first glance, you might think:

-   Only `n` is being used.
-   No array or other data structure is created.
-   Therefore, space complexity = **O(1)**.

That is incorrect because it ignores the **recursive calls**.

* * *

### 2\. What happens during `factorial(5)`?

The calls happen like this:

```
factorial(5)
    ↓
factorial(4)
    ↓
factorial(3)
    ↓
factorial(2)
    ↓
factorial(1)
```

But these calls don't all finish immediately.

For example, `factorial(5)` needs the result of `factorial(4)` before it can calculate:

```
5 × factorial(4)
```

So the program has to **remember the pending work**.

* * *

### 3\. The Call Stack

Conceptually, the call stack looks like:

```
factorial(5) → remember 5
factorial(4) → remember 4
factorial(3) → remember 3
factorial(2) → remember 2
factorial(1) → returns 1
```

At this point, the stack contains roughly:

```
5
4
3
2
```

Then `factorial(1)` returns `1`.

The program starts unwinding:

```
2 × 1  = 2
3 × 2  = 6
4 × 6  = 24
5 × 24 = 120
```

As each function returns, its stack frame is removed.

* * *

### 4\. How Much Space?

For `factorial(n)`, approximately `n - 1` recursive calls are waiting in the call stack.

If each stack entry requires a constant amount of memory `k`:

```
Space ≈ (n - 1) × k
```

Ignoring constants:

**Space complexity = O(n)**

* * *

### 5\. Explicit vs. Implicit Space

This is the important distinction:

| Type | Example | Space |
| --- | --- | --- |
| **Explicit space** | Variables, arrays, stacks we create | Depends on what we create |
| **Implicit space** | Call stack created by recursion | Depends on recursion depth |

So for recursive algorithms, you need to consider **both**.

```
Total Space
    ↓
Explicit Space + Call Stack Space
```

For this factorial example:

```
Explicit space → O(1)
Call stack     → O(n)
----------------------
Total          → O(n)
```

### Key takeaway

**Recursion can increase space complexity even when your code doesn't explicitly create a data structure.**

The deeper the recursion goes, the more function calls remain active in the **call stack**, and therefore the more memory is required.