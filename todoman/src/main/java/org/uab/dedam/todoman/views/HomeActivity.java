package org.uab.dedam.todoman.views;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.support.design.widget.FloatingActionButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.uab.dedam.todoman.R;
import org.uab.dedam.todoman.presenter.HomeTasksAdapter;
import org.uab.dedam.todoman.presenter.TaskPresenter;

public class HomeActivity extends AppCompatActivity {
    TaskPresenter presenter;
    TextView texNoTasks;
    ImageView imgNoTasks;
    ListView lisTasks;
    HomeTasksAdapter listTaskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bindViews();
        presenter = new TaskPresenter(this);
        showTasks();

        FloatingActionButton gotoSubactivity = (FloatingActionButton) findViewById(R.id.btn_new_task);
        gotoSubactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, NewTaskActivity.class);
                startActivity(intent);
            }
        });
    }

    private void bindViews(){
        lisTasks = (ListView) findViewById(R.id.list_tasks);
        texNoTasks = (TextView) findViewById(R.id.tex_notask);
        imgNoTasks = (ImageView) findViewById(R.id.img_notask);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        showTasks();
    }

    private void showTasks(){
        Cursor cursorTasks = presenter.getTasks();

        if(cursorTasks.getCount() > 0) {
            lisTasks.setVisibility(View.VISIBLE);
            texNoTasks.setVisibility(View.GONE);
            imgNoTasks.setVisibility(View.GONE);

            if(listTaskAdapter == null)
            {
                listTaskAdapter = new HomeTasksAdapter(this, cursorTasks, 0);
                lisTasks.setAdapter(listTaskAdapter);
            }
            else
            {
                listTaskAdapter.changeCursor(presenter.getTasks());
                listTaskAdapter.notifyDataSetChanged();
            }
        }
        else {
            texNoTasks.setVisibility(View.VISIBLE);
            imgNoTasks.setVisibility(View.VISIBLE);
        }
    }
}
