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
import sa48.team11.adproject.listeners.IActualValueChangeListener;
import sa48.team11.adproject.models.ItemDisburse;

/**
 * Created by hninnwe on 2019-07-31
 */
public class UpdateDisburseAdapter extends RecyclerView.Adapter<UpdateDisburseAdapter.MyViewHolder> {

    private Context context;
    private List<ItemDisburse> list = new ArrayList<>();
    private LayoutInflater inflater;
    private IActualValueChangeListener listener;

    public UpdateDisburseAdapter(Context context, IActualValueChangeListener listener) {
        this.context = context;
        this.listener = listener;
        inflater = LayoutInflater.from(context);
    }

    public void updateList(List<ItemDisburse> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = inflater.inflate(R.layout.adapter_update_disburse_list, viewGroup, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        myViewHolder.bind(list.get(position));
        myViewHolder.ibtnEdit.setOnClickListener(v -> showInputDialog(position));

    }

    private void showInputDialog(int position) {
        ItemDisburse retrieval = list.get(position);
        retrieval.setOldVal(retrieval.getActualQty());
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle);
        builder.setTitle("UpdateDisbursement");
        View v = inflater.inflate(R.layout.dialog_retrieval, null);
        TextView tv_item = v.findViewById(R.id.tv_item);
        EditText edt_total = v.findViewById(R.id.edt_total);
        EditText edt_actual = v.findViewById(R.id.edt_actual);
        TextInputLayout ti_actual = v.findViewById(R.id.ti_actual);
        tv_item.setText(String.format("Item : %s", retrieval.getDescription()));
        edt_total.setText("" + retrieval.getNeededQty());
        edt_actual.setText("" + retrieval.getActualQty());
        builder.setView(v);
        builder.setIcon(R.drawable.logo);
        builder.setCancelable(false);
        builder.setNegativeButton("Cancel", null);
        builder.setPositiveButton("Ok", (dialog, which) -> {
            ItemDisburse tempItem = list.get(position);
            String value = edt_actual.getText().toString();
            if (TextUtils.isDigitsOnly(value)) {
                int actual = Integer.parseInt(value);
                if (actual < 0) {
                    ti_actual.setError("Number can not be negative.");
                    return;
                }
                if (actual > tempItem.getOldVal()) {
                    ti_actual.setError("Number Exceeded");
                    return;
                }
                int decreasedVal = tempItem.getOldVal()-actual;
                list.get(position).setActualQty(actual);
                notifyDataSetChanged();
                listener.updateActual(position, decreasedVal);
            } else {
                ti_actual.setError("Please add Actual amount");
                return;
            }
            dialog.dismiss();

        });
        builder.show();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvNeeded, tvActual;
        private ImageButton ibtnEdit;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvNeeded = itemView.findViewById(R.id.tv_needed);
            tvActual = itemView.findViewById(R.id.tv_actual);
            ibtnEdit = itemView.findViewById(R.id.ibtn_edit);
        }

        public void bind(ItemDisburse item) {
            tvName.setText(item.getDescription());
            tvNeeded.setText(String.format("%d", item.getNeededQty()));
            tvActual.setText(String.format("%d", item.getActualQty()));
        }
    }
}
