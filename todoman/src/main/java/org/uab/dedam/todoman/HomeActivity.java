package org.uab.dedam.todoman;

import android.app.AlarmManager;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
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
    protected void onResume() {
        super.onResume();
        UpdateTaskList();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int todoId;
        View itemView = ((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).targetView;
        todoId = (int) itemView.getTag();

        switch (item.getItemId()){
            case R.id.idContextMenuDelete:
                //DELETE TASK
                Intent intent = new Intent(HomeActivity.this, NewTodoActivity.class);
                intent.putExtra("todoId", todoId);
                intent.putExtra("action", NewTodoActivity.ACTION_DELETETASK);
                startActivity(intent);

                break;

            case R.id.idContextMenuTaskDone:
                repo.setTaskDone(todoId);
                break;
        }

        UpdateTaskList();
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        lview = (ListView) findViewById(R.id.listTodoList);
        registerForContextMenu(lview);
        UpdateTaskList();

        //CREATE NEW TASK
        Button button = (Button) findViewById(R.id.btnNewTodo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, NewTodoActivity.class);
                intent.putExtra("action", NewTodoActivity.ACTION_NEWTASK);
                startActivity(intent);
            }
        });

        //OPEN EXISTING TASK
        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursorTaskList.moveToPosition(position);

                int todoId = cursorTaskList.getInt(cursorTaskList.getColumnIndexOrThrow("_id"));

                Intent intent = new Intent(HomeActivity.this, NewTodoActivity.class);
                intent.putExtra("todoId", todoId);
                intent.putExtra("action", NewTodoActivity.ACTION_EDITTASK);
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

        //cursorTaskList = repo.getTasks();
        cursorTaskList = repo.getTasksSorted();
        taskListAdapter.changeCursor(cursorTaskList);
    }


}
