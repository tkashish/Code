package LeetCode.HotPads;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by kashishtayal on 2/23/17.
 */
public class UtilsI {
    private static Map<String,Comparator<Person>> fieldComparatorMap;
    private static final String DATE_OF_BIRTH = "dateOfBirth";
    private static final String SSN = "ssn";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String HEIGHT = "heightIn";
    private static final String WEIGHT = "weightLb";
    private static final String DEFAULT = "DEFAULT";
    private static final Comparator<Person> defaultComparator = (person1, person2) -> customCompare(person1.dateOfBirth, person2.dateOfBirth);

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
        init();
        Comparator<Person> comparator = getPersonComparator(sortField, ascending);
        List<Person> sortedList = StreamSupport.stream(people.spliterator(),false)
                .sorted(comparator)
                .collect(Collectors.toList());
        return sortedList;
    }

    private static void init() {
        fieldComparatorMap = new HashMap<>();
        fieldComparatorMap.put(SSN,(person1, person2) -> customCompare(person1.ssn,person2.ssn));
        fieldComparatorMap.put(DATE_OF_BIRTH,(person1, person2) -> customCompare(person1.dateOfBirth,person2.dateOfBirth));
        fieldComparatorMap.put(FIRST_NAME,(person1, person2) -> customCompare(person1.firstName,person2.firstName));
        fieldComparatorMap.put(LAST_NAME,(person1, person2) -> customCompare(person1.lastName,person2.lastName));
        fieldComparatorMap.put(HEIGHT,(person1, person2) -> customCompare(person1.heightIn,person2.heightIn));
        fieldComparatorMap.put(WEIGHT,(person1, person2) -> customCompare(person1.weightLb,person2.weightLb));
        fieldComparatorMap.put(DEFAULT,defaultComparator);
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


    private static Comparator<Person> getPersonComparator(String inSortField, String inAscending){
        String[] fields = new String[]{inSortField};
        Comparator<Person> comparator = getPersonComparator(fields,inAscending);
        return comparator;
    }

    private static Comparator<Person> getPersonComparator(String[] inSortFields, String inAscending){
        List<Comparator<Person>> comparatorList = new ArrayList<>();
        for(String sortField : inSortFields) {
            Comparator<Person> comparator = fieldComparatorMap.get(sortField);
            if(comparator == null){
                comparator = fieldComparatorMap.get(DEFAULT);
            }
            if(!"true".equals(inAscending)){
                comparator = Collections.reverseOrder(comparator);
            }
            comparatorList.add(comparator);
        }
        return new PersonComparator(comparatorList);
    }

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
