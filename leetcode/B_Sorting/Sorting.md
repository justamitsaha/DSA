Many  algorithm will work only if the array is sorted like Bellman ford, Binary Search, Two pointer methods etc. Common sorting algorithm
1. Bubble sort
2. Selection sort
3. Merge Sort
4. Insertion Sort
5. Quick Sot
6. Head Sort
7. Radix sort

# **1. Bubble Sort**

## **1. Concept**

- Bubble Sort is a simple comparison-based sorting algorithm.
- Initially, the entire array is considered unsorted.
- Repeatedly compares **consecutive elements** in the unsorted portion.
- If two consecutive elements are out of order, they are swapped.
- After each iteration (pass), the largest element in the unsorted portion moves to its correct position at the end.
- The sorted portion grows, while the unsorted portion shrinks.

## **2. Working Mechanism**

Assume ascending order.

**Initial array:**

`[20, 10, 40, 50, 25, 15]`

**Pass 1:**

- Compare `20, 10` → Swap.
- Compare `20, 40` → No swap.
- Compare `40, 50` → No swap.
- Compare `50, 25` → Swap.
- Compare `50, 15` → Swap.
- Result: `[10, 20, 40, 25, 15, 50]`
- Largest element `50` reaches its correct position.

**Pass 2:**

- Compare `10, 20` → No swap.
- Compare `20, 40` → No swap.
- Compare `40, 25` → Swap.
- Compare `40, 15` → Swap.
- Result: `[10, 20, 25, 15, 40, 50]`
- Second-largest element `40` reaches its correct position.

**Pass 3:**

- Compare `10, 20` → No swap.
- Compare `20, 25` → No swap.
- Compare `25, 15` → Swap.
- Result: `[10, 20, 15, 25, 40, 50]`
- Third-largest element `25` reaches its correct position.

**Pass 4:**

- Compare `10, 20` → No swap.
- Compare `20, 15` → Swap.
- Result: `[10, 15, 20, 25, 40, 50]`
- Fourth-largest element `20` reaches its correct position.

**Pass 5:**

- Compare `10, 15` → No swap.
- Array remains: `[10, 15, 20, 25, 40, 50]`

## **3. Key Observations**

- Each comparison involves two adjacent elements.
- Swap only when elements are out of order.
- After every pass, the largest remaining unsorted element settles at the end of the unsorted portion.
- The sorted portion increases by one element after each pass.
- The unsorted portion decreases by one element after each pass.
- Already sorted elements are not considered in subsequent passes.

## **4. Implementation**

```java
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        int[] arr = {10, 5, 40, 8, 9};
        int length = arr.length;
        boolean swapped;

        for (int i = 0; i < arr.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                    swapped = true;
                }
            }
            length--;
            
            // If no elements were swapped, the array is already sorted
            if (!swapped) {
                break;
            }
        }

        System.out.println(Arrays.toString(arr));
    }
}
```
# **2. Selection Sort**

## **1. Concept**

- Selection Sort is a simple comparison-based sorting algorithm.
- Initially, the entire array is considered unsorted.
- In every iteration:
  1. Find the smallest element in the unsorted portion.
  2. Swap it with the first element of the unsorted portion.
  3. Consider that element part of the sorted portion.
- The sorted portion grows, while the unsorted portion shrinks.

## **2. Working Mechanism**

Assume ascending order.

**Initial array:**

`[15, 25, 45, 30, 10]`

**Pass 1:**

- Find the smallest element in the entire array: `10`.
- Swap `10` with the element at index `0` (`15`).
- Result: `[10, 25, 45, 30, 15]`
- `10` is now in its correct position.

**Pass 2:**

- Search the unsorted portion: `[25, 45, 30, 15]`.
- Smallest element: `15`.
- Swap `15` with `25`.
- Result: `[10, 15, 45, 30, 25]`

**Pass 3:**

- Search the unsorted portion: `[45, 30, 25]`.
- Smallest element: `25`.
- Swap `25` with `45`.
- Result: `[10, 15, 25, 30, 45]`

