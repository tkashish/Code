package LeetCode.EffectiveJava.Serialization;

import java.io.*;
import java.util.Date;

/**
 * Created by kashishtayal on 12/11/16.
 */
public class Period implements Serializable{
    private Date start;
    private Date end;
    public Period(Date inStart, Date inEnd) throws Exception {
        if(!isConsistent(inStart,inEnd)){
            throw new Exception("dates inconsistent");
        }
        start = inStart;
        end  = inEnd;
    }

    private boolean isConsistent(Date inStart, Date inEnd){
        return inStart.before(inEnd);
    }
    private Object writeReplace(){
        System.out.println("writing in period");
        return new SerializationProxy(start,end);
    }
    private static class SerializationProxy implements Serializable{
        private Date startProxy;
        private Date endProxy;
        public SerializationProxy(Date inStart, Date inEnd){
            startProxy = inStart;
            endProxy = inEnd;
        }
        private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
            System.out.println("readObject ");
            endProxy = (Date) ois.readObject();
            startProxy = new Date(200);
        }
        private Object readResolve() throws Exception {
            System.out.println("reading in SerializationProxy");
            return new Period(startProxy,endProxy);
        }
    }

    public static void main(String[] args) {
//        try {
//            Period period = new Period(new Date(1),new Date(2));
//            File file = new File("period.ser");
//            FileOutputStream fos = new FileOutputStream(file);
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            oos.writeObject(period);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try{
            File file = new File("period.ser");
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Period period = (Period)ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
