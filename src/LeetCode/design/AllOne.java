package LeetCode.design;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by tayalka on 1/17/2017.
 */
public class AllOne {
    private final Map<String, Bucket> _keyBucketMap;
    private final DoublyLinkedList _bucketList;

    /**
     * Initialize your data structure here.
     */
    public AllOne() {
        _keyBucketMap = new HashMap<>();
        _bucketList = new DoublyLinkedList();
    }

    /**
     * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
     */
    public void inc(String key) {
        Bucket currBucket = _keyBucketMap.get(key);
        Bucket newBucket = null;
        if(currBucket == null){
            newBucket = _bucketList.addNewKey(key);
        }else{
            newBucket = _bucketList.removeKeyAndAddNextPossibleBucket(currBucket,key);
        }
        _keyBucketMap.put(key, newBucket);
    }

    /**
     * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
     */
    public void dec(String key) {
        Bucket currBucket = _keyBucketMap.get(key);
        if(currBucket != null) {
            Bucket newBucket = _bucketList.removeKeyAndAddToPrevPossibleBucket(currBucket, key);
            if (newBucket == null) {
                _keyBucketMap.remove(key);
            } else {
                _keyBucketMap.put(key, newBucket);
            }
        }
    }

    /**
     * Returns one of the keys with maximal value.
     */
    public String getMaxKey() {
        return _bucketList.getMaxKey();
    }

    /**
     * Returns one of the keys with Minimal value.
     */
    public String getMinKey() {
        return _bucketList.getMinKey();
    }

    class Bucket {
        final int _count;
        final Set<String> _keySet;
        Bucket _next;
        Bucket _prev;

        public Bucket(int inCount) {
            this._count = inCount;
            _keySet = new HashSet<>();
        }

        public String getAnyKey() {
            if (_keySet.isEmpty()) {
                System.out.println("This bucket should have been deleted as their are no keys in it");
            }
            return _keySet.iterator().next();
        }

        @Override
        public String toString() {
            return "Bucket{" +
                    "_count=" + _count +
                    ", _keySet=" + _keySet +
                    '}';
        }
    }

    class DoublyLinkedList {
        private Bucket _head;
        private Bucket _tail;

        public DoublyLinkedList() {
            _head = null;
            _tail = null;
        }

        public void addToBucket(Bucket inBucket, String inKey) {
            inBucket._keySet.add(inKey);
        }

        /**
         * inKey is added for the first time
         * @param inKey key
         * @return bucket it is added to
         */
        public Bucket addNewKey(String inKey){
            if(_head == null){
                assert _tail == null;
                Bucket bucket = new Bucket(1);
                bucket._keySet.add(inKey);
                _head = bucket;
                _tail = bucket;
            }else{
                if(_head._count > 1){
                    Bucket bucket = new Bucket(1);
                    bucket._keySet.add(inKey);
                    bucket._next = _head;
                    _head._prev = bucket;
                    _head = bucket;
                }else{
                    _head._keySet.add(inKey);
                }
            }
            return _head;
        }

        /**
         * This method will remove the key from inCurrBucket and add it to bucket which
         * has count = 1+count of current bucket,
         * if after removing the key, if the bucket is empty this will remove the bucket
         * @param inCurrBucket bucket
         * @param inKey key
         * @return bucket which contains inKey
         */
        public Bucket removeKeyAndAddNextPossibleBucket(Bucket inCurrBucket, String inKey){
            Bucket nextBucket = null;
            inCurrBucket._keySet.remove(inKey);
            Bucket next = inCurrBucket._next;
            if(next == null){
                nextBucket = new Bucket(inCurrBucket._count+1);
                nextBucket._keySet.add(inKey);
                inCurrBucket._next = nextBucket;
                nextBucket._prev = inCurrBucket;
                _tail = nextBucket;
            }else{
                if(next._count == inCurrBucket._count+1){
                    next._keySet.add(inKey);
                    nextBucket = next;
                }else{
                    nextBucket = new Bucket(inCurrBucket._count + 1);
                    nextBucket._keySet.add(inKey);
                    inCurrBucket._next = nextBucket;
                    nextBucket._prev = inCurrBucket;
                    next._prev = nextBucket;
                    nextBucket._next = next;
                }
            }
            if(inCurrBucket._keySet.size() == 0){
                Bucket prev = inCurrBucket._prev;
                if(prev == null){
                    inCurrBucket._next = null;
                    nextBucket._prev = null;
                    _head = nextBucket;
                }else{
                    prev._next = nextBucket;
                    nextBucket._prev = prev;
                    inCurrBucket._next = null;
                    inCurrBucket._prev = null;
                }
            }
            return nextBucket;
        }

        public Bucket removeKeyAndAddToPrevPossibleBucket(Bucket inCurrBucket, String inKey){
            Bucket newPrevBucket = null;
            Bucket prev = inCurrBucket._prev;
            inCurrBucket._keySet.remove(inKey);
            if(inCurrBucket._count != 1){
                if(prev == null){
                    newPrevBucket = new Bucket(inCurrBucket._count-1);
                    newPrevBucket._keySet.add(inKey);
                    newPrevBucket._next = inCurrBucket;
                    inCurrBucket._prev = newPrevBucket;
                    _head = newPrevBucket;
                }else{
                    if(prev._count == inCurrBucket._count-1){
                        prev._keySet.add(inKey);
                        newPrevBucket = prev;
                    }else{
                        newPrevBucket = new Bucket(inCurrBucket._count - 1);
                        newPrevBucket._keySet.add(inKey);
                        prev._next = newPrevBucket;
                        newPrevBucket._prev = prev;
                        newPrevBucket._next = inCurrBucket;
                        inCurrBucket._prev = newPrevBucket;
                    }
                }
            }
            if(inCurrBucket._keySet.size() == 0){
                Bucket next = inCurrBucket._next;
                if(newPrevBucket == null){
                    if(next == null){
                        _head = null;
                        _tail = null;
                    }else{
                        _head = next;
                        inCurrBucket._next = null;
                        next._prev = null;
                    }
                }else{
                    if(next == null){
                        newPrevBucket._next = null;
                        inCurrBucket._prev = null;
                        _tail = newPrevBucket;
                    }else{
                        newPrevBucket._next = next;
                        next._prev = newPrevBucket;
                        inCurrBucket._prev = null;
                        inCurrBucket._next = null;
                    }
                }
            }
            return newPrevBucket;
        }

        /**
         * Returns one of the keys with maximal value.
         */
        public String getMaxKey() {
            if (_tail == null) return "";
            return _tail.getAnyKey();
        }

        /**
         * Returns one of the keys with Minimal value.
         */
        public String getMinKey() {
            if (_head == null) return "";
            return _head.getAnyKey();
        }
    }

    public static void main(String[] args) {
        AllOne one = new AllOne();
        one.inc("A");
        one.inc("B");
        one.inc("B");
        one.inc("C");
        one.inc("C");
        one.inc("C");
        one.dec("B");
        one.dec("B");
        one.dec("A");
        System.out.println(one.getMaxKey());
        System.out.println(one.getMinKey());
    }
}