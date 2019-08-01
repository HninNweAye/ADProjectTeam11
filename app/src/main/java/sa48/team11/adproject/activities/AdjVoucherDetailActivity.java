package sa48.team11.adproject.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import sa48.team11.adproject.R;
import sa48.team11.adproject.adapters.AdjVoucherDetailAdapter;
import sa48.team11.adproject.models.AdjVoucher;
import sa48.team11.adproject.utils.Constants;

public class AdjVoucherDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private AdjVoucher voucher = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher_detail);
        loadUI();
    }

    private void loadUI() {
        voucher = (AdjVoucher) getIntent().getExtras().get(Constants.DETAIL);
        TextView tvID  = findViewById(R.id.tv_id);
        TextView tvStatus  = findViewById(R.id.tv_status);
        tvID.setText(String.format("VoucherID : %s ", voucher.getVoucherId()));
        tvStatus.setText(String.format("Status    : %s ", voucher.getStatus()));
        renderRecyclerView();
    }
    private void renderRecyclerView() {
        RecyclerView rc_req_list = findViewById(R.id.rc_adj_list);
        rc_req_list.setHasFixedSize(true);
        rc_req_list.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this,R.anim.recycler_layout_anim));
        rc_req_list.setLayoutManager(new LinearLayoutManager(this));
        AdjVoucherDetailAdapter adapter= new AdjVoucherDetailAdapter(this);
        rc_req_list.setAdapter(adapter);
        adapter.updateList(voucher.getItemList());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_approve:
                break;
            case R.id.btn_reject:
                break;
        }
    }
}
