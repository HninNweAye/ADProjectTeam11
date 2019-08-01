package sa48.team11.adproject.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sa48.team11.adproject.utils.Utils;

/**
 * Created by hninnwe on 2019-07-31
 */
public class AdjVoucher  implements Serializable {
    private String voucherId,status;
    private Date date;
    private List<AdjItem> itemList = new ArrayList<>();

    public AdjVoucher(Date date, String voucherId, String status, List<AdjItem> itemList) {
        this.date = date;
        this.voucherId = voucherId;
        this.status = status;
        this.itemList = itemList;
    }
    public AdjVoucher(String date, String voucherId, String status, List<AdjItem> itemList) {
        this.date = Utils.dateString(date);
        this.voucherId = voucherId;
        this.status = status;
        this.itemList = itemList;
    }
    public String getDateString() {
        return Utils.dateString(date);
    }
    public Date getDate() {
        return date;
    }


    public String getVoucherId() {
        return voucherId;
    }

    public String getStatus() {
        return status;
    }

    public List<AdjItem> getItemList() {
        return itemList;
    }
}
