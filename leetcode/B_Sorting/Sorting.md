Many  algorithm will work only if the array is sorted like Bellman ford, Binary Search, Two pointer methods etc. Common sorting algorithm
1. Bubble sort
2. Selection sort
3. Merge Sort
4. Insertion Sort
5. Quick Sot
6. Head Sort
7. Radix sort

# **Bubble Sort**

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
# **Selection Sort**

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

# **Insertion Sort**

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