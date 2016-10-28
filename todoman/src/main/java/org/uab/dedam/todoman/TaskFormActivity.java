package org.uab.dedam.todoman;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class TaskFormActivity extends FragmentActivity implements DatePickerFragment.OnDateSetCallback {

    EditText description,title;
    Switch taskComplete;
    Button taskSetDate;
    Button saveButtom;
    TextView taskEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_form);
        taskSetDate = (Button) findViewById(R.id.taskSetDate);
        taskEndDate = (TextView) findViewById(R.id.taskEndDate);
        saveButtom = (Button) findViewById(R.id.saveTask);
        title = (EditText) findViewById(R.id.title);
        description = (EditText) findViewById(R.id.description);
        taskComplete = (Switch) findViewById(R.id.taskComplete);


        taskSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment timePickerFragment = new DatePickerFragment();
                timePickerFragment.show(getSupportFragmentManager(), "timePickerFragment");
            }
        });

        saveButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("TASK_PREFS" , MODE_PRIVATE);
                SharedPreferences.Editor preferenceEditor = preferences.edit();
                preferenceEditor.putString("task_title", title.getText().toString());
                preferenceEditor.putString("task_description", description.getText().toString());
                preferenceEditor.putBoolean("task_complete", taskComplete.isChecked());
                preferenceEditor.commit();

                finish();

            }
        });
    }

    @Override
    public void onDataSelected(String date) {
        taskEndDate.setText(date);
    }
}
