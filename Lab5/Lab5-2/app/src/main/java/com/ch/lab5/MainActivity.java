package com.ch.lab5;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textFacto, textAnswer;
    EditText editFacto;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textFacto = (TextView) findViewById(R.id.factoText);
        textAnswer = (TextView) findViewById(R.id.answer);
        editFacto = (EditText) findViewById(R.id.editFacto);
        button = (Button) findViewById(R.id.btn_calc);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_calc:
                if (editFacto.getText() != null) {
                    int n = Integer.parseInt(editFacto.getText().toString());
                    new FactoTask().execute(n);
                }
                break;
        }
    }

    class FactoTask extends AsyncTask<Integer, Integer, Integer> {
        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Integer... values) {
            switch(values[0]) {
                case 0:
                    /* Before factorial multiplication, initialize text view */
                    textFacto.setText(String.valueOf(values[1]));
                    textAnswer.setText("= ?");
                    break;
                case 1:
                    /* During Asynctask progress, update factorial text view. */
                    textFacto.append("  " + Integer.toString(values[1]));
                    break;
            }
        }

        @Override
        protected void onPostExecute(Integer result) {
            StringBuffer buf = new StringBuffer();
            buf.append("= ");
            buf.append(result);

            /* After Asynctask finished, set the answer text view. */
            textAnswer.setText(buf.toString());
        }

        @Override
        protected Integer doInBackground(Integer... params) {
            /* Before Asynctask executed, set the each text view. */
            int result = params[0];
            publishProgress(0, params[0]);


            /* During Asynctask progress, calculate factorial in progress. */
            for (int i = params[0] - 1; i > 0; i--) {
                try{
                    Thread.sleep(500);
                    result *= i;
                    publishProgress(1, i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return result;
        }
    }
}