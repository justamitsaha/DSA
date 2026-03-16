import time

def constant_time_demo(items):
    """
    O(1) - Constant Time.
    The time taken remains the same regardless of how many items 
    are in the list because we only look at the first one.
    """
    return items[0]

def linear_time_demo(items, target):
    """
    O(n) - Linear Time.
    The time taken grows proportionally with the list size because 
    we may have to check every single item.
    """
    for item in items:
        if item == target:
            return True
    return False

# Setup: A small list and a very large list
small_list = [i for i in range(10)]
large_list = [i for i in range(10_000_000)]

# --- Testing O(1) ---
start = time.time()
constant_time_demo(small_list)
t1 = time.time() - start

start = time.time()
constant_time_demo(large_list)
t2 = time.time() - start

# --- Testing O(n) ---
start = time.time()
linear_time_demo(small_list, 9)
t3 = time.time() - start

start = time.time()
linear_time_demo(large_list, 9_999_999)
t4 = time.time() - start

print(f"O(1) Small: {t1:.8f}s | O(1) Large: {t2:.8f}s (Almost identical)")
print(f"O(n) Small: {t3:.8f}s | O(n) Large: {t4:.8f}s (Significant growth)")
