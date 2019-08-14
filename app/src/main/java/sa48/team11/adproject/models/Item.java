package sa48.team11.adproject.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by hninnwe on 2019-07-31
 */
public class Item implements Serializable {
    @SerializedName("Id") private String code;
    @SerializedName("Description") private String description;
    @SerializedName("BinNo") private String bin;
    @SerializedName("Qty") private int quantity;

    public Item(String code, String description, int quantity) {
        this.code = code;
        this.description = description;
        this.quantity = quantity;
    }

    public Item(String code, String description, String bin, int quantity) {
        this.code = code;
        this.description = description;
        this.bin = bin;
        this.quantity = quantity;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getBin() {
        return bin;
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
