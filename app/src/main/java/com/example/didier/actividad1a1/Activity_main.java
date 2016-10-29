package com.example.didier.actividad1a1;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Activity_main extends Activity implements MenuFragment.Communicator {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public void Message(String Option){
        DetailFragment detailfragment = (DetailFragment) getFragmentManager()
                .findFragmentById(R.id.detail_Fragment);
        if (detailfragment != null && detailfragment.isInLayout()) {
            detailfragment.setText(Option);
        }
        else {
            Intent intent = new Intent(getApplicationContext(),
                    NewTaskActivity.class);
            Bundle extras = new Bundle();
            extras.putString(NewTaskActivity.option, Option);
            intent.putExtras(extras);
            startActivity(intent);
        }
    }
    public void onButtonClicked(View v){
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(),"Date Picker");
    }
}
