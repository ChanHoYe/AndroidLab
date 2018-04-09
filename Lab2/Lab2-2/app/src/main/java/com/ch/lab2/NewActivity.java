package com.ch.lab2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NewActivity extends AppCompatActivity implements View.OnClickListener {
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        // Connect View Variables and Views and Set onClickListener.
        TextView textUrl = (TextView)findViewById(R.id.textUrl);
        Button btnGo = (Button)findViewById(R.id.btnGo);
        Button btnBack = (Button)findViewById(R.id.btnBack);
        btnGo.setOnClickListener(this);
        btnBack.setOnClickListener(this);

        // Get received intent from previous activity.
        Intent intent = getIntent();

        if(intent != null) {
            // Get received URL data from intent.
            url = intent.getStringExtra("url");
            textUrl.setText(url);

            // If URL value not contains 'http://', append 'http://' to URL value.
            if(!url.contains("http")) {
                StringBuffer buf = new StringBuffer();
                buf.append("http://");
                buf.append(url);
                url = buf.toString();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnGo:
                // Start activity with URL(Web View).
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                break;
            case R.id.btnBack:
                finish();
                break;
        }
    }
}
