# Time Complexity of Recursive Functions

## 1\. Recursion Example

Consider a factorial function:

```
int factorial(int n) {
    if (n == 1)
        return 1;
    else
        return n * factorial(n - 1);
}
```

For `factorial(5)`:

```
factorial(5)
 → 5 × factorial(4)
     → 4 × factorial(3)
         → 3 × factorial(2)
             → 2 × factorial(1)
                 → 1
```

The recursion continues until the **base condition** `n == 1` is reached. Then the results are returned back up:

```
factorial(1) = 1
factorial(2) = 2 × 1 = 2
factorial(3) = 3 × 2 = 6
factorial(4) = 4 × 6 = 24
factorial(5) = 5 × 24 = 120
```

* * *

## 2\. How to Analyze Recursive Time Complexity

Unlike loops, recursion doesn't directly show how many times the code executes.

The useful approach is to **simulate the function calls**.

This is called a **recursion tree**:

```
f(5)
 └── f(4)
      └── f(3)
           └── f(2)
                └── f(1)
```

We then determine:

1.  How many function calls occur.
2.  How much work each function call performs.
3.  The total work across all calls.

* * *

## 3\. Complexity of Each Call

Each factorial function call performs only constant work:

-   Check the condition.
-   Perform a multiplication.
-   Make the next recursive call.

Therefore, each call takes:

**O(1)**

For `factorial(n)`, the recursion goes from `n` down to `1`.

So there are approximately **n function calls**.

Therefore:

**O(1) + O(1) + ... + O(1)**  
**n times**

\= **O(n)**

### Final Time Complexity

**factorial(n) → O(n)**

* * *

## 4\. Key Takeaways

-   Recursive functions should be analyzed by looking at their **function-call structure**.
-   A **recursion tree** helps visualize those calls.
-   Determine the work done by each function call.
-   Determine how many calls are made.
-   For the factorial example:
    
    -   Number of calls = `n`
    -   Work per call = `O(1)`
    -   Total = **O(n)**

The same recursion-tree approach can be applied to more complicated recursive algorithms, including cases where a function makes **multiple recursive calls**.