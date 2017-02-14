package LeetCode.misc;

import de.flapdoodle.embed.process.collections.Collections;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kashishtayal on 2/13/17.
 */
public class ZigzagIterator {
    private int _nextRowIndex = 0;
    private int _nextColIndex = 0;
    private final int MAX_ROWS;
    private List<List<Integer>> _lists;
    private final int MAX_LIST_LENGTH;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        MAX_ROWS = 2;
        _lists = new ArrayList<>();
        _lists.add(v1);
        _lists.add(v2);
        MAX_LIST_LENGTH = Math.max(v1.size(),v2.size());
        if(!hasNext())moveNext();
    }

    public int next() {
        if(hasNext()) {
            int result = _lists.get(_nextRowIndex).get(_nextColIndex);
            moveToNextInteger();
            return result;
        }
        throw new IndexOutOfBoundsException("no next element exists");
    }

    public boolean hasNext() {
        if(_nextColIndex < MAX_LIST_LENGTH){
            if(_nextColIndex<_lists.get(_nextRowIndex).size()){
                return true;
            }
        }
        return false;
    }

    private void moveToNextInteger(){
        moveNext();
        while(_nextColIndex < MAX_LIST_LENGTH){
            if(_nextColIndex < _lists.get(_nextRowIndex).size()){
                return;
            }
            moveNext();
        }
    }

    private void moveNext(){
        if(_nextRowIndex == MAX_ROWS-1){
            _nextRowIndex = 0;
            _nextColIndex++;
        }else{
            _nextRowIndex++;
        }
    }

    public static void main(String[] args) {
        List<Integer> v1 = Collections.newArrayList();
        List<Integer> v2 = Collections.newArrayList(1);
        ZigzagIterator it = new ZigzagIterator(v1,v2);
        System.out.println(it.next());
        System.out.println(it.next());
    }
}
