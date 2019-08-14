package sa48.team11.adproject.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by hninnwe on 2019-07-31
 */
public class StockCard {
    @SerializedName("RefType") private String refType;
    @SerializedName("Date") private Date date;
    @SerializedName("Qty") private int quantity;
    @SerializedName("Balance") private int balance;

    public StockCard(String refType, Date date, int quantity, int balance) {
        this.refType = refType;
        this.date = date;
        this.quantity = quantity;
        this.balance = balance;
    }

    public String getRefType() {
        return refType;
    }

    public Date getDate() {
        return date;
    }

    public String getQuantity() {
        return quantity>0 ? "+"+quantity : ""+quantity;
    }

    public int getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "StockCard{" +
                "refType='" + refType + '\'' +
                ", date='" + date + '\'' +
                ", quantity='" + quantity + '\'' +
                ", balance=" + balance +
                '}';
    }
}
