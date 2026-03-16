import time

def demonstrate_linear_o_n(n):
    """
    This function has O(n) complexity.
    The loop runs exactly 'n' times.
    """
    items = list(range(n))
    total = 0
    
    start_time = time.time()
    
    # The O(n) part: 1 loop through n items
    for item in items:
        total += item
        
    end_time = time.time()
    return end_time - start_time

# Test with three different sizes
sizes = [100_000, 1_000_000, 10_000_000]

print(f"{'Input Size (n)':<20} | {'Time Taken':<15}")
print("-" * 40)

for size in sizes:
    duration = demonstrate_linear_o_n(size)
    print(f"{size:<20,} | {duration:.6f} seconds")
