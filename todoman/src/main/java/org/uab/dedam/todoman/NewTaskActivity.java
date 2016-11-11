package org.uab.dedam.todoman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class NewTaskActivity extends AppCompatActivity {
    Button taskSetDate;
    Button save;
    TextView taskEndDate;
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

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskRepository taskRepository = new TaskRepository(NewTaskActivity.this);
                taskRepository.save(
                        taskTitle.getText().toString(),
                        taskDescription.getText().toString(),
                        taskCompleted.getText().toString());
                Intent intent = new Intent(NewTaskActivity.this, HomeActivity.class);
                startActivity(intent);

            }
        });

    }
}
