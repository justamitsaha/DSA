# Manual Binary Search: O(log n)
# Goal: Understand why logarithmic time is so powerful.

def binary_search_iterative(arr, target):
    """O(log n) time | O(1) space"""
    low = 0
    high = len(arr) - 1
    steps = 0
    
    while low <= high:
        steps += 1
        mid = (low + high) // 2
        
        if arr[mid] == target:
            return mid, steps
        elif arr[mid] < target:
            low = mid + 1
        else:
            high = mid - 1
            
    return -1, steps

# --- Comparison: Why O(log n) wins? ---

n = 1_000_000
data = list(range(n))
target = 999_999 # Worst case

# O(n) scan would take 1,000,000 steps.
# O(log n) binary search:
result, steps = binary_search_iterative(data, target)

print(f"Searching for {target} in a list of {n} items...")
print(f"Result: Index {result}")
print(f"Steps taken by Binary Search: {steps}")
print(f"Estimated steps for Linear Search: {n}")

# In O(log n), we cut the search space in half at every step.
# log2(1,000,000) is roughly 20 steps.
