package org.uab.dedam.todoman;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.DatePicker;


import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    OnDateSetCallBack listener;

    public DatePickerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.listener = (OnDateSetCallBack) activity;
        }
        catch (ClassCastException e) {
            throw new ClassCastException("Activity does not implement OnDateSetCallback");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        FragmentActivity parentActivity = getActivity();
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                parentActivity,
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        AlertDialog alertDialog = new Builder(parentActivity)
                .setMessage("Are you sure?")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();

        return datePickerDialog;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int day) {
        this.listener.onDateSelected(year + "/" + (monthOfYear + 1) + "/" + day);
    }

    public interface OnDateSetCallBack {
        public abstract void onDateSelected(String date);
    }
}