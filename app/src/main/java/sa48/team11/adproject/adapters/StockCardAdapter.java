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
import sa48.team11.adproject.models.StockCard;
import sa48.team11.adproject.utils.Utils;

/**
 * Created by hninnwe on 2019-07-31
 */
public class StockCardAdapter extends RecyclerView.Adapter<StockCardAdapter.MyViewHolder> {

    private Context context;
    private List<StockCard> list = new ArrayList<>();

    public StockCardAdapter(Context context) {
        this.context = context;
    }

    public void updateList(List<StockCard> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.adapter_stockcard, viewGroup, false);
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
        private TextView tvDate,tvRefType, tvQuantity,tvTotal;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate =itemView.findViewById(R.id.tv_date);
            tvRefType =itemView.findViewById(R.id.tv_ref);
            tvQuantity =itemView.findViewById(R.id.tv_quantity);
            tvTotal =itemView.findViewById(R.id.tv_total);
        }
        public void bind(StockCard card) {
            tvDate.setText(Utils.shortDateString(card.getDate()));
            tvRefType.setText(card.getRefType());
            tvQuantity.setText(card.getQuantity());
            tvTotal.setText(String.format("%s",card.getBalance()));
        }
    }
}
