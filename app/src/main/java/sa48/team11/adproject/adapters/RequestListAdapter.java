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
import sa48.team11.adproject.activities.ManageRequestActivity;
import sa48.team11.adproject.activities.RequestDetailActivity;
import sa48.team11.adproject.models.Request;
import sa48.team11.adproject.utils.Constants;
import sa48.team11.adproject.utils.Utils;

/**
 * Created by hninnwe on 2019-07-24
 */

public class RequestListAdapter extends RecyclerView.Adapter<RequestListAdapter.MyViewHolder> {
    private Context context;
    private List<Request> reqList = new ArrayList<>();
    private Bundle bdl = new Bundle();

    public RequestListAdapter(Context context) {
        this.context = context;
    }

    public void updateList(List<Request> reqList) {
        this.reqList = reqList;
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

        myViewHolder.bind(reqList.get(position));

        myViewHolder.itemView.setOnClickListener(v ->
        {
            bdl.putSerializable(Constants.DETAIL, reqList.get(position));
            Utils.goNext((ManageRequestActivity) context, RequestDetailActivity.class, bdl);
        });
    }

    @Override
    public int getItemCount() {
        return reqList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvEmpName, tvNoOfItems, tvDate, tvStatus;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEmpName = itemView.findViewById(R.id.tv_emp_name);
            tvNoOfItems = itemView.findViewById(R.id.tv_no_items);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvStatus = itemView.findViewById(R.id.tv_status);
        }

        public void bind(Request request) {
            tvEmpName.setText(String.format("Name    : %s ", request.getEmployeeName()));
            tvNoOfItems.setText(String.format("Number of items : %d", request.getNoOfItems()));
            tvDate.setText(request.getDateString());
            tvStatus.setText(request.getStatus());
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

