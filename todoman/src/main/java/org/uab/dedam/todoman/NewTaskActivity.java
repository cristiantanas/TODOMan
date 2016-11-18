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
import android.widget.TextView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewTaskActivity extends AppCompatActivity
        implements DatePickerFragment.OnDateSetCallBack, TimePickerFragment.OnDateSetCallback {
    Button taskSetDate;
    Button save;
    Button setTime;
    TextView taskEndDate;
    TextView taskTime;
    EditText taskTitle;
    EditText taskDescription;
    CheckBox taskCompleted;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newtask);

        taskTitle = (EditText) findViewById(R.id.taskTitle);
        taskDescription = (EditText) findViewById(R.id.taskDescription);
        taskCompleted = (CheckBox) findViewById(R.id.taskComplete) ;
        save = (Button) findViewById(R.id.saveTask);
        taskEndDate = (TextView) findViewById(R.id.taskDate);
        taskSetDate = (Button) findViewById(R.id.taskSetDate);
        taskTime = (TextView) findViewById(R.id.taskTime);
        setTime = (Button)findViewById(R.id.taskSetTime);
        taskSetDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.onAttach(NewTaskActivity.this);
                datePickerFragment.show(getSupportFragmentManager(), "datePickerFragment");
            }
        });

        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment timePickerFragment = new TimePickerFragment();
                timePickerFragment.onAttach(NewTaskActivity.this);
                timePickerFragment.show(getSupportFragmentManager(),"TimePicker");
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskRepository taskRepository = new TaskRepository(NewTaskActivity.this);
                taskRepository.save(
                        taskTitle.getText().toString(),
                        taskDescription.getText().toString(),
                        taskCompleted.getText().toString(),
                        taskEndDate.getText().toString(),
                        taskTime.getText().toString()
                        );


                String hour = taskTime.getText().toString();
                String date = taskEndDate.getText().toString();
                String hd = date +" "+hour;
                Date parsedDate = null;
                Calendar calendar = Calendar.getInstance();

                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                try {
                    parsedDate = format.parse(hd);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                
                calendar.setTime(parsedDate);

                Intent intent2 = new Intent(NewTaskActivity.this, Receiver.class);
                PendingIntent pendingIntent = PendingIntent.getService(NewTaskActivity.this, 001, intent2,0);
                AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

                Intent intent = new Intent(NewTaskActivity.this, HomeActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onDateSelected(String date){taskEndDate.setText(date);}

    @Override
    public void onTimeSelected(String time){taskTime.setText(time);}


}
