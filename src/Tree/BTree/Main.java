package Tree.BTree;

/**
 * Created by kashishtayal on 12/15/16.
 */
public class Main {
    public static void main(String[] args) {
//        List<Long> time = new ArrayList<>();
//        int j = 100;
//        BTree<Integer> tree = new BTree<>(j);
//        long start = System.currentTimeMillis();
//        int val = -10000000;
//        for (int i = 0; i > val; i--) {
//            tree.add(i);
//        }
//        for (int i = 1; i < -val; i++) {
//            tree.add(i);
//        }
//        System.out.println(j + "  " + (System.currentTimeMillis() - start));
//        System.out.println(tree.height());
        BTree<Integer> tree = new BTree<>(2);
        tree.add(1);
        tree.add(1);
        tree.add(1);
        tree.add(1);
        tree.add(1);
        tree.add(1);
        tree.add(1);
    }
}
