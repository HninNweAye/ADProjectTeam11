package sa48.team11.adproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import sa48.team11.adproject.R;

public class ManageCollectionActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvLastUpdate,tv_dept_rep,tv_collection;
    private Spinner spin_collection,spin_rep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_collection);
        loadUI();
    }

    private void loadUI() {

        tvLastUpdate = findViewById(R.id.tvLastUpdate);
        tv_dept_rep = findViewById(R.id.tv_representative);
        tv_collection = findViewById(R.id.tv_collection);

        spin_collection = findViewById(R.id.spin_collection);
        spin_rep = findViewById(R.id.spin_representative);

        findViewById(R.id.btn_cancel).setOnClickListener(this);
        findViewById(R.id.btn_submit).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_submit:
                break;
            case R.id.btn_cancel:
                finish();
                break;
        }
    }
}
