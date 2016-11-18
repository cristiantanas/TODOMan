package org.uab.dedam.todoman;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.widget.TimePicker;
import java.util.Calendar;

import static java.util.Calendar.getInstance;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    OnTimeSetCallBack listener;

    public TimePickerFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.listener = (OnTimeSetCallBack) activity;
        }
        catch (ClassCastException e) {
            throw new ClassCastException("Activity does not implement OnTimeSetCallBack");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = getInstance();
        FragmentActivity parent = getActivity();
        TimePickerDialog timePicker = new TimePickerDialog(
                parent,
                this,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true);
        return timePicker;
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hh, int mm) {
        listener.onTimeSelected(hh + ":" + mm);
    }

    public interface OnTimeSetCallBack {
        public abstract void onTimeSelected(String time);
    }
}
