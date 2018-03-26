package com.abc.lab1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView, imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.imageView);
        imageView2 = (ImageView)findViewById(R.id.imageView2);
    }

    public void onClicked(View v) {
        switch (v.getId()) {
            case R.id.btn:
                changeImage();
                break;
        }
    }

    public void changeImage() {
        if(imageView.getVisibility() == View.VISIBLE) {
            imageView.setVisibility(View.INVISIBLE);
            imageView2.setVisibility(View.VISIBLE);
        } else if(imageView2.getVisibility() == View.VISIBLE) {
            imageView.setVisibility(View.VISIBLE);
            imageView2.setVisibility(View.INVISIBLE);
        }
    }
}
