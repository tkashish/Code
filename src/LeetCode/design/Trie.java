package LeetCode.design;

/**
 * Created by kashishtayal on 12/1/16.
 */
public class Trie {
    private TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode node = root;
        for(char c : word.toCharArray()){
            node = node.addChar(c);
        }
        node.setEndForAnyWord();
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode node = root;
        for(char c : word.toCharArray()){
            node = node.containsChar(c);
            if(node == null) return false;
        }
        if(node.isEndForAnyWord()) return true;
        return false;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for(char c : prefix.toCharArray()){
            node = node.containsChar(c);
            if(node == null) return false;
        }
//        if(node.isEndForAnyWord()) return true;
        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("jack");
        trie.insert("jam");
        trie.insert("jill");
        System.out.println(trie.search("jam"));
        System.out.println(trie.startsWith("ja"));
        System.out.println(trie.startsWith("jam"));
        System.out.println(trie.search("jake"));
        System.out.println(trie.search("jame"));
        System.out.println(trie.search("ja"));
    }
}
class TrieNode {
    private TrieNode[] characters;
    private boolean isEndForAnyWord = false;
    public TrieNode() {
        characters = new TrieNode[26];
    }
    public TrieNode addChar(char inC){
        int i = inC - 97;
        if(characters[i] == null){
            characters[i] = new TrieNode();
        }
        return characters[i];
    }
    public TrieNode containsChar(char inC){
        return characters[inC-97];
    }
    public void setEndForAnyWord(){
        isEndForAnyWord = true;
    }
    public boolean isEndForAnyWord(){
        return isEndForAnyWord;
    }
}
