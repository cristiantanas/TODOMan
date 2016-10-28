package org.uab.dedam.todoman;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        SecondFragment secondFragment = new SecondFragment();
        FragmentManager fragMgr = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragMgr.beginTransaction();
        fragmentTransaction.add(R.id.activity_form,secondFragment);
        fragmentTransaction.commit();


    }
}
