package LeetCode.HotPads;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kashishtayal on 2/23/17.
 */
public class Main {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        for(int i = 1; i < 4; i++) {
            Person p1 = new Person();
            p1.firstName = String.valueOf(i);
            p1.weightLb = i*10d;
            list.add(p1);
        }
        for(int i = 5; i <11; i++) {
            Person p1 = new Person();
            p1.firstName = String.valueOf(i);
            list.add(p1);
        }
        Utils.sort(list,null,null).stream().map(person->person.firstName).forEach(System.out::print);
        System.out.println();
        UtilsI.sort(list,null,null).stream().map(person->person.firstName).forEach(System.out::print);

    }
}
