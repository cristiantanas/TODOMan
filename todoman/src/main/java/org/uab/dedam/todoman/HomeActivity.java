package org.uab.dedam.todoman;

import android.content.Intent;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity implements MainFragment.MainFragmentIterationListener, ContentFragment.ContentFragmentIterationListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


    }

    @Override
    public void onMainFragmentIteration(Bundle parameters) {

    }

    @Override
    public void onContentFragmentIteration(Bundle parameters) {

    }
}

