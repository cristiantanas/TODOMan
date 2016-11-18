package org.uab.dedam.todoman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.uab.dedam.todoman.Service.TODOManService;

public class HomeActivity extends AppCompatActivity {

    ITaskPresenter taskPresenter = new TaskPresenter(this);
    ListView listTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button newTask = (Button) findViewById(R.id.newTask);
        newTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, NewTaskActivity.class);
                startActivity(intent);
            }
        });
        listTasks();

        //startService(new Intent(getBaseContext(), TODOManService.class));
    }

    protected void listTasks() {
        listTasks = (ListView) findViewById(R.id.listTasks);
        taskPresenter.listAllTasks(listTasks);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        listTasks();
    }
}
