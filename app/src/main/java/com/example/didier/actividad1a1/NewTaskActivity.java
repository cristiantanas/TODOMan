package com.example.didier.actividad1a1;
/**
 * Created by Didier on 29/10/2016.
 */
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

public class NewTaskActivity extends Activity {
    public static String option = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }
        setContentView(R.layout.newtask_activity);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String name = extras.getString(option);
            DetailFragment detailFragment = (DetailFragment) getFragmentManager()
                    .findFragmentById(R.id.detailFragment);
            detailFragment.setText(name);
        }
    }
    public void onButtonClicked(View v){
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(),"Date Picker");
    }
}

