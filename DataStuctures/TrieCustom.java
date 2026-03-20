
import java.util.HashMap;
import java.util.Map;

/**
 * Custom Trie Implementation for Prefix-based Search.
 */
public class TrieCustom {

    private static class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEndOfWord;

        TrieNode() {
            children = new HashMap<>();
            isEndOfWord = false;
        }
    }

    private final TrieNode root;

    public TrieCustom() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            current.children.putIfAbsent(ch, new TrieNode());
            current = current.children.get(ch);
        }
        current.isEndOfWord = true;
    }

    public boolean search(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            TrieNode node = current.children.get(ch);
            if (node == null) return false;
            current = node;
        }
        return current.isEndOfWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode current = root;
        for (char ch : prefix.toCharArray()) {
            TrieNode node = current.children.get(ch);
            if (node == null) return false;
            current = node;
        }
        return true;
    }

    public static void main(String[] args) {
        TrieCustom trie = new TrieCustom();
        trie.insert("apple");
        System.out.println("Search apple: " + trie.search("apple"));
        System.out.println("Search app: " + trie.search("app"));
        System.out.println("StartsWith app: " + trie.startsWith("app"));
        trie.insert("app");
        System.out.println("Search app (after insert): " + trie.search("app"));
    }
}