**Pass 4:**

- Search the unsorted portion: `[30, 45]`.
- Smallest element: `30`.
- Swap `30` with itself.
- Result: `[10, 15, 25, 30, 45]`

* Only one element (`45`) remains.
* The entire array is now sorted.

## **3. General Algorithm**

For an array of size `n`:

- Iteration 1: Find the smallest element from index `0` to `n-1`; place it at index `0`.
- Iteration 2: Find the smallest element from index `1` to `n-1`; place it at index `1`.
- Iteration 3: Find the smallest element from index `2` to `n-1`; place it at index `2`.
- Continue until the unsorted portion contains one element.

## **4. Key Observations**

- Each iteration selects the smallest element from the unsorted portion.
- Only one swap is required per iteration (at most).
- Sorted portion grows by one element after each iteration.
- Unsorted portion shrinks by one element.
- The algorithm terminates when only one element remains unsorted.
- The same approach can be used for descending order by selecting the largest element instead.

## **5. Time Complexity**

- Best Case: O(n²)
- Average Case: O(n²)
- Worst Case: O(n²)
- Space Complexity: O(1) (in-place sorting).

## **6. Implementation**

```java
public class Test {
    public static void main(String[] args) {
        int[] arr = {15, 20, 5, 45, 30, 10};
        int start = 0;
        for (int i = 0; i < arr.length; i++) {
            int minVal = Integer.MAX_VALUE;
            int minPosition = 0;
            for (int j = start; j < arr.length; j++) {
                if(arr[j]< minVal){
                    minVal = arr[j];
                    minPosition = j;
                }
            }
            var temp = arr[start];
            arr[start] = minVal;
            arr[minPosition] = temp;
            start++;
        }
        

        System.out.println(Arrays.toString(arr));
    }
}
```
Four Ways to Clean It Up

While functionally accurate, there is one subtle edge case and a few redundant variables you can streamline:

1.  **Avoid `Integer.MAX_VALUE` (Edge Case Fix):** If every remaining element in the array happens to be `Integer.MAX_VALUE` (`2147483647`), `arr[j] < minVal` will evaluate to `false`, leaving `minPosition` at its initial `0` and corrupting the sorted prefix at index `0`. Instead, initialize `minPosition = i` (assuming the first unsorted element is the smallest) and compare `arr[j] < arr[minPosition]`. This also removes the need for `minVal` completely.
2.  **Drop the `start` Variable:** Because `start` starts at `0` and increments by `1` at the end of every outer loop iteration, `start` is always identical to `i`. You can use `i` directly.
3.  **Outer Loop Bound (`arr.length - 1`):** Once the first n−1n - 1n−1 elements are placed, the last element is automatically in its final position, so the outer loop only needs to run `i < arr.length - 1`.
4.  **Skip Self-Swaps:** Only perform the swap if `minPosition != i` to avoid unnecessary writes when an element is already in the right spot.

Here is the cleaner, idiomatic version:

```java
import java.util.Arrays;

public class Test {
public static void main(String[] args) {
int[] arr = {15, 20, 5, 45, 30, 10};

        for (int i = 0; i < arr.length - 1; i++) {
            int minPosition = i;

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minPosition]) {
                    minPosition = j;
                }
            }

            if (minPosition != i) {
                int temp = arr[i];
                arr[i] = arr[minPosition];
                arr[minPosition] = temp;
            }
        }

        System.out.println(Arrays.toString(arr));
    }
}
```

# **3. Insertion Sort**

## **1. Concept**

- Insertion Sort is a comparison-based sorting algorithm.
- Unlike basic Bubble Sort and Selection Sort, it performs fewer operations when the array is already or partially sorted.
- Initially, the **first element (index 0) is considered sorted**, since a single element is always sorted.
- In every iteration:
  1. Pick the first element `x` from the unsorted portion.
  2. Shift all elements greater than `x` in the sorted portion one position to the right.
  3. Insert `x` into its correct position.
