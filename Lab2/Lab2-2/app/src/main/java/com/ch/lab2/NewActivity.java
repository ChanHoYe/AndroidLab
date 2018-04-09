package com.ch.lab2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
            if(TextUtils.isEmpty(url))
                Toast.makeText(this, "주소를 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
            else if(!url.contains("http")) {
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
                if(!TextUtils.isEmpty(url))
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                break;
            case R.id.btnBack:
                Toast.makeText(this, "뒤로가기 버튼을 눌렀습니다.", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}
