package sa48.team11.adproject.models;

/**
 * Created by hninnwe on 2019-07-31
 */
public class RetrievalItem {
    private String name,bin;
    private int needed,actual;

    public RetrievalItem(String name, String bin, int needed, int actual) {
        this.name = name;
        this.bin = bin;
        this.needed = needed;
        this.actual = actual;
    }

    public String getName() {
        return name;
    }

    public String getBin() {
        return bin;
    }

    public int getNeeded() {
        return needed;
    }

    public int getActual() {
        return actual;
    }
}
