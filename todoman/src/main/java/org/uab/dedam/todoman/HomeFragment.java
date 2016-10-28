package org.uab.dedam.todoman;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by mon on 28/10/16.
 */
public class HomeFragment extends Fragment {
    OnNextButtonCallback listener;
    private Button gotoSubActivity;

    public interface OnNextButtonCallback{
        public abstract void onNextPressed();
    }


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        gotoSubActivity = (Button) view.findViewById(R.id.gotoSubactivity);

        gotoSubActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onNextPressed();
            }

        });
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.listener = (OnNextButtonCallback) activity;
        } catch (Exception e) {
            throw new ClassCastException("Activity does not implement");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.listener=null;
    }
}
