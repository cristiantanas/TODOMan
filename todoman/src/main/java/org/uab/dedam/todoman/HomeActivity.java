package org.uab.dedam.todoman;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    ListView list;
    List<String> task = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        list = (ListView) findViewById(R.id.taskList);

        Button gotoSubactivity = (Button) findViewById(R.id.newTask);
        gotoSubactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, NewTaskActivity.class);
                startActivity(intent);
            }
        });

        showTask();
    }

    private void showTask(){
        TaskRepository taskRepository = new TaskRepository(HomeActivity.this);
        Cursor cursor = taskRepository.getTasks();
        task = new ArrayList<String>();
        String title ="", description ="", completed ="", endDate="", endTime="";
        if(cursor.moveToFirst()){
            do {
                title = cursor.getString(1);
                description = cursor.getString(2);
                completed = cursor.getString(3);
                endDate = cursor.getString(4);
                endTime = cursor.getString(5);

                task.add('\n'+title+'\n'+'\n'+description+'\n'+'\n'+completed+'\n'+'\n'+endDate+'\n'+'\n'+endTime+'\n');

            }while (cursor.moveToNext());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, task);
        list.setAdapter(adapter);

    }
}
