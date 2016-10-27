package org.uab.dedam.todoman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class NewTodoActivity extends AppCompatActivity
        implements NewTodoFragment.NewTodoFragmentActionListener {

    @Override
    public void onSave() {
        Toast.makeText(NewTodoActivity.this, R.string.toastTodoSaved, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onCancel() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_todo);
    }
}
