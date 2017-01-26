package MapReduce.main;

/**
 * Created by kashishtayal on 1/25/17.
 */
public enum Signal {
    REDUCE_START("REDUCE_START"),
    JOB_COMPLETE("JOB_COMPLETE"),
    FILE_PROCESSED("FILE_PROCESSED"),
    START_MAP_REDUCE("START_MAP_REDUCE"),
    MAP_WORKER_SETUP_FAILED("MAP_WORKER_SETUP_FAILED");

    private String _signal;
    private Signal(String inSig){
        _signal = inSig;
    }
    @Override
    public String toString(){
        return _signal;
    }
}
