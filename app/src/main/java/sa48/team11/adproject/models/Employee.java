
package sa48.team11.adproject.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@SuppressWarnings("unused")
public class Employee implements Serializable {

    @SerializedName("DepartmentId")
    private String departmentId;
    @SerializedName("Email")
    private String email;
    @SerializedName("Id")
    private int id;
    @SerializedName("Role")
    private String role;
    @SerializedName("Name")
    private String name;

    public Employee(String departmentId, String email, int id, String role, String name) {
        this.departmentId = departmentId;
        this.email = email;
        this.id = id;
        this.role = role;
        this.name = name;
    }


    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
