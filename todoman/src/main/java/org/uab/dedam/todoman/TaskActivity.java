package org.uab.dedam.todoman;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TaskActivity extends Activity implements View.OnClickListener {


    private SQLiteDataRepository sqliteDatabase;




    Button btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;


    EditText title;
    EditText description;
    CheckBox completed;

    long timeInMilliseconds;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);


        title = (EditText) findViewById(R.id.TitleText);
        description = (EditText) findViewById(R.id.DescripText);
        completed = (CheckBox) findViewById(R.id.Completed);



        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        txtDate=(EditText)findViewById(R.id.in_date);
        txtTime=(EditText)findViewById(R.id.in_time);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);






        sqliteDatabase = new SQLiteDataRepository(this);

        Button savebutton = (Button) findViewById(R.id.SaveButton);
        savebutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                String titletext = title.getText().toString();
                String descriptiontext = description.getText().toString();
                Boolean completedcheckBox = completed.isChecked();



                sqliteDatabase.openDatabaseForWrite();


                sqliteDatabase.saveTask(titletext,descriptiontext,completedcheckBox.toString(),"");



                clearvalues();



                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                try {
                    Date mDate = null;

                        mDate = sdf.parse(txtDate.getText().toString() + " "+ txtTime.getText().toString());

                     timeInMilliseconds = mDate.getTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                }



                scheduleNotification(timeInMilliseconds);







                finish();
            }
        });


        Button clearbutton = (Button) findViewById(R.id.ClearButton);
        clearbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearvalues();
            }
        });


        Button cancelbutton = (Button) findViewById(R.id.CancelButton);
        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });




    }


    private void clearvalues() {

        title.setText("");
        description.setText("");
        completed.setChecked(false);


    }


    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }


    private void scheduleNotification( long delay) {

        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, delay, pendingIntent);
    }







}
