package sa48.team11.adproject.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

import sa48.team11.adproject.R;
import sa48.team11.adproject.utils.LoginPref;
import sa48.team11.adproject.utils.Utils;

public class DeptRepDashBoard extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dept_rep_dashboard);
        loadUI();
    }

    private void loadUI() {
        CardView disbursementCard = findViewById(R.id.card_confirm_disbursement);
        CardView collectionCard = findViewById(R.id.card_update_collectionpoint);
        disbursementCard.setOnClickListener(this);
        collectionCard.setOnClickListener(this);
        (findViewById(R.id.fab_logout)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card_confirm_disbursement:
                Utils.goNext(DeptRepDashBoard.this, ConfirmDisbursementActivity.class);
                break;
            case R.id.card_update_collectionpoint:
                Utils.goNext(DeptRepDashBoard.this, UpdateCollectionPointActivity.class);
                break;
            case R.id.fab_logout:
                Utils.showAlert(true,R.string.alert_logout, R.string.confirm_logout, this, (dialog, which) -> {
                    LoginPref pref = LoginPref.getInstance(this);
                    pref.clear();
                    finish();
                });
                break;
        }
    }


}
