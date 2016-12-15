package LeetCode.EffectiveJava.Serialization;

import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by kashishtayal on 12/11/16.
 */
public class Elvis implements Serializable{
    private static final long serialVersionUID = 1l;
    private static final Elvis INSTANCE = new Elvis("defaultElvis",20);;
    private String name;
    private int age;
    private Elvis(String inName, int inAge){
        name = inName;
        age = inAge;
    }
    public static Elvis getInstance(){
        return INSTANCE;
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public void setAge(int inAge){
        age = inAge;
    }
    public void setName(String inName){
        name = inName;
    }
    private Object readResolve(){
        return getInstance();
    }
}
