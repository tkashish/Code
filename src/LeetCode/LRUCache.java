package LeetCode;

import java.util.HashMap;

/**
 * Created by tayalka on 12/12/2016.
 */
public class LRUCache {
    Cache cache;
    public LRUCache(int capacity) {
        cache = new Cache(capacity);
    }

    public int get(int key) {
        return cache.get(key);
    }

    public void set(int key, int value) {
        cache.put(key,value);
        cache.print();
    }

    private class Cache {
        private HashMap<Integer,Node> map;
        private final int CAPACITY;
        private Node head;
        private Node tail;
        Cache(int inCapacity){
            if(inCapacity < 0 ) {
                throw new IllegalArgumentException("Capacity cannot be less than zero");
            }
            map = new HashMap<>();
            CAPACITY = inCapacity;
            head = null;
            tail = null;
        }
        public void put(int key , int value){
            if (CAPACITY == 0) return;
            if(!map.containsKey(key)){
                putFirstTime(key,value);
            }else{
                if(CAPACITY == 1) return;
                Node node = map.get(key);
                removeNodeFromList(node);
                putToTheTail(node);
            }
        }
        public void remove(int key){
            if(CAPACITY == 0) return;
            Node node = map.remove(key);
            removeNodeFromList(node);
        }
        public int get(int key){
            Node node = map.get(key);
            removeNodeFromList(node);
            putToTheTail(node);
            return node.value;
        }
        private void putFirstTime(int key, int value) {
            if (CAPACITY == map.size()) {
                removeOldest(key);
            }
            Node node = new Node(value);
            if (head == null) {
                head = node;
            }
            putToTheTail(node);
            map.put(key, node);
        }
        private void removeOldest(int key){
            map.remove(key);
            if (CAPACITY == 1){
                tail = null;
            }
            head = head.next;
            if(head != null) head.prev = null;
        }
        private void removeNodeFromList(Node inNode){
            Node prev = inNode.prev;
            Node next = inNode.next;
            if (prev != null) prev.next = next;
            if (next != null) next.prev = prev;
            if(tail == inNode) tail = prev;
            if(head == inNode) head = next;
            inNode.prev = null;
            inNode.next = null;
        }
        private void putToTheTail(Node node){
            if(node.prev != null || node.next != null){
                throw new IllegalStateException(node +"node should not be already preasent in the list");
            }
            if(tail != null) {
                tail.next = node;
                node.prev = tail;
            }
            tail = node;
        }
        public void print(){
            Node node = head;
            while(node != null){
                System.out.print(node+" - ");
                node = node.next;
            }
            System.out.println("");
            System.out.println(map);
        }
    }

    private class Node{
        int value;
        Node prev = null;
        Node next = null;
        public Node(int inValue){
            value = inValue;
        }

        @Override
        public String toString() {
            return "Node:["+ value+"]";
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(5);
        cache.set(1,1);
        cache.set(2,2);
        cache.set(3,3);
        cache.set(4,4);
        cache.set(1,1);
        cache.set(5,5);
        cache.set(6,6);
        System.out.println(cache.get(2));
    }
}
