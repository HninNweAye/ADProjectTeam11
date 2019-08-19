package sa48.team11.adproject.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.internal.Util;
import retrofit2.Call;
import sa48.team11.adproject.R;
import sa48.team11.adproject.adapters.RequestListAdapter;
import sa48.team11.adproject.listeners.IDatePickerListener;
import sa48.team11.adproject.models.Employee;
import sa48.team11.adproject.models.Item;
import sa48.team11.adproject.models.Request;
import sa48.team11.adproject.retrofit.ApiClient;
import sa48.team11.adproject.retrofit.ApiService;
import sa48.team11.adproject.retrofit.BaseResponse;
import sa48.team11.adproject.retrofit.MyRetrofit;
import sa48.team11.adproject.retrofit.ResponseList;
import sa48.team11.adproject.retrofit.ResponseObj;
import sa48.team11.adproject.utils.App;
import sa48.team11.adproject.utils.Constants;
import sa48.team11.adproject.utils.Utils;

public class ManageRequestActivity extends AppCompatActivity implements View.OnClickListener, IDatePickerListener {
    private EditText edtStartDate, edtEndDate;
    private Button btnApproveAll;
    private List<Request> dataList = new ArrayList<>();
    private RequestListAdapter adapter;
    private Employee currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_request);
        currentUser =((App) getApplicationContext()).getUser();
        loadUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        btnApproveAll.setVisibility(View.INVISIBLE);

        ApiService service = ApiClient.getAPIService();
             Call<ResponseList<Request>> call = service.getRequestHistory(currentUser.getDepartmentId());
                call.enqueue(new MyRetrofit<>(this, response -> {
                    if (response.isSuccess()) {
                        dataList.clear();
                        dataList.addAll(response.getResultList());
                        renderRecyclerView();
                    }
                }));
    }

    private void loadUI() {
        edtStartDate = findViewById(R.id.edt_start_date);
        edtEndDate = findViewById(R.id.edt_end_date);
        edtStartDate.setOnClickListener(this);
        edtEndDate.setOnClickListener(this);
        btnApproveAll = findViewById(R.id.btn_approve);
        btnApproveAll.setOnClickListener(this);
    }

    private void renderRecyclerView() {
        RecyclerView rc_req_list = findViewById(R.id.rc_req_list);
        rc_req_list.setHasFixedSize(true);
        rc_req_list.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this, R.anim.recycler_layout_anim));
        rc_req_list.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RequestListAdapter(this, () -> {
            if(btnApproveAll.getVisibility() == View.INVISIBLE)btnApproveAll.setVisibility(View.VISIBLE);
        });
        rc_req_list.setAdapter(adapter);
        adapter.updateList(dataList);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.edt_start_date:
                Utils.showDatePicker(getFragmentManager(), (EditText) v, this);
                break;
            case R.id.edt_end_date:
                Utils.showDatePicker(getFragmentManager(), (EditText) v, this);
                break;
            case R.id.btn_approve:
                Utils.showAlert(true, R.string.confirm, R.string.approve_all_confirm, ManageRequestActivity.this, (dialog, which) -> {
                    approveAll();
                });
                break;
        }
    }

    private void approveAll() {
        ApiService service = ApiClient.getAPIService();
             Call<BaseResponse> call = service.approveAllRequest(currentUser.getDepartmentId());
                call.enqueue(new MyRetrofit<>(this, response -> {
                    if (response.isSuccess()) {
                        Utils.showAlert(R.string.alert_approve, R.string.success, ManageRequestActivity.this, (dialog, which) -> {
                            loadData();
                        });
                    }
                }));
    }

    private boolean filterByDate(Date startDate, Date endDate) {

//        List<Request> tempList = dataList.stream().filter(req -> !(req.getDate().after(endDate) || req.getDate().before(startDate))).collect(Collectors.toList());
        List<Request> tempList = new ArrayList<>();
        for (Request req : dataList) {
            if (!(req.getDate().after(endDate) || req.getDate().before(startDate))) {
                tempList.add(req);
            }
        }
        adapter.updateList(tempList);
        return true;
    }


    @Override
    public void dateChange() {
        if (edtStartDate.getText().toString().isEmpty() || edtEndDate.getText().toString().isEmpty())
            return;
        Date startDate = Utils.dateString(edtStartDate.getText().toString());
        Date endDate = Utils.dateString(edtEndDate.getText().toString());
        if (startDate.after(endDate)) {
            Utils.showAlert(R.string.alert_error, R.string.msg_date_error, this);
            return;
        }
        filterByDate(startDate, endDate);
    }
}