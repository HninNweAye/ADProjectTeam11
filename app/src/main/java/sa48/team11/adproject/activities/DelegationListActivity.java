package sa48.team11.adproject.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import sa48.team11.adproject.R;
import sa48.team11.adproject.adapters.DelegationListAdapter;
import sa48.team11.adproject.models.Delegation;
import sa48.team11.adproject.retrofit.MyRetrofit;
import sa48.team11.adproject.retrofit.ResponseList;
import sa48.team11.adproject.retrofit.ApiClient;
import sa48.team11.adproject.retrofit.ApiService;
import sa48.team11.adproject.utils.Utils;

public class DelegationListActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtStartDate, edtEndDate;
    private List<Delegation> dataList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delegation_list);
        loadData();
        loadUI();


    }

    private void loadData() {
        ApiService service = ApiClient.getAPIService();
        Call<ResponseList<Delegation>> call = service.getDelegationHistory("COMM");
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
                Utils.showDatePicker(getFragmentManager(), (EditText) v);
                break;
            case R.id.edt_end_date:
                Utils.showDatePicker(getFragmentManager(), (EditText) v);
                break;
            case R.id.fab:
                Utils.goNext(DelegationListActivity.this,NewDelegationActivity.class);
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
        rc_delegation_list.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this,R.anim.recycler_layout_anim));
        rc_delegation_list.setLayoutManager(new LinearLayoutManager(this));
        DelegationListAdapter adapter= new DelegationListAdapter(this);
        rc_delegation_list.setAdapter(adapter);
        adapter.updateList(dataList);
    }

}