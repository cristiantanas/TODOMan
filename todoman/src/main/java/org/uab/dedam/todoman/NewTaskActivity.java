package org.uab.dedam.todoman;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class NewTaskActivity extends FragmentActivity implements ShowTasksFragment.IListTasks,
                                                                AddUpdateFragment.IAddUpdateListener,
                                                                DateTimeFragment.onDateSetCallback  {
    AddUpdateFragment addUpdateFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newtask);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        addUpdateFragment = new AddUpdateFragment();
        fragmentTransaction.add(R.id.fragmentAddUpdateTask, addUpdateFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void saveTask() {

    }

    @Override
    public void onDateSelected(String date) {
        addUpdateFragment.setDueDate(date);
    }

    @Override
    public void addUpdateTask() {

    }
}
