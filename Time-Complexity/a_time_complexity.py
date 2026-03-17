import time


n = 10_000_000 #kept smaller size than Java
print("Initializing array...")
arr = list(range(n))# Initialize the array takes longer in Python than Java
complexity_times = {}

def measureConstantComplexity(index):
    iterations = 1000
    total_time = 0
    
    for _ in range(iterations):
        start = time.perf_counter_ns()
        value = arr[index]
        end = time.perf_counter_ns()
        total_time += (end - start)
        
    return total_time // iterations

def measureLinearComplexity(target):
    iterations = 10 
    total_time = 0
    
    for _ in range(iterations):
        start = time.perf_counter_ns()
        linearSearchLogic(arr, target)
        end = time.perf_counter_ns()
        total_time += (end - start)
        
    return total_time // iterations

def linearSearchLogic(array, target):
    for i in range(len(array)):
        if array[i] == target:
            return i
    return -1


# --- "Warm-up" Logic ---
# Note: CPython (standard Python) doesn't have a JIT like Java, 
# but we keep this to mirror your Java structure.
print("Warming up...")
for i in range(10000):
    dummy = arr[i % 1000]

for _ in range(2):
    linearSearchLogic(arr, -1)

# --- Execution ---
if __name__ == "__main__":
    print("Starting measurements...")
    
    # 1. Constant Complexity O(1)
    constant_results = [
        measureConstantComplexity(0),
        measureConstantComplexity(n // 2),
        measureConstantComplexity(n - 1)
    ]
    complexity_times["constantComplexity"] = constant_results

    # 2. Linear Complexity O(n)
    linear_results = [
        measureLinearComplexity(0),
        measureLinearComplexity(n // 2),
        measureLinearComplexity(-1)
    ]
    complexity_times["linearComplexity"] = linear_results

    # --- Print Results ---
    for key, values in complexity_times.items():
        # Displaying in nanoseconds
        print(f"{key}: {values}")


# In Java, int[] is a primitive array, taking up exactly 400 MB for 100 million integers. In Python, a list is an array of pointers to objects. Each integer in Python is an object that carries overhead. A list of 100 million integers can easily consume 2.5 GB to 4 GB of RAM. If your system has limited RAM, you might need to drop $n$ to 10,000_000 for the Python test.
# Configuration

# Key Differences to Observe
# Measurement Tool: Instead of System.nanoTime(), we use time.perf_counter_ns(). This is the most precise clock in Python for measuring short durations.

# The "JIT" Factor: You will likely notice that the "Warm-up" in Python doesn't change the results as drastically as it does in Java. Standard Python (CPython) is interpreted, so it doesn't "optimize" the loop into machine code mid-run the way Java does.

# Raw Speed: Brace yourself—the linear_search_logic in Python will be significantly slower than Java. While Java might finish a 100M search in ~30-50ms, Python might take several seconds. This is a great lesson in why we use specialized libraries like NumPy for heavy data lifting in Python!

# Summary of the Results you'll see
# Constant: Will still be very low (though likely higher than Java's ns).

# Linear: You will see a very clear jump: linear_results[0] will be near zero, [1] will be roughly half of [2]. The last one (searching for -1) will be the slowest, as it has to traverse the entire array. Expect it to be in the order of seconds, especially for n=1,000,000.        