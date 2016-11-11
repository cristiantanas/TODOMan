package org.uab.dedam.todoman;

import android.app.DatePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class NewTaskActivity extends FragmentActivity implements
        TimePickerFragment.OnFragmentInteractionListener,
        DatePickerFragment.DatePickerFragmentListener {

    Button saveTask;
    Button setTime;
    Button setDate;
    ITaskPresenter taskPresenter;
    TextView title;
    TextView description;
    Switch done;
    TextView dueDate;
    TextView dueTime;
    private static final String TIME_PICKER_FRAGMENT = "TIME_PICKER_FRAGMENT";
    private static final String DATE_PICKER_FRAGMENT = "DATE_PICKER_FRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newtask);

        taskPresenter = new TaskPresenter(this);
        saveTask = (Button) findViewById(R.id.saveTask);
        saveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTask();
            }
        });
        setTime = (Button) findViewById(R.id.buttonSetDueTime);
        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker(v);
            }
        });
        setDate = (Button) findViewById(R.id.buttonSetDueDate);
        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(v);
            }
        });
        title = (TextView) findViewById(R.id.taskTitle);
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (title.getText().toString().contains("Task title")) title.setText("");
            }
        });
        description = (TextView) findViewById(R.id.taskDescription);
        description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (description.getText().toString().contains("Task description")) description.setText("");
            }
        });
    }

    protected void showTimePicker(View v) {
        DialogFragment timeFragment = new TimePickerFragment();
        timeFragment.show(getSupportFragmentManager(), TIME_PICKER_FRAGMENT);
    }

    protected void showDatePicker(View v) {
        DialogFragment dateFragment = new DatePickerFragment();
        dateFragment.show(getSupportFragmentManager(), DATE_PICKER_FRAGMENT);
    }

    protected void saveTask() {
        title = (TextView) findViewById(R.id.taskTitle);
        description = (TextView) findViewById(R.id.taskDescription);
        done = (Switch) findViewById(R.id.switchDone);
        dueDate = (TextView) findViewById(R.id.textDueDate);
        dueTime = (TextView) findViewById(R.id.textDueTime);

        taskPresenter.addTask(
                title.getText().toString(),
                description.getText().toString(),
                done.isChecked(),
                dueDate.getText().toString(),
                dueTime.getText().toString());
        finish();
    }

    @Override
    public void onTimeSet(int hour, int minute) {
        dueTime = (TextView) findViewById(R.id.textDueTime);
        dueTime.setText(hour + ":" + minute);
    }

    @Override
    public void onSetDate(int day, int month, int year) {
        dueDate = (TextView) findViewById(R.id.textDueDate);
        dueDate.setText(day + "/" + (month + 1) + "/" + year);
    }
}
