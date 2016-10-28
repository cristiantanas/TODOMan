package org.uab.dedam.todoman;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class NewtaskFragment extends Fragment {

    INewTaskFragment listener;
    Button btnSetDate;
    TextView labelDateSelected;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.listener = (INewTaskFragment) context;
        }
        catch (ClassCastException e) {
            throw new ClassCastException("Activity does not implement INewTaskFragment");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newtask, container);
        btnSetDate = (Button) view.findViewById(R.id.btnSetDate);
        labelDateSelected = (TextView) view.findViewById(R.id.LabelDateSelected);

        btnSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.showDatePickerFragment();
            }
        });

        return view;
    }

    public void setDate(String date) {
        labelDateSelected.setText(date);
    }

    public interface INewTaskFragment {
        public abstract void showDatePickerFragment();
    }
}
