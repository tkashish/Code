package LeetCode.EffectiveJava.Serialization;

import java.io.*;

/**
 * Created by kashishtayal on 12/11/16.
 */
public class Main {
    public static void main(String[] args) {
        Elvis elvis = Elvis.getInstance();
//        try{
//            File file = new File("Elvis.ser");
//            file.createNewFile();
//            FileOutputStream fos = new FileOutputStream(file);
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            oos.writeObject(elvis);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        elvis.setAge(40);
        elvis.setName("OldElvis");

        try{
            File file = new File("Elvis.ser");
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream oos = new ObjectInputStream(fis);
            Elvis elvisCopy = (Elvis)oos.readObject();
            System.out.println(elvisCopy.getAge());
            System.out.println(elvisCopy.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
