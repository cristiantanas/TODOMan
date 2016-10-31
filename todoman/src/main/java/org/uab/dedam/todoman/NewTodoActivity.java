package org.uab.dedam.todoman;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

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

        //check if we have received a todoId
        Intent intent = getIntent();
        if (intent.hasExtra("todoId")){
            openExisting = true;
            todoId = intent.getIntExtra("todoId", -1);
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
        }

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

        Button btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
