package com.ch.lab3;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button mBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtn = (Button)findViewById(R.id.btn);

        // Register Button For Context Menu.
        registerForContextMenu(mBtn);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu,v,menuInfo);

        // Set Menu's Title.
        menu.setHeaderTitle("Button Menu");

        // Inflate Menu layout for Context Menu.
        getMenuInflater().inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // Checking menu's ID and change button's text color.
        switch(item.getItemId()) {
            case R.id.red:
                mBtn.setTextColor(Color.RED);
                break;
            case R.id.green:
                mBtn.setTextColor(Color.GREEN);
                break;
            case R.id.blue:
                mBtn.setTextColor(Color.BLUE);
                break;
        }

        return super.onContextItemSelected(item);
    }
}
