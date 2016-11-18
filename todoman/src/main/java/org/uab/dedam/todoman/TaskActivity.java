package org.uab.dedam.todoman;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

public class TaskActivity extends AppCompatActivity implements TaskView {
    EditText txt_taskName;
    EditText txt_taskDescription;
    EditText txt_taskDate;
    Button btn_taskDate;
    EditText txt_taskTime;
    Button btn_tasTime;
    ToggleButton tgl_taskCompleted;
    Button btn_taskSave;
    Button btn_taskClear;
    Button btn_taskCancel;
    HomePresenter presenter;
    Context taskActivityContext;
    task myTask;
    Handler h = new Handler(){
        @Override
        public void handleMessage(Message msg){
            txt_taskDate.setText(msg.getData().getString("selected_date"));
        }
    };
    Handler t = new Handler(){
        @Override
        public void handleMessage(Message msg){
            txt_taskTime.setText(msg.getData().getString("selected_time"));
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newtask);
        taskActivityContext = this;
        presenter = new HomePresenterImpl(taskActivityContext, null, this, new DataBaseInteractorImpl(), new AlarmsInteractorImpl());
        txt_taskName = (EditText) findViewById(R.id.txt_taskName);
        txt_taskDescription = (EditText) findViewById(R.id.txt_taskDescription);
        txt_taskDate = (EditText) findViewById(R.id.txt_taskDate);
        btn_taskDate = (Button) findViewById(R.id.btn_taskDate);
        txt_taskTime = (EditText) findViewById(R.id.txt_taskTime);
        btn_tasTime = (Button) findViewById(R.id.btn_taskTime);
        tgl_taskCompleted = (ToggleButton) findViewById(R.id.tgl_taskCompleted);
        btn_taskSave = (Button) findViewById(R.id.btn_taskSave);
        btn_taskClear = (Button) findViewById(R.id.btn_taskClear);
        btn_taskCancel = (Button) findViewById(R.id.btn_taskCancel);
        Bundle taskBundle=getIntent().getExtras();
        myTask=presenter.getTaskFromIntent(taskBundle);
        fillForm(myTask);
        btn_taskDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialogFragment();
            }
        });
        btn_tasTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialogFragment();
            }
        });
        btn_taskSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validityForm()==true){
                    presenter.onTaskSaveClicked(myTask);
                    finish();
                }
            }
        });
        btn_taskClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearForm();
            }
        });
        btn_taskCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeForm();
            }
        });

    }

        private void showTimeDialogFragment() {
            String[] Time=txt_taskTime.getText().toString().split(":");
            int hour= Integer.parseInt(Time[0]);
            int min= Integer.parseInt(Time[1]);
                        FragmentManager fm=getSupportFragmentManager();
            TimePickerFragment newFragment= new TimePickerFragment(hour,min,t);
            newFragment.show(fm, "timePicker");
        }

    private void showDateDialogFragment() {
                String[] Date=txt_taskDate.getText().toString().split("/");
        int day= Integer.parseInt(Date[0]);
        int month= Integer.parseInt(Date[1])-1;
        int year= Integer.parseInt(Date[2]);
        FragmentManager fm=getSupportFragmentManager();
        DatePickerFragment newFragment= new DatePickerFragment(year,month,day,h);
        newFragment.show(fm, "datePicker");
    }
    @Override
    public void clearForm() {
        txt_taskName.setText("");
        txt_taskDescription.setText("");
        txt_taskDate.setText("");
        txt_taskTime.setText("");
        tgl_taskCompleted.setChecked(false);
    }

    @Override
    public void fillForm(task myTask) {
        if (myTask.getId()==0){
            setCurrentDateAndTime();
        }else{
            txt_taskName.setText(myTask.getName());
            txt_taskDescription.setText(myTask.getDescription());
            txt_taskDate.setText(myTask.getDate());
            txt_taskTime.setText(myTask.getTime());
            tgl_taskCompleted.setChecked(myTask.getCompleted());
                    }
    }

    private void setCurrentDateAndTime() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month= c.get(Calendar.MONTH);
        int day= c.get(Calendar.DAY_OF_MONTH);
        int hour=c.get(Calendar.HOUR_OF_DAY);
        int min=c.get(Calendar.MINUTE);
        txt_taskDate.setText(new StringBuilder()
                .append(day)
                .append("/")
                .append(month + 1)
                .append("/")
                .append(year));
        txt_taskTime.setText(new StringBuilder()
                .append(hour)
                .append(":")
                .append(min));

    }

    @Override
    public boolean validityForm() {
        String message = "";
        if (TextUtils.isEmpty(txt_taskName.getText().toString())){
            message = getResources().getString(R.string.validityName);
        }
        if (TextUtils.isEmpty(txt_taskDescription.getText().toString())) {
            message = getResources().getString(R.string.validityDescription);
        }
        if (TextUtils.isEmpty(txt_taskDate.getText().toString())) {
            message = getResources().getString(R.string.validityDate);
        }
        if (TextUtils.isEmpty(txt_taskTime.getText().toString())) {
            message = getResources().getString(R.string.validityTime);
        }

        if (message == "") {
                        myTask.setName(txt_taskName.getText().toString());
            myTask.setDescription(txt_taskDescription.getText().toString());
            myTask.setDate(txt_taskDate.getText().toString());
            myTask.setTime(txt_taskTime.getText().toString());
            myTask.setCompleted(tgl_taskCompleted.isChecked());
            return true;
        } else {
            showMessage(message);
            return false;
        }

    }

    @Override
    public void closeForm() {
        clearForm();
        finish();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    }

