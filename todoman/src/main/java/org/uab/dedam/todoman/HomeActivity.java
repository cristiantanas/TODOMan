package org.uab.dedam.todoman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity
        implements TodoListFragment.TodoListFragmentActionListener,
        NewTodoFragment.NewTodoFragmentActionListener {

    FrameLayout frameLayout1;
    FrameLayout frameLayout2;
    boolean tabletSize;


    @Override
    public void onSave() {
        Toast.makeText(HomeActivity.this, R.string.toastTodoSaved, Toast.LENGTH_SHORT).show();
        frameLayout2.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onCancel() {
        frameLayout2.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onNewTodoButtonClicked() {
        if (tabletSize) {
            frameLayout2.setVisibility(View.VISIBLE);
        } else {
            Intent intent = new Intent(this, NewTodoActivity.class);
            startActivity(intent);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tabletSize = getResources().getBoolean(R.bool.isTablet);
        frameLayout1 = (FrameLayout) findViewById(R.id.fragment1_container);
        frameLayout2 = (FrameLayout) findViewById(R.id.fragment2_container);

        FragmentManager fragmentMgr = getSupportFragmentManager();
        if(tabletSize) {
            frameLayout1.setVisibility(View.VISIBLE);
            frameLayout2.setVisibility(View.INVISIBLE);

            TodoListFragment frgList = new TodoListFragment();
            NewTodoFragment frgNew = new NewTodoFragment();

            FragmentTransaction fragmentTransaction = fragmentMgr.beginTransaction();
            fragmentTransaction.add(R.id.fragment1_container, frgList);
            fragmentTransaction.add(R.id.fragment2_container, frgNew);
            fragmentTransaction.commit();
        }else{
            TodoListFragment frgList = new TodoListFragment();
            FragmentTransaction fragmentTransaction = fragmentMgr.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, frgList);
            fragmentTransaction.commit();
        }
    }
}
