package LeetCode.misc;

/**
 * Created by kashishtayal on 1/15/17.
 */
public class SentenceScreenFitting {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int len = sentence.length;
        int result = 0;
        int total = 0;
        for(String s : sentence){
            total += s.length()+1;
        }
        int numOfCompleteLineFitting = cols/total;
        int numSpaceLeft = cols % total;
        result += rows*numOfCompleteLineFitting;
        total--;
        if(numSpaceLeft/total != 0){
            return result+rows;
        }
        int nextWordIndex = 0;
        for(int i = 0; i < rows; i++){
            int effectiveCols = numSpaceLeft;
            for(int j = nextWordIndex; effectiveCols > 0; j++){
                nextWordIndex = j;
                if(sentence[j].length()<=effectiveCols){
                    effectiveCols -= (sentence[j].length()+1);
                    nextWordIndex = j+1;
                }else{
                    nextWordIndex = j;
                    break;
                }
                if(j == len-1){
                    result++;
                    j = -1;
                    nextWordIndex = 0;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SentenceScreenFitting fitting = new SentenceScreenFitting();
        {
            String[] sentense = new String[]{
                    "hello", "world"
            };
            System.out.println(fitting.wordsTyping(sentense, 2, 8));
        }
        {
            String[] sentense = new String[]{
                    "a", "bcd", "e"
            };
            System.out.println(fitting.wordsTyping(sentense, 3, 14));
        }
        {
            String[] sentense = new String[]{
                    "i", "had", "apple","pie"
            };
            System.out.println(fitting.wordsTyping(sentense, 4, 5));
        }
        {
            String[] sentense = new String[]{
                    "i"
            };
            System.out.println(fitting.wordsTyping(sentense, 2, 3));
        }
    }
}
