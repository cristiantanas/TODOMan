package org.uab.dedam.todoman;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
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
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    OnDateSetCallback  listener;

    public DatePickerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.listener = (OnDateSetCallback) activity;
        } catch (ClassCastException e) {
           throw  new ClassCastException("Activity does not implement OnDataSetCallback");
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
                calendar.get(Calendar.WEEK_OF_MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)

        );

        return datePickerDialog;

    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        this.listener.onDataSelected(year + "-" + (monthOfYear +1) + "-" + dayOfMonth);
    }
    public interface OnDateSetCallback{
        public abstract void onDataSelected(String date);
    };
}
