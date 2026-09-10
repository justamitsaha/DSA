import time
import bisect
import sys

# We use a slightly smaller N for Python's base to save memory
# 10 million integers in Python can take ~300-800MB
N_BASE = 10_000_000
arr = list(range(N_BASE))

results = {}

def format_time(ns):
    if ns < 1000: return f"{ns} ns"
    if ns < 1_000_000: return f"{ns / 1000.0:.2f} μs"
    return f"{ns / 1_000_000.0:.2f} ms"

def benchmark(label, func, iterations=1):
    start = time.perf_counter_ns()
    for _ in range(iterations):
        func()
    end = time.perf_counter_ns()
    avg_ns = (end - start) // iterations
    results[label] = format_time(avg_ns)

# --- Complexity Implementations ---

def fib_exponential(k):
    if k <= 1: return k
    return fib_exponential(k - 1) + fib_exponential(k - 2)

def permute_factorial(k):
    if k == 0: return
    for i in range(k):
        permute_factorial(k - 1)

# --- Main Execution ---

print(f"Starting Python Benchmark (N_BASE = {N_BASE:,})...\n")

# O(1) - Constant: Direct access
benchmark("O(1) [Constant]", lambda: arr[N_BASE // 2], 10000)

# O(log n) - Logarithmic: Binary Search
benchmark("O(log n) [Logarithmic]", lambda: bisect.bisect_left(arr, N_BASE - 1), 10000)

# O(n) - Linear: Iterate entire list
benchmark("O(n) [Linear]", lambda: [x for x in arr if x == -1], 1)

# O(n log n) - Linearithmic: Built-in Sort (on a smaller subset)
n_log_n_scale = 100_000 
sub_arr = list(range(n_log_n_scale))
benchmark(f"O(n log n) [Sort {n_log_n_scale:,}]", lambda: sorted(sub_arr), 5)

# O(n^2) - Quadratic: Nested Loops
n2_scale = 3_000 # Python is slow, 10k might take too long
def quadratic():
    total = 0
    for i in range(n2_scale):
        for j in range(n2_scale):
            total += 1
benchmark(f"O(n^2) [Nested {n2_scale:,}]", quadratic, 1)

# O(2^n) - Exponential: Recursive Fib
exp_scale = 25 # 2^25 is already very heavy for Python
benchmark(f"O(2^n) [Rec-Fib {exp_scale}]", lambda: fib_exponential(exp_scale), 1)

# O(n!) - Factorial: Permutations
fact_scale = 9 # 9! is 362,880. 10! might hang for a bit.
benchmark(f"O(n!) [Fact {fact_scale}]", lambda: permute_factorial(fact_scale), 1)

# --- Output Results ---
print(f"{'Complexity':<25} | {'Avg Time':<15}")
print("-" * 45)
for k, v in results.items():
    print(f"{k:<25} | {v:<15}")


# Porting this to Python is a "reality check" for many developers. While Python is beautiful and concise, it is an interpreted language, meaning it doesn't have a JIT compiler that optimizes code to the same level as the JVM.In Python, the gap between "Fast" ($O(1)$) and "Slow" ($O(n!)$) is even more dramatic. Because of Python's overhead, we have to be even more careful with the input sizes ($N$) to avoid freezing your computer.
#                                                                                                                                                                                                                                                                                                                                                   Key Observations in PythonThe Interpretation Tax: You will notice that Python's $O(n)$ is significantly slower than Java's. Java's JIT can optimize a loop into a single CPU instruction sequence; Python has to evaluate each line of the loop through its interpreter.Binary Search ($O(\log n)$): Notice how fast this is compared to $O(n)$. Even in an interpreted language, the algorithm (cutting the work in half every step) wins over raw speed.Recursion Depth: Python has a limit on recursion depth (usually 1,000). While $O(2^n)$ and $O(n!)$ are slow, they also risk hitting this limit if the "depth" of the tree gets too high.Comparison Table: Why We Scale Down                                                            