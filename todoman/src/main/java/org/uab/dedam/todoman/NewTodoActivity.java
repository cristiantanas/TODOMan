package org.uab.dedam.todoman;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
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
    public static final int ACTION_SETTASKDONE = 4;

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
                //CANCEL alarm, set TASK DONE, CLOSE DB
                cancelTaskAlarm();
                repo.setTaskDone(todoId);
                repo.CloseDB();
                finish();
                return;

            case ACTION_DELETETASK:
                //CANCEL alarm, DELETE task, CLOSE DB
                cancelTaskAlarm();
                repo.deleteTask(todoId);
                repo.CloseDB();
                finish();
                return;

            case ACTION_EDITTASK:
                //set openExisting flag, and continue with UI
                openExisting=true;
                break;

            case ACTION_NEWTASK:
                //set openExisting flag, and continue with UI
                openExisting=false;
                break;

            default:
                throw new RuntimeException("Invalid action error");
        }

        //Now we can continue building up the UI
        setContentView(R.layout.activity_new_todo);

        editTitle = (EditText) findViewById(R.id.editTodoTitle);
        editDescription = (EditText) findViewById(R.id.editTodoDescription);
        textDueDate = (TextView) findViewById(R.id.textDueDate);
        textDueTime = (TextView) findViewById(R.id.textDueTime);
        checkDone = (CheckBox) findViewById(R.id.checkDone);

        if(openExisting){
            //opening EXISTING TASK ITEM
            Cursor crs = repo.getTaskByID(todoId);

            //convert UTC date from DB to LOCAL date
            String textUTCDate = crs.getString(crs.getColumnIndexOrThrow(TaskDBContract.COLUMN_DUEDATE));
            String textLocalDate = Tools.convertFromUTC(textUTCDate);
            String datePart = textLocalDate.split(" ")[0];
            String timePart = textLocalDate.split(" ")[1];

            //populate data into UI
            editTitle.setText(crs.getString(crs.getColumnIndexOrThrow(TaskDBContract.COLUMN_TITLE)));
            editDescription.setText(crs.getString(crs.getColumnIndexOrThrow(TaskDBContract.COLUMN_DESCRIPTION)));
            textDueDate.setText(datePart);
            textDueTime.setText(timePart);
            checkDone.setChecked(crs.getInt(crs.getColumnIndexOrThrow(TaskDBContract.COLUMN_DONE)) == 1);
        } else {
            //creating NEW TASK ITEM - (default DueDate is 30 days from now at 12:00)
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, 30);
            textDueDate.setText(String.valueOf(calendar.get(Calendar.YEAR)) + "." +
                                String.format("%02d", calendar.get(Calendar.MONTH) + 1) + "." +
                                String.format("%02d", calendar.get(Calendar.DAY_OF_MONTH)));
            textDueTime.setText("12:00");
        }

        //DATE PICKER
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

        //TIME PICKER
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
                    //update record, cancel ALARM, create new ALARM
                    repo.updateTask(todoId,
                            editTitle.getText().toString(),
                            editDescription.getText().toString(),
                            textUTCDate,
                            checkDone.isChecked());

                    //cancel old alarm
                    cancelTaskAlarm();
                    //create new alarm, if not DONE
                    if(!checkDone.isChecked()) { createTaskAlarm(); }

                }else {
                    //insert new record, and create ALARM
                    todoId = repo.saveTask(editTitle.getText().toString(),
                            editDescription.getText().toString(),
                            textUTCDate);

                    if(!checkDone.isChecked()) { createTaskAlarm(); }
                }
                finish();
            }
        });

        //CLEAR - set default values
        Button btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, 30);

                editTitle.setText("");
                editDescription.setText("");
                textDueDate.setText(String.valueOf(calendar.get(Calendar.YEAR)) + "." +
                        String.format("%02d", calendar.get(Calendar.MONTH) + 1) + "." +
                        String.format("%02d", calendar.get(Calendar.DAY_OF_MONTH)));
                textDueTime.setText("12:00");
                checkDone.setChecked(false);

                //if we are editing an existing item, cancel alarm
                if (openExisting) { cancelTaskAlarm(); }
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

    @Override
    protected void onStop() {
        if (repo != null) {
            repo.CloseDB();
            repo = null;
        }
        super.onStop();
    }

    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            textDueDate.setText(String.valueOf(year) + "." +
                    String.format("%02d", monthOfYear + 1) + "." +
                    String.format("%02d", dayOfMonth));
        }
    };

    TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            textDueTime.setText(String.format("%02d", hourOfDay) + ":" +
                                String.format("%02d", minute));
        }
    };

    private void createTaskAlarm(){
        //get the AlarmManager
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        //calculate exact date value as LONG in UTC time
        String textUTCDate = Tools.convertToUTC(textDueDate.getText().toString() + " " + textDueTime.getText().toString());
        Long msTime = Tools.convertFromUTCToMilliseconds(textUTCDate);

        if(msTime<Calendar.getInstance().getTimeInMillis()){
            Toast.makeText(this, "Can't set alarm in the past", Toast.LENGTH_SHORT).show();
            return;
        }

        alarmManager.set(AlarmManager.RTC, msTime, getTaskAlarmPendingIntent());

        //update HASALARM flag
        repo.setHasAlarm(todoId);
    }

    private  void cancelTaskAlarm(){
        //get the AlarmManager
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.cancel(getTaskAlarmPendingIntent());

        //clear HASALARM flag
        repo.unsetHasAlarm(todoId);
    }

    private PendingIntent getTaskAlarmPendingIntent(){
        //prepare pending intent
        Intent alarmIntent = new Intent("org.uab.dedam.todoman.TASKDUE");
        alarmIntent.putExtra("todoId", todoId);
        return PendingIntent.getBroadcast(NewTodoActivity.this, todoId, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT);
    }

}
