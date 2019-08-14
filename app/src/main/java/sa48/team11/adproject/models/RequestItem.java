package sa48.team11.adproject.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by hninnwe on 2019-07-31
 */
public class RequestItem implements Serializable {
    @SerializedName("Description") private String description;
    @SerializedName("Quantity") private int quantity;


    public RequestItem(String description, int quantity) {
        this.description = description;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
