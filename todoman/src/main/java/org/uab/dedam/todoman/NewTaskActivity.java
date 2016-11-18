package org.uab.dedam.todoman;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;


public class NewTaskActivity extends AppCompatActivity implements DatePickerFragment.OnDateSetCallback,TimePickerFragment.OnTimeSetCallback {

    private DbAdapter sqliteDatabase;


    EditText taskTitle;
    EditText taskDescription;
    CheckBox taskCompleted;
    TextView taskEndDate;
    Button taskSetDate;
    Button saveButton;
    Button cancelButton;
    Button clearButton;
    Button taskSetHour;
    TextView taskEndHour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newtask);

        sqliteDatabase = new DbAdapter(this);

        taskTitle = (EditText) findViewById(R.id.taskTitle);
        taskDescription = (EditText) findViewById(R.id.taskDescription);
        taskCompleted = (CheckBox) findViewById(R.id.taskCompleted);
        taskEndDate = (TextView) findViewById(R.id.taskEndDate);
        taskSetDate = (Button) findViewById(R.id.taskSetDate);
        saveButton = (Button) findViewById(R.id.saveTask);
        cancelButton = (Button) findViewById(R.id.cancelTask);
        clearButton = (Button) findViewById(R.id.clearTask);
        taskSetHour = (Button) findViewById(R.id.taskSetHour);
        taskEndHour =(TextView) findViewById(R.id.taskEndHour);


        taskSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.show(getFragmentManager(), "datePickerFragment");
            }
        });

        taskSetHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment timePickerFragment=new TimePickerFragment();
                timePickerFragment.show(getFragmentManager(),"timePickerFragment");
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = taskTitle.getText().toString();
                String description = taskDescription.getText().toString();
                String endDate = taskEndDate.getText().toString();
                String endTime = taskEndHour.getText().toString();
                Boolean done = taskCompleted.isChecked();

                if (title.isEmpty()) {
                    messageBox("Choose a Title");

                } else {
                    sqliteDatabase.openDatabaseForWrite();
                    sqliteDatabase.saveTask(title, description, endDate, endTime, done);

                    int i = 1;
                    Intent intent = new Intent(NewTaskActivity.this,TimerAlarm.class);
                    PendingIntent pendingIntent =
                            PendingIntent.getBroadcast(getApplicationContext(),234324243, intent, 0);
                    AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                    alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+2000,
                            pendingIntent);
                    messageBox("Alarma puesta en "+5+" segundos");

                }
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearForm();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void clearForm() {
        taskTitle.setText("");
        taskDescription.setText("");
        taskEndDate.setText("No Date");
        taskCompleted.setChecked(false);
    }

    @Override
    public void onDateSelected(String date) {
        taskEndDate.setText(date);
    }

    public void onTimeSelected(String hour) {
        taskEndHour.setText(hour);
    }

    private void messageBox(String message) {
        Toast.makeText(this.getApplicationContext(),
                message, Toast.LENGTH_SHORT).show();
    }
}
