package sa48.team11.adproject.activities;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sa48.team11.adproject.R;
import sa48.team11.adproject.adapters.ReqDetailAdapter;
import sa48.team11.adproject.adapters.StockCardAdapter;
import sa48.team11.adproject.models.StockCard;

public class StockCardActivity extends AppCompatActivity implements View.OnClickListener {
    private List<StockCard> list = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_card);
        loadUI();
        loadData();
    }

    private void loadData() {
        list.add(new StockCard("10/08/19","ADJ00010","+6","502"));
        list.add(new StockCard("02/08/19","BANE","+200","500"));
        list.add(new StockCard("05/08/19","ADJ00009","-4","496"));

    }

    private void loadUI() {
        TextView tvName  = findViewById(R.id.tv_name);
        TextView tvCode  = findViewById(R.id.tv_code);
        TextView tvBin  = findViewById(R.id.tv_bin);
        tvCode.setText(String.format("ItemCode    : %s ", "P085"));
        tvName.setText(String.format("Name    : %s ", "John"));
        tvBin.setText(String.format("Bin    : %s ", "#A07"));
        EditText edtSearch = findViewById(R.id.edt_search);
        edtSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
               // performSearch();
                return true;
            }
            return false;
        });

        renderRecyclerView();
    }

    private void renderRecyclerView() {
        RecyclerView rc_req_list = findViewById(R.id.rc_stock_card);
        rc_req_list.setHasFixedSize(true);
        rc_req_list.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this,R.anim.recycler_layout_anim));
        rc_req_list.setLayoutManager(new LinearLayoutManager(this));
        StockCardAdapter adapter= new StockCardAdapter(this);
        rc_req_list.setAdapter(adapter);
        adapter.updateList(list);
    }
    @Override
    public void onClick(View v) {

    }
}
