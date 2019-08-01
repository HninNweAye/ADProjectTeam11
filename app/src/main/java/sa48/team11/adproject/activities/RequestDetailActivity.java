package sa48.team11.adproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import sa48.team11.adproject.R;
import sa48.team11.adproject.adapters.ReqDetailAdapter;
import sa48.team11.adproject.adapters.RequestListAdapter;
import sa48.team11.adproject.models.Request;
import sa48.team11.adproject.utils.Constants;

public class RequestDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private Request request = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_detail);
        loadUI();
    }

    private void loadUI() {
        request = (Request) getIntent().getExtras().get(Constants.DETAIL);
        LinearLayout buttonLayout = findViewById(R.id.button_layout);
        TextView tvName  = findViewById(R.id.tv_name);
        tvName.setText(String.format("Name    : %s ", request.getEmployeeName()));
        if (request.getStatus().equals(Constants.PENDING)){buttonLayout.setVisibility(View.VISIBLE);}
        findViewById(R.id.btn_approve).setOnClickListener(this);
        findViewById(R.id.btn_reject).setOnClickListener(this);
        renderRecyclerView();
    }
    private void renderRecyclerView() {
        RecyclerView rc_req_list = findViewById(R.id.rc_req_list);
        rc_req_list.setHasFixedSize(true);
        rc_req_list.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this,R.anim.recycler_layout_anim));
        rc_req_list.setLayoutManager(new LinearLayoutManager(this));
        ReqDetailAdapter adapter= new ReqDetailAdapter(this);
        rc_req_list.setAdapter(adapter);
        adapter.updateList(request.getItemList());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_approve:
                break;
            case R.id.btn_reject:
                break;
        }
    }
}
