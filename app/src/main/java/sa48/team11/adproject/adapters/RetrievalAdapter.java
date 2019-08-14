package sa48.team11.adproject.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sa48.team11.adproject.R;
import sa48.team11.adproject.listeners.IRetrievalActualChangeListener;
import sa48.team11.adproject.models.Retrieval;

/**
 * Created by hninnwe on 2019-07-31
 */
public class RetrievalAdapter extends RecyclerView.Adapter<RetrievalAdapter.MyViewHolder> {

    private Context context;
    private List<Retrieval> list = new ArrayList<>();
    private LayoutInflater inflater;
    private  IRetrievalActualChangeListener listener;
    public RetrievalAdapter(Context context, IRetrievalActualChangeListener listener) {
        this.context = context;
        this.listener = listener;
        inflater = LayoutInflater.from(context);
    }

    public void updateList(List<Retrieval> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v =inflater.inflate(R.layout.adapter_retrieval_list, viewGroup, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        myViewHolder.bind(list.get(position));
        myViewHolder.ibtnEdit.setOnClickListener(v->showInputDialog(position));

    }

    private void showInputDialog(int position) {
        Retrieval retrieval = list.get(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle);
        builder.setTitle("Retrieval");
        View v  = inflater.inflate(R.layout.dialog_retrieval,null);
        TextView tv_item = v.findViewById(R.id.tv_item);
        EditText edt_total = v.findViewById(R.id.edt_total);
        EditText edt_actual = v.findViewById(R.id.edt_actual);
        tv_item.setText(String.format("Item : %s",retrieval.getDescription()));
        edt_total.setText(""+retrieval.getQty());
        edt_actual.setText(""+retrieval.getActualQty());
        builder.setView(v);
        builder.setIcon(R.drawable.logo);
        builder.setCancelable(false);
        builder.setNegativeButton("Cancel",null);
        builder.setPositiveButton("Ok", (dialog, which) -> {
            dialog.dismiss();
            if(TextUtils.isDigitsOnly(edt_actual.getText().toString())){
                int actual = Integer.parseInt(edt_actual.getText().toString());
                list.get(position).setActualQty(actual);
                notifyDataSetChanged();
                listener.updateActual(position,actual);
            }else{
                TextInputLayout tiActual = v.findViewById(R.id.ti_actual);
                tiActual.setError("Please add Actual amount");
            }
        });
        builder.show();
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
        public void bind(Retrieval item) {
            tvName.setText(item.getDescription());
            tvBin.setText(String.format("#%s",item.getBinNo()));
            tvNeeded.setText(String.format("%d",item.getQty()));
            tvActual.setText(String.format("%d",item.getActualQty()));
        }
    }
}
