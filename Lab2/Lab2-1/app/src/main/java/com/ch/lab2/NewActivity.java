package com.ch.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NewActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        // Connect View Variables and Views and Set onClickListener.
        Button btnBack = (Button)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        // Get received intent from previous activity.
        Intent intent = getIntent();

        if(intent != null) {
            // Get received data from intent.
            String name = intent.getStringExtra("name");
            String age = intent.getStringExtra("age");

            Toast.makeText(this, "Student info : " + name + ", " + age, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnBack:
                finish();
        }
    }
}