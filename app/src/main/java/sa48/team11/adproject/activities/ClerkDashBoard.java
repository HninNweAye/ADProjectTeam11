package sa48.team11.adproject.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;

import sa48.team11.adproject.R;
import sa48.team11.adproject.models.Disbursement;
import sa48.team11.adproject.utils.App;
import sa48.team11.adproject.utils.LoginPref;
import sa48.team11.adproject.utils.Utils;

public class ClerkDashBoard extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clerk_dashboard);
        loadUI();
        App app = (App)getApplicationContext();
        Log.d("User",app.getUser().getName());
    }

    private void loadUI() {
        CardView reqCard = findViewById(R.id.card_adj_req);
        CardView stockCard = findViewById(R.id.card_stock_card);
        CardView disburseCard = findViewById(R.id.card_disbursement);
        CardView retrievalCard = findViewById(R.id.card_retrival);
        reqCard.setOnClickListener(this);
        stockCard.setOnClickListener(this);
        disburseCard.setOnClickListener(this);
        retrievalCard.setOnClickListener(this);
        (findViewById(R.id.fab_logout)).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        App app = (App)getApplicationContext();
        Log.d("User Resume",app.getUser().getName());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_adj_req:
                Utils.goNext(ClerkDashBoard.this, AdjVoucherHistoryActivity.class);
                break;
            case R.id.card_stock_card:
                Utils.goNext(ClerkDashBoard.this, StockCardActivity.class);
                break;
            case R.id.card_disbursement:
                Utils.goNext(ClerkDashBoard.this, DisbursementActivity.class);
                break;
            case R.id.card_retrival:
                Utils.goNext(ClerkDashBoard.this,RetrievalListActivity.class);
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
