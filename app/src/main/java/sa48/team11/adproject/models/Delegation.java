package sa48.team11.adproject.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Delegation {
    @SerializedName("Id") private int id;//delegationId
    @SerializedName("EmployeeId") private int empId;
    @SerializedName("EmployeeName") private String empName;
    @SerializedName("Email") private String email;
    @SerializedName("Status") private boolean status;
    @SerializedName("StartDate") private Date startDate;
    @SerializedName("EndDate") private Date endDate;
    @SerializedName("Reason") private String reason;
    public Delegation(int id, int empId, Date startDate, Date endDate,String name,boolean status, String reason) {
        this.id = id;
        this.empId = empId;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.empName = name;
        this.reason = reason;
    }
    public Delegation(int empId,String empName,String email, Date startDate, Date endDate,String reason) {
        this.empId = empId;
        this.empName = empName;
        this.email = email;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
    }

    public Delegation(int id,int empId, Date startDate, Date endDate) {
        this.id = id;
        this.empId = empId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
    }

    public String getEmail() {
        return email;
    }

    public int getEmpId() {
        return empId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getReason(){
        return  reason;
    }

    public int getId() {
        return id;
    }

    public String getEmpName() {
        return empName == null ? "" : empName;
    }

    public boolean isActive() {
       return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
