package org.uab.dedam.todoman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.annotation.Retention;

public class NewTaskActivity extends AppCompatActivity implements DatePickerFragment.OnDateSetCallBack,
        NewtaskFragment.INewTaskFragment {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newtask);
    }

    @Override
    public void onDateSelected(String date) {
        NewtaskFragment newtaskFragment = (NewtaskFragment) getSupportFragmentManager().findFragmentById(R.id.NewTask);
        newtaskFragment.setDate(date);
    }

    @Override
    public void showDatePickerFragment() {
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.onAttach(NewTaskActivity.this);
        datePickerFragment.show(getSupportFragmentManager(), "datePickerFragment");
    }
}