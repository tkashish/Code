package Tree.BTree;

import java.util.Arrays;

/**
 * Created by tayalka on 12/15/2016.
 */
public class NodeImpl<T extends Comparable<T>>{
    private final int MINIMUM;
    private final int MAXIMUM;
    private Entry[] keys;
    private int _count;
    private NodeImpl _parent;
    public NodeImpl(int inDegree) {
        MINIMUM = inDegree;
        MAXIMUM = 2*MINIMUM;
        keys = new Entry[MAXIMUM+1];
    }

    public void add(T inValue) {
        boolean isRoot = isRoot();
        if(isRoot){
            Entry entry = new Entry(inValue);
            addEntry(entry,false);
        }else{
            NodeImpl nextNode = null;
            Entry left = null;
            Entry right = null;
            for(int i = 0; i < _count; i++){
                Entry curr = keys[i];
                int comp = curr._value.compareTo(inValue);
                if(comp < 0){
                    left = curr;
                }else if(comp >= 0){
                    right = curr;
                    break;
                }
            }
            if(right != null){
                nextNode = right._nextLevelLeft;
            }else{
                nextNode = left._nextLevelRight;
            }
            nextNode.add(inValue);
        }
    }

    public boolean contains(T inValue) {
        Entry left = null;
        Entry right = null;
        for(int i = 0; i < _count; i++){
            Entry curr = keys[i];
            int comp = curr._value.compareTo(inValue);
            if(comp < 0){
                left = curr;
            }else if(comp > 0){
                right = curr;
                break;
            }else{
                return true;
            }
        }
        NodeImpl child = null;
        if(left == null){
            child = right._nextLevelLeft;
        }else if (right == null){
            child = left._nextLevelRight;
        }else{
            child = left._nextLevelRight;
        }
        if(child == null) return false;
        return child.contains(inValue);
    }

    public void remove(T inValue) {

    }

    private void addEntry(Entry entry, boolean isInternalEntry){
        if(_count == MAXIMUM){
            keys[MAXIMUM] = entry;
            Arrays.sort(keys);
            Entry center = keys[MINIMUM];

            NodeImpl rightNode = new NodeImpl(MINIMUM);
            rightNode.keys = splitKeys();
            rightNode._count = MINIMUM;
            rightNode._parent = _parent;
            center._nextLevelLeft = this;
            center._nextLevelRight = rightNode;
            if(isInternalEntry){
                adjustChildPointers(rightNode);
            }
            if(_parent!= null) {
                adjustParentPointers((T) entry._value,rightNode);
                _parent.addEntry(center,true);
            }else{
                // if the node is root and leaf
                NodeImpl root = new NodeImpl(MINIMUM);
                root.keys[0] = center;
                root._count = 1;
                _parent = root;
                rightNode._parent = root;
            }
        }else{
            keys[_count] = entry;
            _count++;
            Arrays.sort(keys,0,_count);
        }
    }

    public Entry[] getKeys(){
        return keys;
    }

    public NodeImpl getParent(){
        return _parent;
    }

    public int getMINIMUM() {
        return MINIMUM;
    }

    public String getString() {
        String result = "";
        Entry lastEntry = null;
        for(int i = 0; i < keys.length; i++){
            if(keys[i] == null)break;
            lastEntry = keys[i];
            result = result.concat(String.valueOf(lastEntry._value)+",");
        }
        return result;
    }

    private void adjustChildPointers(NodeImpl rightNode){
        Entry[] keys = rightNode.keys;
        for(Entry key : keys){
            if(key == null) break;
            NodeImpl leftChild = key._nextLevelLeft;
            NodeImpl rightChild = key._nextLevelRight;
            if(leftChild != null) leftChild._parent = rightNode;
            if(rightChild != null) rightChild._parent = rightNode;
        }
    }

    private void adjustParentPointers(T inValue, NodeImpl inRightNode){
        Entry[] parentKeys = _parent.keys;
        Entry left = null;
        Entry right = null;
        for(int i = 0; i < _parent._count; i++){
            Entry curr = parentKeys[i];
            int comp = curr._value.compareTo(inValue);
            if(comp < 0){
                left = curr;
            }else if(comp >= 0){
                right = curr;
                break;
            }
        }
        if(left == null){
            // added to the leftmost child
            right._nextLevelLeft = inRightNode;
        }else if(right == null){
            // added to the rightmost child
            // do nothing
        }else{
            // added in the middle
            right._nextLevelLeft = inRightNode;
        }
    }

    private boolean isRoot(){
        Entry entry = keys[0];
        if(entry == null){
            return true;
        }
        return entry._nextLevelLeft == null;
    }


    private Entry[] splitKeys(){
        Entry[] rightNodeKeys = new Entry[MAXIMUM+1];
        System.arraycopy(keys,MINIMUM+1,rightNodeKeys,0,MINIMUM);
        for(int i = MINIMUM; i < keys.length; i++){
            keys[i] = null;
        }
        _count = MINIMUM;
        return rightNodeKeys;
    }

    static class Entry<V extends Comparable<V>> implements Comparable<Entry<V>> {
        NodeImpl _nextLevelLeft;
        NodeImpl _nextLevelRight;
        V _value;

        public Entry(V inValue) {
            _value = inValue;
        }

        @Override
        public int compareTo(Entry<V> o) {
            return _value.compareTo(o._value);
        }
    }
}

