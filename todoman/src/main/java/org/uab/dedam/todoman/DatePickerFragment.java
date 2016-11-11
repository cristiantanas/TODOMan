package org.uab.dedam.todoman;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import java.io.InvalidClassException;
import java.util.Calendar;


public class DatePickerFragment extends DialogFragment implements
        DatePickerDialog.OnDateSetListener{

    private DatePickerFragmentListener mListener;

    public DatePickerFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getActivity(),
                this,
                year,
                month,
                day);
        return datePickerDialog;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DatePickerFragmentListener) {
            mListener = (DatePickerFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement DatePickerFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        try {
            mListener = (DatePickerFragmentListener) getActivity();
            mListener.onSetDate(dayOfMonth, monthOfYear, year);
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement DatePickerFragmentListener.");
        }
    }

    public interface DatePickerFragmentListener {
        // TODO: Update argument type and name
        void onSetDate(int day, int month, int year);
    }
}
