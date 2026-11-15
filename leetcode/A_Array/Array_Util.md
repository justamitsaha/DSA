# **Important Array Methods in Java (**`Arrays`**&#x20;Class)**

Java provides the `java.util.Arrays` class with built-in utility methods useful for array operations and coding interviews.

## **1.&#x20;**`Arrays.asList()`

- Converts multiple elements into a `List`.
- Provides a convenient way to create and initialize a list without adding elements individually.

**Example:**

```
List<Integer> list = Arrays.asList(10, 20, 30);
```

- Creates a list containing `10, 20, 30`.
- Avoids manually creating an `ArrayList` and calling `add()` for each element.

## **2.&#x20;**`Arrays.sort()`

- Sorts an array in ascending order.
- Supports sorting the entire array or a specified range.

**Sort entire array:**

```
int[] arr = {10, 5, 4, 3, 12, 8};

Arrays.sort(arr);
```

Result:

```
[3, 4, 5, 8, 10, 12]
```

**Sort a specific range:**

```
Arrays.sort(arr, fromIndex, toIndex);
```

- `fromIndex`: Starting index (inclusive).
- `toIndex`: Ending index (exclusive).
- Only elements within the specified range are sorted.

Example:

```
int[] arr = {10, 5, 4, 3, 12, 8};

Arrays.sort(arr, 0, 3);
```

Result:

```
[4, 5, 10, 3, 12, 8]
```

- Only indices `0, 1, 2` are sorted.
- Index `3` is excluded.

**Important:** Java's `Arrays.sort()` range follows the `[fromIndex, toIndex)` convention (inclusive start, exclusive end).

# **Important Array Methods in Java (**`Arrays`**&#x20;Class) – Binary Search**

## **1.&#x20;**`Arrays.binarySearch()`

- Searches for a given element in a **sorted array**.
- Uses the Binary Search algorithm.
- Time Complexity: **O(log n)**.
- Returns the index of the element if found; otherwise, returns a negative value.
- Prefer built-in methods during coding interviews to avoid unnecessary implementation errors and save time.

**Search in the entire array:**

```
int[] arr = {50, 40, 10, 90, 3, 89};

Arrays.sort(arr); // [3, 10, 40, 50, 89, 90]

int index = Arrays.binarySearch(arr, 10);

System.out.println(index); // 1
```

- First argument: Array to search.
- Second argument: Key to search.
- Returns the index where the key is found.

## **2. Binary Search Within a Specific Range**

\- Java provides an overloaded `binarySearch()` method to search within a specific range.

```
Arrays.binarySearch(arr, fromIndex, toIndex, key);
```

- `fromIndex`: Starting index (inclusive).
- `toIndex`: Ending index (exclusive).
- `key`: Element to search.

**Example:**

```
int[] arr = {3, 10, 40, 50, 89, 90};

int index = Arrays.binarySearch(arr, 3, 6, 90);

System.out.println(index); // 5
```

- Searches indices `3, 4, 5`.
- `toIndex = 6` is excluded.
- Searches only the second half of the array.

## **3. Return Value**

- If the element is found: Returns its index (non-negative).
- If the element is not found: Returns a negative value.
- Example:
  - Searching `90` returns `5`.
  - Searching `10` within indices `3` to `5` returns a negative value.

**Important:**

- The array or specified range must be sorted before performing binary search.
- Range convention: `[fromIndex, toIndex)` (inclusive start, exclusive end).
- Both full-array and range-based binary search have **O(log n)** time complexity.

# **Important Array Methods in Java (**`Arrays`**&#x20;Class)**

## **1.&#x20;**`Arrays.fill()`

- Fills an entire array with a specified value.
- Avoids manually iterating through the array using a `for` loop.

**Example:**

```
int[] arr = new int[10];

// Using loop
for (int i = 0; i < arr.length; i++) {
    arr[i] = 5;
}

// Using Arrays.fill()
Arrays.fill(arr, 5);
```

- All elements are initialized to `5`.
- By default, an integer array is initialized with `0`.

## **2. Custom Sorting Using&#x20;**`Arrays.sort()`

- `Arrays.sort()` supports custom sorting using a `Comparator`.
- Useful when sorting:
  - Custom objects/classes.
  - Primitive values using custom criteria.

### **A. Using&#x20;**`Comparable`**&#x20;Interface**

- The class itself implements `Comparable`.
- Requires implementing the `compareTo()` method.
- Defines the class's natural/default sorting order.
- Without `Comparable` or a supplied `Comparator`, Java cannot sort custom objects by default.

**Comparison return values:**

| Return Value | Meaning                   |
| ------------ | ------------------------- |
| Negative     | Current object is smaller |
| Zero         | Both objects are equal    |
| Positive     | Current object is greater |

**Example:**

```
class Tuple implements Comparable<Tuple> {
    public int a, b;

    Tuple(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int compareTo(Tuple other) {
        if (this.b != other.b) {
            return this.b - other.b;
        }
        return this.a - other.a;
    }
}
```

\- Sorting criteria:

1. Sort by `b` in ascending order.
2. If `b` is equal, sort by `a` in ascending order.

**Usage:**

```
Arrays.sort(arr);
```

### **B. Using&#x20;**`Comparator`**&#x20;Interface**

- Instead of implementing `Comparable` in the class, pass a custom `Comparator` directly to `Arrays.sort()`.
- Useful when:
  - You cannot modify the class.
  - You need different sorting criteria for the same class.
- The comparator defines how two objects should be compared.

**Using an anonymous class:**

```
Arrays.sort(arr, new Comparator<Tuple>() {
    @Override
    public int compare(Tuple o1, Tuple o2) {
        if (o1.b != o2.b) {
            return o1.b - o2.b;
        }
        return o1.a - o2.a;
    }
});
```

**Using a Lambda Expression:**

```
Arrays.sort(arr, (o1, o2) -> {
    if (o1.b != o2.b) {
        return o1.b - o2.b;
    }
    return o1.a - o2.a;
});
```

\- Both approaches produce the same sorting result.

## **3. Comparable vs Comparator**

| Comparable                       | Comparator                           |
| -------------------------------- | ------------------------------------ |
| Class implements `Comparable`    | Passed separately to sorting method  |
| Implements `compareTo()`         | Implements `compare()`               |
| Defines natural/default ordering | Defines custom ordering              |
| Modifies the class               | Does not require modifying the class |
| `Arrays.sort(arr)`               | `Arrays.sort(arr, comparator)`       |

**Key takeaway:** Java supports custom object sorting through two approaches: `Comparable` and `Comparator`.