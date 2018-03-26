package com.abc.lab1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView;
    EditText editText;
    Button clearBtn, printBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect the variables to Views
        clearBtn = (Button)findViewById(R.id.btn_clear);
        printBtn = (Button)findViewById(R.id.btn_print);
        editText = (EditText)findViewById(R.id.editTxt);
        textView = (TextView)findViewById(R.id.printTV);

        // Set OnClickListener to each Button.
        clearBtn.setOnClickListener(this);
        printBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_print:
                String text = editText.getText().toString();

                // If Text of EditText is empty, text not assigns to TextView.
                if(!TextUtils.isEmpty(text))
                    // If user presses 'Print', Text of textview sets value of editText.
                    textView.setText(text);
                else
                    textView.setText("contents");
                break;
            case R.id.btn_clear:
                // If user presses 'CLEAR', Text of editText is clear.
                editText.setText("");
                textView.setText("contents");
                break;
        }
    }
}
