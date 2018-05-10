package com.ch.lab4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout slide_ll;
    private Animation animOpen, animClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        final LinearLayout base_ll = (LinearLayout) findViewById(R.id.base_ll);
        slide_ll = (LinearLayout) findViewById(R.id.slide_ll);

        Button btn_open = (Button) findViewById(R.id.btn_open);
        Button btn_close = (Button) findViewById(R.id.btn_close);

        btn_open.setOnClickListener(this);
        btn_close.setOnClickListener(this);

        // Load Open and Close animation.
        animClose = AnimationUtils.loadAnimation(this, R.anim.right_slide);
        animOpen = AnimationUtils.loadAnimation(this, R.anim.left_slide);

        // Set animation listener to each animation.
        animOpen.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // Before open anmation start, sliding area up to front of frame layout.
                slide_ll.setVisibility(View.VISIBLE);
                slide_ll.bringToFront();
            }

            @Override
            public void onAnimationEnd(Animation animation) {}

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        animClose.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                // After close anmation finished, base area up to front of frame layout.
                slide_ll.setVisibility(View.GONE);
                base_ll.bringToFront();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_open:
                slide_ll.startAnimation(animOpen);
                break;
            case R.id.btn_close:
                slide_ll.startAnimation(animClose);
                break;
        }
    }
}
