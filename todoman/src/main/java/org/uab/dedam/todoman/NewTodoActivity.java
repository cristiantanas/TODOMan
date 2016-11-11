package org.uab.dedam.todoman;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewTodoActivity extends AppCompatActivity {

    EditText editTitle;
    EditText editDescription;
    TextView textDueDate;
    CheckBox checkDone;
    TaskRepository repo;

    boolean openExisting;
    int todoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_todo);

        //OPEN EXISTING, OR NEW
        Intent intent = getIntent();
        if (intent.hasExtra("todoId")){
            openExisting = true;
            todoId = intent.getIntExtra("todoId", -1);

            if (todoId==-1) throw new RuntimeException("ERROR: Failed to open todo item");

        }else{
            openExisting = false;
        }

        editTitle = (EditText) findViewById(R.id.editTodoTitle);
        editDescription = (EditText) findViewById(R.id.editTodoDescription);
        textDueDate = (TextView) findViewById(R.id.textDueDate);
        checkDone = (CheckBox) findViewById(R.id.checkDone);

        repo = new TaskRepository(this);

        if(openExisting){
            Cursor crs = repo.getTaskByID(todoId);
            editTitle.setText(crs.getString(crs.getColumnIndexOrThrow(TaskDBContract.COLUMN_TITLE)));
            editDescription.setText(crs.getString(crs.getColumnIndexOrThrow(TaskDBContract.COLUMN_DESCRIPTION)));
            textDueDate.setText(crs.getString(crs.getColumnIndexOrThrow(TaskDBContract.COLUMN_DUEDATE)));
            checkDone.setChecked(crs.getInt(crs.getColumnIndexOrThrow(TaskDBContract.COLUMN_DONE)) == 1);
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, 30);
            textDueDate.setText(String.valueOf(calendar.get(Calendar.YEAR)) + "." +
                                String.format("%02d", calendar.get(Calendar.MONTH) + 1) + "." +
                                String.format("%02d", calendar.get(Calendar.DAY_OF_MONTH)));
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

        //SAVE TODO
        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(openExisting){
                    repo.updateTask(todoId,
                            editTitle.getText().toString(),
                            editDescription.getText().toString(),
                            textDueDate.getText().toString(),
                            checkDone.isChecked());
                }else {
                    repo.saveTask(editTitle.getText().toString(),
                            editDescription.getText().toString(),
                            textDueDate.getText().toString());
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
}