- The sorted portion grows by one element after each iteration.

## **2. Working Mechanism**

**Initial array:**

`[20, 10, 40, 15, 5]`

**Pass 1: Insert&#x20;**`10`

- Sorted portion: `[20]`
- Shift `20` right and insert `10`.
- Result: `[10, 20, 40, 15, 5]`

**Pass 2: Insert&#x20;**`40`

- Sorted portion: `[10, 20]`
- No elements are greater than `40`; no shifting required.
- Result: `[10, 20, 40, 15, 5]`

**Pass 3: Insert&#x20;**`15`

- Sorted portion: `[10, 20, 40]`
- Shift `40` and `20` right.
- Insert `15` after `10`.
- Result: `[10, 15, 20, 40, 5]`

**Pass 4: Insert&#x20;**`5`

- Sorted portion: `[10, 15, 20, 40]`
- Shift `40, 20, 15, 10` right.
- Insert `5` at index `0`.
- Result: `[5, 10, 15, 20, 40]`

## **3. Already Sorted Array**

Example: `[5, 10, 15, 20, 25]`

- Each element is already greater than or equal to all elements to its left.
- No shifting is required.
- Only one traversal is needed.

**Time Complexity:**

| Case                        | Complexity |
| --------------------------- | ---------- |
| Best Case (Already Sorted)  | O(n)       |
| Average Case                | O(n²)      |
| Worst Case (Reverse Sorted) | O(n²)      |
| Space Complexity            | O(1)       |

## **4. Key Observations**

- Starts processing elements from index `1`.
- Maintains a sorted portion on the left and an unsorted portion on the right.
- Uses **shifting instead of repeated swapping** to insert elements.
- The number of shifts depends on how far an element is from its correct position.
- More efficient for already sorted or nearly sorted arrays.
- The next section covers implementation and further time-complexity analysis.

## **5. implementation

```java
import java.util.Arrays;

public class Test {
  public static void main(String[] args) {
    int[] arr = {20, 10, 40, 50, 25, 15};

    for (int i = 1; i < arr.length; i++) {
      int j = i;
      while (j >0 && arr[j]<arr[j-1]){
        var temp = arr[j];
        arr[j] = arr[j-1];
        arr[j-1] = temp;
        j--;
      }

    }
    System.out.println(Arrays.toString(arr));
  }
}
```

# **Merge Operation**

## **1. Concept**

- Merge operation takes **two sorted arrays** and produces a single sorted array containing elements from both.
- Input arrays can have different sizes.
- Both input arrays and the resulting array must be sorted.
- Merge operation is an important component of Merge Sort.

## **2. Working Mechanism – Two Pointers**

- Uses three pointers/indexes: `i`, `j`, and `k`.
- Create a new array `C` with capacity equal to the combined sizes of `A` and `B`.

**Pointer responsibilities:**

| Pointer | Purpose                                               |
| ------- | ----------------------------------------------------- |
| `i`     | Points to the smallest unprocessed element in array A |
| `j`     | Points to the smallest unprocessed element in array B |
| `k`     | Points to the next empty position in array C          |

\- Initially, all three pointers start at index `0`.

## **3. Merge Algorithm**

1. Compare `A[i]` and `B[j]`.
2. Copy the smaller element into `C[k]`.
3. Move the corresponding pointer (`i` or `j`) forward.
4. Increment `k`.
5. Repeat until one of the input arrays is exhausted.
6. Copy all remaining elements from the other array directly into C.

- If both elements are equal, either can be copied first.
- Since both input arrays are already sorted, comparing their smallest unprocessed elements is sufficient to identify the next smallest element overall.

## **4. Example**

**Input:**

```
A = [2, 5, 9, 13, 19]
B = [7, 8, 10, 16]
```

**Merge process:**

