package sa48.team11.adproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import sa48.team11.adproject.R;
import sa48.team11.adproject.utils.Utils;

public class DeptHeadDashBoard extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dept_head_dashboard);
        loadUI();
    }

    private void loadUI() {
        CardView reqCard = findViewById(R.id.card_manage_request);
        CardView delegateCard = findViewById(R.id.card_manage_delegation);
        CardView collectionCard = findViewById(R.id.card_manage_collection);
        reqCard.setOnClickListener(this);
        delegateCard.setOnClickListener(this);
        collectionCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_manage_request:
                Utils.goNext(DeptHeadDashBoard.this, ManageRequestActivity.class);
                break;
            case R.id.card_manage_delegation:
                Utils.goNext(DeptHeadDashBoard.this, DelegationListActivity.class);
                break;
            case R.id.card_manage_collection:
                Utils.goNext(DeptHeadDashBoard.this, ManageCollectionActivity.class);
                break;

        }
    }


}
