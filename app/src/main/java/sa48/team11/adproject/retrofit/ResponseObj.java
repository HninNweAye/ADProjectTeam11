package sa48.team11.adproject.retrofit;

import com.google.gson.annotations.SerializedName;

public class ResponseObj<T> extends BaseResponse {
    @SerializedName("ResObj") private T resObj;


    public ResponseObj(boolean success, String message, T resObj) {
        super(success, message);
        this.resObj = resObj;
    }

    public T getResObj() {
        return resObj;
    }

    @Override
    public String toString() {
        return "ResponseObj{" +
                "resObj=" + resObj +
                ", success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}

