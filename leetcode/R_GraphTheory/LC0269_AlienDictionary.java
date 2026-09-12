package leetcode.R_GraphTheory;

import java.util.*;

/**
 * LeetCode 269: Alien Dictionary
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/alien-dictionary/
 *
 * Algorithm:
 * - Topological Sort via Kahn's Algorithm (BFS)
 *
 * Concepts:
 * - Given a list of words from an alien language whose alphabet characters are sorted lexicographically:
 *   Derive the order of letters in this language.
 * - Rules for Graph Construction:
 *   1. Extract all unique characters across all words (initialize indegree to 0).
 *   2. Compare adjacent word pairs (words[i] and words[i + 1]):
 *      - Prefix check: If words[i] is longer than words[i + 1] and starts with words[i + 1]
 *        (e.g., "apple" before "app"), the dictionary is invalid! Return "".
 *      - Find the first character difference between words[i] and words[i + 1]:
 *        If words[i].charAt(j) != words[i + 1].charAt(j), there is a directed dependency:
 *        `c1 -> c2`. Add edge and increment `indegree(c2)` (if not already added).
 *        Break immediately, because only the first mismatch dictates ordering between these two words.
 *   3. Kahn's Algorithm:
 *      - Enqueue all characters with indegree == 0.
 *      - Pop node, append to result, decrement neighbor indegrees, enqueue when indegree reaches 0.
 *   4. Cycle Check:
 *      - If the topological order contains fewer characters than the total unique characters,
 *        there is a dependency cycle (impossible ordering), so return "".
 *
 * Complexity:
 * - Time Complexity:  O(C) where C is the total number of characters across all words.
 *   Building the graph compares adjacent words, and Kahn's algorithm runs in O(V + E) where V <= 26.
 * - Space Complexity: O(1) or O(U + min(U^2, N)) where U <= 26 is the number of unique alien letters.
 */
public class LC0269_AlienDictionary {

    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }

        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();

        // Step 1: Initialize all unique characters in the dictionary
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
                indegree.putIfAbsent(c, 0);
            }
        }

        // Step 2: Build directed edges from adjacent word comparisons
        for (int i = 0; i < words.length - 1; i++) {
            String current = words[i];
            String next = words[i + 1];

            // Invalid prefix condition: "apple" cannot appear before "app"
            if (current.length() > next.length() && current.startsWith(next)) {
                return "";
            }

            int minLen = Math.min(current.length(), next.length());
            for (int j = 0; j < minLen; j++) {
                char c1 = current.charAt(j);
                char c2 = next.charAt(j);

                if (c1 != c2) {
                    if (!graph.get(c1).contains(c2)) {
                        graph.get(c1).add(c2);
                        indegree.put(c2, indegree.get(c2) + 1);
                    }
                    break; // Only the first differing character establishes order
                }
            }
        }

        // Step 3: Kahn's BFS queue
        Queue<Character> queue = new LinkedList<>();
        for (char c : indegree.keySet()) {
            if (indegree.get(c) == 0) {
                queue.offer(c);
            }
        }

        StringBuilder order = new StringBuilder();
        while (!queue.isEmpty()) {
            char curr = queue.poll();
            order.append(curr);

            for (char neighbor : graph.get(curr)) {
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                if (indegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // Step 4: If result length != total unique characters, a cycle exists
        if (order.length() != indegree.size()) {
            return "";
        }

        return order.toString();
    }

    public static void main(String[] args) {
        LC0269_AlienDictionary solver = new LC0269_AlienDictionary();

        // Scenario 1: Standard valid dictionary
        String[] words1 = {"wrt", "wrf", "er", "ett", "rftt"};
        System.out.println("Alien Order 1: " + solver.alienOrder(words1)); // Expected: "wertf"

        // Scenario 2: Cycle detection (z < x and x < z)
        String[] words2 = {"z", "x", "z"};
        System.out.println("Alien Order 2 (cycle): \"" + solver.alienOrder(words2) + "\""); // Expected: ""

        // Scenario 3: Invalid prefix ("abc" before "ab")
        String[] words3 = {"abc", "ab"};
        System.out.println("Alien Order 3 (invalid prefix): \"" + solver.alienOrder(words3) + "\""); // Expected: ""

        // Scenario 4: Single word
        String[] words4 = {"z"};
        System.out.println("Alien Order 4: " + solver.alienOrder(words4)); // Expected: "z"
    }
}