| A[i] | B[j] | Selected | C                             |
| ---- | ---- | -------- | ----------------------------- |
| 2    | 7    | 2        | `[2]`                         |
| 5    | 7    | 5        | `[2, 5]`                      |
| 9    | 7    | 7        | `[2, 5, 7]`                   |
| 9    | 8    | 8        | `[2, 5, 7, 8]`                |
| 9    | 10   | 9        | `[2, 5, 7, 8, 9]`             |
| 13   | 10   | 10       | `[2, 5, 7, 8, 9, 10]`         |
| 13   | 16   | 13       | `[2, 5, 7, 8, 9, 10, 13]`     |
| 19   | 16   | 16       | `[2, 5, 7, 8, 9, 10, 13, 16]` |

- Array B is now exhausted.
- Copy the remaining element `19` from array A.

**Final merged array:**

`[2, 5, 7, 8, 9, 10, 13, 16, 19]`

## **5. Time Complexity**

Let:

- `n` = Size of array A.
- `m` = Size of array B.
- Every element is processed exactly once.
- Each comparison, copy and pointer movement takes O(1).
- Total Time Complexity: **O(n + m)**.
- Space Complexity: **O(n + m)** for the resulting array.

## **6. Why Merge Operation Is Important**

- Sorting two arrays independently by combining them and sorting again would require at least O((n+m) log(n+m)) using comparison-based sorting algorithms.
- Since both input arrays are already sorted, merging them takes only O(n+m).
- This linear-time operation is the foundation of Merge Sort.

## **7. Implementation*

```java
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        int[] arr1 = {2, 7, 9, 16};
        int[] arr2 = {8, 14, 25, 35};
        int[] result = new int[arr1.length + arr2.length];

        int x = 0;
        int y = 0;
        int i = 0;

        while (x < arr1.length || y < arr2.length) {
            if (x < arr1.length && y < arr2.length) {
                if (arr1[x] < arr2[y]) {
                    result[i++] = arr1[x++];
                } else {
                    result[i++] = arr2[y++];
                }
            } else if (x < arr1.length) {
                // arr2 is finished; copy remaining from arr1
                result[i++] = arr1[x++];
            } else {
                // arr1 is finished; copy remaining from arr2
                result[i++] = arr2[y++];
            }
        }
        System.out.println(Arrays.toString(result));
    }
}
```

# **5. Merge Sort**

## **1. Concept**

- Merge Sort is a **Divide and Conquer** sorting algorithm.
- It divides the original array into two equal or approximately equal parts.
- Sorts both parts independently.
- Merges the two sorted parts using the previously discussed **Merge Operation**.

## **2. Divide and Conquer Approach**

Two main steps:

1. **Divide:** Recursively divide the problem into smaller parts.
2. **Conquer:** Solve the smaller problems and merge their results to obtain the final solution.

## **3. Working Mechanism**

**Initial array:**

`[15, 5, 20, 30, 40, 60, 7, 19]`

### **Step 1: Divide**

\- Divide the array into two approximately equal halves.

```
[15, 5, 20, 30]       [40, 60, 7, 19]
```

\- Recursively divide each half into smaller halves.

```
[15, 5] [20, 30]     [40, 60] [7, 19]
```

\- Continue dividing until every subarray contains exactly one element.

```
[15] [5] [20] [30] [40] [60] [7] [19]
```

\- A single-element array is already sorted.

### **Step 2: Merge**

\- Merge individual elements into sorted arrays using the Merge Operation.

**First-level merging:**

```
[15] [5]     → [5, 15]
[20] [30]    → [20, 30]
[40] [60]    → [40, 60]
[7]  [19]    → [7, 19]
```

**Second-level merging:**

```
[5, 15] [20, 30]    → [5, 15, 20, 30]

[40, 60] [7, 19]    → [7, 19, 40, 60]
```

**Final merging:**

```
[5, 15, 20, 30] [7, 19, 40, 60]

→ [5, 7, 15, 19, 20, 30, 40, 60]
```

Sorted array

`[5, 7, 15, 19, 20, 30, 40, 60]`

## **4. Important Observations**

