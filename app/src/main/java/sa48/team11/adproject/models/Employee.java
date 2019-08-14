package sa48.team11.adproject.models;


import com.google.gson.annotations.SerializedName;

public class Employee {
    @SerializedName("Id")
    private int id;
    @SerializedName("Name")
    private String name;

    public Employee(int id, String name) {
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
        return getName();
    }
}
