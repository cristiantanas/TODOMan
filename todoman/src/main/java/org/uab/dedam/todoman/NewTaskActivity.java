package org.uab.dedam.todoman;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class NewTaskActivity extends AppCompatActivity implements DatePickerFragment.OnDateSetCallback {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newtask);
	}

	@Override
	public void onDateSelected(String date) {
		FragmentNewTask frgNewTask = ((FragmentNewTask)getSupportFragmentManager().findFragmentById(R.id.fragment_new_task));
		frgNewTask.setDate(date);
	}

	@Override
	public void selectDate() {
		DatePickerFragment datePickerFragment = new DatePickerFragment();
		datePickerFragment.show(getSupportFragmentManager(), "datePickerFragment");
	}
}