- Recursively divides the array until each subarray contains one element.
- For even-sized arrays, divides into two equal halves.
- For odd-sized arrays, divides into two approximately equal halves.
- A single-element array is considered sorted.
- Merges smaller sorted arrays into larger sorted arrays.
- Uses recursion to implement the divide-and-conquer process.
- The Merge Operation is the core component responsible for combining sorted subarrays.

## **5. Overall Algorithm Flow**

```
Original Array
      |
      v
Divide into two halves
      |
      v
Recursively divide each half
      |
      v
Continue until single-element arrays
      |
      v
Merge individual elements
      |
      v
Merge sorted subarrays
      |
      v
Final Sorted Array
```

## **Next Section**

- Implementation of Merge Sort using recursion.
- Time and space complexity analysis.

# **Partitioning Algorithm (Foundation of Quick Sort)**

## **1. Concept**

- Partitioning rearranges an array based on a given **key (pivot)**.
- The pivot must be an element present in the array.
- After partitioning:
  - Elements smaller than the pivot are on the left.
  - Elements equal to the pivot are in the middle.
  - Elements greater than the pivot are on the right.
- Partitioning does **not** mean sorting. Elements within each partition may still be unsorted.

**Example:**

Pivot = `3`

```
[2, 1, 0, 3, 5, 4, 6]
```

- Left: Elements smaller than `3`.
- Middle: Elements equal to `3`.
- Right: Elements greater than `3`.

## **2. Partitioning Algorithm (Two-Way Partitioning)**

- The algorithm discussed assumes that the pivot is always the **last element of the array**.
- If the required pivot is elsewhere, swap it with the last element before applying the algorithm.

### **Variables**

- `pivot`: Element used for partitioning.
- `left`: Initially `-1`; tracks the boundary of elements smaller than the pivot.
- `i`: Iterates through the array from left to right, excluding the last element.

### **Algorithm**

1. Initialize `left = -1`.
2. Iterate through the array from index `0` to `n-2`.
3. For every element:
  - If `arr[i] < pivot`:
    - Increment `left`.
    - Swap `arr[i]` with `arr[left]`.
  - If `arr[i] >= pivot`, do nothing.
4. After the loop:
  - Increment `left`.
  - Swap the pivot (last element) with `arr[left]`.
5. The pivot is now at its correct partition position.

## **3. Working Example**

**Initial array:**

`[2, 5, 1, 0, 4, 6, 3]`

Pivot = `3`

Initial `left = -1`.

| i | Current element | Condition        | Action           | left |
| - | --------------- | ---------------- | ---------------- | ---- |
| 0 | 2               | 2 less than 3    | Swap with arr[0] | 0    |
| 1 | 5               | 5 greater than 3 | Ignore           | 0    |
| 2 | 1               | 1 less than 3    | Swap with arr[1] | 1    |
| 3 | 0               | 0 less than 3    | Swap with arr[2] | 2    |
| 4 | 4               | 4 greater than 3 | Ignore           | 2    |
| 5 | 6               | 6 greater than 3 | Ignore           | 2    |

After processing all elements:

- `left = 2`
- Elements up to index `2` are smaller than the pivot.
- Swap the pivot `3` with the element at index `3`.

**Final array:**

`[2, 1, 0, 3, 4, 6, 5]`

Pivot positioned

- Left partition: `[2, 1, 0]`
- Pivot: `3`
- Right partition: `[4, 6, 5]`

## **4. Key Observations**

- This is called **Two-Way Partitioning**.
- The array is divided into elements smaller than the pivot and elements greater than or equal to the pivot, with the pivot placed between them.
- The `left` variable tracks the boundary of the smaller elements.
- Elements greater than or equal to the pivot are left in the right partition.
- The pivot reaches its correct partition position after the final swap.
- The resulting partitions are not necessarily sorted.
- Partitioning is the core operation used by Quick Sort.

## **5. Time Complexity**

- Time Complexity: O(n) — each element is traversed once.
- Space Complexity: O(1) — partitioning is performed in-place.

## **Next Section**

