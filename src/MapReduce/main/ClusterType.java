package MapReduce.main;

/**
 * Created by kashishtayal on 2/1/17.
 */
public enum ClusterType {
    SOCKET("socket"),
    VERTX("vertx");

    private final String _type;

    ClusterType(String inType) {
        this._type = inType;
    }

    public String getType(){
        return _type;
    }
}
