package sa48.team11.adproject.activities;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import okhttp3.internal.Util;
import retrofit2.Call;
import sa48.team11.adproject.R;
import sa48.team11.adproject.adapters.AdjVoucherDetailAdapter;
import sa48.team11.adproject.models.AdjItem;
import sa48.team11.adproject.models.AdjVoucher;
import sa48.team11.adproject.models.Category;
import sa48.team11.adproject.models.Employee;
import sa48.team11.adproject.models.Item;
import sa48.team11.adproject.models.ItemSpinner;
import sa48.team11.adproject.retrofit.ApiClient;
import sa48.team11.adproject.retrofit.ApiService;
import sa48.team11.adproject.retrofit.BaseResponse;
import sa48.team11.adproject.retrofit.MyRetrofit;
import sa48.team11.adproject.retrofit.ResponseList;
import sa48.team11.adproject.retrofit.ResponseObj;
import sa48.team11.adproject.utils.App;
import sa48.team11.adproject.utils.Utils;

public class AdjVoucherCreateActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private ArrayList<ItemSpinner> items = new ArrayList<>();
    private ArrayList<Category> categories = new ArrayList<>();
    private TextInputEditText edtReason, edtQuantity;
    private List<AdjItem> adjItemList = new ArrayList<>();
    private AdjVoucherDetailAdapter adapter;
    private Spinner spinItem, spinCategory;
    private String reason = "Lost";
    private Employee currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_adj_voucher);
        currentUser =((App) getApplicationContext()).getUser();
        loadData();
        loadUI();
    }

    private void loadData() {
        ApiService service = ApiClient.getAPIService();
        Call<ResponseList<Category>> call = service.getCategories();
        call.enqueue(new MyRetrofit<>(this, response -> {
            if (response.isSuccess()) {
                categories.addAll(response.getResultList());
                ArrayAdapter spinnerAdapterCategory = new ArrayAdapter(this, R.layout.adapter_spin_row, categories);
                spinCategory.setAdapter(spinnerAdapterCategory);
            }
        }));
        Call<ResponseList<ItemSpinner>> call2 = service.getItems();
        call2.enqueue(new MyRetrofit<>(this, response -> {
            if (response.isSuccess()) {
                items.addAll(response.getResultList());
                if (categories.size() > 0) {
                    loadItemByCategory(categories.get(0).getId());
                } else {
                    loadItemSpinner(items);
                }
            }
        }));
    }

    private void loadItemSpinner(ArrayList<ItemSpinner> itemList) {
        ArrayAdapter spinnerAdapterItem = new ArrayAdapter(this, R.layout.adapter_spin_row, itemList);
        spinItem.setAdapter(spinnerAdapterItem);
    }


    private void loadUI() {

        edtQuantity = findViewById(R.id.edt_Quantity);
        edtReason = findViewById(R.id.edt_reason);

        spinCategory = findViewById(R.id.spin_category);
        spinItem = findViewById(R.id.spin_item);

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
            Toast.makeText(AdjVoucherCreateActivity.this, "CategorysSelect" + categories.get(position), Toast.LENGTH_SHORT).show();
            loadItemByCategory(categories.get(position).getId());
        } else {

        }
    }

    private void loadItemByCategory(int categoryId) {
        ArrayList<ItemSpinner> temp = new ArrayList<>();
        for (ItemSpinner item : items) {
            if (item.getCategoryId() == categoryId) {
                temp.add(item);
            }
        }
        loadItemSpinner(temp);
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
                submitAdjVoucher();
                break;
        }

    }

    private void submitAdjVoucher() {
        if (adjItemList.size() == 0) {
            Utils.showAlert(R.string.alert_error,R.string.add_more_items, AdjVoucherCreateActivity.this);
            return;
        }
        ApiService service = ApiClient.getAPIService();
        Call<BaseResponse> call = service.createAdjVoucher(currentUser.getId(), adjItemList);
        call.enqueue(new MyRetrofit<>(this, response -> {
            if (response.isSuccess()) {
                Utils.showAlert(R.string.create_adj_voucher, R.string.success, AdjVoucherCreateActivity.this, true);
            }
        }));
    }

    private void addToList() {
        if (Utils.getText(edtQuantity).isEmpty()) {
            ((TextInputLayout) findViewById(R.id.ti_quantity)).setError("Please add quantity");
        }
        String qty = getQuantity();
        if (qty == null) {
            ((TextInputLayout) findViewById(R.id.ti_quantity)).setError("Please add + or - ");
        }
        if (reason.equals("Other")) reason = Utils.getText(edtReason);

        ItemSpinner item = (ItemSpinner) spinItem.getSelectedItem();
        adjItemList.add(new AdjItem(item.getItemId(), item.getDescription(), Integer.parseInt(qty), reason));

        adapter.updateList(adjItemList);
    }

    private String getQuantity() {
        String quantity = Utils.getText(edtQuantity);
        Pattern pattern = Pattern.compile("\\+|-");
        if (pattern.matcher(quantity).lookingAt()) {
            return quantity;
        } else {
            if (reason.equals("Other")) {
                return quantity;
            } else if (reason.equals("Gift")) {
                return "+".concat(quantity);
            } else {
                return "-".concat(quantity);
            }
        }
    }


}
