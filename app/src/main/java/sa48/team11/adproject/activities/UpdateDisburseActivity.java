package sa48.team11.adproject.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import sa48.team11.adproject.R;
import sa48.team11.adproject.adapters.UpdateDisburseAdapter;
import sa48.team11.adproject.models.Disbursement;
import sa48.team11.adproject.models.Employee;
import sa48.team11.adproject.models.ItemDisburse;
import sa48.team11.adproject.retrofit.ApiClient;
import sa48.team11.adproject.retrofit.ApiService;
import sa48.team11.adproject.retrofit.BaseResponse;
import sa48.team11.adproject.retrofit.MyRetrofit;
import sa48.team11.adproject.utils.App;

public class UpdateDisburseActivity extends AppCompatActivity {
    private List<ItemDisburse> items = new ArrayList<>();
    private List<ItemDisburse> updatedItems = new ArrayList<>();
    private Employee currentUser;
    private Disbursement disbursement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_disburse);

        currentUser = ((App) getApplicationContext()).getUser();
        if (getIntent().hasExtra("data")) {
            disbursement = (Disbursement) getIntent().getSerializableExtra("data");
            items = disbursement.getItems();
        }
        loadUI();

    }

    private void loadUI() {
        TextView tvDeptName = findViewById(R.id.tv_dept_name);
        TextView tvRep = findViewById(R.id.tv_representative);
        tvDeptName.setText(String.format("Department : %s", disbursement.getDeptName()));
        tvRep.setText(String.format("Representative : %s", disbursement.getRepName()));
        (findViewById(R.id.btn_update)).setOnClickListener(v -> submitUpdates());
        renderRecyclerView();
    }

    private void submitUpdates() {
        ApiService service = ApiClient.getAPIService();
        Call<BaseResponse> call = service.updateDisbursementItems(currentUser.getId(), disbursement.getDeptId(), updatedItems);
        call.enqueue(new MyRetrofit<>(this, response -> {
            if (response.isSuccess()) {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this, "Response" + response.isSuccess(), Toast.LENGTH_SHORT).show();
        }));
    }


    private void renderRecyclerView() {
        RecyclerView rc_req_list = findViewById(R.id.rc_retrieval_list);
        rc_req_list.setHasFixedSize(true);
        rc_req_list.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this, R.anim.recycler_layout_anim));
        rc_req_list.setLayoutManager(new LinearLayoutManager(this));
        UpdateDisburseAdapter adapter = new UpdateDisburseAdapter(this, (position, decreaseValue) -> {
            ItemDisburse  original = items.get(position);
            original.setActualQty(decreaseValue);
            updatedItems.add(original);
            Toast.makeText(this, "Update"+original.getActualQty(), Toast.LENGTH_SHORT).show();
//            items.get(position).setActualQty(actual);

        });
        rc_req_list.setAdapter(adapter);
        adapter.updateList(items);
    }


}
