package LeetCode.EffectiveJava.Serialization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kashishtayal on 12/11/16.
 */
public class Test {
    @SuppressWarnings("unchecked")
    private static void addToList(List list, Object obj) {
    list.add(obj);// Unchecked warning suppressed
    }
   private static <T> void printNum(T type) {
if (!(type instanceof Integer || type instanceof Double)) {
System.out.println("Cannot print in the supplied type");
}
List<T> list = new ArrayList<T>();
addToList(list, type);
System.out.println(list.get(0));
}
public static void main(String[] args) {
double d = 42;
int i = 42;
System.out.println(d);
Test.printNum(d);
System.out.println(i);
Test.printNum(i);
}
}
