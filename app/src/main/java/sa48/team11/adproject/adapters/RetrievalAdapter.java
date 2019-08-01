package sa48.team11.adproject.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sa48.team11.adproject.R;
import sa48.team11.adproject.models.RetrievalItem;

/**
 * Created by hninnwe on 2019-07-31
 */
public class RetrievalAdapter extends RecyclerView.Adapter<RetrievalAdapter.MyViewHolder> {

    private Context context;
    private List<RetrievalItem> list = new ArrayList<>();

    public RetrievalAdapter(Context context) {
        this.context = context;
    }

    public void updateList(List<RetrievalItem> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.adapter_retrieval_list, viewGroup, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        myViewHolder.bind(list.get(position));
        myViewHolder.ibtnEdit.setOnClickListener(v->System.out.println("Edit"));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName,tvBin, tvNeeded,tvActual;
        private ImageButton ibtnEdit;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName =itemView.findViewById(R.id.tv_name);
            tvBin =itemView.findViewById(R.id.tv_bin);
            tvNeeded =itemView.findViewById(R.id.tv_needed);
            tvActual =itemView.findViewById(R.id.tv_actual);
            ibtnEdit =itemView.findViewById(R.id.ibtn_edit);
        }
        public void bind(RetrievalItem item) {
            tvName.setText(item.getName());
            tvBin.setText(item.getBin());
            tvNeeded.setText(String.format("%d",item.getNeeded()));
            tvActual.setText(String.format("%d",item.getActual()));
        }
    }
}
