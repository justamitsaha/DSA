package leetcode.L_RecursionBacktracking;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 212: Word Search II
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/word-search-ii/
 *
 * Algorithm:
 * - Prefix Tree (Trie) + 2D Board DFS Backtracking with In-Place Visited Masking
 *
 * Concepts:
 * - Given an m x n board of characters and a list of strings `words`, return all words on the board.
 * - Why Word Search I fails here:
 *   - Running standard DFS for each of the W words takes O(W * m * n * 4^L), resulting in Time Limit Exceeded.
 * - Optimal Trie Approach:
 *   1. Build a Trie containing all words in `words`.
 *   2. Store the matched word directly in the end TrieNode (`node.word = word`). This avoids string concatenation during DFS!
 *   3. Iterate through each cell `(r, c)` of the board and trigger DFS traversal guided by the Trie.
 *   4. In DFS:
 *      - If the current character is not a child of the current TrieNode, prune this branch immediately!
 *      - If `child.word != null`, a word is found: add it to results and set `child.word = null` to prevent duplicate additions.
 *      - Temporarily mask `board[r][c] = '#'` to prevent reusing the same cell.
 *      - Recurse in 4 adjacent directions.
 *      - Backtrack (restore `board[r][c] = origChar`).
 *
 * Complexity:
 * - Time Complexity:  O(M * (4 * 3^(L - 1))) - Where M is total cells (m * n) and L is maximum word length.
 *                     Building Trie takes O(sum of word lengths).
 * - Space Complexity: O(N)                   - Where N is the total number of characters across all words in the Trie.
 */
public class LC0212_WordSearchII {

    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null; // Stores the complete word when a word ends at this node
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        if (board == null || board.length == 0 || words == null || words.length == 0) {
            return result;
        }

        // Step 1: Construct the Trie
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                int idx = ch - 'a';
                if (node.children[idx] == null) {
                    node.children[idx] = new TrieNode();
                }
                node = node.children[idx];
            }
            node.word = word; // Store the complete word at the leaf node
        }

        // Step 2: DFS Backtracking starting from each cell
        int m = board.length;
        int n = board[0].length;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                int idx = board[r][c] - 'a';
                if (root.children[idx] != null) {
                    dfs(board, r, c, root, result);
                }
            }
        }

        return result;
    }

    private void dfs(char[][] board, int r, int c, TrieNode parent, List<String> result) {
        char ch = board[r][c];
        int idx = ch - 'a';
        TrieNode currNode = parent.children[idx];

        // Check if current prefix is a complete word
        if (currNode.word != null) {
            result.add(currNode.word);
            currNode.word = null; // De-duplicate: avoid adding the same word multiple times
        }

        // Mark cell as visited
        board[r][c] = '#';

        // 4 directions: Up, Down, Left, Right
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr >= 0 && nr < board.length && nc >= 0 && nc < board[0].length) {
                char nextChar = board[nr][nc];
                if (nextChar != '#' && currNode.children[nextChar - 'a'] != null) {
                    dfs(board, nr, nc, currNode, result);
                }
            }
        }

        // Backtrack: restore cell
        board[r][c] = ch;
    }

    public static void main(String[] args) {
        LC0212_WordSearchII solver = new LC0212_WordSearchII();

        // Scenario 1: Standard dictionary search
        char[][] board1 = {
            {'o', 'a', 'a', 'n'},
            {'e', 't', 'a', 'e'},
            {'i', 'h', 'k', 'r'},
            {'i', 'f', 'l', 'v'}
        };
        String[] words1 = {"oath", "pea", "eat", "rain"};
        System.out.println("Scenario 1: " + solver.findWords(board1, words1));
        // Expected: [oath, eat]

        // Scenario 2: No words found
        char[][] board2 = {
            {'a', 'b'},
            {'c', 'd'}
        };
        String[] words2 = {"abcd"};
        System.out.println("Scenario 2: " + solver.findWords(board2, words2));
        // Expected: []

        // Scenario 3: Single cell
        char[][] board3 = {{'a'}};
        String[] words3 = {"a"};
        System.out.println("Scenario 3: " + solver.findWords(board3, words3));
        // Expected: [a]
    }
}
