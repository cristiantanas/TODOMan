package org.uab.dedam.todoman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity implements HomeFragment.OnNextButtonCallback{

    private boolean tablet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //tablet o smartphone
        if (findViewById(R.id.fragment_task) != null) {
            tablet = true;
            //Toast.makeText(getApplicationContext(), "Tablet", Toast.LENGTH_SHORT).show();
        } else {
            //Toast.makeText(getApplicationContext(), "Mobile", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onNextPressed() {
        if (!tablet) {
            Intent intent = new Intent(HomeActivity.this, NewTaskActivity.class);
            startActivity(intent);
        } else {
           /* FragmentManager fragmentManager=getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            TaskFragment formFragment =new TaskFragment();
            fragmentTransaction.add(R.id.fragment_task,formFragment);
            fragmentTransaction.commit();*/
        }
    }
}
