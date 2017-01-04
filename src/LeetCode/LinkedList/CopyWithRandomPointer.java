package LeetCode.LinkedList;

import java.util.*;

/**
 * Created by kashishtayal on 1/3/17.
 */
public class CopyWithRandomPointer {
    HashMap<RandomListNode,RandomListNode> map = new HashMap<>();
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null) return null;
        initMap(head);
        HashSet<RandomListNode> set = new HashSet<>();
        Stack<RandomListNode> stack = new Stack<>();
        stack.push(head);
        while(!stack.isEmpty()){
            RandomListNode node = stack.pop();
            if(set.contains(node)) continue;
            set.add(node);
            RandomListNode copyOfNode   = map.get(node);
            RandomListNode copyOfRandom = map.get(node.random);
            RandomListNode copyOfNext   = map.get(node.next);
            copyOfNode.random = copyOfRandom;
            copyOfNode.next   = copyOfNext;
            if(node.next != null && !set.contains(node.next))   stack.push(node.next);
            if(node.random != null && !set.contains(node.random)) stack.push(node.random);
        }
        return map.get(head);
    }
    private void initMap(RandomListNode head){
        Stack<RandomListNode> stack = new Stack<>();
        stack.push(head);
        while(!stack.isEmpty()){
            RandomListNode node = stack.pop();
            if(map.containsKey(node)) continue;
            RandomListNode deepCopy = new RandomListNode(node.label);
            map.put(node,deepCopy);
            if(node.next != null) stack.push(node.next);
            if(node.random != null) stack.push(node.random);
        }
    }
    class RandomListNode {
        int label;
        RandomListNode next, random;
        RandomListNode(int x) { this.label = x; }
    }

    /**
     * BFS
     */
    HashMap<RandomListNode, RandomListNode> map1 = new HashMap<>();
    public RandomListNode copyRandomList1(RandomListNode head) {
        if(head == null) return null;
        Queue<RandomListNode> queue = new LinkedList<>();
        queue.add(head);
        RandomListNode headCopy = new RandomListNode(head.label);
        map1.put(head,headCopy);
        while(!queue.isEmpty()){
            RandomListNode node = queue.remove();
            RandomListNode next = node.next;
            RandomListNode random = node.random;
            RandomListNode nodeCopy = map1.get(node);
            RandomListNode nextCopy = map1.get(next);
            if(next != null && nextCopy == null){
                nextCopy = new RandomListNode(next.label);
                queue.add(next);
                map1.put(next,nextCopy);
            }
            RandomListNode randomCopy = map1.get(random);
            if(random != null && randomCopy == null){
                randomCopy = new RandomListNode(random.label);
                queue.add(random);
                map1.put(random,randomCopy);
            }
            nodeCopy.next = nextCopy;
            nodeCopy.random = randomCopy;
        }
        return map1.get(head);
    }
}
