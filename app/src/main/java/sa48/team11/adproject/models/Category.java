package sa48.team11.adproject.models;

import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("Id") int id;
    @SerializedName("Name") String name;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
