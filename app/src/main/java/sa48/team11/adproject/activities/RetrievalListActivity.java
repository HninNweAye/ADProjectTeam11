package sa48.team11.adproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;
import java.util.List;

import sa48.team11.adproject.R;
import sa48.team11.adproject.adapters.RetrievalAdapter;
import sa48.team11.adproject.adapters.StockCardAdapter;
import sa48.team11.adproject.models.RetrievalItem;

public class RetrievalListActivity extends AppCompatActivity {
    private List<RetrievalItem> itemList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieval_list);
        loadData();
    }

    private void loadData() {
        itemList.add(new RetrievalItem("Pencil","#A07",10,10));
        itemList.add(new RetrievalItem("Marker","#B09",5,5));
        itemList.add(new RetrievalItem("Pen","#A08",10,8));
        renderRecyclerView();
    }
    private void renderRecyclerView() {
        RecyclerView rc_req_list = findViewById(R.id.rc_retrieval_list);
        rc_req_list.setHasFixedSize(true);
        rc_req_list.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this,R.anim.recycler_layout_anim));
        rc_req_list.setLayoutManager(new LinearLayoutManager(this));
        RetrievalAdapter adapter= new RetrievalAdapter(this);
        rc_req_list.setAdapter(adapter);
        adapter.updateList(itemList);
    }

}
