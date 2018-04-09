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
    EditText editUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editUrl = (EditText)findViewById(R.id.editUrl);
        Button btnNext = (Button)findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnNext:
                String url = editUrl.getText().toString();

                if(!TextUtils.isEmpty(url)) {
                    // Start new activity. (intent contains URL value)
                    Intent intent = new Intent(MainActivity.this, NewActivity.class);
                    intent.putExtra("url", url);
                    startActivity(intent);
                } else {
                    // If URL value is empty, Showing toast message instead of starting new activity.
                    Toast.makeText(this, "값을 입력해 주십시오.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
