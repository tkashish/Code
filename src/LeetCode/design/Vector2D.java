package LeetCode.design;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by kashishtayal on 12/1/16.
 */
public class Vector2D implements Iterator<Integer> {
    Iterator<List<Integer>> listIterator;
    Iterator<Integer> iterator;
    public Vector2D(List<List<Integer>> vec2d) {
        if(vec2d != null) {
            listIterator = vec2d.iterator();
        }else{
            List<List<Integer>> dummyList = new ArrayList<>();
            listIterator = dummyList.iterator();
        }
    }

    @Override
    public Integer next() {
        if(this.hasNext()) {
            return iterator.next();
        }
        throw new NoSuchElementException();
    }

    @Override
    public boolean hasNext() {
        if(iterator == null || !iterator.hasNext()){
            while(listIterator.hasNext()){
                List<Integer> list = listIterator.next();
                if(!list.isEmpty()){
                    iterator = list.iterator();
                    break;
                }
            }
        }
        if(iterator == null) return false;
        return iterator.hasNext();
    }

    public static void main(String[] args) {
        {
            Vector2D vex = new Vector2D(new ArrayList<>());
            System.out.println(vex.hasNext() == false);
        }
        {
            List<List<Integer>> list = new ArrayList<>();
            List<Integer> subList = new ArrayList<>();
            subList.add(1);
            list.add(subList);
            Vector2D vex = new Vector2D(list);
            System.out.println(vex.next() == 1);
            System.out.println(vex.hasNext() == false);
        }
        {
            List<List<Integer>> list = new ArrayList<>();
            List<Integer> subList = new ArrayList<>();
            subList.add(1);
            List<Integer> subList1 = new ArrayList<>();
            subList1.add(2);
            list.add(subList);
            list.add(subList1);
            System.out.println(list);
            Vector2D vex = new Vector2D(list);
            System.out.println(vex.next() == 1);
            System.out.println(vex.next() == 2);
            System.out.println(vex.hasNext() == false);
        }

        {
            Vector2D vex = new Vector2D(null);
            System.out.println(vex.hasNext() == false);
        }

        {
            List<List<Integer>> list = new ArrayList<>();
            List<Integer> subList = new ArrayList<>();
//            subList.add(1);
            List<Integer> subList1 = new ArrayList<>();
            subList.add(2);
            list.add(subList);
            list.add(subList1);
            Vector2D vex = new Vector2D(list);
            System.out.println(vex.hasNext() == true);
            System.out.println(vex.next() == 2);
            System.out.println(vex.hasNext() == false);
        }
    }
}
