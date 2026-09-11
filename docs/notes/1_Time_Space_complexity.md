Time complexity : Growth in runtime of the algorithm
Space Complexity: Growth in space of the algorithm with respect to its input okay.

So we measure time complexity by counting the number of operations that we do doingin the code. The amount of time that algorithm will take will be proportional to how many operations that algorithm does.Say if I have an algorithm which is taking only 10 steps.So its complexity will be different.If there is an algorithm which is taking, 10,000 steps, its complexity can be different.Time complexity is measured by counting the number of operations that your code performs.

We count the number of operations. Number of operations don't need to be exact. You can count approximate number After that we convert it into big O notation.And that is how we calculate the time complexity.

For space complexity we count the data, the variables, the data structure and the variables that I am creating in the code.

e.g.

```java
for(int i=0; i<n ; i++){
    System.out.println(n)
}
```

No of operations
1. `i<n`  check n times
2. `i++` n times
3. Print operation n times
4. Declation  `int n =0` one time

So total `n x (1+1+1) +1`. So in Big O notation it becomes O(3N +1). Some rules of big O notation

1. ignore constants which are getting divided or multiplied, i.e. 3 in 3N
2. Second rule is ignore lower value terms okay, so first is first rule is ignore constants that are getting multiplied or divided, i.e. +1

So it becomes O(N)

Belew  will have O(N)
```java
        for (int i = 0; i % 2 == 0; i++) {
            System.out.println(i);
        }
```

Generally acceptable criteria in interview
1. 10⁷ ~10⁸ can be acceptable
2. < 10⁷ always acceptable
3. > 10⁸ never acceptable


**Which complexity is acceptable**
Very often interview questions have clues which tell what complexity is acceptable. it is kind of reverese engineering.
 1. If question mention range of an array is 0~ 10⁵ definitely it means `O(N²)` is not acceptable. Why? Because if we replave N with 10⁵, It will bewcome N¹⁰. So how about `O(Nlogⁿ)`. If we replace N with 10ⁿ. It will be 10⁵ X log10⁵.log10⁵ is ~ 15 . So it wil become 10ⁿx 15 which is acceptable.
 2. If the range is 10¹². So we need either `O(logⁿ)` or `√N`. `O(logⁿ)` is the more common. And how does logging get introduced? Log n gets introduced with the help of binary search. So if you have such high constraints it's a big hint that we need `O(logⁿ)`.
 3. If the problem mentions small constrain like 0-20. Then we can go for O(N²)


 # Time Complexity: Logarithmic and Nested Loops

## 1\. Logarithmic Time Complexity — O(log n)

Consider the following loop:

```java
int i = 1;

while (i < n) {
    System.out.println(i);
    i = i * 2;
}
```

### Understanding the Pattern

The value of `i` doubles on every iteration:

| Iteration | `i` |
| --- | --- |
| 0   | 1 = 2⁰ |
| 1   | 2 = 2¹ |
| 2   | 4 = 2² |
| 3   | 8 = 2³ |
| 4   | 16 = 2⁴ |
| ... | ... |
| k   | 2ᵏ  |

Assume the loop runs `k` iterations. On the last iteration: 

** < n** 

Taking log base 2 on both sides:

**log₂2ᵏ < log₂(n)**

which is equal to

**k < log₂(n)**

Therefore, the number of iterations is proportional to `log n`. Since each iteration performs a constant amount of work, the overall time complexity is:

**O(log n)**

### Key Idea

Whenever a loop repeatedly **multiplies or divides a value by a constant**, its complexity is often logarithmic.

Examples:

-   `i = i * 2`
-   `i = i * 3`
-   `i = i / 2`

These generally result in **O(log n)** complexity.

* * *

# 2\. Nested Loops

Consider:

``` java
for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
        System.out.println(i + j);
    }
}
```

There are two loops:

-   Outer loop runs `n` times.
-   Inner loop runs `m` times for **each** iteration of the outer loop.

### Inner Loop

The inner loop independently runs `m` times: **O(m)**

### How Many Times Does the Inner Loop Execute?

The outer loop runs `n` times.

Every time the outer loop executes, the inner loop runs again:

```
m + m + m + ... + m
```

There are `n` occurrences of `m`.

Therefore:

**m × n = mn**

So the overall time complexity is:

**O(n × m)**

* * *

# 3\. When Nested Loops Can Be Multiplied

When nested loops are **independent of each other**, their complexities can generally be multiplied.

For example:

```
for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
        // constant operation
    }
}
```

Complexity:

**O(n) × O(m) = O(nm)**

If both loops run `n` times:

```
for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
        // operation
    }
}
```

Then:

**O(n × n) = O(n²)**

* * *

# 4\. Dependent Nested Loops

Not all nested loops can be analyzed simply by multiplying their apparent limits.

For example, if the number of iterations of the inner loop depends on the value of the outer loop, the analysis requires counting the actual number of operations.

The basic approach remains the same:

1.  Determine how many times each loop executes.
2.  Determine whether one loop depends on another.
3.  Count the total number of operations.
4.  Convert the result into Big-O notation.

* * *

# 5\. General Method for Calculating Loop Complexity

When the complexity of a loop is not immediately obvious, use this approach:

### Step 1 — Find the iteration pattern

Ask:

-   Is the variable increasing by a constant?
-   Is it being multiplied?
-   Is it being divided?
-   Does the number of iterations depend on another loop?

### Step 2 — Count the iterations

Determine how many times the loop actually executes.

Examples:

```
i++
```

Usually gives:

**O(n)**

```
i = i * 2
```

Usually gives:

**O(log n)**

```
i++
```

inside another independent `n`\-iteration loop:

**O(n²)**

### Step 3 — Count the work per iteration

If each iteration performs constant work, the iteration count directly determines the complexity.

### Step 4 — Convert to Big-O

Ignore:

-   Constant factors
-   Lower-order terms

For example:

**O(2 log n + 1)**

becomes:

**O(log n)**

# 6\. Important Takeaways

-   A loop that repeatedly **doubles/halves** a value is typically **O(log n)**.
-   A simple loop that increments/decrements by one is typically **O(n)**.
-   Independent nested loops generally **multiply** their complexities.
-   `n` iterations inside `m` iterations gives **O(nm)**.
-   Two independent `n`\-iteration loops nested together give **O(n²)**.
-   When the relationship is unclear, don't guess. **Count the number of operations and derive the complexity.**
-   The same fundamental approach—**counting operations**—can be used for more complicated loops and recursive algorithms.