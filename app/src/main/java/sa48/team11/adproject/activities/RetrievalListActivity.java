package sa48.team11.adproject.activities;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import sa48.team11.adproject.R;
import sa48.team11.adproject.adapters.RetrievalAdapter;
import sa48.team11.adproject.models.Retrieval;
import sa48.team11.adproject.retrofit.ApiClient;
import sa48.team11.adproject.retrofit.ApiService;
import sa48.team11.adproject.retrofit.BaseResponse;
import sa48.team11.adproject.retrofit.MyRetrofit;
import sa48.team11.adproject.retrofit.ResponseList;
import sa48.team11.adproject.utils.App;
import sa48.team11.adproject.utils.Utils;

public class RetrievalListActivity extends AppCompatActivity {
    private List<Retrieval> retrievals = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieval_list);
        loadUI();
        loadData();
    }

    private void loadUI() {
        Button btnConfirm = findViewById(R.id.btn_confirm);
        btnConfirm.setOnClickListener(v -> {
            submitRetrieval();
        });
    }

    private void submitRetrieval() {
        App app = (App) getApplicationContext();
        ApiService service = ApiClient.getAPIService();
             Call<BaseResponse> call = service.confirmRetrievals(app.getUser().getId(), retrievals);
                call.enqueue(new MyRetrofit<>(this, response -> {
                    if (response.isSuccess()) {
                        Utils.showAlert(R.string.retrieval_list, R.string.retrieval_success, RetrievalListActivity.this, (DialogInterface.OnClickListener) (dialog, which) -> {
                            finish();
                        });
                    }
                }));
    }

    private void loadData() {
    ApiService service = ApiClient.getAPIService();
         Call<ResponseList<Retrieval>> call = service.getRetrievalList();
            call.enqueue(new MyRetrofit<>(this, response -> {
                if (response.isSuccess()) {
                    retrievals.clear();
                    retrievals.addAll(response.getResultList());
                    renderRecyclerView();
                }
            }));
    }
    private void renderRecyclerView() {
        RecyclerView rc_req_list = findViewById(R.id.rc_retrieval_list);
        rc_req_list.setHasFixedSize(true);
        rc_req_list.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this,R.anim.recycler_layout_anim));
        rc_req_list.setLayoutManager(new LinearLayoutManager(this));
        RetrievalAdapter adapter= new RetrievalAdapter(this, (pos,qty)-> {
            retrievals.get(pos).setActualQty(qty);
        });
        rc_req_list.setAdapter(adapter);
        adapter.updateList(retrievals);
    }

}
