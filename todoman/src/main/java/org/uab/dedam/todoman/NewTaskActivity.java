package org.uab.dedam.todoman;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.uab.dedam.todoman.Service.TODOManService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.datatype.Duration;

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
    private Boolean isTitleCleaned;
    private Boolean isDescriptionCleaned;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newtask);
        isTitleCleaned = false;
        isDescriptionCleaned = false;
        title = (TextView) findViewById(R.id.taskTitle);
        description = (TextView) findViewById(R.id.taskDescription);
        done = (Switch) findViewById(R.id.switchDone);
        dueDate = (TextView) findViewById(R.id.textDueDate);
        dueTime = (TextView) findViewById(R.id.textDueTime);
        saveTask = (Button) findViewById(R.id.saveTask);
        taskPresenter = new TaskPresenter(this);
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
                if (!isTitleCleaned) {
                    isTitleCleaned = cleanPreLoadedText(title);
                }
            }
        });
        description = (TextView) findViewById(R.id.taskDescription);
        description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isDescriptionCleaned) {
                    isDescriptionCleaned = cleanPreLoadedText(description);
                }
            }
        });
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

    protected void showTimePicker(View v) {
        DialogFragment timeFragment = new TimePickerFragment();
        timeFragment.show(getSupportFragmentManager(), TIME_PICKER_FRAGMENT);
    }

    protected void showDatePicker(View v) {
        DialogFragment dateFragment = new DatePickerFragment();
        dateFragment.show(getSupportFragmentManager(), DATE_PICKER_FRAGMENT);
    }

    protected void saveTask() {
        long alarmId = taskPresenter.addTask(
                title.getText().toString(),
                description.getText().toString(),
                done.isChecked(),
                dueDate.getText().toString(),
                dueTime.getText().toString());
        Date alarmTicks = convertDateToTicks(
                dueDate.getText().toString(),
                dueTime.getText().toString());
        long alarmTime = alarmTicks.getTime();
        createAlarm(alarmId, alarmTime);
        Toast.makeText(getApplicationContext(), "Task saved successfully!", Toast.LENGTH_SHORT).show();
        finish();
    }

    protected Boolean cleanPreLoadedText(TextView textView) {
            textView.setText("");
        return true;
    }

    protected void createAlarm(long alarmId, long timeToElapse) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intentAlarm = new Intent(this, TODOManService.class);
        intentAlarm.putExtra("alarmId", alarmId);
        pendingIntent = PendingIntent.getService(this, (int)alarmId, intentAlarm, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP,
                timeToElapse,
                pendingIntent);
    }

    protected Date convertDateToTicks(String textDate, String textTime) {
        DateFormat parser = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date dateAlarm = new Date();
        try {
            dateAlarm= parser.parse(textDate + " " + textTime);
        } catch (ParseException e) {
            Log.e("saveTask", e.getMessage());
        }
        return dateAlarm;
    }
}
