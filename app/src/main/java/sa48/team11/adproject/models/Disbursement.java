
package sa48.team11.adproject.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Disbursement {

    @SerializedName("CollectionPointID")
    private Long collectionPointID;
    @SerializedName("DeptId")
    private String deptId;
    @SerializedName("DeptName")
    private String deptName;
    @SerializedName("Phone")
    private Object phone;
    @SerializedName("RepName")
    private String repName;
    @SerializedName("ItemList")
    private List<ItemDisburse> items ;

    private boolean isItemAlreadyAdd;

    public void setItemAlreadyAdd(boolean itemAlreadyAdd) {
        isItemAlreadyAdd = itemAlreadyAdd;
    }

    public boolean isItemAlreadyAdd() {
        return isItemAlreadyAdd;
    }

    public Disbursement(Long collectionPointID, String deptId, String deptName, Object phone, String repName, List<ItemDisburse> items) {
        this.collectionPointID = collectionPointID;
        this.deptId = deptId;
        this.deptName = deptName;
        this.phone = phone;
        this.repName = repName;
        this.items = items;
    }

    public Long getCollectionPointID() {
        return collectionPointID;
    }

    public void setCollectionPointID(Long collectionPointID) {
        this.collectionPointID = collectionPointID;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public Object getPhone() {
        return phone;
    }

    public String getRepName() {
        return repName;
    }

    public List<ItemDisburse> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Disbursement{" +
                "collectionPointID=" + collectionPointID +
                ", deptId='" + deptId + '\'' +
                ", items=" + items +
                '}';
    }
}
