package org.uab.dedam.todoman;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class AddUpdateFragment extends Fragment {

    private IAddUpdateListener mListener;
    TextView txtDueDate;

    public AddUpdateFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AddUpdateFragment newInstance(String param1, String param2) {
        AddUpdateFragment fragment = new AddUpdateFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_update, container, false);
        Button btnSetDate = (Button) view.findViewById(R.id.btnSetDate);
        txtDueDate = (TextView) view.findViewById(R.id.txtTaskDueDate);
        btnSetDate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogFragment dateTimeFragment = new DateTimeFragment();
                        dateTimeFragment.show(getFragmentManager(), "timePickerFragment");
                    }
                }
        );

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.saveTask();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IAddUpdateListener) {
            mListener = (IAddUpdateListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void setDueDate(String date) {
        txtDueDate.setText(date);
    }

    public interface IAddUpdateListener {
        void saveTask();
    }
}
