package sa48.team11.adproject.models;

import java.io.Serializable;

/**
 * Created by hninnwe on 2019-07-31
 */
public class Item implements Serializable {
    private String code,name;
    private int quantity;

    public Item(String code, String name, int quantity) {
        this.code = code;
        this.name = name;
        this.quantity = quantity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return getName();
    }
}
