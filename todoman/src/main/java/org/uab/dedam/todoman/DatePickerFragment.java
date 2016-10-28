package org.uab.dedam.todoman;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.widget.DatePicker;

import java.util.Calendar;

import static java.util.Calendar.*;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
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
        Calendar calendar = getInstance();
        FragmentActivity parent = getActivity();
        DatePickerDialog datepicker = new DatePickerDialog(
                parent,
                this,
                calendar.get(YEAR),
                calendar.get(MONTH),
                calendar.get(DAY_OF_MONTH));
        return datepicker;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        listener.onDateSelected(day + "/" + (month + 1) + "/" + year);
    }

    public interface OnDateSetCallBack {
        public abstract void onDateSelected(String date);
    }
}
