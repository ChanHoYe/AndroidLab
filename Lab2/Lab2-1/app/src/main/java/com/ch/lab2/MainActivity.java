package com.ch.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editName, editAge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect View Variables and Views and Set onClickListener.
        editName = (EditText)findViewById(R.id.editName);
        editAge = (EditText)findViewById(R.id.editAge);
        Button btnSend = (Button)findViewById(R.id.btnSend);
        btnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnSend:
                String name = editName.getText().toString();
                String age = editAge.getText().toString();

                // If name and age fields are not empty, then start new activity.
                if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(age)) {
                    Intent intent = new Intent(MainActivity.this, NewActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("age", age);
                    startActivity(intent);
                } else {
                    // If name or age field is empty, show toast message.
                    Toast.makeText(this, "값을 모두 입력해 주십시오.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}