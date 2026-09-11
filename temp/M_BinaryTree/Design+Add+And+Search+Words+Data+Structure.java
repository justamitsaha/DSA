class TrieNode {
    private boolean isEnd;
    private TrieNode[] children;
    
    public TrieNode() {
        this.isEnd = false;
        this.children = new TrieNode[26];
    }
    
    public void addCharacter(char ch) {
        this.children[ch - 'a'] = new TrieNode();
    }
    
    public TrieNode getCharacter(char ch) {
        return this.children[ch - 'a'];
    }
    
    public boolean getIsEnd() {
        return this.isEnd;
    }
    
    public void setIsEnd(boolean end) {
        this.isEnd = end;
    }
    
    public TrieNode[] getCharacters() {
        return this.children;
    }
}

class WordDictionary {

    private TrieNode root;
    
    public WordDictionary() {
        root = new TrieNode();
    }
    
    public void addWord(String word) {
        TrieNode current = root;
        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (current.getCharacter(ch) == null) {
                current.addCharacter(ch);
            }
            current = current.getCharacter(ch);
        }
        current.setIsEnd(true);
    }
    
    public boolean search(String word) {
        System.out.println("Searching for word: " + word);
        return searchWord(word, root, 0);
    }
    
    private boolean searchWord(String word, TrieNode node, int index) {
        if (index == word.length()) {
            return true;
        }
        for(int i = index; i < word.length(); i++) {
            if (word.charAt(i) == '.') {
                for(TrieNode child: node.getCharacters()) {
                    if (child != null) {
                        System.out.println("Trying out " + word.charAt(index));
                        if (searchWord(word, child, index + 1)) {
                            return true;
                        }
                    }
                }
                return false;
            }
            else {
                System.out.println(word.charAt(i));
                System.out.println(node);
                if (node.getCharacter(word.charAt(i)) == null) {
                    return false;
                }
                else {
                    node = node.getCharacter(word.charAt(i));
                }
            }
        }
        return node.getIsEnd();
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
