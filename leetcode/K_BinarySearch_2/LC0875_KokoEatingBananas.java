package leetcode.K_BinarySearch_2;

/**
 * LeetCode 875: Koko Eating Bananas
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/koko-eating-bananas/
 *
 * Algorithm:
 * - Binary Search on Answer Space (Monotonic Feasibility Function)
 *
 * Concepts:
 * - Koko wants to eat all bananas within `h` hours at minimum integer eating speed `k` (bananas/hour).
 * - Monotonicity:
 *   - If eating speed `k` allows Koko to finish within `h` hours, any speed `k' > k` will also work.
 *   - If eating speed `k` is too slow (hours > h), any speed `k' < k` will also be too slow.
 *   - This monotonic property allows us to Binary Search the answer speed `k`!
 * - Search Space:
 *   - Minimum possible speed: `start = 1` (must eat at least 1 banana per hour).
 *   - Maximum needed speed: `end = max(piles)` (eating faster than the largest pile never saves extra hours per pile).
 * - Feasibility Check:
 *   - For a candidate speed `k`, hours spent on pile `p` is `ceil(p / k) = (p + k - 1) / k`.
 *   - Accumulate total hours in a 64-bit `long` to prevent 32-bit integer overflow.
 *
 * Complexity:
 * - Time Complexity:  O(n * log(max(piles))) - Binary search takes log(max(piles)) steps, each requiring O(n) check.
 * - Space Complexity: O(1)                   - Constant auxiliary space.
 */
public class LC0875_KokoEatingBananas {

    public int minEatingSpeed(int[] piles, int h) {
        int start = 1;
        int end = piles[0];
        for (int pile : piles) {
            if (pile > end) {
                end = pile;
            }
        }

        int ans = end;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (countHours(piles, mid) > (long) h) {
                // Too slow, need higher speed
                start = mid + 1;
            } else {
                // Feasible speed, record and try finding an even smaller speed
                ans = mid;
                end = mid - 1;
            }
        }

        return ans;
    }

    private long countHours(int[] piles, int speed) {
        long totalHours = 0;
        for (int pile : piles) {
            // Integer arithmetic for ceil(pile / speed)
            totalHours += (pile + (long) speed - 1) / speed;
        }
        return totalHours;
    }

    public static void main(String[] args) {
        LC0875_KokoEatingBananas solver = new LC0875_KokoEatingBananas();

        // Scenario 1: Standard case
        int[] piles1 = {3, 6, 7, 11};
        int h1 = 8;
        System.out.println("Scenario 1: " + solver.minEatingSpeed(piles1, h1) + " (Expected: 4)");

        // Scenario 2: Large piles with generous hours
        int[] piles2 = {30, 11, 23, 4, 20};
        int h2 = 5;
        System.out.println("Scenario 2: " + solver.minEatingSpeed(piles2, h2) + " (Expected: 30)");

        // Scenario 3: Large piles with tighter hours
        int[] piles3 = {30, 11, 23, 4, 20};
        int h3 = 6;
        System.out.println("Scenario 3: " + solver.minEatingSpeed(piles3, h3) + " (Expected: 23)");

        // Scenario 4: Single pile
        int[] piles4 = {312884470};
        int h4 = 968709470;
        System.out.println("Scenario 4 (single pile): " + solver.minEatingSpeed(piles4, h4) + " (Expected: 1)");
    }
}
