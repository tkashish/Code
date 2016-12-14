package LeetCode.prac;

/**
 * Created by tayalka on 12/8/2016.
 */
public class Person{
    private int age;
    private String firstName;
    private String secondName;
    private String middleName;

    public Person() {
//        this(0,"defaultFirstName","defaultLastName","defaultMiddleName");
    }

    public Person(int age, String firstName, String secondName, String middleName) {
        this.age = age;

        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getAge() {

        return age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    private void initPerson(int age, String first, String middle, String second){
        setAge(age);
        setFirstName(first);
        setSecondName(second);
        setMiddleName(middle);
    }
}
