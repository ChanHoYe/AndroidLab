package com.ch.lab3;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_tab1, btn_tab2;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        // Get Fragment Manager and Initialize FrameLayout with First Fragment.
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frameLayout, Fragment1.newInstance()).commit();
    }

    /*
     * Initialize Method that Connects View Variables and Views.
     */
    private void init() {
        btn_tab1 = (Button) findViewById(R.id.btnTab1);
        btn_tab2 = (Button) findViewById(R.id.btnTab2);
        btn_tab1.setOnClickListener(this);
        btn_tab2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // If Press the button, Change Fragment.
        switch (v.getId()) {
            case R.id.btnTab1:
                fragmentManager.beginTransaction().replace(R.id.frameLayout, Fragment1.newInstance()).commit();
                break;
            case R.id.btnTab2:
                fragmentManager.beginTransaction().replace(R.id.frameLayout, Fragment2.newInstance()).commit();
                break;
        }
    }
}
