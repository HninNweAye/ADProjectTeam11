package sa48.team11.adproject.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
//    private static final String URL = "http://172.17.59.128:80/api/";
    private static final String URL = "https://stationerystore-adteam11.conveyor.cloud/api/";
//    private static final String BaseURL = "http://10.0.2.2:49796/api/";
    private static Retrofit retrofit = null;
    public static Retrofit getRetrofit(){

        if(retrofit == null){
            Gson gson = new GsonBuilder().registerTypeAdapter(Date.class,new DateGsonDeserializer()).create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    public static ApiService getAPIService(){
        return  getRetrofit().create(ApiService.class);
    }

}