- Implementation of the partitioning algorithm.
- How Quick Sort uses partitioning to sort an array.

# **7. Quick Sort**

## **1. Concept**

- Quick Sort is a **Divide and Conquer** sorting algorithm.
- It uses the Partitioning Algorithm to divide an array into smaller parts.
- Unlike Merge Sort, it does not divide the array into equal halves.
- The division depends on the position of the pivot after partitioning.

## **2. Key Property of Partitioning**

- Partitioning does not sort the entire array.
- However, it places the **pivot at its correct position** in the final sorted array.
- All elements smaller than the pivot are on its left.
- All elements greater than the pivot are on its right.
- The elements within the left and right partitions may still be unsorted.

**Example:**

```
Initial:      [2, 5, 1, 0, 4, 6, 3]

After partition:
              [2, 1, 0, 3, 4, 6, 5]
                       |
                  Pivot = 3
```

- `3` is now at its final sorted position.
- Elements on either side may still be unsorted.

## **3. Working Mechanism**

1. Select a pivot and partition the array.
2. The pivot reaches its correct sorted position.
3. Recursively apply Quick Sort to the left partition.
4. Recursively apply Quick Sort to the right partition.
5. Continue until the partitions contain zero or one element.

**Example:**

```
Initial Array:
[2, 5, 1, 0, 4, 3]

             |
         Partition
             |
[2, 1, 0] [3] [5, 4]
               |
       3 is in correct position
             |
     Recursively Quick Sort
       /             \
 [2, 1, 0]         [5, 4]
       |               |
   Partition         Partition
       |               |
 [0, 1, 2]           [4, 5]
       \               /
        Final Sorted Array
       [0, 1, 2, 3, 4, 5]
```

## **4. Quick Sort vs Merge Sort**

| Merge Sort                                                 | Quick Sort                                  |
| ---------------------------------------------------------- | ------------------------------------------- |
| Divides array into two equal or approximately equal halves | Divides based on pivot position             |
| Uses the Merge operation to combine sorted parts           | Uses Partitioning to position the pivot     |
| Division is predictable                                    | Division depends on pivot selection         |
| Merges sorted subarrays                                    | Recursively sorts left and right partitions |

## **5. Key Observations**

- Every partition operation places the pivot in its final sorted position.
- Once positioned, the pivot does not need to be considered again.
- Only the left and right partitions require further sorting.
- Quick Sort recursively applies the same partitioning process.
- Unlike Merge Sort, partitions may be highly unequal depending on the pivot.
- Eventually, all elements reach their correct positions.

## **6 Implementation**
```java
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        int[] arr = {2, 5, 1, 0, 4, 6, 3};
//        var x = partition(arr, 0, arr.length - 1);
//        System.out.println("Partitioned by 3" + Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println("Fully sorted " + Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int partitionIndex = partition(arr, start, end);
        quickSort(arr, start, partitionIndex - 1);
        quickSort(arr, partitionIndex + 1, end);
    }


    public static int partition(int[] arr, int start, int end) {
        int pivot = arr[end];
        int left = start - 1;
        for (int i = start; i < end; i++) {
            if (arr[i] < pivot) {
                left++;
                var temp = arr[i];
                arr[i] = arr[left];
                arr[left] = temp;
            }
        }
        left++;
        arr[end] = arr[left];
        arr[left] = pivot;
        return left;
    }
}
```


# **8. Counting Sort**

## **1. Concept**

- Counting Sort is a **non-comparison-based sorting algorithm**.
- Unlike Bubble Sort, Selection Sort, Merge Sort and Quick Sort, it does not compare elements to determine their relative order.
- It works by counting the frequency of each element and calculating its final position.
- It can achieve linear time complexity: O(n + m).

**Comparison-based vs Non-comparison-based sorting:**

- Comparison-based algorithms determine order by comparing elements.
- Comparison-based sorting algorithms have a proven lower bound of O(n log n).
- Counting Sort avoids comparisons and can achieve linear complexity.

## **2. Working Mechanism**

