package LeetCode.design;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by kashishtayal on 12/3/16.
 */
public class RandomizedCollection {
//    Map<Integer, List<Node>> store;
//    int count;
//    final Node head;
//    Node lastAdded = null;
//    Node lastRandomFetch = null;
//    /** Initialize your data structure here. */
//    public RandomizedCollection() {
//        store = new HashMap<>();
//        count = 0;
//        head = new Node(Integer.MIN_VALUE,null,null);
//        head.head = true;
//        lastAdded = head;
//        lastRandomFetch = head;
//    }
//
//    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
//    public boolean insert(int val) {
//        lastRandomFetch = head;
//        Node curr = new Node(val,lastAdded,null);
//        lastAdded.next = curr;
//        boolean result = false;
//        List<Node> list = store.get(val);
//        if(list == null || list.isEmpty()){
//            result = true;
//            list = new ArrayList<>();
//        }
//        list.add(curr);
//        store.put(val,list);
//        count++;
//        lastAdded = curr;
//        return result;
//    }
//
//    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
//    public boolean remove(int val) {
//        lastRandomFetch = head;
//        List<Node> list = store.get(val);
//        if(list == null || list.isEmpty()) return false;
//        Node delNode = list.get(list.size()-1);
//        list.remove(list.size()-1);
//        Node prevNode = delNode.prev;
//        Node nextNode  = delNode.next;
//        prevNode.next = nextNode;
//        if(nextNode != null) nextNode.prev = prevNode;
//        if(delNode == lastAdded) lastAdded = lastAdded.prev;
//        count++;
//        return true;
//    }
//
//    /** Get a random element from the collection. */
//    public int getRandom() {
//        Node fetchNode = lastRandomFetch.next;
//        if(fetchNode == null){
//            fetchNode = head.next;
//        }
//        lastRandomFetch = fetchNode;
//        return fetchNode.val;
//    }
//    class Node{
//        boolean head = false;
//        int val;
//        Node prev = null;
//        Node next = null;
//        public Node(int inVal, Node inprev, Node innext){
//            val = inVal;
//            prev = inprev;
//            next = innext;
//        }
//    }
    public static void main(String[] args) {
        RandomizedCollection rc = new RandomizedCollection();
        System.out.println(rc.insert(1));
        System.out.println(rc.insert(1));
        System.out.println(rc.insert(2));
        System.out.println(rc.insert(2));
        System.out.println(rc.insert(3));
        System.out.println(rc.insert(3));
        System.out.println(rc.remove(1));
        System.out.println(rc.remove(1));
        System.out.println(rc.remove(3));
        System.out.println(rc.remove(3));
        System.out.println(rc.getRandom());

    }
    HashMap<Integer, List<Integer>> map;
    LinkedList<Integer> valueList;
    Random r = new Random();
    /** Initialize your data structure here. */
    public RandomizedCollection() {
        map = new HashMap<>();
        valueList = new LinkedList<>();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        valueList.add(val);
        List<Integer> list  = map.get(val);
        boolean result = false;
        if(list == null || list.isEmpty()){
            result = true;
            list = new ArrayList<>();
        }
        list.add(valueList.size()-1);
        map.put(val,list);
        return result;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val)) return false;
        List<Integer> list = map.get(val);
        if(list.isEmpty()) return false;
        int indexToRemove = list.remove(list.size()-1);
        if(indexToRemove < valueList.size()-1) {
            int tempIndex = valueList.size() - 1;
            int temp = valueList.get(tempIndex);
            valueList.set(indexToRemove, temp);
            valueList.remove(tempIndex);
            List<Integer> tempList = map.get(temp);
            tempList.remove(tempList.size() - 1);
            tempList.add(indexToRemove);
            Collections.sort(tempList);
        }else{
            valueList.remove(valueList.size()-1);
        }
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        int index = r.nextInt(valueList.size());
        System.out.println(index);
        return valueList.get(index);
    }
}
