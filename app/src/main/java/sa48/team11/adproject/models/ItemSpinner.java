package sa48.team11.adproject.models;

import com.google.gson.annotations.SerializedName;

public class ItemSpinner {
    @SerializedName("Id") private String itemId;
    @SerializedName("CategoryId") private int categoryId;
    @SerializedName("Description") private String description;

    public ItemSpinner(String itemId, int categoryId, String description) {
        this.itemId = itemId;
        this.categoryId = categoryId;
        this.description = description;
    }

    public String getItemId() {
        return itemId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)",itemId,description);
    }
}
