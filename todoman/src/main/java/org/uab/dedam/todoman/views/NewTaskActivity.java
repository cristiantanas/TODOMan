package org.uab.dedam.todoman.views;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.uab.dedam.todoman.R;
import org.uab.dedam.todoman.presenter.TaskPresenter;

public class NewTaskActivity extends AppCompatActivity implements DatePickerFragment.OnDateSetCallback, TimePickerFragment.OnTimeSetCallback {
    Button btnSaveTask;
    Button btnCancel;
    Button btnClear;
    Button btnSetDate;
    TextView texDate;
    Button btnSetTime;
    TextView texTime;
    EditText texTitle;
    EditText texDescription;
    Switch swtCompleted;
    TaskPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newtask);

        presenter = new TaskPresenter(this);

        bindViews();
        bindListeners();
    }

    private void bindViews(){
        texTitle = (EditText) findViewById(R.id.tex_title);
        texDescription = (EditText) findViewById(R.id.tex_description);
        btnSetDate = (Button) findViewById(R.id.btn_set_date);
        texDate = (TextView) findViewById(R.id.tex_date);
        btnSetTime = (Button) findViewById(R.id.btn_set_time);
        texTime = (TextView) findViewById(R.id.tex_time);
        btnSaveTask = (Button)findViewById(R.id.btn_save_task);
        swtCompleted = (Switch) findViewById(R.id.swc_done);
        btnCancel = (Button) findViewById(R.id.btn_cancel);
        btnClear = (Button) findViewById(R.id.btn_clear);
    }

    private void bindListeners(){
        btnSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    DatePickerFragment datePickerFragment = new DatePickerFragment();
                    datePickerFragment.show(getSupportFragmentManager(), "datePickerFragment");
                }
                catch(ClassCastException exception) {
                    throw new ClassCastException("The activity does not implement the interface.");
                }
            }
        });

        btnSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    TimePickerFragment timePickerFragment = new TimePickerFragment();
                    timePickerFragment.show(getSupportFragmentManager(), "timePickerFragment");
                }
                catch(ClassCastException exception) {
                    throw new ClassCastException("The activity does not implement the interface.");
                }
            }
        });

        btnSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            boolean resultValidation = fieldValidations();
            if(!resultValidation)
            {
                showMessage(getResources().getString(R.string.validationError), true);
            }
            else
            {
                String title = texTitle.getText().toString();
                String description = texDescription.getText().toString();
                String date = texDate.getText().toString();
                String time = texTime.getText().toString();
                boolean isCompleted = swtCompleted.isChecked();
                boolean resultSaved = presenter.saveTasks(title, description, isCompleted, date, time);

                if(resultSaved){
                    showMessage(getResources().getString(R.string.taskSaved), false);
                }
                else{
                    showMessage(getResources().getString(R.string.taskNoSaved), true);
                }
                finish();
            }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                texDescription.setText("");
                texTitle.setText("");
                swtCompleted.setChecked(false);
                texDate.setText(getResources().getString(R.string.date));
                texTime.setText(getResources().getString(R.string.time));
            }
        });
    }

    public void showMessage(String message, boolean isError) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast, (ViewGroup) findViewById(R.id.custom_toast_container));
        TextView textToast = (TextView) layout.findViewById(R.id.text);
        LinearLayout containerToast = (LinearLayout) layout.findViewById(R.id.custom_toast_container);
        textToast.setText(message);
        if(isError){
            containerToast.setBackgroundColor(Color.RED);
        }
        else{
            containerToast.setBackgroundColor(Color.GREEN);
        }
        showToastWithLayout(layout);
    }

    private void showToastWithLayout(View layout) {
        Toast toastError = new Toast(getApplicationContext());
        toastError.setDuration(Toast.LENGTH_LONG);
        toastError.setView(layout);
        toastError.show();
    }

    private boolean fieldValidations(){
        CharSequence title = texTitle.getText();
        if(title == null || title.length() == 0)
        {
            return false;
        }
        return true;
    }

    @Override
    public void onDateSelected(String date) {
        texDate.setText(date);
    }

    @Override
    public void onTimeSelected(String time) {
        texTime.setText(time);
    }
}
