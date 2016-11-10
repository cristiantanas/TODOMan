package org.uab.dedam.todoman.views;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

    OnTimeSetCallback listener;

    public TimePickerFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            this.listener = (OnTimeSetCallback) activity;
        }
        catch(ClassCastException exception) {
            throw new ClassCastException("The activity does not implement the interface.");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        FragmentActivity parentActivity = getActivity();
        TimePickerDialog timePicker = new TimePickerDialog(parentActivity, this, 8,0,true);
        return timePicker;
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minutes) {
        String stringHour = hour < 10 ? "0" + hour :  String.valueOf(hour);
        String stringMinutes = minutes < 10 ? "0" + minutes : String.valueOf(minutes);
        listener.onTimeSelected(stringHour + ":" + stringMinutes);
    }

    public interface OnTimeSetCallback {
        public abstract void onTimeSelected(String time);
    }
}
