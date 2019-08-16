package sa48.team11.adproject.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import sa48.team11.adproject.R;
import sa48.team11.adproject.adapters.DisburseItemAdapter;
import sa48.team11.adproject.models.CollectionPoint;
import sa48.team11.adproject.models.ItemDisburse;
import sa48.team11.adproject.retrofit.ApiClient;
import sa48.team11.adproject.retrofit.ApiService;
import sa48.team11.adproject.retrofit.MyRetrofit;
import sa48.team11.adproject.retrofit.ResponseListAndObj;

public class ConfirmDisbursementActivity extends AppCompatActivity {

    private List<ItemDisburse> items = new ArrayList<>();
    private CollectionPoint point = null;
    private TextView tvCollectionPointName, tvCollectionTime, tvListNotReady;
    private Button btnApprove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_disbursement);
        loadUI();
        loadData();
    }

    private void loadData() {
        ApiService service = ApiClient.getAPIService();
        Call<ResponseListAndObj<ItemDisburse, CollectionPoint>> call = service.getDisbursementInfo("COMM");
        call.enqueue(new MyRetrofit<>(this, response -> {

            items = response.getResultList();
            bindCollectionPointInfo(response.getResObj());
            renderRecyclerView();
            if (response.isSuccess()) {
                btnApprove.setVisibility(View.VISIBLE);
            } else {
                btnApprove.setVisibility(View.INVISIBLE);
            }
        }));
    }

    private void bindCollectionPointInfo(CollectionPoint resObj) {
        if (resObj == null) return;

        tvCollectionPointName.setText(String.format("CollectionPoint : %s", resObj.getName()));
        tvCollectionTime.setText(String.format("CollectionTime : %s", resObj.getCollectTime()));
    }

    private void renderRecyclerView() {
        if (items == null) {
            tvListNotReady.setVisibility(View.VISIBLE);
            return;
        }else{
            tvListNotReady.setVisibility(View.GONE);
        }
        RecyclerView rc_req_list = findViewById(R.id.rc_item_list);
        rc_req_list.setHasFixedSize(true);
        rc_req_list.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this, R.anim.recycler_layout_anim));
        rc_req_list.setLayoutManager(new LinearLayoutManager(this));
        DisburseItemAdapter adapter = new DisburseItemAdapter(this);
        rc_req_list.setAdapter(adapter);
        adapter.updateList(items);
    }

    private void loadUI() {
        btnApprove = findViewById(R.id.btn_approve);
        tvCollectionPointName = findViewById(R.id.tv_collection_name);
        tvCollectionTime = findViewById(R.id.tv_collection_time);
        tvListNotReady = findViewById(R.id.tv_list_not_ready);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 1, Menu.NONE, "Refresh").setIcon(R.drawable.ic_refresh)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        loadData();
        Toast.makeText(this, "Refresh", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}
