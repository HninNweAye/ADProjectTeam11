package sa48.team11.adproject.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sa48.team11.adproject.R;
import sa48.team11.adproject.models.Item;
import sa48.team11.adproject.models.Request;
import sa48.team11.adproject.models.RequestItem;

/**
 * Created by hninnwe on 2019-07-31
 */
public class ReqDetailAdapter extends RecyclerView.Adapter<ReqDetailAdapter.MyViewHolder> {

    private Context context;
    private List<RequestItem> itemList = new ArrayList<>();

    public ReqDetailAdapter(Context context) {
        this.context = context;
    }

    public void updateList(List<RequestItem> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.adapter_req_detail, viewGroup,false);
        return new MyViewHolder(v);    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        myViewHolder.bind(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvQuantity;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName =itemView.findViewById(R.id.tv_name);
            tvQuantity =itemView.findViewById(R.id.tv_quantity);
        }
        public void bind(RequestItem item) {
            tvName.setText(item.getDescription());
            tvQuantity.setText(String.format("%d",item.getQuantity()));
        }
    }
}

