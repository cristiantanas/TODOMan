package org.uab.dedam.todoman;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class NewTaskActivity extends AppCompatActivity
                            implements DatePickerFragment.OnDateSetCallBack,
                                TimePickerFragment.OnTimeSetCallBack {

    Button buttonCancelTask;
    Button buttonClearTask;
    Button buttonSaveTask;

    CheckBox checkboxTaskCompleted;

    EditText editTextTaskTitle;
    EditText editTextTaskDescription;

    ImageButton imageButtonTaskSetDate;
    ImageButton imageButtonTaskSetTime;

    private static final String LAUNCH_NOTIFICATION = "org.uab.dedam.todoman.LAUNCH_NOTIFICATION";
    private static final String NEW_TASK_CREATED = "You have created a new task!";
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

                String taskEndDate = formatDate(textViewTaskEndDate.getText().toString());
                boolean isAlarmSet = isAlarmSet(taskEndDate);
                String taskEndTime = formatTime(textViewTaskEndTime.getText().toString(), isAlarmSet);

                TaskRepository taskRepository = new TaskRepository(NewTaskActivity.this);
                taskRepository
                        .save(editTextTaskTitle.getText().toString(),
                                editTextTaskDescription.getText().toString(),
                                checkboxTaskCompleted.isChecked(),
                                taskEndDate,
                                taskEndTime
                        );

                if (isAlarmSet) {
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                        Date dateTimeAlarm = dateFormat.parse(taskEndDate + " " + taskEndTime);

                        Calendar whenAlarm = new GregorianCalendar(
                                dateTimeAlarm.getYear(),
                                dateTimeAlarm.getMonth(),
                                dateTimeAlarm.getDay()
                        );

                        whenAlarm.setTime(dateTimeAlarm);

                        Intent intent = new Intent(LAUNCH_NOTIFICATION);
                        intent.putExtra("completedTask", checkboxTaskCompleted.isChecked());
                        intent.putExtra("titleTask", editTextTaskTitle.getText().toString());
                        intent.putExtra("descriptionTask", editTextTaskDescription.getText().toString());
                        PendingIntent pendingIntent = PendingIntent
                                .getBroadcast(NewTaskActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                        alarmManager.set(AlarmManager.RTC_WAKEUP, whenAlarm.getTimeInMillis(), pendingIntent);

                        Toast.makeText(getApplicationContext(), NEW_TASK_CREATED, Toast.LENGTH_SHORT).show();
                        finish();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
            }
            }
        });
    }

    public boolean isAlarmSet(String date) {
        String labelDefaultDate = "Set end date";
        return date.compareTo(labelDefaultDate) != 0;
    }

    public String formatDate(String date) {
        String labelDefaultDate = "Set end date";

        if(date.compareTo(labelDefaultDate) != 0)
            return date;
        else
            return "";
    }

    public String formatTime(String time, boolean isAlarmSet) {
        String labelDefaultDate = "Set end time";

        if(isAlarmSet){
            if(time.compareTo(labelDefaultDate) != 0)
                return time;
            else
                return "09:00";
        }
        else
            return "";
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
