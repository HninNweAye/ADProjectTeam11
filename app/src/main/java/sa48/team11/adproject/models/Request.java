package sa48.team11.adproject.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sa48.team11.adproject.utils.Utils;

/**
 * Created by hninnwe on 2019-07-24
 */
public class Request implements Serializable {
    private String employeeName,status;
    private Date date;
    private int noOfItems;
    private List<Item> itemList = new ArrayList<>();

    public Request(String employeeName, Date date, String status,List<Item> itemList , int noOfItems) {
        this.employeeName = employeeName;
        this.date = date;
        this.status = status;
        this.itemList = itemList;
        this.noOfItems = noOfItems;
    }
    public Request(String employeeName, String date, String status,List<Item> itemList , int noOfItems) {
        this.employeeName = employeeName;
        this.date = Utils.dateString(date);
        this.status = status;
        this.itemList = itemList;
        this.noOfItems = noOfItems;
    }
    public List<Item> getItemList() {
        return itemList;
    }



    public String getEmployeeName() {
        return employeeName;
    }


    public Date getDate() {
        return date;
    }
    public String getDateString() {
        return Utils.dateString(date);
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

}
