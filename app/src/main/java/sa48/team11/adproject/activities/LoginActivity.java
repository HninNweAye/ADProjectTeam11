package sa48.team11.adproject.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import sa48.team11.adproject.R;
import sa48.team11.adproject.utils.Utils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
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
        Button btnSignIn = findViewById(R.id.btn_signin);
        btnSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Utils.goNext(this,ClerkDashBoard.class);
    }

}
