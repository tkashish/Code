package LeetCode.prac;

/**
 * Created by tayalka on 7/15/2016.
 */
public class GenericsPrac {
    static class GenImpl<V> implements Gen<V> {
        @Override
        public V getVal() {
            return val;
        }
        @Override
        public void setVal(V val) {
            this.val = val;
        }
        private V val;
    }

    public static void reImpl(Gen<?> gen){
        System.out.println(gen.getVal().getClass().getName());
        reImplhelper(gen);
    }

    public static <T> void reImplhelper(Gen<T> gen){
        gen.setVal(gen.getVal());
    }

    public static void main(String[] args) {
        Gen<String> gen = new GenImpl();
        gen.setVal("kashish");
        reImpl(gen);
    }
}
