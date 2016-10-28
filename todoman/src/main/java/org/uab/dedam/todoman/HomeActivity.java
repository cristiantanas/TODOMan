package org.uab.dedam.todoman;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
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
    public void onMainFragmentIteration(Bundle parameters){
	String action= parameters.getString("action");
ContentFragment content = (ContentFragment)getSupportFragmentManager().findFragmentById(R.id.ContentFragment);
        if (content != null) {
content.doAction(action);
        } else {
            Intent intent = new Intent(HomeActivity.this,ContentActivity.class);
            intent.putExtra("action", action);
            startActivity(intent);
        }
	            }

    @Override
    public void onContentFragmentIteration(Bundle parameters) {
// TODO Implementar interacciones entre el Activity y el fragment de contenido.
    }

}

