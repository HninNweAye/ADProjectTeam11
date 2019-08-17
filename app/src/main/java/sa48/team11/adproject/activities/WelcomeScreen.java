package sa48.team11.adproject.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sa48.team11.adproject.R;
import sa48.team11.adproject.listeners.IMyAnimFinishListener;
import sa48.team11.adproject.models.Employee;
import sa48.team11.adproject.models.LoginModel;
import sa48.team11.adproject.retrofit.ApiClient;
import sa48.team11.adproject.retrofit.ApiService;
import sa48.team11.adproject.retrofit.ResponseObj;
import sa48.team11.adproject.utils.App;
import sa48.team11.adproject.utils.LoginPref;
import sa48.team11.adproject.utils.TypeWriter;
import sa48.team11.adproject.utils.Utils;

public class WelcomeScreen extends AppCompatActivity implements IMyAnimFinishListener {
    private TypeWriter tvName;
    private ImageView ivStationery;
    int i = 0;

    private boolean isAnimFinish = false, isFail,isSuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        loadUI();
        loadData();
    }

    private void loadData() {
        LoginPref pref = LoginPref.getInstance(this);
        LoginModel obj = pref.get();
        if(obj == null){ isFail =true; return;}
        ApiService service = ApiClient.getAPIService();
        Call<ResponseObj<Employee>> call = service.login(obj);
        call.enqueue(new Callback<ResponseObj<Employee>>() {
            @Override
            public void onResponse(Call<ResponseObj<Employee>> call, Response<ResponseObj<Employee>> res) {
                ResponseObj<Employee> response = res.body();
                Log.d("Retrofit ","Welcome"+res.body().toString());
                if (res.body() == null && isAnimFinish) {
                    Utils.goNext(WelcomeScreen.this, LoginActivity.class,true);
                    return;
                }
                if (response.isSuccess() && isAnimFinish) {
                    App app = (App) getApplicationContext();
                    app.setUser(response.getResObj());
                    Bundle bdl = new Bundle();
                    bdl.putSerializable("employee", response.getResObj());
                    Utils.goNext(WelcomeScreen.this, LoginActivity.class, bdl);
                    WelcomeScreen.this.finish();
                } else {
                    Utils.goNext(WelcomeScreen.this, LoginActivity.class,true);
                }
                isSuccess = true;
            }

            @Override
            public void onFailure(Call<ResponseObj<Employee>> call, Throwable t) {
                isFail = true;
                if(isAnimFinish)
                Utils.showAlert(R.string.alert_sorry, R.string.alert_something_worng, WelcomeScreen.this, (dialog, which) -> Utils.goNext(WelcomeScreen.this, LoginActivity.class));
            }
        });
    }


    private void loadUI() {
        tvName = findViewById(R.id.tv_name);
        ivStationery = findViewById(R.id.iv_stationery);

        Animation anim = new AnimationUtils().loadAnimation(this, R.anim.iv_anim);

        ivStationery.startAnimation(anim);

        tvName.setText("");
        tvName.setCharacterDelay(150);
        tvName.animateText("Logic University ", this);

    }

    @Override
    public void animFinish() {
        isAnimFinish = true;
        if (isFail | isSuccess){
            Log.d("Welcome","Go");
            Utils.goNext(WelcomeScreen.this,LoginActivity.class);
            finish();
        }
    }

}
