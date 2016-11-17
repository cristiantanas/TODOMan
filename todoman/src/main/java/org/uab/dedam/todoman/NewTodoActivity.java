package org.uab.dedam.todoman;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewTodoActivity extends AppCompatActivity {

    public static final int ACTION_NEWTASK = 1;
    public static final int ACTION_EDITTASK = 2;
    public static final int ACTION_DELETETASK = 3;
    public static final int ACTION_DUETASK = 4; //used by the alarm
    public static final int ACTION_SETTASKDONE = 5;

    EditText editTitle;
    EditText editDescription;
    TextView textDueDate;
    TextView textDueTime;
    CheckBox checkDone;
    TaskRepository repo;

    boolean openExisting;
    int todoId;
    int action;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        todoId = intent.getIntExtra("todoId", -1);
        action = intent.getIntExtra("action", 0);
        repo = new TaskRepository(this);

        switch (action) {
            case ACTION_SETTASKDONE:
                //Set task DONE, CANCEL alarm
                repo.setTaskDone(todoId);
                cancelTaskAlarm();
                finish();
                return;

            case ACTION_DELETETASK:
                //DELETE task, CANCEL alarm
                repo.deleteTask(todoId);
                cancelTaskAlarm();
                finish();
                return;

            case ACTION_DUETASK:
                //CREATE notification

                //createTaskAlarm();
                finish();
                return;

            case ACTION_EDITTASK:
                openExisting=true;
                break;

            case ACTION_NEWTASK:
                openExisting=false;
                break;

            default:
                throw new RuntimeException("Invalid action error");
        }

        //NOW we can continue building up the UI
        setContentView(R.layout.activity_new_todo);

        editTitle = (EditText) findViewById(R.id.editTodoTitle);
        editDescription = (EditText) findViewById(R.id.editTodoDescription);
        textDueDate = (TextView) findViewById(R.id.textDueDate);
        textDueTime = (TextView) findViewById(R.id.textDueTime);
        checkDone = (CheckBox) findViewById(R.id.checkDone);

        if(openExisting){
            //OPENING EXISTING TASK ITEM
            Cursor crs = repo.getTaskByID(todoId);

            //convert UTC date from DB to LOCAL date
            String textUTCDate = crs.getString(crs.getColumnIndexOrThrow(TaskDBContract.COLUMN_DUEDATE));
            String textLocalDate = Tools.convertFromUTC(textUTCDate);
            String datePart = textLocalDate.split(" ")[0];
            String timePart = textLocalDate.split(" ")[1];

            editTitle.setText(crs.getString(crs.getColumnIndexOrThrow(TaskDBContract.COLUMN_TITLE)));
            editDescription.setText(crs.getString(crs.getColumnIndexOrThrow(TaskDBContract.COLUMN_DESCRIPTION)));
            textDueDate.setText(datePart);
            textDueTime.setText(timePart);
            checkDone.setChecked(crs.getInt(crs.getColumnIndexOrThrow(TaskDBContract.COLUMN_DONE)) == 1);
        } else {
            //CREATING NEW TASK ITEM - (default due date is in 30 days from now)
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, 30);
            textDueDate.setText(String.valueOf(calendar.get(Calendar.YEAR)) + "." +
                                String.format("%02d", calendar.get(Calendar.MONTH) + 1) + "." +
                                String.format("%02d", calendar.get(Calendar.DAY_OF_MONTH)));
            textDueTime.setText("12:00");
        }

        //DATE SELECTION
        textDueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dateText = textDueDate.getText().toString();
                Calendar calendar = Calendar.getInstance();
                Date date;

                if(!dateText.isEmpty()){
                    DateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
                    try {
                        date = formatter.parse(dateText);
                        calendar.setTime(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                DatePickerDialog datePicker = new DatePickerDialog(
                        NewTodoActivity.this,
                        onDateSetListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));

                datePicker.show();
            }
        });

        //TIME SELECTION
        textDueTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String timeText = textDueTime.getText().toString();
                int timeHour = Integer.parseInt(timeText.split(":")[0]);
                int timeMin = Integer.parseInt(timeText.split(":")[1]);

                TimePickerDialog timePicker = new TimePickerDialog(
                        NewTodoActivity.this,
                        onTimeSetListener,
                        timeHour,
                        timeMin,
                        true);

                timePicker.show();
            }
        });

        //SAVE TASK
        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //convert LOCAL date to UTC date to save in DB
                String textUTCDate = Tools.convertToUTC(textDueDate.getText().toString() + " " + textDueTime.getText().toString());

                if(openExisting){
                    repo.updateTask(todoId,
                            editTitle.getText().toString(),
                            editDescription.getText().toString(),
                            textUTCDate,
                            checkDone.isChecked());
                }else {
                    todoId = repo.saveTask(editTitle.getText().toString(),
                            editDescription.getText().toString(),
                            textUTCDate);
                    createTaskAlarm();
                }
                finish();
            }
        });

        //CLEAR
        Button btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTitle.setText("");
                editDescription.setText("");
                textDueDate.setText("");
                checkDone.setChecked(false);
            }
        });

        //CANCEL
        Button btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            textDueDate.setText(String.valueOf(year) + "." + String.format("%02d", monthOfYear + 1) + "." + String.format("%02d", dayOfMonth));
        }
    };

    TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            textDueTime.setText(String.valueOf(hourOfDay) + ":" + String.valueOf(minute));
        }
    };

    private void createTaskAlarm(){
        //get the AlarmManager
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        //calculate exact date value as LONG in UTC time
        String textUTCDate = Tools.convertToUTC(textDueDate.getText().toString() + " " + textDueTime.getText().toString());
        Long msTime = Tools.convertFromUTCToMilliseconds(textUTCDate);

        if(msTime==0){
            Toast.makeText(this, "Something went wrong setting up the alarm", Toast.LENGTH_SHORT);
            return;
        }

        alarmManager.set(AlarmManager.RTC, msTime, getTaskAlarmPendingIntent());
    }

    private  void cancelTaskAlarm(){
        //get the AlarmManager
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.cancel(getTaskAlarmPendingIntent());
    }

    private PendingIntent getTaskAlarmPendingIntent(){
        //prepare pending intent
        Intent taskIntent = new Intent("org.uab.dedam.todoman.TASKDUE");
        taskIntent.putExtra("todoId", todoId);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(NewTodoActivity.this, 1, taskIntent, 0);
        return pendingIntent;
    }

}
