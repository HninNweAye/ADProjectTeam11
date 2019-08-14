package sa48.team11.adproject.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import sa48.team11.adproject.R;
import sa48.team11.adproject.retrofit.BaseResponse;
import sa48.team11.adproject.models.Delegation;
import sa48.team11.adproject.models.Employee;
import sa48.team11.adproject.retrofit.MyRetrofit;
import sa48.team11.adproject.retrofit.ResponseList;
import sa48.team11.adproject.retrofit.ApiClient;
import sa48.team11.adproject.retrofit.ApiService;
import sa48.team11.adproject.utils.Utils;

public class NewDelegationActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private EditText edtReason, edtStartDate, edtEndDate;
    private List<Employee> empList = new ArrayList<Employee>();
    private ArrayAdapter spinnerAdapter;
    private Spinner spin_employee;
    private int empId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_delegation);
        loadUI();
        loadData();

    }

    private void loadData() {

        ApiService service = ApiClient.getAPIService();
        Call<ResponseList<Employee>> call = service.getEmployeeList("COMM");
//        call.enqueue(new Callback<ResponseList<Employee>>() {
//            @Override
//            public void onResponse(Call<ResponseList<Employee>> call, BaseResponse<ResponseList<Employee>> response) {
//                if(response.body() != null){
//                    Log.d("Res",response.body().toString());
//                    empList.addAll(response.body().getResultList());
//                    loadSpinner();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseList<Employee>> call, Throwable t) {
//                Log.d("Res",t.getMessage());
//
//            }
//        });
        call.enqueue(new MyRetrofit<>(this, response -> {
            if (response.isSuccess()) {
                empList.addAll(response.getResultList());
                loadSpinner();
            }
        }));
    }

    private void loadUI() {
        edtReason = findViewById(R.id.edt_reason);
        edtStartDate = findViewById(R.id.edt_start_date);
        edtEndDate = findViewById(R.id.edt_end_date);
        edtStartDate.setOnClickListener(this);
        edtEndDate.setOnClickListener(this);
        findViewById(R.id.btn_submit).setOnClickListener(this);
    }

    private void loadSpinner() {
        spin_employee = findViewById(R.id.spin_representative);
        spinnerAdapter = new ArrayAdapter(this, R.layout.adapter_spin_row, empList);
        spin_employee.setAdapter(spinnerAdapter);
        spin_employee.setOnItemSelectedListener(this);
    }

    private void submitDelegation() {
        Date start = Utils.dateString(edtStartDate.getText().toString());
        Date end = Utils.dateString(edtEndDate.getText().toString());

        Delegation delegation = new Delegation(empId,start,end,edtReason.getText().toString());

        ApiService service = ApiClient.getAPIService();
        Call<BaseResponse> call = service.delegate(delegation);
        call.enqueue(new MyRetrofit<>(this, res -> {
            Log.d("Res", res.toString());
            Toast.makeText(this, "Rsult" + res.getMessage(), Toast.LENGTH_SHORT).show();
            Utils.showAlert(R.string.new_delegation,R.string.success,NewDelegationActivity.this,true);
        }));


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.edt_start_date:
                Utils.showDatePicker(getFragmentManager(), (EditText) v);
                break;
            case R.id.edt_end_date:
                Utils.showDatePicker(getFragmentManager(), (EditText) v);
                break;
            case R.id.btn_submit:
                if(validate())submitDelegation();
                break;
        }
    }

    private boolean validate() {
        try {
            Employee e = (Employee) spin_employee.getSelectedItem();
            empId = e.getId();
            if(edtStartDate.getText().toString().isEmpty() || edtEndDate.getText().toString().isEmpty()){
                Utils.showAlert(R.string.alert_error,R.string.alert_date_req,NewDelegationActivity.this);
                return false;
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
