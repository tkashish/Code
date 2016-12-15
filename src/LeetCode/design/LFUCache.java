package LeetCode.design;

import java.util.HashMap;

/**
 * Created by kashishtayal on 12/12/16.
 */
public class LFUCache {
    Cache cache;
    public LFUCache(int capacity) {
        cache = new Cache(capacity);
    }

    public int get(int key) {
        int num = cache.get(key);
        System.out.println(num);
        return num;
    }

    public void set(int key, int value) {
        cache.put(key,value);
    }

    private class Cache{
        private final int CAPACITY;
        private HashMap<Integer, Node> map;
        private HashMap<Integer, DoublyLinkedList> countMap;
        Cache(int inCapacity){
            CAPACITY = inCapacity;
            map = new HashMap<>();
            countMap = new HashMap<>();
        }
        public void put(int key, int value){
            if(CAPACITY == 0)return;
            if(map.containsKey(key)){
                Node node = map.get(key);
                node.value = value;
                DoublyLinkedList dll = countMap.get(node.count);
                dll.removeNode(node);
                if(dll.count == 0) countMap.remove(node.count);
                node.count += 1;
                addNodeToList(node);
            }else{
                if(map.size() == CAPACITY){
                    int i = 0;
                    while(!countMap.containsKey(i)){
                        i++;
                    }
                    DoublyLinkedList dll = countMap.get(i);
                    Node node = dll.removeOldest();
                    if(dll.count == 0)countMap.remove(i);
                    map.remove(node.key);
                }
                Node node = new Node(key,value);
                addNodeToList(node);
                map.put(key,node);
            }
        }
        public int get(int key){
            if(CAPACITY == 0)return -1;
            Node node = null;
            if((node = map.get(key)) == null)return -1;
            DoublyLinkedList dll = countMap.get(node.count);
            dll.removeNode(node);
            if(dll.count == 0)countMap.remove(node.count);
            node.count += 1;
            addNodeToList(node);
            return node.value;
        }
        private void addNodeToList(Node node){
            DoublyLinkedList dll = countMap.get(node.count);
            if(dll == null){
                dll = new DoublyLinkedList();
            }
            dll.addNode(node);
            countMap.put(node.count,dll);
        }
    }

    private class DoublyLinkedList{
        Node head = null;
        Node tail = null;
        int count = 0;
        public void addNode(Node inNode){
            if(count == 0){
                head = inNode;
                tail = inNode;
            }else{
                tail.next = inNode;
                inNode.prev = tail;
                tail = inNode;
            }
            count++;
        }
        public void removeNode(Node inNode){
            removeNodeFromList(inNode);
            count--;
        }
        public void moveNodeToTail(Node inNode){
            removeNodeFromList(inNode);
            moveToTail(inNode);
        }
        private void moveToTail(Node inNode){
            if(count > 1){
                tail.next = inNode;
                inNode.prev = tail;
                tail = inNode;
            }
        }
        private void removeNodeFromList(Node inNode){
            Node prev = inNode.prev;
            Node next = inNode.next;
            if(prev != null)prev.next = next;
            if(next != null)next.prev = prev;
            if(tail == inNode)tail = prev;
            if(head == inNode)head = next;
            inNode.next = null;
            inNode.prev = null;
        }
        public Node removeOldest(){
            Node node = head;
            if(count == 1){
                head = null;
                tail = null;
            }else{
                head = head.next;
            }
            node.prev = null;
            node.next = null;
            count--;
            return node;
        }
    }

    private class Node{
        int count = 0;
        int key;
        int value;
        Node prev = null;
        Node next = null;
        Node(int inKey, int inValue){
            key = inKey;
            value = inValue;
        }
    }

    public static void main(String[] args) {
//        {
//            LFUCache cache = new LFUCache(2);
//            cache.set(1, 1);
//            cache.set(2, 2);
//            System.out.println(cache.get(1));
//            cache.set(3, 3);
//            System.out.println(cache.get(2));
//            System.out.println(cache.get(3));
//            cache.set(4, 4);
//            System.out.println(cache.get(1));
//            System.out.println(cache.get(3));
//            System.out.println(cache.get(4));
//        }
//        {
//            LFUCache cache = new LFUCache(2);
//            cache.set(1, 1);
//            cache.get(1);
//            cache.get(1);
//            cache.get(1);
//            cache.get(1);
//            cache.get(1);
//            cache.get(1);
//            cache.get(1);
//            cache.set(2,2);
//            cache.get(2);
//            cache.set(1,2);
//            cache.set(3,3);
//            System.out.println(cache.get(1));
//            System.out.println(cache.get(2));
//            System.out.println(cache.get(3));
//        }
        {
            LFUCache cache = new LFUCache(10);
            cache.set(10,13);
            cache.set(3,17);
            cache.set(6,11);
            cache.set(10,5);
            cache.set(9,10);
            cache.get(13);
            cache.set(2,19);
            cache.get(2);
            cache.get(3);
            cache.set(5,25);
            cache.get(8);
            cache.set(9,22);
            cache.set(5,5);
            cache.set(1,30);
            cache.get(11);
            cache.set(9,12);
            cache.get(7);
            cache.get(5);
            cache.get(8);
            cache.get(9);
            cache.set(4,30);
            cache.set(9,3);
            cache.get(9);
            cache.get(10);
            cache.get(10);
            cache.set(6,14);
//            cache.get(6);
            cache.set(3,1);
            cache.get(3);
            cache.set(10,11);
            cache.get(8);
            cache.set(2,14);
            cache.get(1);
            cache.get(5);
            cache.get(4);
            cache.set(11,4);
            cache.set(12,24);
            cache.set(5,18);
            cache.get(13);
            cache.set(7,23);
//            cache.get(8);
//            cache.get(12);
//            cache.set(3,27);
//            cache.set(2,12);
//            cache.get(5);
//            cache.set(2,9);
//            cache.set(13,4);
//            cache.set(8,18);
//            cache.set(1,7);
            cache.get(6);
        }
    }
}

