package sa48.team11.adproject.models;

import com.google.gson.annotations.SerializedName;

public class LoginModel {

    @SerializedName("UserName")
    private String userName;
    @SerializedName("Password")
    private String password;

    public LoginModel(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
