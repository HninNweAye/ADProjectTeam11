package sa48.team11.adproject.retrofit;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseListAndObj<ListType,ObjType> extends BaseResponse {

    @SerializedName("ResList") @Expose private List<ListType> resultList;
    @SerializedName("ResObj")  @Expose private ObjType resObj;

    public ResponseListAndObj(boolean success, String message, List<ListType> resultList, ObjType resObj) {
        super(success,message);
        this.resultList = resultList;
        this.resObj = resObj;
    }


    public ObjType getResObj() {
        return resObj;
    }

    public List<ListType> getResultList() {
        return resultList;
    }

    @Override
    public String toString() {
        return "ResponseListAndObj{" +
                "resultList=" + resultList +
                ", resObj=" + resObj +
                ", resObj=" + resObj +
                '}';
    }
}
