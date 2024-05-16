package trie;

public class WordDictionary {
    private class TrieNode {
        TrieNode[] children;
        String word;
        boolean isWord;
        public TrieNode() {
            children = new TrieNode[26];
            word = "";
            isWord = false;
        }
    }

    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }
        node.isWord = true;
        node.word = word;
    }

    public boolean search(String word) {
        TrieNode node = root;
        return findWord(node, word, 0);
    }

    private boolean findWord(TrieNode node, String word, int count) {
        if (node == null) return false;
        if (count == word.length()) return node.isWord;
        if (word.charAt(count) == '.') {
            for (TrieNode child : node.children) {
                if (findWord(child, word, count + 1)) return true;
            }
        } else {
            int idx = word.charAt(count) - 'a';
            TrieNode temp = node.children[idx];
            return findWord(temp, word, count + 1);
        }
        return false;
    }
}
