package sa48.team11.adproject.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import sa48.team11.adproject.R;
import sa48.team11.adproject.utils.Constants;
import sa48.team11.adproject.utils.Utils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    TextInputEditText edtUsername, edtPassword;
    private String userName,password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        loadUI();
    }

    private void loadUI() {

        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);

        Button btnSignIn = findViewById(R.id.btn_signin);
        btnSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(isValidateFail())return;
        if(userName.equals(Constants.ROLE_HEAD)) {
            Utils.goNext(this, DeptHeadDashBoard.class);
        }else if(userName.equals(Constants.ROLE_CLERK)){
            Utils.goNext(this, ClerkDashBoard.class);
        }else{
            Utils.goNext(this, DeptRepDashBoard.class);
        }
    }

    private boolean isValidateFail() {
         userName = edtUsername.getText().toString();
        if(userName.isEmpty()){
           TextInputLayout tiUsername = findViewById(R.id.ti_username);
            tiUsername.setError("Please enter user name ");
            return  true;
        }

         password = edtPassword.getText().toString();
//        if(password.isEmpty()){
//            TextInputLayout tiPassword = findViewById(R.id.ti_password);
//            tiPassword.setError("Please enter password ");
//            return true;
//        }
        return false;
    }

}
