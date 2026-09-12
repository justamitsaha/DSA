package leetcode.J_BinarySearch_1;

/**
 * LeetCode 33: Search in Rotated Sorted Array
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/search-in-rotated-sorted-array/
 *
 * Algorithm:
 * - Method 1: Single-Pass Binary Search with Sorted Half Determination (Optimal)
 * - Method 2: Two-Step Binary Search (Find Pivot / Point of Rotation, then Binary Search appropriate half)
 *
 * Concepts:
 * - An array sorted in ascending order of distinct values is rotated at an unknown pivot index.
 * - Key Observation:
 *   - At least ONE half of the array (either left `[start..mid]` or right `[mid..end]`) is ALWAYS sorted!
 *   - Case 1: Left half is sorted (`nums[start] <= nums[mid]`):
 *     - If `nums[start] <= target && target < nums[mid]`, target lies in the left half -> `end = mid - 1`.
 *     - Otherwise, target lies in the right half -> `start = mid + 1`.
 *   - Case 2: Right half is sorted (`nums[mid] <= nums[end]`):
 *     - If `nums[mid] < target && target <= nums[end]`, target lies in the right half -> `start = mid + 1`.
 *     - Otherwise, target lies in the left half -> `end = mid - 1`.
 *
 * Complexity:
 * - Time Complexity:  O(log n) - Halving search space at each step.
 * - Space Complexity: O(1)     - Constant space.
 */
public class LC0033_SearchInRotatedSortedArray {

    /**
     * Single-pass binary search (standard optimal interview solution).
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // Check if left half is normally sorted
            if (nums[start] <= nums[mid]) {
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid - 1; // Target lies in the sorted left half
                } else {
                    start = mid + 1; // Target lies in the right half
                }
            } else {
                // Right half must be normally sorted
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid + 1; // Target lies in the sorted right half
                } else {
                    end = mid - 1; // Target lies in the left half
                }
            }
        }

        return -1;
    }

    /**
     * Two-step approach: Find Point of Rotation (POR) first, then binary search the target half.
     * (As taught in the Udemy course).
     */
    public int searchViaPivot(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int por = findPointOfRotation(nums);

        // If array is not rotated (por == 0)
        if (por == 0) {
            return binarySearch(nums, 0, nums.length - 1, target);
        }

        // Target is in the left rotated half
        if (target >= nums[0]) {
            return binarySearch(nums, 0, por - 1, target);
        } else {
            // Target is in the right rotated half
            return binarySearch(nums, por, nums.length - 1, target);
        }
    }

    private int findPointOfRotation(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        int ans = 0;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] <= arr[arr.length - 1]) {
                ans = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return ans;
    }

    private int binarySearch(int[] arr, int start, int end, int key) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == key) {
                return mid;
            } else if (arr[mid] < key) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        LC0033_SearchInRotatedSortedArray solver = new LC0033_SearchInRotatedSortedArray();

        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};

        // Scenario 1: Target exists in right sorted portion
        System.out.println("Search 0 in [4, 5, 6, 7, 0, 1, 2]: " + solver.search(nums1, 0) + " (Expected: 4)");
        System.out.println("Via Pivot: " + solver.searchViaPivot(nums1, 0) + " (Expected: 4)");

        // Scenario 2: Target does not exist
        System.out.println("Search 3 in [4, 5, 6, 7, 0, 1, 2]: " + solver.search(nums1, 3) + " (Expected: -1)");

        // Scenario 3: Target exists in left sorted portion
        System.out.println("Search 5 in [4, 5, 6, 7, 0, 1, 2]: " + solver.search(nums1, 5) + " (Expected: 1)");

        // Scenario 4: Single element array - target found
        int[] nums2 = {1};
        System.out.println("Search 1 in [1]: " + solver.search(nums2, 1) + " (Expected: 0)");

        // Scenario 5: Single element array - target absent
        System.out.println("Search 0 in [1]: " + solver.search(nums2, 0) + " (Expected: -1)");

        // Scenario 6: Array rotated by 0 positions (regular sorted array)
        int[] nums3 = {1, 3, 5};
        System.out.println("Search 5 in [1, 3, 5]: " + solver.search(nums3, 5) + " (Expected: 2)");
    }
}
