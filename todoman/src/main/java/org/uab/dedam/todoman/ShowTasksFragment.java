package org.uab.dedam.todoman;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ShowTasksFragment extends Fragment {

    private IListTasks mListener;

    public ShowTasksFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_tasks, container, false);

        Button gotoSubactivity = (Button) view.findViewById(R.id.newTask);
        gotoSubactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUpdateTask();
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void addUpdateTask() {
        if (mListener != null) {
            mListener.addUpdateTask();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IListTasks) {
            mListener = (IListTasks) context;
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

    public interface IListTasks {
        void addUpdateTask();
    }
}
