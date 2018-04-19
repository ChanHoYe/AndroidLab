package com.ch.lab3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    RadioButton radMale, radFemale;
    CheckBox chkSMS, chkMail;
    EditText editName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign View Values to View.
        editName = (EditText)findViewById(R.id.editName);
        radMale = (RadioButton)findViewById(R.id.radMale);
        radFemale = (RadioButton)findViewById(R.id.radFemale);
        chkSMS = (CheckBox)findViewById(R.id.chkSMS);
        chkMail = (CheckBox)findViewById(R.id.chkMail);

        Button btnCommit = (Button)findViewById(R.id.btnCommit);
        btnCommit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnCommit:
                boolean flag = true;

                /* Defensive Code */
                if(TextUtils.isEmpty(editName.getText().toString())) {
                    Toast.makeText(this, "이름을 입력해 주세요", Toast.LENGTH_SHORT).show();
                    flag = false;
                }
                if(!radMale.isChecked() && !radFemale.isChecked()) {
                    Toast.makeText(this, "성별을 선택하여 주세요", Toast.LENGTH_SHORT).show();
                    flag = false;
                }

                // If There is no empty values, start new activity from intent that contains name, sex, check.
                if(flag) {
                    Intent intent = new Intent(this, NewActivity.class);
                    StringBuffer buffer = new StringBuffer();

                    if (radMale.isChecked()) {
                        intent.putExtra("sex", radMale.getText().toString());
                    } else if (radFemale.isChecked()) {
                        intent.putExtra("sex", radFemale.getText().toString());
                    }

                    intent.putExtra("name", editName.getText().toString());

                    if (chkSMS.isChecked())
                        buffer.append("SMS");
                    if (chkMail.isChecked()) {
                        if (buffer.length() == 0)
                            buffer.append("e-mail");
                        else
                            buffer.append(" & e-mail");
                    }

                    if (buffer.length() == 0)
                        buffer.append("none");

                    intent.putExtra("check", buffer.toString());

                    startActivity(intent);
                    finish();
                }
                break;
        }
    }
}