**Initial array:**

`[2, 5, 1, 2, 3, 4, 6, 5, 2]`

Let:

- `n` = Number of elements.
- `m` = Maximum element in the array (6).

### **Step 1: Create Frequency Array**

- Find the maximum element in the original array.
- Create a frequency array of size `max + 1`.
- Index represents the element; value represents its frequency.

| Index (Element) | 0 | 1 | 2 | 3 | 4 | 5 | 6 |
| --------------- | - | - | - | - | - | - | - |
| Frequency       | 0 | 1 | 3 | 1 | 1 | 2 | 1 |

### **Step 2: Calculate Cumulative Frequency**

- Convert the frequency array into a cumulative frequency array.
- Each position represents the number of elements less than or equal to that index.
- This gives the final position (1-based rank) of the corresponding element.

| Index (Element)      | 0 | 1 | 2 | 3 | 4 | 5 | 6 |
| -------------------- | - | - | - | - | - | - | - |
| Frequency            | 0 | 1 | 3 | 1 | 1 | 2 | 1 |
| Cumulative Frequency | 0 | 1 | 4 | 5 | 6 | 8 | 9 |

**Interpretation:**

- `6` has cumulative frequency `9` → Its final position is index `8`.
- `5` has cumulative frequency `8` → Its final position is index `7`.
- `4` has cumulative frequency `6` → Its final position is index `5`.
- `3` has cumulative frequency `5` → Its final position is index `4`.
- `2` has cumulative frequency `4` → Its final position is index `3`.

### **Step 3: Create Temporary Array**

- Create a temporary array of the same size as the original array.
- This array stores the sorted result.

### **Step 4: Place Elements in Their Correct Positions**

- Iterate through the original array **from right to left**.
- For each element `x`:
  1. Check its cumulative frequency.
  2. Subtract 1 to get its zero-based final index.
  3. Place the element at that index in the temporary array.
  4. Decrement its cumulative frequency by 1.

**Example:**

- First element processed from the right: `2`.
- Cumulative frequency of `2` = `4`.
- Final index = `4 - 1 = 3`.
- Place `2` at index `3`.
- Decrement cumulative frequency of `2` to `3`.

* Next element: `5`.
* Cumulative frequency of `5` = `8`.
* Final index = `8 - 1 = 7`.
* Place `5` at index `7`.
* Decrement cumulative frequency of `5` to `7`.

\- Repeat for all remaining elements.

### **Step 5: Copy Result Back**

- Once all elements are placed, the temporary array contains the sorted result.
- Copy all elements back into the original array.

Final sorted array

`[1, 2, 2, 2, 3, 4, 5, 5, 6]`

## **3. Why Iterate from Right to Left?**

- Counting Sort is a **stable sorting algorithm**.
- Stability means that the relative order of equal elements is maintained.
- Example: If equal elements are identified as `2A`, `2B`, `2C` in the original array, their relative order must remain the same in the sorted array.
- Iterating from right to left while decrementing cumulative frequency preserves this relative order.

## **4. Stable vs Unstable Sorting**

- **Stable Sort:** Maintains the relative order of equal elements.
- **Unstable Sort:** Does not guarantee preservation of the relative order of equal elements.
- Counting Sort is stable.
- Stability is important for Radix Sort, which uses Counting Sort internally.

## **5. Time Complexity**

Let:

- `n` = Size of the original array.
- `m` = Maximum element in the array.

| Operation                      | Complexity |
| ------------------------------ | ---------- |
| Find maximum element           | O(n)       |
| Calculate frequency            | O(n)       |
| Calculate cumulative frequency | O(m)       |
| Populate temporary array       | O(n)       |
| Copy result back               | O(n)       |

**Overall Time Complexity: O(n + m)**

**Space Complexity: O(n + m)**

- Temporary array requires O(n) space.
- Frequency array requires O(m) space.

## **6. Limitations**

