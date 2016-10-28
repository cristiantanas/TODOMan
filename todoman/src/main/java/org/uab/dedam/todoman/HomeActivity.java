package org.uab.dedam.todoman;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import static org.uab.dedam.todoman.R.id.textView;

public class HomeActivity extends FragmentActivity {

    TextView taskTitle;
    TextView taskDescription;
    TextView taskCompleted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (findViewById(R.id.mobile) != null){
             ListFragment list = new ListFragment();
             getSupportFragmentManager().beginTransaction().add(R.id.mobile, list).commit();
        }

        taskTitle = (TextView) findViewById(R.id.title);
        taskDescription = (TextView) findViewById(R.id.description);
        taskCompleted = (TextView) findViewById(R.id.completed);

        Button gotoSubactivity = (Button) findViewById(R.id.gotoSubactivity);
        gotoSubactivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(HomeActivity.this , TaskFormActivity.class);
                startActivity(intent);
                

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("TASK_PREFS", MODE_PRIVATE);
        taskTitle.setText(preferences.getString("task_title", "No title Yet"));
        taskDescription.setText(preferences.getString("task_description", "No description Yet"));
        taskCompleted.setText(preferences.getBoolean("task_complete", false) ? "Task Completed" : "Task not Completed");
    }
}
