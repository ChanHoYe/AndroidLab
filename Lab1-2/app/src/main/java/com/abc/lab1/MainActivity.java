package com.abc.lab1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

                clearBtn = (Button)findViewById(R.id.btn_clear);
                printBtn = (Button)findViewById(R.id.btn_print);
                editText = (EditText)findViewById(R.id.editTxt);
                textView = (TextView)findViewById(R.id.printTV);

                clearBtn.setOnClickListener(this);
                printBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_print:
                // Print button을 눌렀을때 textview에 edittext의 값 설정.
                textView.setText(editText.getText().toString());
                break;
            case R.id.btn_clear:
                // clear button을 눌렀을때 edittext 초기화.
                editText.setText("");
                break;
        }
    }
}
