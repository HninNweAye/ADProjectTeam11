//package sa48.team11.adproject.retrofit;
//
//import android.util.Log;
//
//import com.google.gson.TypeAdapter;
//import com.google.gson.stream.JsonReader;
//import com.google.gson.stream.JsonWriter;
//
//import java.io.IOException;
//import java.util.Date;
//
//import sa48.team11.adproject.utils.Utils;
//
//public class DateAdapter extends TypeAdapter<Date> {
//    @Override
//    public void write(JsonWriter out, Date value) throws IOException {
//        out.value(Utils.dateString(value));
//    }
//
//    @Override
//    public Date read(JsonReader in) throws IOException {
//        Log.d("Date","Read"+in.nextName());
//        return Utils.dateString(in.nextString());
//    }
//}
