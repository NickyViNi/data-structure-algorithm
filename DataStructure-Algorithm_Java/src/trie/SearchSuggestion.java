package trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SearchSuggestion {
    //1268 medium -> Search Suggestions System
    //solution1
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

    //Solution2: Two Pointers, Time Complexity: O(NlogN + M*N)
    public List<List<String>> suggestedProducts1(String[] products, String searchWord) {
        int left = 0, right = products.length - 1;
        List<List<String>> result = new ArrayList<>();
        Arrays.sort(products);
        for (int i = 0; i < searchWord.length(); i++) {
            while (left <= right && (i >= products[left].length() ||
            products[left].charAt(i) != searchWord.charAt(i))) {
                left++;
            }

            while (left <= right && (i >= products[right].length() ||
            products[right].charAt(i) != searchWord.charAt(i))) {
                right--;
            }

            List<String> suggested = new ArrayList<>();
            for (int j = left; j <= Math.min(right, left + 2); j++) {
                suggested.add(products[j]);
            }
            result.add(suggested);
        }
        return result;
    }

    //Solution3: StringBuilder
    public List<List<String>> suggestedProducts2(String[] products, String searchWord) {
        Arrays.sort(products);
        List<List<String>> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < searchWord.length(); i++) {
            sb.append(searchWord.charAt(i));
            String typeWord = sb.toString();
            List<String> suggested = new ArrayList<>();
            for (int j = 0; j < products.length; j++) {
                if (products[j].startsWith(typeWord)) {
                    suggested.add(products[j]);
                }
                if (suggested.size() >= 3) break;
            }
            result.add(suggested);
        }
        return result;
    }
}
