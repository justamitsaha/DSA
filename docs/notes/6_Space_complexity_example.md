# Space Complexity Example: Constant Space

## 1\. Problem

Suppose an integer array is given as input, and the program needs to print double of every element.

For example:

```
Input:  [5, 10, 15]
Output: 10, 20, 30
```

A possible implementation is:

```
void f(int[] r) {
    int n = r.length;

    for (int i = 0; i < n; i++) {
        int num = r[i] * 2;
        print(num);
    }
}
```

* * *

## 2\. Analyze the Three Sources of Space

### A. Recursion

There is no recursion.

**Space due to recursion = O(1)**

* * *

### B. Data Structures

The array is passed as an **input** to the algorithm.

We are not creating the array inside the algorithm, so its memory is **not counted** as auxiliary space.

Therefore:

**Space due to data structures = O(1)**

> When comparing algorithms, the input data is normally excluded because the same input is available to each algorithm.

* * *

### C. Variables

The algorithm creates:

-   `n`
-   `i`
-   `num`

Assuming an integer occupies a fixed amount of memory, each variable requires constant space.

An important point is `num`.

Although `num` is created during every iteration, the previous `num` is no longer needed when the next iteration creates it.

Therefore, we do **not** have `n` copies of `num` in memory simultaneously.

At any point, only a constant number of variables exist.

So:

**Space due to variables = O(1)**

* * *

## 3\. Total Space Complexity

Combining the contributions:

```
Recursion       → O(1)
Data structures → O(1)
Variables       → O(1)
```

Therefore:

**Total Space Complexity = O(1)**

* * *

## 4\. Why Doesn't Array Size Matter?

Suppose the input array contains:

```
10 elements
```

or:

```
10,000 elements
```

The algorithm still uses the same fixed set of variables:

```
n
i
num
```

The amount of additional memory used by the algorithm does not grow with the array size.

Therefore, the space remains constant.

```
Input size ↑
     |
     |──────────── Space
     |
     +──────────────────→
```

### Key Takeaway

An algorithm can process an input of size `n` without requiring **O(n) additional space**.

In this example, the input array itself is excluded, and the algorithm only uses a constant amount of additional memory.

**Space Complexity = O(1)**