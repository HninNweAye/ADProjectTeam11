
package sa48.team11.adproject.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import sa48.team11.adproject.utils.Utils;

public class Retrieval {

    @SerializedName("BinNo")
    private String binNo;
    @SerializedName("Date")
    private Date date;
    @SerializedName("Description")
    private String description;
    @SerializedName("ItemId")
    private String itemId;
    @SerializedName("Qty")
    private int qty;
    @SerializedName("RetrievalQty")
    private int actualQty;

    public Retrieval(String description, int qty, int actualQty) {
        this.description = description;
        this.qty = qty;
        this.actualQty = actualQty;
    }

    public Retrieval(String binNo, Date date, String description, String itemId, int qty, int retrievalQty) {
        this.binNo = binNo;
        this.date = date;
        this.description = description;
        this.itemId = itemId;
        this.qty = qty;
        this.actualQty = retrievalQty;
    }

    public String getBinNo() {
        return binNo;
    }


    public Date getDate() {
        return date;
    }

    public String getDateString() {
        return date != null ? Utils.dateString(date) : " ";
    }

    public String getDescription() {
        return description;
    }

    public String getItemId() {
        return itemId;
    }
    
    public int getQty() {
        return qty;
    }

    public void setActualQty(int actualQty) {
        this.actualQty = actualQty;
    }

    public int getActualQty() {
        return actualQty;
    }

}
