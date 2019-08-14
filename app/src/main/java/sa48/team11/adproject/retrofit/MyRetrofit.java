package sa48.team11.adproject.retrofit;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import sa48.team11.adproject.R;
import sa48.team11.adproject.listeners.IRetrofitResponse;
import sa48.team11.adproject.utils.Utils;

public class MyRetrofit<T> implements Callback<T> {
    private Activity activity;
    private IRetrofitResponse iRetrofitResponse;
    private ProgressDialog pDialog = null;
    public MyRetrofit(Activity activity, IRetrofitResponse<T> iRetrofitResponse){
        this.activity = activity;
        this.iRetrofitResponse = iRetrofitResponse;
        this.pDialog = Utils.showLoadingDia(activity);
    }
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        pDialog.dismiss();
        Utils.dismissLoadingDia();
        if(response.body() != null) {
            Log.d("Retrofit",response.body().toString());
            iRetrofitResponse.response(response.body());
        }else{
            Toast.makeText(activity, "Response Null", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        pDialog.dismiss();
        Utils.dismissLoadingDia();
        Log.d("Retrofit","Failure"+t.getMessage());
        if(t instanceof IOException) {
            Utils.showAlert(R.string.alert_sorry, R.string.alert_something_worng, activity);
        }else{
            Toast.makeText(activity, "Conversion Error", Toast.LENGTH_SHORT).show();
        }
    }
}
