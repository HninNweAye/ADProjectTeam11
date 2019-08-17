package sa48.team11.adproject.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.widget.EditText;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import sa48.team11.adproject.R;
import sa48.team11.adproject.activities.RequestDetailActivity;
import sa48.team11.adproject.listeners.IDatePickerListener;


/**
 * Created by hninnwe on 2019-07-23
 */
public class Utils {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat sdfShortForm = new SimpleDateFormat("yy/MM/dd");
    private static ProgressDialog pDialog;

    public static String dateString(Date date) {
        return date == null ? "":sdf.format(date);
    }

    public static Date dateString(String date) {
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String shortDateString(Date date) {
        return date == null ? " " :sdfShortForm.format(date);
    }

    public static Date shortDateString(String date) {
        try {
            return sdfShortForm.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void goNext(Activity source, Class destination) {
       goNext(source,destination,false);
    }

    public static void goNext(Activity source, Class destination,boolean close) {
        Intent intent = new Intent(source, destination);
        source.startActivity(intent);
        if(close){
            source.finish();
        }
    }

    public static void goNext(Activity source, Class destination, Bundle bdl) {
        Intent intent = new Intent(source, destination);
        intent.putExtras(bdl);
        source.startActivity(intent);
    }

    public static void showDatePicker(FragmentManager fm, final EditText edt, IDatePickerListener listener) {
        Calendar calendar = Calendar.getInstance();

        DatePickerDialog dia = DatePickerDialog.newInstance((view, year, monthOfYear, dayOfMonth) -> {
            edt.setText(String.format("%d-%02d-%02d", year, monthOfYear + 1, dayOfMonth));
            if (listener != null) listener.dateChange();
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dia.setAccentColor(Color.parseColor("#33B35A"));
        dia.show(fm, "DateDialog");
    }

    public static void showDatePicker(FragmentManager fm, final EditText edt) {
        showDatePicker(fm, edt, null);
    }
    public static void showAlert(boolean isConfirmationAlert,@StringRes int title, @StringRes int msg, Activity activity, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.MyAlertDialogStyle);
        builder.setTitle(activity.getString(title));
        builder.setMessage(activity.getString(msg));
        builder.setIcon(R.drawable.logo);
        builder.setCancelable(false);
        if(isConfirmationAlert) builder.setNegativeButton("Cancel",null);
        builder.setPositiveButton("Ok", listener);
        builder.show();
    }
    public static void showAlert(@StringRes int title, @StringRes int msg, Activity activity, DialogInterface.OnClickListener listener) {
       showAlert(false,title,msg,activity,listener);
    }
    public static void showAlert(@StringRes int title, @StringRes int msg, Activity activity, boolean close) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.MyAlertDialogStyle);
        builder.setTitle(activity.getString(title));
        builder.setMessage(activity.getString(msg));
        builder.setIcon(R.drawable.logo);
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", (dialog, which) -> {
            dialog.dismiss();
            if (close)
                activity.finish();

        });
            builder.show();
    }

    public static void showAlert ( @StringRes int title, @StringRes int msg, Activity activity){
            showAlert(title, msg, activity, false);
    }

    public static String getText (EditText editText){
            if (editText == null) return "";
            return editText.getText().toString();
    }

    public static ProgressDialog showLoadingDia(Activity activity) {
        if(pDialog ==  null) {
            pDialog = new ProgressDialog(activity);
            pDialog.setIcon(R.drawable.logo);
            pDialog.setTitle("LogicUniversity ");
            pDialog.setMessage("Please Wait....");
            pDialog.setCancelable(false);
            pDialog.show();
        }
        return pDialog;
    }

    public static void dismissLoadingDia() {
        pDialog = null;
    }


//    public static <T extends View>T findView (@IdRes int id , View v,T resView){
//        return  (resView)v.findViewById(id);
//    }

    }
