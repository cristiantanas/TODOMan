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


public class DateTimeFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    onDateSetCallback listener;

    public DateTimeFragment() {
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try{
            this.listener = (onDateSetCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity does not implement the interface ");
        }

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        this.listener.onDateSelected(dayOfMonth + "/" + (month + 1) + "/" + year);
    }

    public interface onDateSetCallback{
        void onDateSelected(String date);
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
        return datePickerDialog;
    }
}
