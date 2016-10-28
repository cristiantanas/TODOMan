package org.uab.dedam.todoman;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HomeActivity extends AppCompatActivity
                            implements ShowTasksFragment.IListTasks,
                                        AddUpdateFragment.IAddUpdateListener,
                                        DateTimeFragment.onDateSetCallback {

    Fragment showTasksFragment;
    AddUpdateFragment addUpdateFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        showTasksFragment = new ShowTasksFragment();
        fragmentTransaction.add(R.id.frameShowTasks, showTasksFragment);
        if (tabletSize) {
            addUpdateFragment = new AddUpdateFragment();
            fragmentTransaction.add(R.id.frameAddUpdateTask, addUpdateFragment);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void addUpdateTask() {
        Intent intent = new Intent(HomeActivity.this, NewTaskActivity.class);
        startActivity(intent);
    }

    @Override
    public void saveTask() {

    }

    @Override
    public void onDateSelected(String date) {
        //addUpdateFragment.setDueDate(date);
    }
}
