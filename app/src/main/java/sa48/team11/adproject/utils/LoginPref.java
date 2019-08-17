package sa48.team11.adproject.utils;

import android.content.Context;
import android.content.SharedPreferences;

import sa48.team11.adproject.models.LoginModel;

public class LoginPref {
    private SharedPreferences pref;
    private final String FILE = "LoginPref";
    private static LoginPref loginPref;
    private final String KEY_USERNAME = "userName";
    private final String KEY_PASSWORD = "password";

    /**/
    private LoginPref(Context context) {
        if (pref == null) {
            pref = context.getSharedPreferences(FILE, Context.MODE_PRIVATE);
        }
    }

    public static LoginPref getInstance(Context context) {
        if (loginPref == null) {
            loginPref = new LoginPref(context);
        }
        return loginPref;
    }

    public void put(LoginModel user) {
        pref.edit().putString(KEY_USERNAME, user.getUserName())
                .putString(KEY_PASSWORD, user.getPassword()).commit();
    }

    public LoginModel get() {
        LoginModel user = new LoginModel(pref.getString(KEY_USERNAME, ""), pref.getString(KEY_PASSWORD, ""));
        if(user.getUserName().isEmpty()){
            return null;
        }else{
            return user;
        }
    }

    public void clear(){
        pref.edit().clear().commit();
    }

}
