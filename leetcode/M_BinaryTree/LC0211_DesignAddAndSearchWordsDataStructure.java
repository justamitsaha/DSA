package leetcode.M_BinaryTree;

/**
 * LeetCode 211: Design Add and Search Words Data Structure
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/design-add-and-search-words-data-structure/
 *
 * Algorithm:
 * - Trie (Prefix Tree) with Recursive Backtracking DFS for Wildcard ('.') Matching
 *
 * Concepts:
 * - Design a data structure that supports adding words and searching words with wildcard '.' character matching any letter.
 * - Structure:
 *   - Trie where each node has `TrieNode[26] children` and `boolean isEndOfWord`.
 * - Add Word:
 *   - Standard Trie insertion in O(L) time.
 * - Search:
 *   - When character is a regular letter ('a' - 'z'), advance directly down `children[ch - 'a']`.
 *   - When character is a wildcard ('.'), branch recursively across all non-null children of the current node.
 *     If any branch returns true, the wildcard pattern matches!
 *
 * Complexity:
 * - Time Complexity:
 *   - `addWord`: O(L) - Where L is the word length.
 *   - `search`:  O(L) for exact words without dots; O(26^D * L) worst case for all-dots ("....") where D is number of dots.
 * - Space Complexity: O(total characters inserted * 26) for the Trie nodes.
 */
public class LC0211_DesignAddAndSearchWordsDataStructure {

    private static class TrieNode {
        private final TrieNode[] children = new TrieNode[26];
        private boolean isEndOfWord = false;
    }

    private final TrieNode root;

    public LC0211_DesignAddAndSearchWordsDataStructure() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        if (word == null) {
            return;
        }

        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (current.children[idx] == null) {
                current.children[idx] = new TrieNode();
            }
            current = current.children[idx];
        }
        current.isEndOfWord = true;
    }

    public boolean search(String word) {
        return searchHelper(word, 0, root);
    }

    private boolean searchHelper(String word, int index, TrieNode node) {
        if (node == null) {
            return false;
        }

        if (index == word.length()) {
            return node.isEndOfWord;
        }

        char ch = word.charAt(index);

        if (ch == '.') {
            // Wildcard: explore all existing child branches
            for (TrieNode child : node.children) {
                if (child != null && searchHelper(word, index + 1, child)) {
                    return true;
                }
            }
            return false;
        } else {
            // Direct character lookup
            int idx = ch - 'a';
            return searchHelper(word, index + 1, node.children[idx]);
        }
    }

    public static void main(String[] args) {
        LC0211_DesignAddAndSearchWordsDataStructure wordDictionary =
            new LC0211_DesignAddAndSearchWordsDataStructure();

        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");

        System.out.println("search(\"pad\"): " + wordDictionary.search("pad") + " (Expected: false)");
        System.out.println("search(\"bad\"): " + wordDictionary.search("bad") + " (Expected: true)");
        System.out.println("search(\".ad\"): " + wordDictionary.search(".ad") + " (Expected: true)");
        System.out.println("search(\"b..\"): " + wordDictionary.search("b..") + " (Expected: true)");
        System.out.println("search(\"....\"): " + wordDictionary.search("....") + " (Expected: false)");
    }
}
