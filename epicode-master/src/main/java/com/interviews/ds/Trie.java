package com.interviews.ds;

/**
 * With my solution I took the simple approach of giving each TrieNode a 26 element array of each possible child
 * node it may have.
 * I only gave 26 children nodes because we are only working with lowercase 'a' - 'z'.
 * If you are uncertain why I made the root of my Trie an empty character this is a standard/typical approach for
 * building out a Trie it is somewhat arbitrary what the root node is.
 * <p>
 * For insert I used the following algorithm. Loop through each character in the word being inserted check if the
 * character is a child node of the current TrieNode i.e. check if the array has a populated value in the index of
 * this character. If the current character ISN'T a child node of my current node add this character representation
 * to the corresponding index location then set current node equal to the child that was added.
 * However if the current character IS a child of the current node only set current node equal to the child. Aft
 */

class TrieNode {
    public char val;
    public boolean isWord;
    public TrieNode[] children = new TrieNode[26];

    public TrieNode() {
    }

    TrieNode(char c) {
        TrieNode node = new TrieNode();
        node.val = c;
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
        root.val = ' ';
    }

    public void insert(String word) {
        TrieNode ws = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (ws.children[c - 'a'] == null) {
                ws.children[c - 'a'] = new TrieNode(c);
            }
            ws = ws.children[c - 'a'];
        }
        ws.isWord = true;
    }

    public boolean search(String word) {
        TrieNode ws = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (ws.children[c - 'a'] == null) return false;
            ws = ws.children[c - 'a'];
        }
        return ws.isWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode ws = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (ws.children[c - 'a'] == null) return false;
            ws = ws.children[c - 'a'];
        }
        return true;
    }
}