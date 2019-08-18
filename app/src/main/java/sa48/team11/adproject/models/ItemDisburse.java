
package sa48.team11.adproject.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@SuppressWarnings("unused")
public class ItemDisburse implements Serializable {

    @SerializedName("ActualQty")
    private int actualQty;
    @SerializedName("Description")
    private String description;
    @SerializedName("ItemId")
    private String itemId;
    @SerializedName("NeededQty")
    private int neededQty;

    public ItemDisburse(int actualQty, String description, String itemId, int neededQty) {
        this.actualQty = actualQty;
        this.description = description;
        this.itemId = itemId;
        this.neededQty = neededQty;
    }

    public int getActualQty() {
        return actualQty;
    }

    public void setActualQty(int actualQty) {
        this.actualQty = actualQty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getNeededQty() {
        return neededQty;
    }

    public void setNeededQty(int neededQty) {
        this.neededQty = neededQty;
    }

    @Override
    public String toString() {
        return "ItemDisburse{" +
                "actualQty=" + actualQty +
                ", description='" + description + '\'' +
                ", itemId='" + itemId + '\'' +
                ", neededQty=" + neededQty +
                '}';
    }
}
