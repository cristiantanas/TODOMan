package org.uab.dedam.todoman;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class HomeActivity extends AppCompatActivity implements DatePickerFragment.OnDateSetCallBack,
        NewtaskFragment.INewTaskFragment {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button gotoSubactivity = (Button) findViewById(R.id.btnNewTask);
        Boolean isTablet = (getApplicationContext().getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
        Boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        if( !(isTablet && isLandscape) ) {
            gotoSubactivity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, NewTaskActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void showDatePickerFragment() {
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.onAttach(HomeActivity.this);
        datePickerFragment.show(getSupportFragmentManager(), "datePickerFragment");
    }

    @Override
    public void onDateSelected(String date) {
        NewtaskFragment newtaskFragment = (NewtaskFragment) getSupportFragmentManager().findFragmentById(R.id.NewTask);
        newtaskFragment.setDate(date);
    }
}
