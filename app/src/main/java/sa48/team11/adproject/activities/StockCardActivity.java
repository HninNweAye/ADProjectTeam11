package sa48.team11.adproject.activities;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import sa48.team11.adproject.R;
import sa48.team11.adproject.adapters.StockCardAdapter;
import sa48.team11.adproject.models.Item;
import sa48.team11.adproject.retrofit.MyRetrofit;
import sa48.team11.adproject.retrofit.ResponseListAndObj;
import sa48.team11.adproject.models.StockCard;
import sa48.team11.adproject.retrofit.ApiClient;
import sa48.team11.adproject.retrofit.ApiService;
import sa48.team11.adproject.utils.Utils;

public class StockCardActivity extends AppCompatActivity implements View.OnClickListener {
    private List<StockCard> list = new ArrayList<>();
    private TextView   tvCode,tvBin,tvName;
    private EditText edtSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_card);
        loadData("P085");
        loadUI();
    }

    private void loadData(String itemCode) {
        ApiService service = ApiClient.getAPIService();
             Call<ResponseListAndObj<StockCard, Item>> call = service.getStockCard(itemCode);
                call.enqueue(new MyRetrofit<>(this, response -> {
                    if (response.isSuccess()) {
                        list = response.getResultList();
                        bindData(response.getResObj());
                        if(list != null) renderRecyclerView();
                    }else{
                        Utils.showAlert(R.string.alert_error,R.string.item_code_error,StockCardActivity.this);
                    }
                }));
    }

    private void bindData(Item resObj) {
        tvCode.setText(String.format("ItemCode    : %s ",resObj.getCode()));
        tvName.setText(String.format("Name    : %s ", resObj.getDescription()));
        tvBin.setText(String.format("Bin      : #%s ", resObj.getBin()));
    }


    private void loadUI() {
         tvName  = findViewById(R.id.tv_name);
         tvCode  = findViewById(R.id.tv_code);
         tvBin  = findViewById(R.id.tv_bin);
        edtSearch = findViewById(R.id.edt_search);
        edtSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                loadData(edtSearch.getText().toString());
               // performSearch();
                return true;
            }
            return false;
        });

    }

    private void renderRecyclerView() {
        RecyclerView rc_req_list = findViewById(R.id.rc_stock_card);
        rc_req_list.setHasFixedSize(true);
        rc_req_list.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this,R.anim.recycler_layout_anim));
        rc_req_list.setLayoutManager(new LinearLayoutManager(this));
        StockCardAdapter adapter= new StockCardAdapter(this);
        rc_req_list.setAdapter(adapter);
        adapter.updateList(list);
    }
    @Override
    public void onClick(View v) {

    }
}
