# Missing Theory: Time & Space Complexity

## 1. Rules of Big O (The 3 Golden Rules)
To simplify complexity analysis, we follow these rules:

1.  **Drop Constants:** $O(2n)$ becomes $O(n)$. We care about the *shape* of the curve, not the multiplier.
2.  **Drop Non-Dominant Terms:** In $O(n^2 + n)$, the $+ n$ becomes insignificant as $n$ grows. We simplify it to $O(n^2)$.
3.  **Worst Case Focus:** We usually report the "Worst Case" (Upper Bound) because it guarantees the algorithm will *never* perform worse than this.

---

## 2. Best, Average, and Worst Case
Let's use **Linear Search** as an example:
- **Best Case ($Ω$ - Omega):** Target is the first element. $O(1)$.
- **Average Case ($Θ$ - Theta):** Target is in the middle. $O(n/2)$ which simplifies to $O(n)$.
- **Worst Case ($O$ - Big O):** Target is the last element or not there at all. $O(n)$.

---

## 3. Amortized Analysis (The "Average" of a sequence)
Sometimes a single operation is expensive, but it only happens once in a long while.
- **Example:** `ArrayList` in Java or `list` in Python.
- Most `append` operations are $O(1)$.
- When the array is full, it must **resize** (create a new array and copy everything). This is $O(n)$.
- Because the resize happens rarely (every time we double the size), the **average cost per append** is still $O(1)$. We call this **Amortized O(1)**.

---

## 4. Space Complexity
This measures the **extra space** (memory) an algorithm needs as input size $n$ grows.
- **Iterative Loop:** Usually $O(1)$ extra space (just a few variables).
- **Recursive Call:** Usually $O(n)$ extra space (each call adds a frame to the "Stack").
- **Creating a copy of an array:** $O(n)$ extra space.
