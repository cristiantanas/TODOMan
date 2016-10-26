package org.uab.dedam.todoman;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.uab.dedam.todoman.R;


public class HomeActivity extends AppCompatActivity implements DatePickerFragment.OnDateSetCallback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        boolean existsFrgNewTask = (getSupportFragmentManager().findFragmentById(R.id.fragment_new_task) != null);
        if(!existsFrgNewTask){
            FloatingActionButton btnNewTask = (FloatingActionButton) findViewById(R.id.btn_new_task);
            btnNewTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                boolean existsFrgNewTask = (getSupportFragmentManager().findFragmentById(R.id.fragment_new_task) != null);
                if(!existsFrgNewTask) {
                    Intent i = new Intent(HomeActivity.this, NewTaskActivity.class);
                    startActivity(i);
                }
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDateSelected(String date) {
        FragmentNewTask frgNewTask = ((FragmentNewTask)getSupportFragmentManager().findFragmentById(R.id.fragment_new_task));
        frgNewTask.setDate(date);
    }

    @Override
    public void selectDate() {
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(getSupportFragmentManager(), "datePickerFragment");
    }
}

