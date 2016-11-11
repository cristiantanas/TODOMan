package org.uab.dedam.todoman;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class HomeActivity extends AppCompatActivity {
    private ListView listViewTaskList;
    private TaskCursorAdapter taskCursorAdapter;
    private Context context;
    private TaskRepository taskRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        taskRepository = new TaskRepository(this);

        FloatingActionButton floatingActionButtonNewTask = (FloatingActionButton) findViewById(R.id.floatingActionButtonNewTask);
        floatingActionButtonNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, NewTaskActivity.class);
                startActivity(intent);
            }
        });

        listViewTaskList = (ListView) findViewById(R.id.listViewTaskList);
        taskCursorAdapter = new TaskCursorAdapter(HomeActivity.this, taskRepository.getTasks(), 0);
        listViewTaskList.setAdapter(taskCursorAdapter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        taskCursorAdapter.changeCursor(taskRepository.getTasks());
        taskCursorAdapter.notifyDataSetChanged();
    }
}
