"""
Structure:
      (root)
     /   \   \
    a     b   c
   / \     \
  n   p     a
 /     \     \
t       p     t
(ant)  (app)  (cat)
"""

class TrieNode:
    def __init__(self):
        self.children = {}
        self.is_end_of_word = False


class TrieCustom:
    """Custom Trie Implementation."""
    def __init__(self):
        self.root = TrieNode()

    def insert(self, word):
        current = self.root
        for char in word:
            if char not in current.children:
                current.children[char] = TrieNode()
            current = current.children[char]
        current.is_end_of_word = True

    def search(self, word):
        current = self.root
        for char in word:
            if char not in current.children:
                return False
            current = current.children[char]
        return current.is_end_of_word

    def starts_with(self, prefix):
        current = self.root
        for char in prefix:
            if char not in current.children:
                return False
            current = current.children[char]
        return True


if __name__ == "__main__":
    trie = TrieCustom()
    trie.insert("apple")
    print("Search apple:", trie.search("apple"))
    print("Search app:", trie.search("app"))
    print("StartsWith app:", trie.starts_with("app"))
    trie.insert("app")
    print("Search app (after insert):", trie.search("app"))
