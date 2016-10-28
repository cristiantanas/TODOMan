package org.uab.dedam.todoman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_todo);

        editTitle = (EditText) findViewById(R.id.editTodoTitle);
        editDescription = (EditText) findViewById(R.id.editTodoDescription);
        textDueDate = (TextView) findViewById(R.id.textDueDate);
        checkDone = (CheckBox) findViewById(R.id.checkDone);

        repo = new TaskRepository(this);

        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repo.saveTask(editTitle.getText().toString(),
                        editDescription.getText().toString(),
                        textDueDate.getText().toString());

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
