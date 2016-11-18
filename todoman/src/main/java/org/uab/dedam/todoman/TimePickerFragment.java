package org.uab.dedam.todoman;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.widget.TimePicker;
import java.util.Calendar;




/**
 * A simple {@link Fragment} subclass.
 */
public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {


     OnDateSetCallback listener;


    public TimePickerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.listener = (OnDateSetCallback) activity;
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
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        AlertDialog alertDialog = new AlertDialog.Builder(parentActivity)
                .setMessage("Are you sure?")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();

                return new TimePickerDialog(parentActivity ,this, hour, minute,
                DateFormat.is24HourFormat(parentActivity));

    }

    public void onTimeSet(TimePicker view, int hour, int minute) {
        this.listener.onTimeSelected(String.valueOf(hour) + ":" + String.valueOf(minute));
    }

    public interface OnDateSetCallback {
        public abstract void onTimeSelected(String time);
    }


}