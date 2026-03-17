import time
import sys

# Amortized Analysis: The "Pay Now, Save Later" Approach
# Example: Adding elements to a Python list (Dynamic Array)

def demonstrate_amortization(n=1000):
    """
    Shows how Python lists grow. 
    Most appends are O(1), but some are O(n) because the list must resize.
    The 'Amortized' average remains O(1).
    """
    my_list = []
    prev_size = sys.getsizeof(my_list)
    
    print(f"{'Index':<10} | {'List Size (Bytes)':<20} | {'Status':<15}")
    print("-" * 50)

    for i in range(n):
        my_list.append(i)
        current_size = sys.getsizeof(my_list)
        
        if current_size > prev_size:
            # Resize happened! 
            # In a real O(n) operation, this would take significant time.
            print(f"{i:<10} | {current_size:<20} | RESIZE (O(n))")
            prev_size = current_size
        elif i % 100 == 0:
            # Just to show some O(1) samples
            print(f"{i:<10} | {current_size:<20} | O(1)")

# Why is this O(1)?
# If we double the array size every time it's full, 
# the expensive O(n) resize happens very infrequently.
# Summing all costs / n = O(1) average.

if __name__ == "__main__":
    demonstrate_amortization(500)
