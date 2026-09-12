package leetcode.G_Two_pointer_methods;

/**
 * LeetCode 125: Valid Palindrome
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/valid-palindrome/
 *
 * Algorithm:
 * - Two-Pointer Inward Comparison with Alphanumeric Filtering & Case Normalization
 *
 * Concepts:
 * - A phrase is a palindrome if, after converting all uppercase letters into lowercase letters
 *   and removing all non-alphanumeric characters, it reads the same forward and backward.
 * - Algorithm Steps:
 *     1. Initialize `left = 0` and `right = s.length() - 1`.
 *     2. While `left < right`:
 *        - If `s.charAt(left)` is not letter or digit: advance `left++`.
 *        - If `s.charAt(right)` is not letter or digit: decrement `right--`.
 *        - Once both point to valid alphanumeric characters:
 *            - Compare case-insensitively using `Character.toLowerCase()`.
 *            - If they don't match, return `false`.
 *            - If they match, advance `left++` and decrement `right--`.
 *     3. If pointers cross without mismatch, return `true`.
 *
 * Complexity:
 * - Time Complexity:  O(n) - Single pass over the string with at most n character inspections.
 * - Space Complexity: O(1) - Constant extra space (no auxiliary strings created).
 */
public class LC0125_ValidPalindrome {

    public boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            char leftChar = s.charAt(left);
            char rightChar = s.charAt(right);

            if (!Character.isLetterOrDigit(leftChar)) {
                left++;
            } else if (!Character.isLetterOrDigit(rightChar)) {
                right--;
            } else {
                if (Character.toLowerCase(leftChar) != Character.toLowerCase(rightChar)) {
                    return false;
                }
                left++;
                right--;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        LC0125_ValidPalindrome solver = new LC0125_ValidPalindrome();

        // Scenario 1: Standard sentence with punctuation
        String s1 = "A man, a plan, a canal: Panama";
        System.out.println("Scenario 1: \"" + s1 + "\"");
        System.out.println("  Is Palindrome: " + solver.isPalindrome(s1) + " | Expected: true\n");

        // Scenario 2: Non-palindrome
        String s2 = "race a car";
        System.out.println("Scenario 2: \"" + s2 + "\"");
        System.out.println("  Is Palindrome: " + solver.isPalindrome(s2) + " | Expected: false\n");

        // Scenario 3: Empty or spaces-only string
        String s3 = " ";
        System.out.println("Scenario 3: \" \"");
        System.out.println("  Is Palindrome: " + solver.isPalindrome(s3) + " | Expected: true\n");

        // Scenario 4: Alphanumeric false friend "0P"
        String s4 = "0P";
        System.out.println("Scenario 4: \"0P\"");
        System.out.println("  Is Palindrome: " + solver.isPalindrome(s4) + " | Expected: false");
    }
}
