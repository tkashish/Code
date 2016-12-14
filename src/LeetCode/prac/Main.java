package LeetCode.prac;

import java.io.*;

/**
 * Created by tayalka on 12/8/2016.
 */
public class Main {
    public static void main(String[] args) {
        Employee kashish = new Employee(123,"dada","adad","adas",699l);
        try {
            File file = new File("kashish.txt");
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(kashish);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            File file1 = new File("kashish.txt");
            FileInputStream fis = new FileInputStream(file1);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Employee tayal = (Employee)ois.readObject();
            System.out.println(tayal.getAddress());
            System.out.println(tayal.getAge());
            System.out.println(tayal.getFirstName());
            System.out.println(tayal.getMiddleName());
            System.out.println(tayal.getSecondName());
        }catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
