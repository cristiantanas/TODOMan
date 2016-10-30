package org.uab.dedam.todoman;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class HomeActivity extends AppCompatActivity {

    TaskRepository repo;
    Cursor cursorTaskList;
    ListView lview;
    TaskListAdapter taskListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        lview = (ListView) findViewById(R.id.listTodoList);

        repo = new TaskRepository(this);
        cursorTaskList = repo.getTasks();

        taskListAdapter = new TaskListAdapter(this, cursorTaskList, true);
        lview.setAdapter(taskListAdapter);

        Button button = (Button) findViewById(R.id.btnNewTodo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, NewTodoActivity.class);
                startActivity(intent);
            }
        });
    }
}
