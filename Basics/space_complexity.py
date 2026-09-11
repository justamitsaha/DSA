import sys

# Space Complexity: O(1) vs O(n)
# Goal: Understand how much extra memory our code consumes.

def iterative_sum(n):
    """Space Complexity: O(1)
    We only use a single variable 'total' regardless of how large n is.
    """
    total = 0
    for i in range(n):
        total += i
    return total

def recursive_sum(n):
    """Space Complexity: O(n)
    Each recursive call adds a new 'stack frame' to the system memory.
    If n = 1000, there will be 1000 frames on the stack.
    """
    if n <= 0:
        return 0
    return n + recursive_sum(n - 1)

# Note: Python has a recursion limit (usually 1000) to prevent 
# crashing the system memory (Stack Overflow).
print(f"Recursion Limit: {sys.getrecursionlimit()}")

n = 500
print(f"Iterative Sum of {n}: {iterative_sum(n)} (Uses O(1) extra space)")
print(f"Recursive Sum of {n}: {recursive_sum(n)} (Uses O(n) extra space)")

# Summary:
# Even if an algorithm is fast, it might be "expensive" in terms of memory.
# In interviews, always ask: "Should I optimize for time or space?"
