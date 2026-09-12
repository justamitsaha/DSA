package leetcode.M_BinaryTree;

/**
 * LeetCode 208: Implement Trie (Prefix Tree)
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/implement-trie-prefix-tree/
 *
 * Algorithm:
 * - 26-ary Prefix Tree (Trie)
 *
 * Concepts:
 * - A Trie is an efficient information-retrieval tree data structure.
 * - Each node contains:
 *   1. An array of 26 child references (`TrieNode[] children`), one for each lowercase English letter ('a' through 'z').
 *   2. A boolean flag (`isEndOfWord`) marking whether a complete word terminates at this node.
 * - Operations:
 *   - `insert(word)`: Traverse character by character, allocating child nodes on-demand. Mark the terminal node `isEndOfWord = true`.
 *   - `search(word)`: Traverse character by character. Return true iff all characters exist and terminal node has `isEndOfWord == true`.
 *   - `startsWith(prefix)`: Traverse character by character. Return true iff all prefix characters exist in the path (independent of `isEndOfWord`).
 *
 * Complexity:
 * - Time Complexity:
 *   - insert:     O(L) - Where L is the length of the word.
 *   - search:     O(L) - Where L is the length of the word.
 *   - startsWith: O(P) - Where P is the length of the prefix.
 * - Space Complexity: O(total number of characters inserted * 26).
 */
public class LC0208_ImplementTriePrefixTree {

    private static class TrieNode {
        private final TrieNode[] children = new TrieNode[26];
        private boolean isEndOfWord = false;
    }

    private final TrieNode root;

    public LC0208_ImplementTriePrefixTree() {
        root = new TrieNode();
    }

    public void insert(String word) {
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
        TrieNode node = findNode(word);
        return node != null && node.isEndOfWord;
    }

    public boolean startsWith(String prefix) {
        return findNode(prefix) != null;
    }

    private TrieNode findNode(String str) {
        if (str == null) {
            return null;
        }

        TrieNode current = root;
        for (int i = 0; i < str.length(); i++) {
            int idx = str.charAt(i) - 'a';
            if (current.children[idx] == null) {
                return null;
            }
            current = current.children[idx];
        }
        return current;
    }

    public static void main(String[] args) {
        LC0208_ImplementTriePrefixTree trie = new LC0208_ImplementTriePrefixTree();

        trie.insert("apple");
        System.out.println("search(\"apple\"): " + trie.search("apple") + " (Expected: true)");
        System.out.println("search(\"app\"): " + trie.search("app") + " (Expected: false)");
        System.out.println("startsWith(\"app\"): " + trie.startsWith("app") + " (Expected: true)");

        trie.insert("app");
        System.out.println("search(\"app\") after insert: " + trie.search("app") + " (Expected: true)");
    }
}
