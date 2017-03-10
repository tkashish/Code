package LeetCode.DynamicPrograming;


import java.util.*;

/**
 * Created by tayalka on 3/9/2017.
 */
public class ConcatenatedWords {
    Node[] root = new Node[26];
    public List<String> findAllConcatenatedWordsInADictI(String[] words) {
        Arrays.sort(words, Comparator.comparing(String::length));
        List<String> result = new ArrayList<>();
        for(int i = 0; i < words.length; i++){
            String currWord = words[i];
            if(currWord.length() == 0) continue;
            if(isConcatination(currWord,0)){
                result.add(currWord);
            }
            add(words[i]);
        }
        return result;
    }

    private boolean isConcatination(String inWord, int startIndex){
        if(inWord.length() == startIndex)return true;
        List<String> list = findPossiblePrefix(inWord,startIndex);
        for(String prefix : list){
            boolean isTrue = isConcatination(inWord,startIndex+prefix.length());
            if(isTrue)return true;
        }
        return false;
    }

    private void add(String inWord){
        if(inWord.length() == 0) return;
        char[] chars = inWord.toCharArray();
        Node[] curr = root;
        int index = 0;
        for(int i = 0; i < inWord.length(); i++){
            index = inWord.charAt(i)-'a';
            if(curr[index] == null){
                curr[index] = new Node(inWord.charAt(i));
            }
            if(i < inWord.length()-1)curr = curr[index].children;
        }
        curr[index].isEnd = true;
        curr[index].wordIfEnd = inWord;
    }

    private List<String> findPossiblePrefix(String inWord, int startIndex){
        List<String> possiblePrefix = new ArrayList<>();
        Node[] curr = root;
        for(int i = startIndex; i < inWord.length() && curr != null; i++){
            int index = inWord.charAt(i)-'a';
            Node currNode = curr[index];
            if(currNode == null) return possiblePrefix;
            if(currNode.isEnd)possiblePrefix.add(currNode.wordIfEnd);
            curr = currNode.children;
        }
        return possiblePrefix;
    }

    private class Node{
        Node[] children;
        char val;
        boolean isEnd;
        String wordIfEnd;
        public Node(char val) {
            this.val = val;
            children = new Node[26];
        }
    }

    public static void main(String[] args) {
        ConcatenatedWords conc = new ConcatenatedWords();
        System.out.println(conc.findAllConcatenatedWordsInADict(new String[]{""}));
    }

    private List<String> findAllConcatenatedWordsInADict(String[] inWords){
        Arrays.sort(inWords, Comparator.comparing(String::length));
        Set<String> words = new HashSet<>();
        for (String s : inWords)words.add(s);
        List<String> result = new ArrayList<>();
        for(String word : inWords){
            if(check(word,words))result.add(word);
        }
        return result;
    }
    private boolean check(String inWord, Set<String> setOfWords){
        if(inWord.length() == 0)return false;
        int[] dp = new int[inWord.length()];
        for(int i = 1; i <= inWord.length(); i++){
            for(int j = 0; j <= i; j++){
                if(j-1 > -1 && dp[j-1] == 0)continue;
                String subString = inWord.substring(j,i);
                if(setOfWords.contains(subString) && subString != inWord)dp[i-1] = 1;
            }
        }
        return dp[inWord.length()-1]==1;
    }
}
