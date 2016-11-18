package org.uab.dedam.todoman;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment
        implements
        TimePickerDialog.OnTimeSetListener {

    private OnFragmentInteractionListener mListener;

    public TimePickerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Calendar c = Calendar.getInstance();
        int hourOfDay = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                getActivity(),
                this,
                hourOfDay,
                minute,
                DateFormat.is24HourFormat(getActivity()));
        return timePickerDialog;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        try {
            mListener = (OnFragmentInteractionListener) getActivity();
            mListener.onTimeSet(hourOfDay, minute);

        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement OnTimeSetByUserListener.");
        }
    }

    public interface OnFragmentInteractionListener {
        void onTimeSet(int hour, int minute);
    }
}
