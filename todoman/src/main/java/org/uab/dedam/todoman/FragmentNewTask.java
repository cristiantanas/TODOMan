package org.uab.dedam.todoman;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class FragmentNewTask extends Fragment {
	Button btnSetDate;
	TextView texDate;

	@Override
	public View onCreateView(LayoutInflater inflater, 
			                 ViewGroup container, 
			                 Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_new_task, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		btnSetDate = (Button) getView().findViewById(R.id.btn_set_date);
		texDate = (TextView) getView().findViewById(R.id.tex_date);
		btnSetDate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				try{
					((DatePickerFragment.OnDateSetCallback)getActivity()).selectDate();
				}
				catch(ClassCastException exception) {
					throw new ClassCastException("The activity does not implement the interface.");
				}
			}
		});
	}

	public void setDate(String date){
		texDate.setText(date);
	}
}
