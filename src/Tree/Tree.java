package Tree;

/**
 * Created by kashishtayal on 12/13/16.
 */
public interface Tree<T> {
    void add(T inValue);
    boolean contains(T inValue);
    boolean remove(T inValue);
}
