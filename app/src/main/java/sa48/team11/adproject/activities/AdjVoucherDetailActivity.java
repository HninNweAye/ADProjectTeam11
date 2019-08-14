package sa48.team11.adproject.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import sa48.team11.adproject.R;
import sa48.team11.adproject.adapters.AdjVoucherDetailAdapter;
import sa48.team11.adproject.models.AdjItem;
import sa48.team11.adproject.models.AdjVoucher;
import sa48.team11.adproject.retrofit.ApiClient;
import sa48.team11.adproject.retrofit.ApiService;
import sa48.team11.adproject.retrofit.MyRetrofit;
import sa48.team11.adproject.retrofit.ResponseList;
import sa48.team11.adproject.utils.Constants;

public class AdjVoucherDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private AdjVoucher voucher = null;
    private List<AdjItem> itemList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher_detail);
        voucher = (AdjVoucher) getIntent().getExtras().get(Constants.DETAIL);
        loadData();
        loadUI();
    }

    private void loadData() {
        ApiService service = ApiClient.getAPIService();
             Call<ResponseList<AdjItem>> call = service.getAdjVoucherItems(voucher.getVoucherId());
                call.enqueue(new MyRetrofit<>(this, response -> {
                    if (response.isSuccess()) {
                        itemList.clear();
                        itemList.addAll(response.getResultList());
                        renderRecyclerView();
                    }
                }));
    }

    private void loadUI() {
        TextView tvID = findViewById(R.id.tv_id);
        TextView tvStatus = findViewById(R.id.tv_status);
//        TextView tvTotalPrice = findViewById(R.id.tv_total_price);
        tvID.setText(String.format("VoucherID : %03d ", voucher.getVoucherId()));
        tvStatus.setText(String.format("Status    : %s ", voucher.getStatus()));
//        double total = 0;
//        for(AdjItem item : itemList){
//            total+=(item.getPrice()*Math.abs(item.getQuantity()));
//        }
//        tvTotalPrice.setText(String.format("Total Price"));
    }

    private void renderRecyclerView() {
        RecyclerView rc_req_list = findViewById(R.id.rc_adj_list);
        rc_req_list.setHasFixedSize(true);
        rc_req_list.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this, R.anim.recycler_layout_anim));
        rc_req_list.setLayoutManager(new LinearLayoutManager(this));
        AdjVoucherDetailAdapter adapter = new AdjVoucherDetailAdapter(this);
        rc_req_list.setAdapter(adapter);
        adapter.updateList(itemList);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_approve:
                break;
            case R.id.btn_reject:
                break;
        }
    }
}
