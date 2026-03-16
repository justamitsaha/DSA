import java.util.*;

public class D_ComplexityComparison {
    private static final int n = 100_000_000;
    private static final int[] arr = new int[n];
    private static final Map<String, String> results = new LinkedHashMap<>();

    static {
        for (int i = 0; i < n; i++) arr[i] = i;
        // JIT Warm-up
        for (int i = 0; i < 10000; i++) { int d = arr[i % 1000]; }
    }

    public static void main(String[] args) {
        System.out.println("Starting Benchmark (Scales vary to prevent CPU meltdown)...\n");

        // O(1) - Constant
        benchmark("O(1) [Constant]", () -> { int x = arr[n / 2]; }, 10000);

        // O(log n) - Logarithmic (Binary Search)
        benchmark("O(log n) [Logarithmic]", () -> Arrays.binarySearch(arr, n - 1), 10000);

        // O(n) - Linear (Linear Search)
        benchmark("O(n) [Linear]", () -> {
            for (int i : arr) { if (i == -1) break; }
        }, 5);

        // O(n log n) - Linearithmic (Sorting a subset)
        int nLogN_Scale = 1_000_000;
        int[] subArr = new int[nLogN_Scale];
        benchmark("O(n log n) [Sorting 1M]", () -> Arrays.sort(subArr), 5);

        // O(n^2) - Quadratic (Nested Loop)
        int n2_Scale = 10_000;
        benchmark("O(n^2) [Nested Loop 10k]", () -> {
            long sum = 0;
            for (int i = 0; i < n2_Scale; i++) 
                for (int j = 0; j < n2_Scale; j++) sum++;
        }, 2);

        // O(2^n) - Exponential (Recursive Fibonacci)
        int exp_Scale = 30; // 2^30 is ~1 billion operations
        benchmark("O(2^n) [Recursive Fib 30]", () -> fib(exp_Scale), 1);

        // O(n!) - Factorial (Permutations)
        int fact_Scale = 10; // 10! is 3,628,800 operations
        benchmark("O(n!) [Permutations 10]", () -> permute(fact_Scale), 1);

        // Print final table
        System.out.println(String.format("%-25s | %-15s", "Complexity", "Avg Time"));
        System.out.println("-".repeat(45));
        results.forEach((k, v) -> System.out.println(String.format("%-25s | %-15s", k, v)));
    }

    private static void benchmark(String label, Runnable task, int iterations) {
        long start = System.nanoTime();
        for (int i = 0; i < iterations; i++) task.run();
        long avg = (System.nanoTime() - start) / iterations;
        results.put(label, formatTime(avg));
    }

    private static String formatTime(long ns) {
        if (ns < 1000) return ns + " ns";
        if (ns < 1_000_000) return (ns / 1000.0) + " μs";
        return (ns / 1_000_000.0) + " ms";
    }

    private static int fib(int k) {
        if (k <= 1) return k;
        return fib(k - 1) + fib(k - 2);
    }

    private static void permute(int k) {
        if (k == 0) return;
        for (int i = 0; i < k; i++) permute(k - 1);
    }
}

/*
To compare these fairly, we have to address the "Scaling Trap." If we used your $n = 100,000,000$ for $O(n^2)$, your computer would need to perform $10^{16}$ operations. At 3 GHz, that would take roughly 38 days just for one iteration. For $O(2^n)$ or $O(n!)$, the universe would end before the code finished.

To solve this, I’ve modified the class to use Local Scale Limits for the "expensive" complexities while keeping your logic for the fast ones. This way, we can see the relative differences without waiting for an eternity. The results will be printed in a neat table format at the end.

What to Look for in the Results
When you run this, pay attention to how drastically the units change as you move down the list.

Complexity,Scaling Behavior,Real-World Example
O(1),"Instant, regardless of N.","Array access, HashMap get."
O(logn),Extremely fast. N of 1B only takes ~30 steps.,"Binary Search, Tree operations."
O(n),Predictable growth.,"Linear search, traversing a list."
O(nlogn),"The standard for ""good"" sorting.","Merge Sort, Quick Sort."
O(n2),Bad for large N. 10x more data = 100x more time.,"Bubble Sort, Nested loops."
O(2n),Horrible. Adding 1 to N doubles the time.,Recursive algorithms without memoization.
O(n!),Impossible for N>15.,Generating every possible combination.

Why the scaling limits?I used 10,000 for $O(n^2)$ and only 30 for $O(2^n)$ because these complexities explode.If you tried to run $O(2^n)$ with $N=100,000,000$ like your array, it would take more than$$10^{30,000,000}$$years.This benchmark proves that the Algorithm matters far more than the Hardware.Your Next StepNow that you have the "Grand Map" of all complexities, you can see why we spend so much time trying to turn an $O(n^2)$ algorithm into an $O(n \log n)$ one.
*/
