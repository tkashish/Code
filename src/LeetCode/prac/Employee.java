package LeetCode.prac;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by tayalka on 12/8/2016.
 */
public class Employee extends Person implements Serializable {
    static final long serialVersionUID = 10275539472837495L;
    private long address;

    public Employee(int age, String firstName, String secondName, String middleName, long address) {
        super(age, firstName, secondName, middleName);
        this.address = address;
    }


    public long getAddress() {
        return address;
    }

    public void setAddress(long address) {
        this.address = address;
    }
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        this.setAddress((long)ois.readLong());
        this.setAge((int)ois.readObject());
        this.setFirstName((String)ois.readObject());
        this.setMiddleName((String)ois.readObject());
        this.setSecondName((String)ois.readObject());
        System.out.println("readObject");
    }

    private void writeObject(ObjectOutputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultWriteObject();
//        ois.writeObject(getAddress());
        ois.writeObject(getAge());
        ois.writeObject(getFirstName());
        ois.writeObject(getMiddleName());
        ois.writeObject(getSecondName());
        System.out.println("writeObject");
    }

}
