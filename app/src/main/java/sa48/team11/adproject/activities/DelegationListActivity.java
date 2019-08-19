package sa48.team11.adproject.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import sa48.team11.adproject.R;
import sa48.team11.adproject.adapters.DelegationListAdapter;
import sa48.team11.adproject.listeners.IDatePickerListener;
import sa48.team11.adproject.listeners.IDelegationCancelListener;
import sa48.team11.adproject.models.Delegation;
import sa48.team11.adproject.models.Employee;
import sa48.team11.adproject.models.Request;
import sa48.team11.adproject.retrofit.ApiClient;
import sa48.team11.adproject.retrofit.ApiService;
import sa48.team11.adproject.retrofit.MyRetrofit;
import sa48.team11.adproject.retrofit.ResponseList;
import sa48.team11.adproject.utils.App;
import sa48.team11.adproject.utils.Utils;

public class DelegationListActivity extends AppCompatActivity implements View.OnClickListener, IDatePickerListener {
    private EditText edtStartDate, edtEndDate;
    private List<Delegation> dataList = new ArrayList<>();
    private Employee currentUser;
    private DelegationListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delegation_list);
        currentUser = ((App) getApplicationContext()).getUser();
        loadData();
        loadUI();


    }

    private void loadData() {
        findViewById(R.id.fab).setVisibility(View.VISIBLE);

        ApiService service = ApiClient.getAPIService();
        Call<ResponseList<Delegation>> call = service.getDelegationHistory(currentUser.getDepartmentId());
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
        findViewById(R.id.fab).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.edt_start_date:
                Utils.showDatePicker(getFragmentManager(), (EditText) v,this);
                break;
            case R.id.edt_end_date:
                Utils.showDatePicker(getFragmentManager(), (EditText) v,this);
                break;
            case R.id.fab:
                Utils.goNext(DelegationListActivity.this, NewDelegationActivity.class);
                break;
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        loadData();
    }

    private void renderRecyclerView() {
        RecyclerView rc_delegation_list = findViewById(R.id.rc_delegation_list);
        rc_delegation_list.setHasFixedSize(true);
        rc_delegation_list.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this, R.anim.recycler_layout_anim));
        rc_delegation_list.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DelegationListAdapter(this, new IDelegationCancelListener() {
            @Override
            public void cancelDelegation(int pos) {
//                dataList.get(pos).setStatus(false);
//                findViewById(R.id.fab).setVisibility(View.VISIBLE);
                loadData();
            }

            @Override
            public void hideCreateDelegationButton() {
                findViewById(R.id.fab).setVisibility(View.GONE);
            }
        });
        rc_delegation_list.setAdapter(adapter);
        adapter.updateList(dataList);
    }


    private boolean filterByDate(Date startDate, Date endDate) {
        List<Delegation> tempList = new ArrayList<>();
        for (Delegation req : dataList) {
            if (!(req.getStartDate().after(endDate) || req.getStartDate().before(startDate))) {
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