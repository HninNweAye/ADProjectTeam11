package sa48.team11.adproject.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;
import java.util.Date;

import sa48.team11.adproject.R;
import sa48.team11.adproject.utils.Utils;

public class NewDelegationActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private EditText edtReason, edtStartDate, edtEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_delegation);
        loadUI();

    }

    private void loadUI() {
        edtReason = findViewById(R.id.edt_reason);
        edtStartDate = findViewById(R.id.edt_start_date);
        edtEndDate = findViewById(R.id.edt_end_date);
        edtStartDate.setOnClickListener(this);
        edtEndDate.setOnClickListener(this);
        findViewById(R.id.btn_submit).setOnClickListener(this);
        Spinner spinner = findViewById(R.id.spin_representative);
//        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.adapter_spin_row,items);
//        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    private void submitDelegation() {
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.edt_start_date:
                Utils.showDatePicker(getFragmentManager(), (EditText) v);
                break;
            case R.id.edt_end_date:
                Utils.showDatePicker(getFragmentManager(), (EditText) v);
                show();
                break;
            case R.id.btn_submit:
                break;
        }
    }

    private void show() {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, monthOfYear, dayOfMonth) -> edtEndDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year), mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
