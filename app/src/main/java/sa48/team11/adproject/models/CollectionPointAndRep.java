package sa48.team11.adproject.models;

import com.google.gson.annotations.SerializedName;

public class CollectionPointAndRep {
    @SerializedName("PointId")private int pointId;
    @SerializedName("PointName")private String pointName;
    @SerializedName("ColTime")private String collectionTime;
    @SerializedName("RepId")private int repId;
    @SerializedName("RepName")private String repName;

    public CollectionPointAndRep(int pointId, String pointName,String collectionTime, int repId, String repName) {
        this.pointId = pointId;
        this.pointName = pointName;
        this.collectionTime=collectionTime;
        this.repId = repId;
        this.repName = repName;
    }

    public int getPointId() {
        return pointId;
    }

    public String getPointName() {
        return pointName;
    }

    public String getCollectionTime() {
        return collectionTime;
    }

    public int getRepId() {
        return repId;
    }

    public String getRepName() {
        return repName;
    }

    @Override
    public String toString() {
        return "CollectionPointAndRep{" +
                "pointId=" + pointId +
                ", pointName='" + pointName + '\'' +
                ", repId=" + repId +
                ", repName='" + repName + '\'' +
                '}';
    }
}
