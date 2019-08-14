package sa48.team11.adproject.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sa48.team11.adproject.R;
import sa48.team11.adproject.activities.AdjVoucherDetailActivity;
import sa48.team11.adproject.activities.AdjVoucherHistoryActivity;
import sa48.team11.adproject.models.AdjVoucher;
import sa48.team11.adproject.utils.Constants;
import sa48.team11.adproject.utils.Utils;

/**
 * Created by hninnwe on 2019-07-24
 */

public class AdjustmentListAdapter extends RecyclerView.Adapter<AdjustmentListAdapter.MyViewHolder> {
    private Context context;
    private List<AdjVoucher> voucherList = new ArrayList<>();
    private Bundle bdl = new Bundle();

    public AdjustmentListAdapter(Context context) {
        this.context = context;
    }

    public void updateList(List<AdjVoucher> voucherList) {
        this.voucherList = voucherList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(context).inflate(R.layout.adapter_req_list_row, viewGroup, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

        myViewHolder.bind(voucherList.get(position));

        myViewHolder.itemView.setOnClickListener(v ->
        {
            bdl.putSerializable(Constants.DETAIL, voucherList.get(position));
            Utils.goNext((AdjVoucherHistoryActivity) context, AdjVoucherDetailActivity.class, bdl);
        });
    }

    @Override
    public int getItemCount() {
        return voucherList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvVoucherId,  tvDate, tvStatus,tvNoItems;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvVoucherId = itemView.findViewById(R.id.tv_emp_name);
            tvDate = itemView.findViewById(R.id.tv_no_items);
            tvStatus = itemView.findViewById(R.id.tv_status);
            tvNoItems = itemView.findViewById(R.id.tv_no_items);
        }

        public void bind(AdjVoucher request) {
            tvVoucherId.setText(String.format("VoucherID  : %03d ", request.getVoucherId()));
            tvDate.setText(request.getDateString());
            tvStatus.setText(request.getStatus());
            tvNoItems.setText(String.format("Total No of Items : %d",request.getTotalQuantity()));
            changeTextColor(request.getStatus());

        }

        private void changeTextColor(String status) {
            switch (status) {
                case Constants.PENDING:
                    tvStatus.setTextColor(context.getColor(R.color.status_pending));
                    break;
                case Constants.APPROVED:
                    tvStatus.setTextColor(context.getColor(R.color.status_appproved));
                    break;
                case Constants.REJECT:
                    tvStatus.setTextColor(context.getColor(R.color.status_reject));
                    break;
            }
        }
    }


}

