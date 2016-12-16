package Tree.BTree;

/**
 * Created by tayalka on 12/15/2016.
 */
public class NodeImpl<T extends Comparable<T>> implements INode<T> {
    private final int MINIMUM;
    private final int MAXIMUM;
    public NodeImpl(int inDegree) {
        MINIMUM = inDegree;
        MAXIMUM = 2*MINIMUM;
    }

    @Override
    public void add(T inValue) {

    }

    @Override
    public boolean contains(T inValue) {
        return false;
    }

    @Override
    public void remove(T inValue) {

    }
}
