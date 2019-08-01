package sa48.team11.adproject.activities;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sa48.team11.adproject.R;
import sa48.team11.adproject.adapters.RequestListAdapter;
import sa48.team11.adproject.listeners.IDatePickerListener;
import sa48.team11.adproject.models.Item;
import sa48.team11.adproject.models.Request;
import sa48.team11.adproject.utils.Constants;
import sa48.team11.adproject.utils.Utils;

public class ManageRequestActivity extends AppCompatActivity implements View.OnClickListener, IDatePickerListener {
    private EditText edtStartDate, edtEndDate;
    private List<Request> requestList = new ArrayList<>();
    private RequestListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_request);
        loadUI();
        loadData();
    }

    private void loadData() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("P01", "Pencil", 10));
        items.add(new Item("P03", "Stapler", 7));
        items.add(new Item("P02", "Marker", 5));
        items.add(new Item("P04", "Pen", 6));
        requestList.add(new Request("AyeAye", "20/08/2019", Constants.PENDING, items, 10));
        requestList.add(new Request("John", "10/08/2019", Constants.APPROVED, items, 5));
        requestList.add(new Request("Jesica", "5/08/2019", Constants.REJECT, items, 7));


    }

    private void loadUI() {
        edtStartDate = findViewById(R.id.edt_start_date);
        edtEndDate = findViewById(R.id.edt_end_date);
        edtStartDate.setOnClickListener(this);
        edtEndDate.setOnClickListener(this);
        renderRecyclerView();
    }

    private void renderRecyclerView() {
        RecyclerView rc_req_list = findViewById(R.id.rc_req_list);
        rc_req_list.setHasFixedSize(true);
        rc_req_list.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this, R.anim.recycler_layout_anim));
        rc_req_list.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RequestListAdapter(this);
        rc_req_list.setAdapter(adapter);
        adapter.updateList(requestList);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.edt_start_date:
                Utils.showDatePicker(getFragmentManager(), (EditText) v, this);
                break;
            case R.id.edt_end_date:
                Utils.showDatePicker(getFragmentManager(), (EditText) v, this);
                break;
        }
    }

    private void filterByDate() {
        if (edtStartDate.getText().toString().isEmpty() || edtEndDate.getText().toString().isEmpty())
            return;
        Date startDate = Utils.dateString(edtStartDate.getText().toString());
        Date endDate = Utils.dateString(edtEndDate.getText().toString());
        if (!validateDates(startDate, endDate)) return;
    }

    private boolean validateDates(Date startDate, Date endDate) {
        if (startDate.after(endDate)) {
            Utils.showAlert(R.string.alert_error, R.string.msg_date_error,this);
            return false;
        }
//        List<Request> tempList = requestList.stream().filter(req -> !(req.getDate().after(endDate) || req.getDate().before(startDate))).collect(Collectors.toList());
        List<Request> tempList = new ArrayList<>();
        for (Request req : requestList) {
            if (!(req.getDate().after(endDate) || req.getDate().before(startDate))) {
                tempList.add(req);
            }
        }
        adapter.updateList(tempList);
        return true;
    }


    @Override
    public void dateChange() {
        filterByDate();
    }
}
