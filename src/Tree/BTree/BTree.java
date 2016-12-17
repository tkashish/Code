package Tree.BTree;

import Tree.Tree;

import java.util.*;

/**
 * Created by kashishtayal on 12/13/16.
 */
public class BTree<T extends Comparable<T>> implements Tree<T> {
    NodeImpl _root;
    public BTree(int inMin){
        _root = new NodeImpl(inMin);
    }

    @Override
    public void add(T inValue) {
        _root.add(inValue);
        if(_root.getParent() != null){
            _root = _root.getParent();
        }
    }

    @Override
    public boolean contains(T inValue) {
        return _root.contains(inValue);
    }

    @Override
    public boolean remove(T inValue) {
        return false;
    }

    public int height(){
        int result = 0;
        NodeImpl node = _root;
        while(node != null){
            result++;
            node = node.getKeys()[0]._nextLevelLeft;
        }
        return result;
    }

    public boolean isNodeAddedTwice(){
        Set<String> set = new HashSet<>();
        Queue<NodeImpl> queue = new LinkedList<>();
        queue.add(_root);
        while (!queue.isEmpty()){
            NodeImpl node = queue.remove();
            System.out.println(node.getString());
            if(set.contains(node.toString())){
                System.out.println(node.toString());
                return true;
            }
            set.add(node.toString());
            addChildren(node, queue);
        }
        System.out.println(set);
        return false;
    }
    public void addChildren(NodeImpl inNode, Queue<NodeImpl> queue){
        NodeImpl.Entry[] keys = inNode.getKeys();
        NodeImpl.Entry lastEntry = null;
        for(int i = 0; i < keys.length; i++){
            if(keys[i] == null)break;
            lastEntry = keys[i];
            if(lastEntry._nextLevelLeft == null) return;
            queue.add(lastEntry._nextLevelLeft);
        }
        queue.add(lastEntry._nextLevelRight);
    }
    public void printBfs(){
        Queue<NodeImpl> queue = new LinkedList<>();
        NodeImpl node = _root;
        queue.add(node);
        queue.add(null);
        System.out.print("[");
        while(!queue.isEmpty()){
            NodeImpl key = queue.remove();
            if(key == null){
                System.out.println("]");
                System.out.print("[");
            }else{
                if(key.getMINIMUM() == 0){
                    System.out.println("]");
                    System.out.print("[");
                    continue;
                }
                System.out.print(key.toString());
                addToQueue(key, queue);
                addToQueue(key, queue);
            }
        }
    }
    private void addToQueue(NodeImpl node, Queue<NodeImpl> queue){
        if(node == null) return;
        NodeImpl.Entry[] keys = node.getKeys();
        NodeImpl.Entry lastEntry = null;
        for(int i = 0; i < keys.length-1; i++){
            if(keys[i+1] == null){
                lastEntry = keys[i];
                break;
            }
            if(keys[i] == null) break;
            queue.add(keys[i]._nextLevelLeft);
        }
        queue.add(lastEntry._nextLevelRight);
        queue.add(new NodeImpl(0));
    }
}

