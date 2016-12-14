package Tree;

import java.util.*;

/**
 * Created by kashishtayal on 12/13/16.
 */
public class BTree<T extends Comparable<T>> implements Tree<T> {
    Node _root = new Node();
    private final int MINIMUM;
    private final int MAXIMUM;
    public BTree(){
        MINIMUM = 2;
        MAXIMUM = 2*MINIMUM;
    }
    public BTree(int inMin){
        MINIMUM = inMin;
        MAXIMUM = 2*MINIMUM;
    }

    @Override
    public void add(T inValue) {
        _root.add(inValue);
        if(_root._prevLevel != null){
            _root = _root._prevLevel;
        }
    }

    @Override
    public boolean contains(T inValue) {
        return false;
    }

    @Override
    public boolean remove(T inValue) {
        return false;
    }

    private class Node{
        TreeMap<T,Entry> sortedMap;
        Entry _parentLeft;
        Entry _parentRight;
        Node _prevLevel;
        T lowerKey;
        T higherKey;
        Node(){
            sortedMap = new TreeMap<>();
        }
        public void add(T inValue){
            boolean noChild = findRange(inValue);
            if(noChild){
                Entry entry = new Entry(inValue);
                addNoChild(entry);
            }else{
                addWithChild(inValue);
            }
        }
        private boolean findRange(T inValue){
            boolean noChild = true;
            Set<Map.Entry<T,Entry>> set = sortedMap.entrySet();
            for(Map.Entry<T,Entry> entry  : set){
                T key = entry.getKey();
                Entry value = entry.getValue();
                if(noChild == true && value.nextLevelLeft != null){
                    noChild = false;
                }
                if(key.compareTo(inValue) < 0 ){
                    lowerKey = key;
                }else if(key.compareTo(inValue) > 0){
                    higherKey = key;
                    break;
                }
            }
            return noChild;
        }
        private void addNoChild(Entry entry){
            int size = sortedMap.size();
            sortedMap.put(entry._value,entry);
            if(size == MAXIMUM){
                restructure();
            }
        }
        private void restructure(){
            Node newNode = new Node();
            TreeMap<T,Entry> newMap = new TreeMap<>();
            NavigableSet<T> keySet = (NavigableSet<T>) sortedMap.keySet();
            Iterator<T> it = keySet.descendingIterator();
            int count = 0;
            while(it.hasNext()){
                T next = it.next();
                newMap.put(next,sortedMap.get(next));
                count++;
                if(count == MINIMUM){
                    break;
                }
            }
            keySet.removeAll(newMap.keySet());
            T centerKey = keySet.last();
            Entry center = sortedMap.get(centerKey);
            keySet.remove(centerKey);
            newNode.sortedMap = newMap;
            center.nextLevelLeft = this;
            center.nextLevelRight = newNode;
            if(_parentRight != null){
                _parentRight.nextLevelLeft = newNode;
            }
            if(_prevLevel == null){
                //root
                _prevLevel = new Node();
                _prevLevel.sortedMap.put(center._value,center);
                newNode._prevLevel = _prevLevel;
                return;
            }else{
                _prevLevel.addNoChild(center);
                newNode._prevLevel = _prevLevel;
            }
        }
        public void addWithChild(T inValue){
            Node next = nextNode();
            next.add(inValue);
        }
        private Node nextNode(){
            Node next = null;
            if(lowerKey == null && higherKey != null) {
                Entry high = sortedMap.get(lowerKey);
                next = high.nextLevelLeft;
            }else if(lowerKey != null && higherKey == null){
                Entry low = sortedMap.get(lowerKey);
                next = low.nextLevelRight;
            }else if(lowerKey == null && higherKey == null){
                System.out.println("something is wrong : [(inLower == null && inHigher == null)]");
            }else{
                Entry low = sortedMap.get(lowerKey);
                next = low.nextLevelRight;
            }
            return next;
        }
        public boolean contains(T inValue){
            if(sortedMap.containsKey(inValue)) {
                return true;
            }else{
                T lowerKey = null;
                T higherKey = null;
                boolean noChild = findRange(inValue);
                if(noChild)return false;
                Node next = nextNode();
                return next.contains(inValue);
            }
        }
    }

    private class Entry{
        Node nextLevelLeft;
        Node nextLevelRight;
        T _value;
        public Entry(T inValue){
            _value = inValue;
        }
    }

    public static void main(String[] args) {
        BTree<Integer> bTree = new BTree<Integer>(1);
        bTree.add(1);
        bTree.add(2);
        bTree.add(3);
        bTree.add(4);
        bTree.add(5);
    }
}
