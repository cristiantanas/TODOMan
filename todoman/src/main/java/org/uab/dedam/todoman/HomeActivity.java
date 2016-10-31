package org.uab.dedam.todoman;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class HomeActivity extends AppCompatActivity {

    TaskRepository repo;
    Cursor cursorTaskList;
    ListView lview;
    TaskListAdapter taskListAdapter;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.idMenuPendingTasks:
            {
                item.setChecked(true);
                MenuItem menuitem = (MenuItem) findViewById(R.id.idMenuDoneTasks);
                menuitem.setChecked(false);
            }

            case R.id.idMenuDoneTasks:
            {
                item.setChecked(true);
                MenuItem menuitem = (MenuItem) findViewById(R.id.idMenuPendingTasks);
                menuitem.setChecked(false);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        UpdateTaskList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        lview = (ListView) findViewById(R.id.listTodoList);
        UpdateTaskList();

        Button button = (Button) findViewById(R.id.btnNewTodo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, NewTodoActivity.class);
                startActivity(intent);
            }
        });

        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int todoId;

                cursorTaskList.moveToPosition(position);
                todoId = cursorTaskList.getInt(cursorTaskList.getColumnIndexOrThrow("_id"));
                Intent intent = new Intent(HomeActivity.this, NewTodoActivity.class);
                intent.putExtra("todoId",todoId);
                startActivity(intent);
            }
        });
    }

    private void UpdateTaskList(){
        if (repo == null) repo = new TaskRepository(this);
        if (taskListAdapter == null){
            taskListAdapter = new TaskListAdapter(this, null, true);
            lview.setAdapter(taskListAdapter);
        }

        cursorTaskList = repo.getTasks();
        taskListAdapter.changeCursor(cursorTaskList);
    }


}
