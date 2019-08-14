package sa48.team11.adproject.retrofit;


import com.google.gson.annotations.SerializedName;

public class BaseResponse {
    @SerializedName("Success") protected boolean success;
    @SerializedName("Message") protected String message;

    public BaseResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
