package sa48.team11.adproject.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sa48.team11.adproject.utils.Utils;

/**
 * Created by hninnwe on 2019-07-24
 */
public class Request implements Serializable {
    @SerializedName("Id")
    private int req_id;
    @SerializedName("EmployeeName")
    private String employeeName;
    @SerializedName("Status")
    private String status;
    @SerializedName("Date")
    private Date date;
    @SerializedName("Quantity")
    private int noOfItems;

    public Request(String employeeName, Date date, String status, int noOfItems) {
        this.employeeName = employeeName;
        this.date = date;
        this.status = status;
        this.noOfItems = noOfItems;
    }

    public Request(int req_id, String employeeName, String status, Date date, int noOfItems) {
        this.req_id = req_id;
        this.employeeName = employeeName;
        this.status = status;
        this.date = date;
        this.noOfItems = noOfItems;
    }

    public int getReq_id() {
        return req_id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public Date getDate() {
        return date;
    }

    public String getDateString() {
        return date != null ? Utils.dateString(date) : " ";
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public int getNoOfItems() {
        return noOfItems;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Request{" +
                "req_id='" + req_id + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", status='" + status + '\'' +
                ", date=" + date +
                ", noOfItems=" + noOfItems +
                '}';
    }
}
