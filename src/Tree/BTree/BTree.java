package Tree.BTree;

import Tree.Tree;

/**
 * Created by kashishtayal on 12/13/16.
 */
public class BTree<T extends Comparable<T>> implements Tree<T> {
    NodeImplNotGood _root = new NodeImplNotGood();
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
        return _root.find(inValue) != null;
    }

    @Override
    public boolean remove(T inValue) {
        return false;
    }





    public static void main(String[] args) {
        BTree<Integer> bTree = new BTree<Integer>(1);
//        bTree.add(1);
//        bTree.add(2);
//        bTree.add(3);
//        bTree.add(4);
//        bTree.add(5);
//        System.out.println(bTree.contains(1));
//        System.out.println(bTree.contains(2));
//        System.out.println(bTree.contains(3));
//        System.out.println(bTree.contains(4));
//        System.out.println(bTree.contains(5));
        long start = System.currentTimeMillis();
        for(int i = 10; i < 15; i++){
            bTree.add(i);
        }
        for (int i = 5; i < 10; i++) {
            bTree.add(i);
        }
        for (int i = 5; i < 15; i++) {
            boolean find = bTree.contains(i);
            if(find != true) System.out.println(i+" not found");
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}

