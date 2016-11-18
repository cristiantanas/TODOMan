package org.uab.dedam.todoman;


import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    OnTimeSetCallback listener;

    public TimePickerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.listener = (OnTimeSetCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity does not implement");
        }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        FragmentActivity parentActivity= (FragmentActivity) getActivity();
        Calendar calendar= Calendar.getInstance();
        int hour=calendar.get(Calendar.HOUR_OF_DAY);
        int minute=calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                parentActivity,this,hour,minute,true
        );

        return timePickerDialog;
    }




    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        this.listener.onTimeSelected(String.format("%02d",hourOfDay)+":"+String.format("%02d",minute));
    }

    public interface OnTimeSetCallback{
        public abstract void onTimeSelected(String time);
    }
}
