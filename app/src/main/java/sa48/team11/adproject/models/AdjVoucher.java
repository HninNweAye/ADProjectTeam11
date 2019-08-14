package sa48.team11.adproject.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sa48.team11.adproject.utils.Utils;

/**
 * Created by hninnwe on 2019-07-31
 */
public class AdjVoucher  implements Serializable {
    @SerializedName("Id") private int voucherId;
    @SerializedName("Status") private String status;
    @SerializedName("Date") private Date date;
    @SerializedName("TotalQuantity") private int totalQuantity;

    public AdjVoucher(Date date, int voucherId, String status,int totalQuantity) {
        this.date = date;
        this.voucherId = voucherId;
        this.status = status;
        this.totalQuantity = totalQuantity;
    }

    public String getDateString() {
        return date == null ? "" : Utils.dateString(date);
    }
    public Date getDate() {
        return date;
    }


    public int getVoucherId() {
        return voucherId;
    }

    public String getStatus() {
        return status;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }
}