//-1,19,17,-1,-1,-1,5,-1,12,3,5,5,1,-1,30,5,30,-1,-1,24,18,14,null,null,18,null,null,11,null,null,null,null,null,18,null,null,-1,null,4,29,30,null,12,11,null,null,null,null,29,null,null,null,null,17,-1,18,null,null,null,-1,null,null,null,20,null,null,null,29,18,18,null,null,null,null,20,null,null,null,null,null,null,null]
//-1,19,17,-1,-1,-1,5,-1,12,3,5,5,1,-1,30,5,30,-1,-1,24,18,-1,null,null,18,null,null,11,null,null,null,null,null,18,null,null,24,null,4,29,-1,null,12,11,null,null,null,null,29,null,null,null,null,17,-1,18,null,null,null,24,null,null,null,20,null,null,null,29,18,18,null,null,null,null,20,null,null,null,null,null,null,null]



//["LFUCache","set","set","set","set","set","get","set","get","get","set","get","set","set","set","get","set","get","get","get","get","set","set","get","get","get","set","set","get","set","get","set","get","get","get","set","set","set","get","set","get","get","set","set","get","set","set","set","set","get","set","set","get","set","set","get","set","set","set","set","set","get","set","set","get","set","get","get","get","set","get","get","set","set","set","set","get","set","set","set","set","get","get","get","set","set","set","get","set","set","set","get","set","set","set","get","get","get","set","set","set","set","get","set","set","set","set","set","set","set"]
//[[10],[10,13],[3,17],[6,11],[10,5],[9,10],[13],[2,19],[2],[3],[5,25],[8],[9,22],[5,5],[1,30],[11],[9,12],[7],[5],[8],[9],
//        [4,30],[9,3],[9],[10],[10],[6,14],[3,1],[3],[10,11],[8],[2,14],[1],[5],[4],[11,4],[12,24],[5,18],[13],[7,23],[8],
// [12],[3,27],[2,12],[5],[2,9],[13,4],[8,18],[1,7],[6],[9,29],[8,21],[5],[6,30],[1,12],[10],[4,15],[7,22],[11,26],[8,17],[9,29],[5],[3,4],[11,30],[12],[4,29],[3],[9],[6],[3,4],[1],[10],[3,29],[10,28],[1,20],[11,13],[3],[3,12],[3,8],[10,9],[3,26],[8],[7],[5],[13,17],[2,27],[11,15],[12],[9,19],[2,15],[3,16],[1],[12,17],[9,1],[6,19],[4],[5],[5],[8,1],[11,7],[5,2],[9,28],[1],[2,2],[7,4],[4,22],[7,24],[9,26],[13,28],[11,26]]



//[null,null,null,null,null,null,-1,null,19,17,null,-1,null,null,null,-1,null,-1,5,-1,12,null,null,3,5,5,null,null,1,null,-1,null,30,5,30,null,null,null,-1,null,-1,24,null,null,18,null,null,null,null,-1,null,null,18,null,null,11,null,null,null,null,null,18,null,null,24,null,4,29,-1,null,12,11,null,null,null,null,29,null,null,null,null,17,-1,18,null,null,null,24,null,null,null,20,null,null,null,29,18,18,null,null,null,null,20,null,null,null,null,null,null,null]
//[null,null,null,null,null,null,-1,null,19,17,null,-1,null,null,null,-1,null,-1,5,-1,12,null,null,3,5,5,null,null,1,null,-1,null,30,5,30,null,null,null,-1,null,-1,24,null,null,18,null,null,null,null,14,null,null,18,null,null,11,null,null,null,null,null,18,null,null,-1,null,4,29,30,null,12,11,null,null,null,null,29,null,null,null,null,17,-1,18,null,null,null,-1,null,null,null,20,null,null,null,29,18,18,null,null,null,null,20,null,null,null,null,null,null,null]
