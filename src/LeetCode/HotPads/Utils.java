package LeetCode.HotPads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by kashishtayal on 2/23/17.
 */
public class Utils {
    /**
     * This method give a sorted list of persons in people.
     * If sortField is null, the sorting will be based on Date Of Birth.
     * If ascending is null, the sorting will be in descending order
     * @param people inPeople
     * @param sortField fields on which each person has to be sorted
     * @param ascending true if sorting should be in ascending order, false otherwise
     * @return sorted list of persons
     */
    static List<Person> sort(Iterable<Person> people, String sortField, String ascending){
        if(people == null){
            return new ArrayList<>();
        }
        Comparator<Person> comparator = getPersonComparator(sortField, ascending);
        List<Person> sortedList = StreamSupport.stream(people.spliterator(),false)
                                               .sorted(comparator)
                                               .collect(Collectors.toList());
        return sortedList;
    }

    private static Comparator<Person> getPersonComparator(String inSortField, String inAscending){
        String[] fields = new String[]{inSortField};
        Comparator<Person> comparator = getPersonComparator(fields,inAscending);
        return comparator;
    }


    private static Comparator<Person> getPersonComparator(String[] inSortFields, String inAscending){
        List<Comparator<Person>> comparatorList = new ArrayList<>();
        for(String sortField : inSortFields) {
            Comparator<Person> comparator = null;
            if(DATE_OF_BIRTH.equals(sortField)) {
                comparator = Boolean.TRUE.toString().equals(inAscending) ? dobComparator : Collections.reverseOrder(dobComparator);
            }else if(SSN.equals(sortField)) {
                comparator = Boolean.TRUE.toString().equals(inAscending) ? ssnComparator : Collections.reverseOrder(ssnComparator);
            }else if(FIRST_NAME.equals(sortField)) {
                comparator = Boolean.TRUE.toString().equals(inAscending) ? firstNameComparator : Collections.reverseOrder(firstNameComparator);
            }else if(LAST_NAME.equals(sortField)) {
                comparator = Boolean.TRUE.toString().equals(inAscending) ? lastNameComparator : Collections.reverseOrder(lastNameComparator);
            }else if(HEIGHT.equals(sortField)) {
                comparator = Boolean.TRUE.toString().equals(inAscending) ? heightComparator : Collections.reverseOrder(heightComparator);
            }else if (WEIGHT.equals(sortField)) {
                comparator = Boolean.TRUE.toString().equals(inAscending) ? weightComparator : Collections.reverseOrder(weightComparator);
            }else{
                comparator = Boolean.TRUE.toString().equals(inAscending) ? defaultComparator : Collections.reverseOrder(defaultComparator);
            }
            comparatorList.add(comparator);
        }
        return new PersonComparator(comparatorList);
    }

    private static int customCompare(Comparable o1, Comparable o2){
        if(o1 == null && o2 == null){
            return 0;
        }else if(o1 == null){
            return -1;
        }else if(o2 == null){
            return 1;
        }else{
            return o1.compareTo(o2);
        }
    }

    private static final Comparator<Person> ssnComparator = (person1, person2) -> customCompare(person1.ssn,person2.ssn);
    private static final Comparator<Person> dobComparator = (person1, person2) -> customCompare(person1.dateOfBirth,person2.dateOfBirth);
    private static final Comparator<Person> firstNameComparator = (person1, person2) -> customCompare(person1.firstName, person2.firstName);
    private static final Comparator<Person> lastNameComparator = (person1, person2) -> customCompare(person1.lastName, person2.lastName);
    private static final Comparator<Person> heightComparator = (person1, person2) -> customCompare(person1.heightIn, person2.heightIn);
    private static final Comparator<Person> weightComparator = (person1, person2) -> customCompare(person1.weightLb, person2.weightLb);
    private static final Comparator<Person> defaultComparator = (person1, person2) -> customCompare(person1.dateOfBirth, person2.dateOfBirth);

    static final String DATE_OF_BIRTH = "dateOfBirth";
    static final String SSN = "ssn";
    static final String FIRST_NAME = "firstName";
    static final String LAST_NAME = "lastName";
    static final String HEIGHT = "heightIn";
    static final String WEIGHT = "weightLb";

    private static class PersonComparator implements Comparator<Person>{
        private final List<Comparator<Person>> _comparatorList;
        PersonComparator(List<Comparator<Person>> inComparatorList){
            _comparatorList = inComparatorList;
        }

        @Override
        public int compare(Person o1, Person o2) {
            for(Comparator<Person> comparator : _comparatorList){
                int compVal = comparator.compare(o1,o2);
                if(compVal!=0){
                    return compVal;
                }
            }
            return 0;
        }
    }
}
