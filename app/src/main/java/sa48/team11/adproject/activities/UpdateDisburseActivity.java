package sa48.team11.adproject.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import sa48.team11.adproject.R;
import sa48.team11.adproject.adapters.DisburseItemAdapter;
import sa48.team11.adproject.models.CollectionPoint;
import sa48.team11.adproject.models.Employee;
import sa48.team11.adproject.models.ItemDisburse;
import sa48.team11.adproject.models.Retrieval;
import sa48.team11.adproject.retrofit.ApiClient;
import sa48.team11.adproject.retrofit.ApiService;
import sa48.team11.adproject.retrofit.MyRetrofit;
import sa48.team11.adproject.retrofit.ResponseList;
import sa48.team11.adproject.retrofit.ResponseListAndObj;
import sa48.team11.adproject.utils.App;

public class UpdateDisburseActivity extends AppCompatActivity {
    private List<ItemDisburse> items = new ArrayList<>();
    private Employee currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_disburse);
        currentUser =((App) getApplicationContext()).getUser();


     //   loadData();
//        itemList.add(new Retrieval("Pencil 2B with Eraser End",16,16));
//        itemList.add(new Retrieval("Pen with Eraser",10,10));
        renderRecyclerView();
    }

    private void loadData() {
        ApiService service = ApiClient.getAPIService();
        Call<ResponseListAndObj<ItemDisburse, CollectionPoint>> call = service.getDisbursementInfo(currentUser.getDepartmentId());
        call.enqueue(new MyRetrofit<>(this, response -> {

            items = response.getResultList();
            renderRecyclerView();

        }));
    }

    private void renderRecyclerView() {
        RecyclerView rc_req_list = findViewById(R.id.rc_retrieval_list);
        rc_req_list.setHasFixedSize(true);
        rc_req_list.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this,R.anim.recycler_layout_anim));
        rc_req_list.setLayoutManager(new LinearLayoutManager(this));
        DisburseItemAdapter adapter= new DisburseItemAdapter(this);
        rc_req_list.setAdapter(adapter);
        adapter.updateList(items);
    }

}
