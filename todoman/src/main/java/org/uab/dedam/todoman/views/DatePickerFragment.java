package org.uab.dedam.todoman.views;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.widget.DatePicker;


import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    OnDateSetCallback listener;

    public DatePickerFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            this.listener = (OnDateSetCallback) activity;
        }
        catch(ClassCastException exception) {
            throw new ClassCastException("The activity does not implement the interface.");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        FragmentActivity parentActivity = getActivity();
        DatePickerDialog datePicker = new DatePickerDialog(parentActivity, this,
                                    calendar.get(Calendar.YEAR),
                                    calendar.get(Calendar.MONTH),
                                    calendar.get(Calendar.DAY_OF_MONTH));
        return datePicker;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        listener.onDateSelected(dayOfMonth + "/" + (month +1) + "/" + year);
    }

    public interface OnDateSetCallback {
        public abstract void onDateSelected(String date);
    }
}
