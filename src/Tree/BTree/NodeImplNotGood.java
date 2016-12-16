package Tree.BTree;

import java.util.*;

/**
 * Created by tayalka on 12/15/2016.
 */
class NodeImplNotGood<T extends Comparable<T>> implements INode<T> {
    TreeMap<T, Entry> sortedMap;
    Entry _parentLeft;
    Entry _parentRight;
    NodeImplNotGood _prevLevel;
    T lowerKey;
    T higherKey;
    boolean noChild = true;
    private final int MAXIMUM;
    private final int MINIMUM;
    NodeImplNotGood() {
        sortedMap = new TreeMap<>();
        MINIMUM = 2;
        MAXIMUM = 4;
    }
    @Override
    public void add(T inValue) {
        boolean noChild = findRange(inValue);
        if (noChild) {
            Entry entry = new Entry(inValue);
            addNoChild(entry);
        } else {
            addWithChild(inValue);
        }
    }

    @Override
    public boolean contains(T inValue) {
        return false;
    }

    @Override
    public void remove(T inValue) {

    }

    private boolean findRange(T inValue) {
        Set<Map.Entry<T, Entry>> set = sortedMap.entrySet();
        for (Map.Entry<T, Entry> entry : set) {
            T key = entry.getKey();
            Entry value = entry.getValue();
            if (noChild == true && value.nextLevelLeft != null) {
                noChild = false;
            }
            if (key.compareTo(inValue) < 0) {
                lowerKey = key;
            } else if (key.compareTo(inValue) > 0) {
                higherKey = key;
                break;
            }
        }
        return noChild;
    }

    private void addNoChild(Entry entry) {
        int size = sortedMap.size();
        sortedMap.put((T) entry._value, entry);
        if (size == MAXIMUM) {
            restructure();
        }
    }

    private void restructure() {
        NodeImplNotGood newNode = new NodeImplNotGood();
        TreeMap<T, Entry> newMap = new TreeMap<>();
        NavigableSet<T> keySet = (NavigableSet<T>) sortedMap.keySet();
        Iterator<T> it = keySet.descendingIterator();
        int count = 0;
        while (it.hasNext()) {
            T next = it.next();
            newMap.put(next, sortedMap.get(next));
            count++;
            if (count == MINIMUM) {
                break;
            }
        }
        keySet.removeAll(newMap.keySet());
        T centerKey = keySet.last();
        Entry center = sortedMap.get(centerKey);
        keySet.remove(centerKey);
        newNode.sortedMap = newMap;
        if (!noChild) updateChildren(newNode);
        center.nextLevelLeft = this;
        center.nextLevelRight = newNode;
        if (_parentRight != null) {
            _parentRight.nextLevelLeft = newNode;
        }
        if (_prevLevel == null) {
            //root
            _prevLevel = new NodeImplNotGood();
            _prevLevel.sortedMap.put(center._value, center);
            newNode._prevLevel = _prevLevel;
            return;
        } else {
            _prevLevel.addNoChild(center);
            newNode._prevLevel = _prevLevel;
        }
    }

    private void updateChildren(NodeImplNotGood inNode) {
        TreeMap<T, Entry> map = inNode.sortedMap;
        Collection<Entry> collection = map.values();
        for (Entry e : collection) {
            NodeImplNotGood left = e.nextLevelLeft;
            NodeImplNotGood right = e.nextLevelLeft;
            if (left != null) left._prevLevel = inNode;
            if (right != null) right._prevLevel = inNode;
        }
    }

    public void addWithChild(T inValue) {
        NodeImplNotGood next = nextNode();
        next.add(inValue);
    }

    private NodeImplNotGood nextNode() {
        NodeImplNotGood next = null;
        if (lowerKey == null && higherKey != null) {
            Entry high = sortedMap.get(higherKey);
            next = high.nextLevelLeft;
        } else if (lowerKey != null && higherKey == null) {
            Entry low = sortedMap.get(lowerKey);
            next = low.nextLevelRight;
        } else if (lowerKey == null && higherKey == null) {
            System.out.println("something is wrong : [(inLower == null && inHigher == null)]");
        } else {
            Entry low = sortedMap.get(lowerKey);
            next = low.nextLevelRight;
        }
        lowerKey = null;
        higherKey = null;
        return next;
    }

    public Entry find(T inValue) {
        if (sortedMap.containsKey(inValue)) {
            return sortedMap.get(inValue);
        } else {
            boolean noChild = findRange(inValue);
            if (noChild) return null;
            NodeImplNotGood next = nextNode();
            return next.find(inValue);
        }
    }

    class Entry {
        NodeImplNotGood nextLevelLeft;
        NodeImplNotGood nextLevelRight;
        T _value;

        public Entry(T inValue) {
            _value = inValue;
        }
    }
}
