package LeetCode.misc;

import LeetCode.Backtracking.Trie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by tayalka on 2/13/2017.
 */
public class WordSquares {
    private int MAX_GRID_SIZE;
    private Trie _trie;
    private Set<String> _words;
    public List<List<String>> wordSquares(String[] words) {
        MAX_GRID_SIZE = words[0].length();
        init(words);
        List<List<String>> result = compute();
        return result;
    }

    private void init(String[] inWords){
        _trie = new Trie();
        _words = new HashSet<>();
        for (String word: inWords) {
            _trie.addWord(word);
            _words.add(word);
        }
    }

    List<List<String>> compute(){
        List<List<String>> result = new ArrayList<>();
        char[][] wordSquare = new char[MAX_GRID_SIZE][MAX_GRID_SIZE];
        for(String word : _words){
            addWordAt(wordSquare,word,0);
            result.addAll(getWordSquare(wordSquare,1));
        }
        return result;
    }

    private List<List<String>> getWordSquare(char[][] inWordSquare, int index){
        List<List<String>> result = new ArrayList<>();
        if(index == inWordSquare.length){
            List<String> subList = new ArrayList<>();
            for(char[] words : inWordSquare){
                subList.add(String.valueOf(words));
            }
            result.add(subList);
            return result;
        }
        String prefix = String.valueOf(inWordSquare[index]).substring(0,index);
        List<String> possibility = _trie.findWordWithPrefix(prefix);
        for(String nextWord : possibility){
            addWordAt(inWordSquare,nextWord,index);
            result.addAll(getWordSquare(inWordSquare,index+1));
        }
        return result;
    }

    private void addWordAt(char[][] inWordSquare, String inWord, int index){
        int i = 0;
        for (char c : inWord.toCharArray()) {
            inWordSquare[i][index] = c;
            i++;
        }
    }

    public static void main(String[] args) {
        String[] words = new String[]{"abaa", "aaab", "baaa", "aaba"};
        WordSquares ws = new WordSquares();
        System.out.println(ws.wordSquares(words));
    }
}
