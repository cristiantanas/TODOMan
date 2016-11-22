package org.uab.dedam.todoman;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private DbAdapter sqliteDatabase;

    Cursor tasks;

    ListView lvTasks;
    TextView title;
    TextView description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button gotoSubactivity = (Button) findViewById(R.id.newTask);
        lvTasks = (ListView) findViewById(R.id.lvTasks);
        title = (TextView) findViewById(R.id.taskTitle);
        description = (TextView) findViewById(R.id.taskDescription);

        sqliteDatabase = new DbAdapter(this);

        showTasks();

        gotoSubactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, NewTaskActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showTasks() {
        sqliteDatabase.openDatabaseForReadOnly();
        tasks = sqliteDatabase.returnTasks();
        CustomList taskAdapter = new CustomList(this, tasks);
        lvTasks.setAdapter(taskAdapter);
    }

    private void messageBox(String mensaje) {
        Toast.makeText(this.getApplicationContext(),
                mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showTasks();
    }
}
