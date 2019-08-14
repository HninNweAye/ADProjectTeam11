package sa48.team11.adproject.models;

import com.google.gson.annotations.SerializedName;

public class CollectionPoint {
    @SerializedName("Id") int Id;
    @SerializedName("Name") String name;
    @SerializedName("Address") String address;
    @SerializedName("CollectionTime")String collectTime;

    public CollectionPoint(int id, String name, String address, String collectTime) {
        Id = id;
        this.name = name;
        this.address = address;
        this.collectTime = collectTime;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCollectTime() {
        return collectTime;
    }

    @Override
    public String toString() {
        return "CollectionPoint{" +
                "name='" + name + '\'' +
                ", collectTime='" + collectTime + '\'' +
                '}';
    }
}

