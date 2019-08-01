//package sa48.team11.adproject.activities;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.view.animation.AnimationUtils;
//import android.widget.TextView;
//
//import sa48.team11.adproject.R;
//import sa48.team11.adproject.adapters.ReqDetailAdapter;
//
///**
// * Created by hninnwe on 2019-07-31
// */
//public class Template extends AppCompatActivity implements View.OnClickListener {
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.adapter_req_detail);
//        loadUI();
//
//    }
//
//    private void loadUI() {
//        TextView tvName  = findViewById(R.id.tv_name);
//        tvName.setText(String.format("Name    : %s ", request.getEmployeeName()));
//        findViewById(R.id.btn_reject).setOnClickListener(this);
//    }
//    private void renderRecyclerView() {
//        RecyclerView rc_req_list = findViewById(R.id.rc_req_list);
//        rc_req_list.setHasFixedSize(true);
//        rc_req_list.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this,R.anim.recycler_layout_anim));
//        rc_req_list.setLayoutManager(new LinearLayoutManager(this));
//        ReqDetailAdapter adapter= new ReqDetailAdapter(this);
//        rc_req_list.setAdapter(adapter);
//        adapter.updateList(request.getItemList());
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.btn_approve:
//                break;
//            case R.id.btn_reject:
//                break;
//        }
//    }
//}
