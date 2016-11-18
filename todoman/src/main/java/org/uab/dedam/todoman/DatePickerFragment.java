package org.uab.dedam.todoman;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    OnDateSetCallback listener;

    public DatePickerFragment() {
        // Required empty public constructor
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.listener = (OnDateSetCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity does not implement");
        }
    }

    @NonNull
    @Override

    public Dialog onCreateDialog(Bundle savedInstanceState){

        FragmentActivity parentActivity = (FragmentActivity) getActivity();
        Calendar calendar = Calendar.getInstance();

        DatePickerDialog DatePickerDialog = new DatePickerDialog(
                parentActivity,
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );


        return DatePickerDialog;
    }


    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        this.listener.onDateSelected(String.format("%02d",dayOfMonth)+"-"+ String.format("%02d",(monthOfYear+1))+"-"+year);
    }

    public interface OnDateSetCallback {
        public abstract void onDateSelected(String date);
    };

}
