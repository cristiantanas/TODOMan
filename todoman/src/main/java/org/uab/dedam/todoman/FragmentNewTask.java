package org.uab.dedam.todoman;

import android.os.Bundle;
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
		
		View viewLayout = inflater.inflate(R.layout.fragment_new_task, container, false);

		btnSetDate = (Button) viewLayout.findViewById(R.id.btn_set_date);
		texDate = (TextView) viewLayout.findViewById(R.id.tex_date);
			btnSetDate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				((DatePickerFragment.OnDateSetCallback)getActivity()).selectDate();
			}
		});
		return viewLayout;
	}

	public void setDate(String date){
		texDate.setText(date);
	}
}
