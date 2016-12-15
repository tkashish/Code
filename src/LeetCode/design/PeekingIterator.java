package LeetCode.design;

import java.util.*;

/**
 * Created by kashishtayal on 11/29/16.
 */
public class PeekingIterator implements Iterator<Integer>{

    private Iterator<Integer> iter;
    private Integer peek = null;
    public PeekingIterator(Iterator<Integer> iterator) {
        iter = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if(peek != null){
            return peek;
        }
        if(iter.hasNext()) {
            peek = iter.next();
            return peek;
        }
        return null;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if(peek != null){
            int temp = peek;
            peek = null;
            return temp;
        }
        if(iter.hasNext()) {
            return iter.next();
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        if(peek != null) return true;
        return iter.hasNext();
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        PeekingIterator peekingIterator = new PeekingIterator(list.iterator());
        System.out.println(peekingIterator.next() == 1);
        System.out.println(peekingIterator.peek() == 2);
        System.out.println(peekingIterator.next() == 2);
        System.out.println(peekingIterator.next() == 3);
        System.out.println(peekingIterator.peek() == 3);
    }
}
