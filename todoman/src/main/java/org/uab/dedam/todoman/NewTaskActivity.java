package org.uab.dedam.todoman;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class NewTaskActivity extends AppCompatActivity implements DatePickerFragment.OnDateSetCallBack, TimePickerFragment.OnTimeSetCallBack {

    Button buttonCancelTask;
    Button buttonClearTask;
    Button buttonSaveTask;

    CheckBox checkboxTaskCompleted;

    EditText editTextTaskTitle;
    EditText editTextTaskDescription;

    ImageButton imageButtonTaskSetDate;
    ImageButton imageButtonTaskSetTime;

    static final String NEW_TASK_CREATED = "You have created a new task!";
    TextView textViewTaskEndDate;
    TextView textViewTaskEndTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newtask);

        buttonCancelTask = (Button) findViewById(R.id.buttonCancelTask);
        buttonClearTask = (Button) findViewById(R.id.buttonClearTask);
        buttonSaveTask = (Button) findViewById(R.id.buttonSaveTask);

        checkboxTaskCompleted = (CheckBox) findViewById(R.id.checkBoxTaskCompleted);

        editTextTaskTitle = (EditText) findViewById(R.id.editTextTaskTitle);
        editTextTaskDescription = (EditText) findViewById(R.id.editTextTaskDescription);

        imageButtonTaskSetDate = (ImageButton) findViewById(R.id.imageButtonTaskSetDate);
        imageButtonTaskSetTime = (ImageButton) findViewById(R.id.imageButtonTaskSetTime);

        textViewTaskEndDate = (TextView) findViewById(R.id.textViewTaskEndDate);
        textViewTaskEndTime = (TextView) findViewById(R.id.textViewTaskEndTime);

        imageButtonTaskSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.onAttach(NewTaskActivity.this);
                datePickerFragment.show(getSupportFragmentManager(), "datePickerFragment");
            }
        });

        imageButtonTaskSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment timePickerFragment = new TimePickerFragment();
                timePickerFragment.onAttach(NewTaskActivity.this);
                timePickerFragment.show(getSupportFragmentManager(), "timePickerFragment");
            }
        });

        buttonCancelTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        buttonClearTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkboxTaskCompleted.setChecked(false);
                editTextTaskTitle.setText("");
                editTextTaskDescription.setText("");
                textViewTaskEndDate.setText(getResources().getString(R.string.endDateLabel));
                textViewTaskEndTime.setText(getResources().getString(R.string.endTimeLabel));
            }
        });

        buttonSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskRepository taskRepository = new TaskRepository(NewTaskActivity.this);
                taskRepository
                        .save(editTextTaskTitle.getText().toString(),
                                editTextTaskDescription.getText().toString(),
                                checkboxTaskCompleted.isChecked(),
                                textViewTaskEndDate.getText().toString(),
                                textViewTaskEndTime.getText().toString()
                        );
                Toast.makeText(getApplicationContext(), NEW_TASK_CREATED, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    public void onDateSelected(String date) {
        textViewTaskEndDate.setText(date);
    }

    @Override
    public void onTimeSelected(String time) {
        textViewTaskEndTime.setText(time);
    }
}
