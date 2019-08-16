
package sa48.team11.adproject.models;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ItemDisburse {

    @SerializedName("ActualQty")
    private Long actualQty;
    @SerializedName("Description")
    private String description;
    @SerializedName("ItemId")
    private String itemId;
    @SerializedName("NeededQty")
    private Long neededQty;

    public ItemDisburse(Long actualQty, String description, String itemId, Long neededQty) {
        this.actualQty = actualQty;
        this.description = description;
        this.itemId = itemId;
        this.neededQty = neededQty;
    }

    public Long getActualQty() {
        return actualQty;
    }

    public void setActualQty(Long actualQty) {
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

    public Long getNeededQty() {
        return neededQty;
    }

    public void setNeededQty(Long neededQty) {
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
