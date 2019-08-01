package sa48.team11.adproject.models;

import java.io.Serializable;

/**
 * Created by hninnwe on 2019-07-31
 */
public class AdjItem implements Serializable {
    private String code,quantity,reason;

    public AdjItem(String code, String quantity, String reason) {
        this.code = code;
        this.quantity = quantity;
        this.reason = reason;
    }

    public String getCode() {
        return code;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getReason() {
        return reason;
    }
}
