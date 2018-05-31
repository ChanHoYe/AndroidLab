package com.ch.lab6;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String PREF_NAME = "MyPref";
    private SharedPreferences preferences;
    private EditText editName, editId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Initialize Views */
        editName = (EditText) findViewById(R.id.edit_name);
        editId = (EditText) findViewById(R.id.edit_id);
        ((Button)findViewById(R.id.btn_clear)).setOnClickListener(this);
        ((Button)findViewById(R.id.btn_save)).setOnClickListener(this);
        ((Button)findViewById(R.id.btn_read)).setOnClickListener(this);

        preferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                /* Set SharedPreferences Editor */
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("name", editName.getText().toString());
                editor.putString("id", editId.getText().toString());
                editor.apply();
                Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_read:
                /* Get Data from SharedPreferences */
                editName.setText(preferences.getString("name", ""));
                editId.setText(preferences.getString("id", ""));
                Toast.makeText(getApplicationContext(), "불러오기 완료.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_clear:
                editName.setText("");
                editId.setText("");
                Toast.makeText(getApplicationContext(), "초기화되었습니다.", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}