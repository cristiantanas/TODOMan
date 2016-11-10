package org.uab.dedam.todoman;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class HomeActivity extends AppCompatActivity {


    private SQLiteDataRepository sqliteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sqliteDatabase = new SQLiteDataRepository(this);
        sqliteDatabase.openDatabaseForReadOnly();
        Cursor cursortoretrieve= sqliteDatabase.retrieveAllTasks();
        ListView lvItems = (ListView) findViewById(R.id.ListVi);
        TodoCursorAdapter todoAdapter = new TodoCursorAdapter(this, cursortoretrieve);
        lvItems.setAdapter(todoAdapter);

        Button newactivity = (Button) findViewById(R.id.NewTaskButton);
        newactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomeActivity.this, TaskActivity.class);
                startActivity(intent);

            }
        }
        );


    }

    @Override
    protected void onResume() {
        super.onResume();

        sqliteDatabase = new SQLiteDataRepository(this);
        sqliteDatabase.openDatabaseForReadOnly();
        Cursor cursortoretrieve= sqliteDatabase.retrieveAllTasks();
        ListView lvItems = (ListView) findViewById(R.id.ListVi);
        TodoCursorAdapter todoAdapter = new TodoCursorAdapter(this, cursortoretrieve);
        lvItems.setAdapter(todoAdapter);

    }
}
