package leetcode.A_Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 271: Encode and Decode Strings
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/encode-and-decode-strings/
 *
 * Algorithm:
 * - Delimiter with Escape Character (Escaping Mechanism)
 * - Alternative: Chunked / Length-Prefix Encoding (e.g., "4#neet4#code")
 *
 * Concepts:
 * - A stateless algorithm must convert a list of arbitrary strings into a single string,
 *   and recover the exact original list without ambiguity.
 * - Because the input strings can contain ANY character (including spaces, delimiters, commas, and escapes):
 *     1. We choose ';' as the delimiter between strings.
 *     2. We choose '/' as the escape character.
 *     3. When encoding:
 *        - Every original '/' is escaped as "//"
 *        - Every original ';' is escaped as "/;"
 *        - A trailing ';' is appended to mark the end of each word.
 *     4. When decoding:
 *        - When '/' is encountered, the very next character is escaped and appended literally.
 *        - When ';' is encountered, it marks the end of the current word.
 *
 * Complexity:
 * - Time Complexity:  O(N) for both encode and decode, where N is the total number of characters across all strings.
 * - Space Complexity: O(N) to construct the encoded string and the decoded list.
 */
public class LC0271_EncodeAndDecodeStrings {

    /**
     * Encodes a list of strings to a single string.
     */
    public String encode(List<String> strs) {
        if (strs == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (ch == '/') {
                    sb.append("//"); // Escape the escape character
                } else if (ch == ';') {
                    sb.append("/;"); // Escape the delimiter
                } else {
                    sb.append(ch);
                }
            }
            sb.append(";"); // Delimiter marking end of the current string
        }

        return sb.toString();
    }

    /**
     * Decodes a single string to a list of strings.
     */
    public List<String> decode(String s) {
        List<String> decodedStrings = new ArrayList<>();
        if (s == null || s.isEmpty()) {
            return decodedStrings;
        }

        StringBuilder currentString = new StringBuilder();
        int i = 0;

        while (i < s.length()) {
            char ch = s.charAt(i);

            if (ch == '/') {
                // Escaped character: take the next character literally
                currentString.append(s.charAt(i + 1));
                i += 2;
            } else if (ch != ';') {
                currentString.append(ch);
                i++;
            } else {
                // Delimiter reached: save current string and reset
                decodedStrings.add(currentString.toString());
                currentString.setLength(0);
                i++;
            }
        }

        return decodedStrings;
    }

    public static void main(String[] args) {
        LC0271_EncodeAndDecodeStrings codec = new LC0271_EncodeAndDecodeStrings();

        // Scenario 1: Standard list of words
        List<String> input1 = Arrays.asList("neet", "code", "love", "you");
        String encoded1 = codec.encode(input1);
        List<String> decoded1 = codec.decode(encoded1);
        System.out.println("Scenario 1 - Standard Words:");
        System.out.println("  Original: " + input1);
        System.out.println("  Encoded:  " + encoded1);
        System.out.println("  Decoded:  " + decoded1);
        System.out.println("  Matches:  " + input1.equals(decoded1) + "\n");

        // Scenario 2: Strings containing delimiters and escape characters
        List<String> input2 = Arrays.asList("hello;world", "a/b/c", ";;/;;");
        String encoded2 = codec.encode(input2);
        List<String> decoded2 = codec.decode(encoded2);
        System.out.println("Scenario 2 - Special Characters (/ and ;):");
        System.out.println("  Original: " + input2);
        System.out.println("  Encoded:  " + encoded2);
        System.out.println("  Decoded:  " + decoded2);
        System.out.println("  Matches:  " + input2.equals(decoded2) + "\n");

        // Scenario 3: List containing empty strings
        List<String> input3 = Arrays.asList("", "hello", "", "");
        String encoded3 = codec.encode(input3);
        List<String> decoded3 = codec.decode(encoded3);
        System.out.println("Scenario 3 - Empty Strings:");
        System.out.println("  Original: " + input3);
        System.out.println("  Encoded:  " + encoded3);
        System.out.println("  Decoded:  " + decoded3);
        System.out.println("  Matches:  " + input3.equals(decoded3));
    }
}