- Requires a frequency array based on the maximum element.
- If the maximum element is very large, the frequency array consumes significant memory.
- Time and space complexity depend on both the input size and the maximum element.
- Not suitable for every dataset.
- Most useful when the range of input values is relatively small.

## **7. Key Observations**

- Counting Sort does not compare elements.
- Uses frequency counting and cumulative frequency to determine element positions.
- Iterates through the original array in reverse when placing elements to maintain stability.
- Achieves linear time complexity when the value range is reasonably small.
- Is a stable sorting algorithm.
- Its stability is important for Radix Sort.

## **Implementation **


**Note it assumes numbers are non-negative**
```java
import java.util.Arrays;

public class Test {
  public static void main(String[] args) {
    int[] arr = {2, 5, 1, 2, 3, 4, 6, 5, 2};

    System.out.println(Arrays.toString(countingSort(arr)));
  }

  public static int[] countingSort(int[] arr) {
    if (arr == null || arr.length == 0) {
      return new int[0];
    }

    int max = 0;
    for (int i = 0; i < arr.length; i++) {
      max = Math.max(max, arr[i]);
    }
    int[] frequencyArray = new int[max + 1];
    for (int i = 0; i < arr.length; i++) {
      frequencyArray[arr[i]] = frequencyArray[arr[i]] + 1;

    }

    var sum = 0;
    int[] frequencyCumulative = new int[frequencyArray.length];
    for (int i = 0; i < frequencyArray.length; i++) {
      sum = sum + frequencyArray[i];
      frequencyCumulative[i] = sum;
    }

    int[] result = new int[arr.length];

    for (int i = arr.length - 1; i >= 0; i--) {
      var item = arr[i];
      var position = frequencyCumulative[item] - 1;
      frequencyCumulative[item] = frequencyCumulative[item] - 1;
      result[position] = item;
    }
    return result;
  }
}
```

## **Making Counting Sort Stable**

- **Stable sorting:** If two elements have equal values, their original relative order must be preserved in the sorted result.
- The current Counting Sort implementation is not stable because it processes elements from **left to right** during the final placement step.
- **Why it becomes unstable:**
  - The cumulative count reserves positions for each value.
  - For duplicate values, Counting Sort fills the reserved positions starting from the last position.
  - This reverses the relative order of equal elements.
  - Example: `2A` and `2B` may appear as `2B, 2A` in the result, even though the original order was `2A, 2B`.

### **Making Counting Sort Stable**

- Do not change the cumulative-count placement logic.
- Change the final iteration over the original array from **left to right** to **right to left**.
- This ensures that elements with equal values retain their original relative order.

### **Why Stability Matters**

- Counting Sort is often used as part of another sorting algorithm rather than directly, because it has a limited value range (`0` to `k`).
- It is used in **Radix Sort**, where stability is essential.
- If Counting Sort is not stable, Radix Sort will not work correctly.


## **Radix Sort**

- **Radix Sort** is a non-comparison-based sorting algorithm.
- It sorts numbers by processing individual digit positions, starting with the **least significant digit** and moving towards more significant digits.

### **Working Principle**

- Consider the input: `[259, 142, 842, 741, 356]`.
- Sort the numbers in multiple passes:
  1. **Ones place:** Sort based on the last digit.
    - Result: `[741, 142, 842, 356, 259]`
  2. **Tens place:** Sort based on the tens digit.
    - Result: `[741, 142, 842, 356, 259]`
  3. **Hundreds place:** Sort based on the hundreds digit.
    - Result: `[142, 259, 356, 741, 842]`
- After processing all digit positions, the numbers are sorted.

### **Role of Counting Sort**

- Radix Sort uses **Counting Sort** to sort numbers based on each individual digit position.
- When sorting by ones, tens, or hundreds, the keys are always between `0` and `9`.
- This limited range makes Counting Sort suitable for sorting each digit position.
- Counting Sort must be **stable** so that the ordering established in previous passes is preserved.

### **Overall Process**

- Sort by ones place.
- Sort by tens place.
- Sort by hundreds place.
- Continue until all digit positions are processed.