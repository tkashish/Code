package MapReduce.main;

import java.io.InputStream;

/**
 * Created by kashishtayal on 1/25/17.
 */
public interface ITask {
    void execute(InputStream inputStream);
}
