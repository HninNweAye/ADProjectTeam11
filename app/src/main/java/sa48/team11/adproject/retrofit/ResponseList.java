package sa48.team11.adproject.retrofit;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseList<T1> extends BaseResponse {

    @SerializedName("ResList") private List<T1> resultList;

    public ResponseList(boolean success, String message, List<T1> resultList) {
        super(success,message);
        this.resultList = resultList;
    }


    public List<T1> getResultList() {
        return resultList;
    }

    @Override
    public String toString() {
        return "ResponseList{" +
                "resultList=" + resultList +
                ", success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
