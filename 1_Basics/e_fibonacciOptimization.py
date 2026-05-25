import time
from functools import lru_cache

# The "One Line" Fix: This turns O(2^n) into O(n)
@lru_cache(maxsize=None)
def fib_optimized(k):
    if k <= 1: return k
    return fib_optimized(k - 1) + fib_optimized(k - 2)

n = 50
start = time.perf_counter_ns()
result = fib_optimized(n)
end = time.perf_counter_ns()

print(f"Fibonacci({n}) = {result}")
print(f"Time taken: {(end - start) / 1_000_000:.4f} ms")