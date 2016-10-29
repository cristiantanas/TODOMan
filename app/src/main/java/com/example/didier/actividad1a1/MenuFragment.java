package com.example.didier.actividad1a1;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
public class MenuFragment extends Fragment implements OnClickListener {
    private Communicator communicator;
    Button NewTask_btn;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof Communicator) {
            communicator = (Communicator) activity;
        } else {
            throw new ClassCastException(activity.toString()
                    + " Error with communicator");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_fragment, container, false);
        // Initialize Views
        NewTask_btn = (Button) view.findViewById(R.id.newtask_btn_id);
        // set on click Listeners for buttons
        NewTask_btn.setOnClickListener(this);
        return view;
    }
    public interface Communicator {
        public void Message(String Option);
    }
    @Override
    public void onClick(View v) {
        updateFragment("New Task");
    }
    private void updateFragment(String Option) {
        communicator.Message(Option);
    }
}