package com.ch.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect View Variables and Views and Set onClickListener.
        editUrl = (EditText)findViewById(R.id.editUrl);
        Button btnNext = (Button)findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnNext:
                String url = editUrl.getText().toString();

                // Start new activity. (intent contains URL value)
                Intent intent = new Intent(MainActivity.this, NewActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);

                break;
        }
    }
}