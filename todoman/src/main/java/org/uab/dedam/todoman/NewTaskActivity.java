package org.uab.dedam.todoman;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class NewTaskActivity extends AppCompatActivity implements DatePickerFragment.OnDateSetCallback,TimePickerFragment.OnTimeSetCallback {

    private DbAdapter sqliteDatabase;

    private static final String SAVE_AND_GO="org.uab.dedam.todoman.BROADCAST_ACTION";

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

                    String dateString=endDate+" "+endTime;
                    Date alarmDate=stringToDate(dateString);
                    long alarmMillis=alarmDate.getTime();

                    Intent intent = new Intent(SAVE_AND_GO);

                    intent.putExtra("title",title);
                    intent.putExtra("description",description);
                    intent.putExtra("done",done);


                    PendingIntent pendingIntent =
                            PendingIntent.getBroadcast(getApplicationContext(),234324243, intent, 0);


                    AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                    alarmManager.set(AlarmManager.RTC_WAKEUP, alarmMillis,
                            pendingIntent);
                    messageBox("Alarma puesta: "+dateString);
                    finish();
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
        taskEndHour.setText("No Hour");
        taskCompleted.setChecked(false);
    }

    private Date stringToDate(String dateString){
        Date convertedDate=new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy hh:mm");

        try {
            convertedDate=dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return convertedDate;
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
