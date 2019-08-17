package sa48.team11.adproject.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import sa48.team11.adproject.R;
import sa48.team11.adproject.models.CollectionPoint;
import sa48.team11.adproject.models.Disbursement;
import sa48.team11.adproject.models.Employee;
import sa48.team11.adproject.models.ItemDisburse;
import sa48.team11.adproject.retrofit.ApiClient;
import sa48.team11.adproject.retrofit.ApiService;
import sa48.team11.adproject.retrofit.MyRetrofit;
import sa48.team11.adproject.retrofit.ResponseList;
import sa48.team11.adproject.utils.App;

public class DisbursementActivity extends AppCompatActivity {

    List<Disbursement> disburseList = new ArrayList<>();
    List<ItemDisburse> items = new ArrayList<>();
    List<CollectionPoint> points = new ArrayList<>();
    private LinearLayout root, content;
    private TextView tvDept, tvRep, tvItem, tvActual, tvNeeded;
    private ImageButton ibtnArrow;
    private Employee currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disbursement);
        currentUser =((App) getApplicationContext()).getUser();

        root = findViewById(R.id.parentLayout);
        loadData();
    }

    private void loadUI() {
        for (CollectionPoint p : points) {
            TextView tv = new TextView(this, null, 0, R.style.tv_disbursement_point);
            tv.setText(p.getName());
            root.addView(tv);
            for (Disbursement d : disburseList) {
                if (d.getCollectionPointID() == p.getId()) {
                    createCard2(d);
                }
            }

        }
    }

    private void createCard2(Disbursement d) {
        View card = getLayoutInflater().inflate(R.layout.disburse_card, root, false);
        tvDept = card.findViewById(R.id.tv_dept);
        tvRep = card.findViewById(R.id.tv_representative);
        tvDept.setText(String.format("Department : %s", d.getDeptName()));
        tvRep.setText(String.format("Representative : %s", d.getRepName()));
        ImageButton ibtnArrow = card.findViewById(R.id.ibtn_arrow);
        LinearLayout content = card.findViewById(R.id.content);
        LinearLayout itemLinear = card.findViewById(R.id.itemLinear);

        Toast.makeText(this, "Size " + d.getItems().size(), Toast.LENGTH_SHORT).show();

        ibtnArrow.setOnClickListener(view -> {
            Toast.makeText(this, "Dept " + d.getDeptName(), Toast.LENGTH_SHORT).show();
            if (content.getVisibility() == View.GONE) {
                content.setVisibility(View.VISIBLE);
                content.setAlpha(0.0f);

                content.animate()
                        .setDuration(300)
                        .alpha(1.0f)
                        .setListener(null);
                if (d.isItemAlreadyAdd()) return;
                for (ItemDisburse item : d.getItems()) {
                    View row = getLayoutInflater().inflate(R.layout.disburse_row, root, false);
                    tvItem = row.findViewById(R.id.tv_item);
                    tvNeeded = row.findViewById(R.id.tv_needed);
                    tvActual = row.findViewById(R.id.tv_actual);
                    tvItem.setText(item.getDescription());
                    tvNeeded.setText("" + item.getNeededQty());
                    tvActual.setText("" + item.getActualQty());
                    itemLinear.addView(row);
                }
                d.setItemAlreadyAdd(true);

            } else {
                content.setVisibility(View.GONE);
            }
        });
        root.addView(card);


    }

    private void createCard(Disbursement d) {
        CardView card = new CardView(this);
        card.setCardElevation(10);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        card.setLayoutParams(params);
        params.setMargins(15, 5, 15, 10);

        LinearLayout cardLinear = new LinearLayout(this);
        cardLinear.setOrientation(LinearLayout.VERTICAL);
        cardLinear.setLayoutParams(params);

        TextView tvDept = new TextView(this, null, 0, R.style.normal_textview);
        tvDept.setText(String.format("Department : %s", d.getDeptName()));
        TextView tvRep = new TextView(this, null, 0, R.style.normal_textview);
        tvRep.setText(String.format("Representative : %s", d.getRepName()));

        cardLinear.addView(tvDept);
        cardLinear.addView(tvRep);
        card.addView(cardLinear);
        root.addView(card);
    }

    private void loadData() {
        ApiService service = ApiClient.getAPIService();
        Call<ResponseList<Disbursement>> call = service.getDisbursementList(currentUser.getId());
        call.enqueue(new MyRetrofit<>(this, response -> {
            if (response.isSuccess()) {
                disburseList.clear();
                disburseList.addAll(response.getResultList());
                loadUI();
            }
        }));
        Call<ResponseList<CollectionPoint>> call2 = service.getCollectionPointByClerk(currentUser.getId());
        call2.enqueue(new MyRetrofit<>(this, response -> {
            if (response.isSuccess()) {
                points.clear();
                points.addAll(response.getResultList());
            }
        }));

    }
}
