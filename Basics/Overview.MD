# Basics Module — Complexity & Optimization

This directory contains foundational scripts and benchmarks to demonstrate time complexity and algorithm optimization in both **Java** and **Python**.

## File Overview

| Topic | Java Implementation | Python Implementation | Description |
| :--- | :--- | :--- | :--- |
| **Complexity Deep Dive** | [1_Time-Complexity.MD](./1_Time-Complexity.MD) | - | Comprehensive guide with code examples for all Big-O classes. |
| **Time Complexity Basics** | [A_TimeComplexity.java](./A_TimeComplexity.java) | [a_time_complexity.py](./a_time_complexity.py) | Measures and compares $O(1)$ and $O(n)$ operations. |
| **Insertion Comparison** | [B_InsertionComparison.java](./B_InsertionComparison.java) | [b_insertion_Comparison.py](./b_insertion_Comparison.py) | Demonstrates the $O(n)$ cost of front-insertion in arrays vs $O(1)$ in Linked Lists/Deques. |
| **Full Benchmark Suite** | [D_ComplexityComparison.java](./D_ComplexityComparison.java) | [d_complexityComparison.py](./d_complexityComparison.py) | A grand tour of all complexities from $O(1)$ to $O(n!)$ with safety scaling for slow algorithms. |
| **Recursion Optimization** | [E_FibonacciOptimization.java](./E_FibonacciOptimization.java) | [e_fibonacciOptimization.py](./e_fibonacciOptimization.py) | Shows how Memoization/Caching turns $O(2^n)$ Fibonacci into $O(n)$. |

---

## Key Learning Points

### 1. The Scaling Trap
In **`D_ComplexityComparison`**, notice how we must use different input sizes ($N$) for different complexities. 
- $O(1)$ can handle $N = 100,000,000$ instantly.
- $O(n^2)$ is scaled down to $10,000$ to finish in reasonable time.
- $O(2^n)$ is scaled to $\approx 30$ because growth is explosive.

### 2. Language-Specific Nuances
- **Java**: Uses a JIT (Just-In-Time) compiler. Note the "Warm-up" loops in the code which ensure the JIT has optimized the logic before we measure it.
- **Python**: Interpreted and slower for raw loops. We use `time.perf_counter_ns()` for high-precision measurement and `collections.deque` for $O(1)$ head operations.

### 3. Memoization
In **`E_FibonacciOptimization`**, we solve the same problem twice:
1.  **Naive**: Exponential time ($O(2^n)$) - recomputes the same values millions of times.
2.  **Optimized**: Linear time ($O(n)$) - saves previous results in a cache.

---

## How to Run
- **Java**: Compile and run the `main` methods. All logic is in `static` methods for easy testing.
- **Python**: Run with `python <filename>.py`. No external dependencies required.
