package com.example.didier.actividad1a1;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newtask_fragment, container, false);
        return view;
    }
    public void setText(String item) {
        TextView view = (TextView) getView().findViewById(R.id.display_tv);
        view.setText(item);
    }

}
