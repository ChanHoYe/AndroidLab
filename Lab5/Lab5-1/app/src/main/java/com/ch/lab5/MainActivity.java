package com.ch.lab5;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imageView1;
    ImageView imageView2;
    EditText editText;
    Button button;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView1 = (ImageView) findViewById(R.id.image1);
        imageView2 = (ImageView) findViewById(R.id.image2);
        editText = (EditText) findViewById(R.id.editText);
        editText.setClickable(false);
        button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                DogThread thread1 = new DogThread(0);
                thread1.start();
                DogThread thread2 = new DogThread(1);
                thread2.start();
                break;
        }
    }

    class DogThread extends Thread {
        int dogIndex, stateIndex;
        ArrayList<Integer> images = new ArrayList<>();

        public DogThread(int index) {
            dogIndex = index;

            /* Save Resource's id integer */
            images.add(R.drawable.dog_eating);
            images.add(R.drawable.dog_standing);
            images.add(R.drawable.dog_study);
        }

        private int getRandomTime(int min, int max) {
            return min+(int)(Math.random() * (max-min));
        }

        @Override
        public void run() {
            stateIndex = 0;
            for (int i = 0; i < 9; i++) {
                final String msg = "dog #" + dogIndex + "state : " + stateIndex;

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        editText.append(msg + "\n");
                        editText.setFocusable(false);

                        /* Set Image for each image view */
                        if (dogIndex == 0) {
                            imageView1.setImageResource(images.get(stateIndex));
                        } else if (dogIndex == 1) {
                            imageView2.setImageResource(images.get(stateIndex));
                        }
                    }
                });

                try {
                    int sleepTime = getRandomTime(500, 3000);
                    Thread.sleep(sleepTime); // Thread sleeps for random time(0.5s ~ 3s).
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                stateIndex++;
                if (stateIndex >= images.size()) {
                    stateIndex = 0;
                }
            }
        }
    }
}