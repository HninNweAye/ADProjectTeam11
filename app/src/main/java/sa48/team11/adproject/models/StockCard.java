package sa48.team11.adproject.models;

/**
 * Created by hninnwe on 2019-07-31
 */
public class StockCard {
    private String date,refType,quantity,total;

    public StockCard(String date, String refType, String quantity, String total) {
        this.date = date;
        this.refType = refType;
        this.quantity = quantity;
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public String getRefType() {
        return refType;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getTotal() {
        return total;
    }
}
