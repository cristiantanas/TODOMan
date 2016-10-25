package org.uab.dedam.todoman;

import android.content.Intent;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btn_taskNew = (Button) findViewById(R.id.btn_taskNew);
        btn_taskNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Intent intent=
                                new Intent(HomeActivity.this, ContentActivity.class);
                startActivity(intent);
            }
        });
    }
}

