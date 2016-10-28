package org.uab.dedam.todoman;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mon on 28/10/16.
 */
public class TaskFragment extends Fragment {

    public TaskFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_task, container, false);
        // Inflate the layout for this fragment
        return view;
    }

}