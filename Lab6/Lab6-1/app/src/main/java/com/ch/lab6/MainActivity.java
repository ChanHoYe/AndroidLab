package com.ch.lab6;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String FILEPATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/MyFile";
    private static final String FILENAME = "input.txt";
    private EditText txtData;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Initialize Views */
        txtData = findViewById(R.id.txtData);
        ((Button) findViewById(R.id.btn_clear)).setOnClickListener(this);
        ((Button) findViewById(R.id.btn_fin)).setOnClickListener(this);
        ((Button) findViewById(R.id.btn_read)).setOnClickListener(this);
        ((Button) findViewById(R.id.btn_write)).setOnClickListener(this);

        /* Create File Directory */
        File directory = new File(FILEPATH);
        directory.mkdirs();

        /* Create File */
        file = new File(directory, FILENAME);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_write:
                try {
                    /* Open File Output Stream */
                    FileOutputStream fOut = new FileOutputStream(file);
                    OutputStreamWriter osw = new OutputStreamWriter(fOut);

                    String str = txtData.getText().toString();

                    /* Write String to File */
                    osw.write(str, 0, str.length());
                    osw.flush();

                    /* Close File Output Stream */
                    osw.close();
                    fOut.close();

                    Toast.makeText(getApplicationContext(), "Done writing SD 'input.txt'", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_read:
                try {
                    /* Open File Input Stream */
                    FileInputStream fIn = new FileInputStream(file);
                    InputStreamReader isr = new InputStreamReader(fIn);
                    StringBuffer buf = new StringBuffer();

                    /* Read each character and put into StringBuffer */
                    while (isr.ready()) {
                        buf.append((char) isr.read());
                    }

                    txtData.setText(buf.toString());

                    /* Close File Input Stream */
                    isr.close();
                    fIn.close();

                    Toast.makeText(getApplicationContext(), "Done reading SD 'input.txt'", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_clear:
                txtData.setText("");
                break;
            case R.id.btn_fin:
                finish();
                break;
        }
    }
}
