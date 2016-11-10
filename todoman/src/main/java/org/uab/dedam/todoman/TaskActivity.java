package org.uab.dedam.todoman;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;


public class TaskActivity extends Activity {


    private SQLiteDataRepository sqliteDatabase;


    EditText title;
    EditText description;
    CheckBox completed;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);


        title = (EditText) findViewById(R.id.TitleText);
        description = (EditText) findViewById(R.id.DescripText);
        completed = (CheckBox) findViewById(R.id.Completed);



        sqliteDatabase = new SQLiteDataRepository(this);

        Button savebutton = (Button) findViewById(R.id.SaveButton);
        savebutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                String titletext = title.getText().toString();
                String descriptiontext = description.getText().toString();
                Boolean completedcheckBox = completed.isChecked();



                sqliteDatabase.openDatabaseForWrite();


                sqliteDatabase.saveTask(titletext,descriptiontext,completedcheckBox.toString(),"");



                clearvalues();


                finish();
            }
        });


        Button clearbutton = (Button) findViewById(R.id.ClearButton);
        clearbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearvalues();
            }
        });


        Button cancelbutton = (Button) findViewById(R.id.CancelButton);
        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });




    }


    private void clearvalues() {

        title.setText("");
        description.setText("");
        completed.setChecked(false);


    }


}
