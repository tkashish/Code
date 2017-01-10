package LeetCode.BinaryTree;

/**
 * Created by kashishtayal on 1/9/17.
 */
public class Main {
    public static void main(String[] args) {
        IBinaryTree bt = new BinarySearchTreeImpl();
        bt.add(4);
        bt.add(2);
        bt.add(1);
        bt.add(3);
        bt.add(6);
        bt.add(5);
        bt.add(7);
        System.out.println(Codec.serialize(bt.getRoot()));
        System.out.println(BinaryTreeTraversalUtils.postOrderIterativeNotNull(bt.getRoot()));
        System.out.println(BinaryTreeTraversalUtils.postOrderRecursiveNotNull(bt.getRoot()));
    }
}
