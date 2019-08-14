package sa48.team11.adproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import sa48.team11.adproject.R;
import sa48.team11.adproject.retrofit.BaseResponse;
import sa48.team11.adproject.models.CollectionPoint;
import sa48.team11.adproject.models.CollectionPointAndRep;
import sa48.team11.adproject.models.Employee;
import sa48.team11.adproject.retrofit.MyRetrofit;
import sa48.team11.adproject.retrofit.ResponseList;
import sa48.team11.adproject.retrofit.ResponseListAndObj;
import sa48.team11.adproject.retrofit.ApiClient;
import sa48.team11.adproject.retrofit.ApiService;
import sa48.team11.adproject.utils.Utils;

public class ManageCollectionActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private TextView tvCollectTime, tv_dept_rep, tv_collection;
    private Spinner spin_rep;
    private List<CollectionPoint> cPointList = new ArrayList<>();
    private List<Employee> empList = new ArrayList<Employee>();
    private int repId=-1, pointId=-1;
    private CollectionPoint cp;
//todo Faster start up
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_collection);
        loadUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        ApiService service2 = ApiClient.getAPIService();
        Call<ResponseListAndObj<CollectionPoint, CollectionPointAndRep>> call = service2.getCollectionPointsAndDeptRep();
        call.enqueue(new MyRetrofit<>(this, response -> {
            if (response.isSuccess()) {
                CollectionPointAndRep obj = response.getResObj();
                tv_collection.setText(String.format("Current CollectionPoint : %s",obj.getPointName()));
                tv_dept_rep.setText(String.format("Current Representative : %s",obj.getRepName()));
                repId = obj.getRepId();
                pointId = obj.getPointId();
                if (empList.size() > 0) spin_rep.setSelection(findEmp(empList, repId));
                if (cPointList.size() > 0) changeRadioSelection();
            }
        }));

        ApiService service = ApiClient.getAPIService();
        Call<ResponseList<CollectionPoint>> call1 = service.getCollectionPoints();
        call1.enqueue(new MyRetrofit<>(this, response -> {
            if (response.isSuccess()) {
                cPointList.addAll(response.getResultList());
                bindRadioButton();
            }
        }));
        Call<ResponseList<Employee>> call2 = service.getEmployeeList("COMM");
        call2.enqueue(new MyRetrofit<>(this, response -> {
            if (response.isSuccess()) {
                empList.addAll(response.getResultList());
                bindSpinner();
            }
        }));


    }

    private void changeRadioSelection() {
        int[] ids = {R.id.rb_point1, R.id.rb_point2, R.id.rb_point3, R.id.rb_point4, R.id.rb_point5, R.id.rb_point6};
        for (int i = 0; i < ids.length; i++) {
            Log.d("Coll","Change Point "+pointId+" Or "+cPointList.get(i).getId());
            if (pointId == cPointList.get(i).getId()) {
                ((RadioButton) findViewById(ids[i])).setChecked(true);
            }
        }
    }

    private int findEmp(List<Employee> empList,int repId) {
        for (int i = 0; i < empList.size(); i++) {
            if (empList.get(i).getId() == repId) {
                return i;
            }
        }
        return 0;
    }

    private void bindSpinner() {
        spin_rep = findViewById(R.id.spin_representative);
        ArrayAdapter spinnerAdapter = new ArrayAdapter(this, R.layout.adapter_spin_row, empList);
        spin_rep.setAdapter(spinnerAdapter);
        if(repId != -1)
        spin_rep.setSelection(findEmp(empList,repId));
//        spin_rep.setOnItemSelectedListener(this);
    }

    private void bindRadioButton() {
        if (cPointList.size() < 6) return;
        int[] ids = {R.id.rb_point1, R.id.rb_point2, R.id.rb_point3, R.id.rb_point4, R.id.rb_point5, R.id.rb_point6};
        for (int i = 0; i < ids.length; i++) {
            RadioButton rb = findViewById(ids[i]);
            rb.setText(cPointList.get(i).getName());
            Log.d("Coll","Bind Point "+pointId+" Or "+cPointList.get(i).getId());
            if (pointId != -1 && pointId == cPointList.get(i).getId()) {
                Log.d("Coll", "SELECT True");
                rb.setChecked(true);
            }
        }
    }

    private void loadUI() {

        tvCollectTime = findViewById(R.id.tv_collect_time);
        tv_dept_rep = findViewById(R.id.tv_representative);
        tv_collection = findViewById(R.id.tv_collection);

        findViewById(R.id.btn_cancel).setOnClickListener(this);
        findViewById(R.id.btn_submit).setOnClickListener(this);
        ((RadioGroup) findViewById(R.id.rb_collection_point)).setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.rb_point1)).setChecked(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                submitCollectionPointAndRep();
                break;
            case R.id.btn_cancel:
                finish();
                break;
        }
    }

    private void submitCollectionPointAndRep() {
        Employee emp = (Employee) spin_rep.getSelectedItem();
             ApiService service = ApiClient.getAPIService();
                  Call<BaseResponse> call = service.updateCollectionPointAndRep(pointId,emp.getId());
                     call.enqueue(new MyRetrofit<>(this, response -> {
                         if (response.isSuccess()) {
                             Utils.showAlert(R.string.manage_collection, R.string.success, ManageCollectionActivity.this, (dialog, which) -> {
                                 tv_collection.setText(String.format("Current CollectionPoint : %s",cp.getName()));
                                 tv_dept_rep.setText(String.format("Current Representative : %s",emp.getName()));
                             });
                         }else{
                             Utils.showAlert(R.string.alert_error,R.string.alert_something_worng,ManageCollectionActivity.this);
                         }
                     }));
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_point1:
                selectCollectionPoint(0);
                break;
            case R.id.rb_point2:
                selectCollectionPoint(1);
                break;
            case R.id.rb_point3:
                selectCollectionPoint(2);
                break;
            case R.id.rb_point4:
                selectCollectionPoint(3);
                break;
            case R.id.rb_point5:
                selectCollectionPoint(4);
                break;
            case R.id.rb_point6:
                selectCollectionPoint(5);
                break;
        }

    }
    private void selectCollectionPoint(int rb){
        if (cPointList.size() != 6) return;

            cp = cPointList.get(rb);
        pointId = cp.getId();
            tvCollectTime.setText(String.format("CollectionTime   :  %s", cp.getCollectTime()));
    }
}
