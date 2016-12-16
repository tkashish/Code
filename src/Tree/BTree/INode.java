package Tree.BTree;

/**
 * Created by tayalka on 12/15/2016.
 */
interface INode<T extends Comparable<T>> {
    void add(T inValue);
    boolean contains(T inValue);
    void remove(T inValue);
}
