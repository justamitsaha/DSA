import time
from collections import deque

iterations = 100_000

# 1. Benchmark Standard List (Array-based)
arr_list = []
start_arr = time.perf_counter_ns()
for i in range(iterations):
    arr_list.insert(0, i) # O(n) operation
end_arr = time.perf_counter_ns()
print(arr_list[24])

#2. Benchmark Standard List by adding elements AT beginning
arr_list = []
start_arr = time.perf_counter_ns()
for i in range(iterations):
    arr_list.append(i) # O(1) operation
end_arr = time.perf_counter_ns()
print(arr_list[24])


# 2. Benchmark Deque (Linked-List based)
link_list = deque()
start_link = time.perf_counter_ns()
for i in range(iterations):
    link_list.appendleft(i) # O(1) operation
end_link = time.perf_counter_ns()

print(f"Python list.insert(0): {(end_arr - start_arr) / 1_000_000:.2f} ms")
print(f"Python list.append: {(end_arr - start_arr) / 1_000_000:.2f} ms")
print(f"Python deque.append left: {(end_link - start_link) / 1_000_000:.2f} ms")