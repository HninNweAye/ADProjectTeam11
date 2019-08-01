package sa48.team11.adproject.activities;

import android.os.Bundle;
import android.os.PatternMatcher;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import sa48.team11.adproject.R;
import sa48.team11.adproject.adapters.AdjVoucherDetailAdapter;
import sa48.team11.adproject.models.AdjItem;
import sa48.team11.adproject.models.Item;

public class AdjVoucherCreateActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private ArrayList<Item> items;
    private TextInputEditText edtReason, edtQuantity;
    private List<AdjItem> adjItemList = new ArrayList<>();
    private AdjVoucherDetailAdapter adapter;
    private Spinner spinItem;
    private String reason="Lost";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_adj_voucher);
        loadData();
        loadUI();
    }

    private void loadData() {
        items = new ArrayList<>();
        items.add(new Item("P01", "Pencil", 10));
        items.add(new Item("P03", "Stapler", 7));
        items.add(new Item("P02", "Marker", 5));
        items.add(new Item("P04", "Pen", 6));
    }


    private void loadUI() {

        edtQuantity = findViewById(R.id.edt_Quantity);
        edtReason = findViewById(R.id.edt_reason);

        Spinner spinCategory = findViewById(R.id.spin_category);
        spinItem = findViewById(R.id.spin_item);
        ArrayAdapter spinnerAdapter = new ArrayAdapter(this, R.layout.adapter_spin_row, items);
        spinCategory.setAdapter(spinnerAdapter);
        spinItem.setAdapter(spinnerAdapter);


        findViewById(R.id.btn_submit).setOnClickListener(this);
        findViewById(R.id.btn_add).setOnClickListener(this);
        RadioGroup rgReason = findViewById(R.id.rg_reason);
        rgReason.setOnCheckedChangeListener((group, checkedId) -> {
            findViewById(R.id.ti_reason).setVisibility(View.GONE);

            switch (checkedId) {
                case R.id.rb_lost:
                    reason = "Lost";
                    break;
                case R.id.rb_broken:
                    reason = "Broken";
                    break;
                case R.id.rb_gift:
                    reason = "Gift";
                    break;
                case R.id.rb_other:
                    reason = "Other";
                    findViewById(R.id.ti_reason).setVisibility(View.VISIBLE);
                    break;
            }
        });
        spinCategory.setOnItemSelectedListener(this);
        spinItem.setOnItemSelectedListener(this);
        renderRecyclerView();


    }

    private void renderRecyclerView() {
        RecyclerView rc_req_list = findViewById(R.id.rc_adj_list);
        rc_req_list.setHasFixedSize(true);
        rc_req_list.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this, R.anim.recycler_layout_anim));
        rc_req_list.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdjVoucherDetailAdapter(this);
        rc_req_list.setAdapter(adapter);
        adapter.updateList(adjItemList);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.spin_category) {
            Toast.makeText(AdjVoucherCreateActivity.this, "Name" + items.get(position).getName() + items.get(position).getCode(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(AdjVoucherCreateActivity.this, "Name" + items.get(position).getName(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_add:
                addToList();
                break;
            case R.id.btn_submit:
                break;
        }

    }

    private void addToList() {
        if (edtQuantity.getText().toString().isEmpty()) {
            ((TextInputLayout) findViewById(R.id.ti_quantity)).setError("Please add quantity");
        }
        String qty = getQuantity();
        if(qty == null){
            ((TextInputLayout) findViewById(R.id.ti_quantity)).setError("Please add + or - ");
        }
        if(reason.equals("Other")) {
            reason = edtReason.getText().toString();
        }

        adjItemList.add(new AdjItem(((Item) spinItem.getSelectedItem()).getCode(),qty, reason));

        adapter.updateList(adjItemList);
    }

    private String getQuantity() {
        String quantity = edtQuantity.getText().toString();
        Pattern pattern = Pattern.compile("[+-]d+");
        if(pattern.matcher(quantity).matches()){
            return  quantity;
        }else{
            if(reason.equals("Other")){
                return null;
            }else if(reason.equals("Gift")){
                return "+".concat(quantity);
            }else{
                return "-".concat(quantity);
            }
        }
    }


}
