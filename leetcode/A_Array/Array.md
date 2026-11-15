# **Arrays**

- Arrays are a fundamental data structure available in almost every programming language.
- Arrays store elements in a **contiguous (continuous) block of memory (RAM)**.
- Arrays are generally **homogeneous**, meaning all elements must be of the same data type. This is irespective of language 
  - Example: An integer array can store only integers, not strings or other data types.
- Memory allocation example:
  - `int[] r = new int[10];`
  - Assuming an integer occupies 4 bytes:
    - 10 integers × 4 bytes = **40 bytes**.
    - Memory is allocated contiguously.
    - Element addresses: `0, 4, 8, 12, 16, ... 36` (assuming starting address 0).

## **Array Element Access**

- The array name represents the starting memory address of the array.
- Element memory location is calculated using:

`Address = Starting Address + (Index × Element Size)`

\- Example: `r[2] = 50`

- Starting address = 0
- Index = 2
- Integer size = 4 bytes
- Address = `0 + (2 × 4) = 8`
- Value `50` is stored at memory address 8.

\- Example: Accessing `r[4]`

- Address = `0 + (4 × 4) = 16`
- Value at memory address 16 is retrieved.

## **Time Complexity**

- **Accessing an element:** O(1)
- **Updating an element:** O(1)
- Both operations involve:
  1. Calculating the memory address using multiplication and addition.
  2. Accessing or updating the value at that address.
- Both operations take constant time.

## **Why Arrays Are Fast**

- Contiguous memory allocation makes element addresses predictable.
- Any element can be accessed directly using its index.
- No need to traverse other elements to locate the required element.
- This is what makes array access and updates efficient.

# **Drawbacks of Arrays**

## **1. Static Size**

- Traditionally, arrays have a **fixed size**, which must be specified at compile time.
- Once allocated, the array cannot grow or shrink.
- Modern languages provide workarounds through dynamically resizable arrays.
- Implementation of resizable arrays will be covered separately.

## **2. Insertion and Deletion Inefficiency**

- Arrays use contiguous memory and have a fixed allocated capacity.
- Inserting or deleting elements at the beginning or middle requires shifting other elements.

**Insertion example:**

- Initial array: `[10, 20, 30, 40, 50, 60, 70]`
- Insert `35` between `30` and `40`.
- Shift elements `40, 50, 60, 70` one position right.
- Result: `[10, 20, 30, 35, 40, 50, 60, 70]`

**Deletion example:**

- Delete `20`.
- Shift subsequent elements (`30, 35, 40, 50, 60, 70`) one position left.

**Time Complexity:**

- Insertion at beginning: O(n)
- Insertion in middle: O(n)
- Deletion at beginning: O(n)
- Deletion in middle: O(n)

## **Alternatives to Overcome Array Drawbacks**

| Data Structure                  | Drawbacks Addressed                                  |
| ------------------------------- | ---------------------------------------------------- |
| Linked List                     | Both static size and insertion/deletion inefficiency |
| Vector (C++) / ArrayList (Java) | Static size                                          |
| Python List                     | Dynamic resizing; internally array-based             |

- **Linked List:** Avoids shifting elements during insertion and deletion.
- **Vector / ArrayList:** Provides dynamically resizable arrays without requiring size specification at compile time.
- **Python List:** Built on an array-based implementation and supports dynamic resizing.

## **Next Section**

- How resizable arrays work internally.
- Implementation of vectors, ArrayLists, and similar data structures.

# **Resizable Arrays (ArrayList / Vector)**

## **1. Concept**

- Traditional arrays have a fixed size.
- To change the size, a new array must be allocated.
  - Example: `int[] r = new int[10]` → To store 20 elements, a new array of size 20 must be created.
- Modern languages provide resizable arrays through:
  - Java: `ArrayList`, `Vector`
  - C++: `vector`
  - Python: `list`
- Resizable arrays retain the benefits of contiguous memory while overcoming the fixed-size limitation.

## **2. Internal Implementation**

- A resizable array internally maintains:
  - **Array:** Stores elements in contiguous memory.
  - **Size:** Number of elements currently stored.
  - **Capacity:** Total number of elements the allocated array can hold.
- Initially, an internal array is allocated with some default capacity.
- Example: Assume initial capacity = 2.

| Operation        | Elements             | Size | Capacity |
| ---------------- | -------------------- | ---- | -------- |
| Create ArrayList | `[]`                 | 0    | 2        |
| `add(10)`        | `[10]`               | 1    | 2        |
| `add(5)`         | `[10, 5]`            | 2    | 2        |
| `add(13)`        | `[10, 5, 13]`        | 3    | 4        |
| `add(7)`         | `[10, 5, 13, 7]`     | 4    | 4        |
| `add(15)`        | `[10, 5, 13, 7, 15]` | 5    | 8        |

## **3. Resizing Mechanism**

- When `size == capacity`, the internal array is full.
- A new array is allocated with **double the previous capacity**.
  - Capacity: `2 → 4 → 8 → 16 → ...`
- All existing elements are copied into the new array.
- The new element is added.
- Size and capacity are updated.
- Old array memory is deallocated.
- This process happens internally; the developer only calls `add()`.

## **4. Time Complexity**

- Normal insertion (available capacity): O(1).
- Insertion requiring resizing:
  - Allocate a larger array.
  - Copy all existing elements.
  - Add the new element.
  - Time complexity: O(n).
- Resizing does not happen on every insertion.

**Amortized Time Complexity:**

- Insertion in a resizable array: **O(1) amortized**.
- Occasional O(n) resizing operations are distributed across multiple O(1) insertions.

## **5. Key Takeaway**

- Resizable arrays use an internal contiguous array.
- They maintain two variables: `size` and `capacity`.
- Capacity doubles whenever the array becomes full.
- This provides dynamic resizing while retaining efficient array access.
- The same general implementation approach is used by ArrayList, Vector, and similar data structures.