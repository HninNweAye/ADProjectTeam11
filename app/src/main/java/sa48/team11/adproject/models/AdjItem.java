package sa48.team11.adproject.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by hninnwe on 2019-07-31
 */
public class AdjItem implements Serializable {
    @SerializedName("ItemId") String itemId; //not necessary but according to web model
    @SerializedName("Description") String description;
    @SerializedName("Quantity") int quantity;
    @SerializedName("Reason") String reason;

    public AdjItem(String itemId, String description, int quantity, String reason) {
        this.itemId = itemId;
        this.description = description;
        this.quantity = quantity;
        this.reason = reason;
    }

    public AdjItem(String description, int quantity, String reason) {
        this.description = description;
        this.quantity = quantity;
        this.reason = reason;
    }

    public String getItemId() {
        return itemId;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }


    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "AdjItem{" +
                "itemId=" + itemId +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", reason=" + reason +
                '}';
    }

    public String getQuantityWithSign() {
        if(quantity > 0){
            return "+"+quantity;
        }
        return ""+quantity;
    }
}
