package LeetCode.design;

import java.util.*;

/**
 * Created by kashishtayal on 12/3/16.
 */
public class NestedIterator implements Iterator<Integer> {
    Iterator<NestedInteger> iterator;
    NestedIterator nextIterator = null;
    Integer currInteger = null;
    public NestedIterator(List<NestedInteger> nestedList){
        iterator = nestedList.iterator();
    }
    @Override
    public boolean hasNext() {
        if((currInteger == null && nextIterator == null) || (currInteger == null && nextIterator != null && !nextIterator.hasNext())){
            nextIterator = null;
            while(iterator.hasNext()){
                NestedInteger next = iterator.next();
                if(next != null){
                    if(next.isInteger()){
                        currInteger = next.getInteger();
                        break;
                    }else{
                        NestedIterator nI = new NestedIterator(next.getList());
                        if(nI.hasNext()){
                            nextIterator = nI;
                            break;
                        }
                    }
                }
            }
            return currInteger != null || nextIterator != null;
        }else{
            return true;
        }
    }

    @Override
    public Integer next() {
        if(hasNext()){
            if(currInteger != null){
                int result = currInteger;
                currInteger = null;
                return result;
            }
            else{
                return nextIterator.next();
            }
        }
        throw new NoSuchElementException();
    }
}
interface NestedInteger {
    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();
    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();
    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}