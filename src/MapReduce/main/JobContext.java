package MapReduce.main;

import java.net.URL;

/**
 * Created by kashishtayal on 1/27/17.
 */
public class JobContext {
    private final ITask _mapTask;
    private final ITask _reduceTask;
    private final URL _dataPath;

    public JobContext(ITask mapTask, ITask reduceTask, URL dataPath) {
        _mapTask = mapTask;
        _reduceTask = reduceTask;
        _dataPath = dataPath;
    }

    public ITask get_mapTask() {
        return _mapTask;
    }

    public ITask get_reduceTask() {
        return _reduceTask;
    }

    public URL get_dataPath() {
        return _dataPath;
    }
}
