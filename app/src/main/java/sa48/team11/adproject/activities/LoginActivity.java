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
import android.widget.CheckBox;

import retrofit2.Call;
import sa48.team11.adproject.R;
import sa48.team11.adproject.models.Employee;
import sa48.team11.adproject.models.LoginModel;
import sa48.team11.adproject.retrofit.ApiClient;
import sa48.team11.adproject.retrofit.ApiService;
import sa48.team11.adproject.retrofit.MyRetrofit;
import sa48.team11.adproject.retrofit.ResponseObj;
import sa48.team11.adproject.utils.App;
import sa48.team11.adproject.utils.Constants;
import sa48.team11.adproject.utils.LoginPref;
import sa48.team11.adproject.utils.Utils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    TextInputEditText edtUsername, edtPassword;
    private String userName, password;
    CheckBox chkRemember;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().hasExtra("employee")) {
            Employee em = (Employee) getIntent().getSerializableExtra("employee");
            navigateToActivity(em);
            finish();
        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        loadUI();
    }

    private void loadUI() {
        chkRemember = findViewById(R.id.ch_remember);
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        Button btnSignIn = findViewById(R.id.btn_signin);
        btnSignIn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (isValidateFail()) return;
        login(userName, password);

    }

    private void login(String userName, String password) {
        LoginPref pref = LoginPref.getInstance(this);
        LoginModel obj = new LoginModel(userName, password);

        ApiService service = ApiClient.getAPIService();
        Call<ResponseObj<Employee>> call = service.login(obj);
        call.enqueue(new MyRetrofit<>(this, response -> {
            if (response.isSuccess()) {
                Utils.showAlert(R.string.title_activity_login, R.string.success, LoginActivity.this, (dialog, which) -> {
                    App app = (App) getApplicationContext();
                    app.setUser(response.getResObj());
                    navigateToActivity(response.getResObj());
                });
                if (chkRemember.isChecked()) pref.put(obj);
                else pref.clear();
            } else {
                Utils.showAlert(R.string.title_activity_login, R.string.alert_fail, LoginActivity.this);
            }
        }));

    }

    private void navigateToActivity(Employee e) {
        if(e == null)return;
        if (e.getRole().equals(Constants.ROLE_HEAD)) {
            Utils.goNext(this, DeptHeadDashBoard.class);
        } else if (e.getRole().equals(Constants.ROLE_CLERK)) {
            Utils.goNext(this, ClerkDashBoard.class);
        } else {
            Utils.goNext(this, DeptRepDashBoard.class);
        }
    }

    private boolean isValidateFail() {
        userName = edtUsername.getText().toString();
        if (userName.isEmpty()) {
            TextInputLayout tiUsername = findViewById(R.id.ti_username);
            tiUsername.setError("Please enter user name ");
            return true;
        }

        password = edtPassword.getText().toString();
        if (password.isEmpty()) {
            TextInputLayout tiPassword = findViewById(R.id.ti_password);
            tiPassword.setError("Please enter password ");
            return true;
        }
        return false;
    }

}
