package sa48.team11.adproject.activities;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import sa48.team11.adproject.R;
import sa48.team11.adproject.adapters.ReqDetailAdapter;
import sa48.team11.adproject.adapters.RequestListAdapter;
import sa48.team11.adproject.models.Request;
import sa48.team11.adproject.models.RequestItem;
import sa48.team11.adproject.retrofit.ApiClient;
import sa48.team11.adproject.retrofit.ApiService;
import sa48.team11.adproject.retrofit.BaseResponse;
import sa48.team11.adproject.retrofit.MyRetrofit;
import sa48.team11.adproject.retrofit.ResponseList;
import sa48.team11.adproject.retrofit.ResponseObj;
import sa48.team11.adproject.utils.Constants;
import sa48.team11.adproject.utils.Utils;

public class RequestDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private Request request = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_detail);
        loadData();
        loadUI();
    }

    private void loadData() {
        ApiService service = ApiClient.getAPIService();
             Call<ResponseList<RequestItem>> call = service.getRequestDetails("COMM-111-100");
                call.enqueue(new MyRetrofit<>(this, response -> {
                    if (response.isSuccess()) {
                        List<RequestItem> dataList = response.getResultList();
                        renderRecyclerView(dataList);
                    }
                }));
    }

    private void loadUI() {
        request = (Request) getIntent().getExtras().get(Constants.DETAIL);
        LinearLayout buttonLayout = findViewById(R.id.button_layout);
        TextView tvName  = findViewById(R.id.tv_name);
        tvName.setText(String.format("Name    : %s ", request.getEmployeeName()));
        if (request.getStatus().equals(Constants.PENDING)){buttonLayout.setVisibility(View.VISIBLE);}
        findViewById(R.id.btn_approve).setOnClickListener(this);
        findViewById(R.id.btn_reject).setOnClickListener(this);
    }
    private void renderRecyclerView(List<RequestItem> list) {
        RecyclerView rc_req_list = findViewById(R.id.rc_req_list);
        rc_req_list.setHasFixedSize(true);
        rc_req_list.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this,R.anim.recycler_layout_anim));
        rc_req_list.setLayoutManager(new LinearLayoutManager(this));
        ReqDetailAdapter adapter= new ReqDetailAdapter(this);
        rc_req_list.setAdapter(adapter);
        adapter.updateList(list);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_approve:
                updateRequestStatus(R.string.approve_confirm,Constants.APPROVED);
                break;
            case R.id.btn_reject:
                updateRequestStatus(R.string.reject_confirm,Constants.REJECT);
                break;
        }
    }

    private void updateRequestStatus(int confirmMessage,String status) {
        Utils.showAlert(true,R.string.alert_confirm,confirmMessage, RequestDetailActivity.this, (dialog, which) -> submitStatus(status));

    }

    private void submitStatus(String status) {
        request.setStatus(status);
        ApiService service = ApiClient.getAPIService();
        Call<BaseResponse> call = service.updateRequestStatus("COMM",request);
        call.enqueue(new MyRetrofit<>(this, response -> {
            if (response.isSuccess()) {
                Utils.showAlert(R.string.manage_request, R.string.success, RequestDetailActivity.this, (dialog, which) -> finish());
            }
        }));
    }
}
