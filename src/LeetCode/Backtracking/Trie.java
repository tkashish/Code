package LeetCode.Backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kashishtayal on 10/22/16.
 */
public class Trie {
    private static final int START_INDEX_OFFSET = 97;
    Node[] _firstCharacters;

    public Trie(){
        _firstCharacters = new Node[26];
    }

    public void addWord(String inWord){
        if(!contains(inWord)){
            char[] chars = inWord.toCharArray();
            int len = chars.length;
            Node[] array = _firstCharacters;
            Node currNode = null;
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < len; i++){
                sb.append(chars[i]);
                int currIndex = (int)chars[i] - START_INDEX_OFFSET;
                currNode = array[currIndex];
                if(currNode == null) currNode = new Node(chars[i]);
                currNode.setPrefix(sb.toString());
                currNode.addWord(inWord);
                array[currIndex] = currNode;
                array = currNode.getNextChar();
            }
            currNode.setEnd();
        }
    }
    public boolean contains(String inWord){
        char[] chars = inWord.toCharArray();
        Node[] array = _firstCharacters;
        Node currNode = null;
        for(char c : chars){
            int index = (int) c - START_INDEX_OFFSET;
            currNode = array[index];
            if(currNode == null) return false;
            array = currNode.getNextChar();
        }
        return currNode.isEnd();
    }

    public List<String> findWordWithPrefix(String inPrefx){
        Node currNode = null;
        Node[] array = _firstCharacters;
        for(char c : inPrefx.toCharArray()){
            int index = (int)c-START_INDEX_OFFSET;
            currNode = array[index];
            if(currNode == null) return new ArrayList<>();
            array = currNode.getNextChar();
        }
        return currNode.getWordList();
    }

    class Node{
        private char _character;
        private Node[] _nextCharacters;
        private String _prefix;
        private boolean _isEnd;
        private List<String> _wordList;
        public Node(char inCharacter){
            _character = inCharacter;
            _nextCharacters = new Node[26];
            _isEnd = false;
            _wordList = new ArrayList<>();
        }
        public void addWord(String inWord){
            _wordList.add(inWord);
        }

        public List<String> getWordList() {
            return _wordList;
        }

        public Node[] getNextChar(){
            return _nextCharacters;
        }
        public boolean isEnd(){
            return _isEnd;
        }
        public String getPrefix(){
            return _prefix;
        }
        public void setPrefix(String inPrefix){
            _prefix = inPrefix;
        }
        public void setEnd(){
            _isEnd = true;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.addWord("tree");
        trie.addWord("trie");
        trie.addWord("algo");
        trie.addWord("assoc");
        trie.addWord("all");
        trie.addWord("also");
        System.out.println(trie.findWordWithPrefix("al"));
        System.out.println(trie.findWordWithPrefix("alg"));
        System.out.println(trie.findWordWithPrefix("algos"));
        System.out.println(trie.contains("trie"));
        System.out.println(trie.contains("tri"));

    }

}
