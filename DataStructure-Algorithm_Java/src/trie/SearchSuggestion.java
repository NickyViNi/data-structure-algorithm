package trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SearchSuggestion {
    //1268 medium -> Search Suggestions System
    class TrieNode {
        TrieNode[] children;
        List<String> words;
        TrieNode() {
            this.children = new TrieNode[26];
            this.words = new ArrayList<>();
        }
    }
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> result = new ArrayList<>();
        Arrays.sort(products);

        TrieNode root = new TrieNode();
        for (String product : products) {
            TrieNode node = root;
            for (char ch : product.toCharArray()) {
                int idx = ch - 'a';
                if (node.children[idx] == null) {
                    node.children[idx] = new TrieNode();
                }
                node = node.children[idx];
                if (node.words.size() < 3) node.words.add(product); //??
            }
        }

        TrieNode node = root;
        for (int i = 0; i < searchWord.length(); i++) {
            node = node.children[searchWord.charAt(i) - 'a'];
            if (node != null) {
                result.add(node.words);
            } else {
                for (int j = i; j < searchWord.length(); j++) {
                    result.add(Collections.EMPTY_LIST);
                }
                break;
            }
        }

        return result;
    }
}
