package sa48.team11.adproject.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.Util;
import retrofit2.Call;
import sa48.team11.adproject.R;
import sa48.team11.adproject.activities.DelegationListActivity;
import sa48.team11.adproject.listeners.IDelegationCancelListener;
import sa48.team11.adproject.models.Delegation;
import sa48.team11.adproject.models.Employee;
import sa48.team11.adproject.retrofit.BaseResponse;
import sa48.team11.adproject.retrofit.MyRetrofit;
import sa48.team11.adproject.retrofit.ApiClient;
import sa48.team11.adproject.retrofit.ApiService;
import sa48.team11.adproject.utils.App;
import sa48.team11.adproject.utils.Utils;

/**
 * Created by hninnwe on 2019-07-24
 */

public class DelegationListAdapter extends RecyclerView.Adapter<DelegationListAdapter.MyViewHolder> {
    private Context context;
    private List<Delegation> delegationList = new ArrayList<>();
    private Bundle bdl = new Bundle();
    private IDelegationCancelListener listener;
    private Employee currentUser;
    public DelegationListAdapter(Context context,IDelegationCancelListener listener) {
        currentUser =((App) context.getApplicationContext()).getUser();
        this.listener = listener;
        this.context = context;
    }

    public void updateList(List<Delegation> reqList) {
        this.delegationList = reqList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(context).inflate(R.layout.adapter_delegation_row, viewGroup, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

        myViewHolder.bind(delegationList.get(position));
        myViewHolder.btnCancel.setOnClickListener(v -> {
            Utils.showAlert(true, R.string.alert_confirm, R.string.cancel_delegation_confirm, (DelegationListActivity)context, (dialog, which) -> {
                cancelDelegation(position);
            });
        });
    }

    private void cancelDelegation(int pos) {

        ApiService service = ApiClient.getAPIService();
        Call<BaseResponse> call = service.cancelDelegation(currentUser.getId(),currentUser.getDepartmentId(),delegationList.get(pos));
        Toast.makeText(context, "Result"+delegationList.get(pos).getId(), Toast.LENGTH_SHORT).show();

        call.enqueue(new MyRetrofit<>((DelegationListActivity)context, res -> {
            Log.d("Res",res.toString());
            if(res.isSuccess()) {
                Utils.showAlert(R.string.cancel_delegation,R.string.success,(DelegationListActivity)context);
                delegationList.get(pos).setStatus(false);
                notifyDataSetChanged();
                listener.cancelDelegation(pos);
            }else{
                Utils.showAlert(R.string.cancel_delegation,R.string.alert_fail,(DelegationListActivity)context);
            }
        }));
    }

    @Override
    public int getItemCount() {
        return delegationList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvEmpName, tvStartDate, tvEndDate;
        private Button btnCancel;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEmpName = itemView.findViewById(R.id.tv_emp_name);
            tvStartDate = itemView.findViewById(R.id.tv_start_date);
            tvEndDate = itemView.findViewById(R.id.tv_end_date);
            btnCancel = itemView.findViewById(R.id.btn_cancel);
        }

        public void bind(Delegation del) {
            tvEmpName.setText(String.format("Name    : %s ", del.getEmpName()));
            tvStartDate.setText(String.format("StartDate  : %s ", Utils.dateString(del.getStartDate())));
            tvEndDate.setText(String.format("EndDate  : %s ",Utils.dateString(del.getEndDate())));
            if(del.isActive()){
                btnCancel.setVisibility(View.VISIBLE);
                listener.hideCreateDelegationButton();
            }else{
                btnCancel.setVisibility(View.INVISIBLE);
            }
        }


    }


}

