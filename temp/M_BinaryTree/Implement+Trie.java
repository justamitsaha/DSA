class TrieNode {
    private boolean isEnd;
    private TrieNode[] children;
    
    public TrieNode() {
        isEnd = false;
        children = new TrieNode[26];
    }
    
    public boolean containsChar(char ch) {
        return children[ch - 'a'] != null;
    }
    
    public void addChar(char ch) {
        children[ch - 'a'] = new TrieNode();
    }
    
    public TrieNode getChild(char ch) {
        return children[ch - 'a'];
    }
    
    public void isEnd() {
        this.isEnd = true;
    }
    
    public boolean isEndingNode() {
        return this.isEnd;
    }
}

class Trie {
   
    public TrieNode root;
    
    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode node = root;
        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.containsChar(ch)) {
                node.addChar(ch);
            }
            node = node.getChild(ch);
        }
        node.isEnd();
    }
    
    public boolean search(String word) {
        TrieNode node = root;
        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.containsChar(ch)) {
                return false;
            }
            else {
                node = node.getChild(ch);
            }
        }
        if (node.isEndingNode()) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public boolean startsWith(String word) {
        TrieNode node = root;
        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.containsChar(ch)) {
                return false;
            }
            else {
                node = node.getChild(ch);
            }
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
