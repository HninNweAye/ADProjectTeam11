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
import sa48.team11.adproject.models.AdjItem;

/**
 * Created by hninnwe on 2019-07-31
 */
public class AdjVoucherDetailAdapter extends RecyclerView.Adapter<AdjVoucherDetailAdapter.MyViewHolder> {

    private Context context;
    private List<AdjItem> list = new ArrayList<>();

    public AdjVoucherDetailAdapter(Context context) {
        this.context = context;
    }

    public void updateList(List<AdjItem> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.adapter_adj_row, viewGroup, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        myViewHolder.bind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName,tvQuantity, tvReson;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName =itemView.findViewById(R.id.tv_name);
            tvQuantity =itemView.findViewById(R.id.tv_quantity);
            tvReson =itemView.findViewById(R.id.tv_reason);
        }
        public void bind(AdjItem item) {
            tvName.setText(item.getDescription());
            tvQuantity.setText(item.getQuantityWithSign());
            tvReson.setText(item.getReason());
        }
    }
}
