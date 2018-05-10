package com.ch.lab4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = new MyView(this);
        setContentView(view);
    }

    class MyView extends View {
        private Paint mPaint;
        private Path mPath;

        MyView(Context context) {
            super(context);
            init();
        }

        MyView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        /* Initialize Variables. */
        public void init() {
            mPaint = new Paint();
            mPaint.setColor(Color.BLUE); // Set Blue Color.
            mPath = new Path();
            mPaint.setStyle(Paint.Style.STROKE); // Set Paint Style to Line.
            mPaint.setStrokeWidth(5f); // Set Line's thickness.
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            canvas.drawPath(mPath, mPaint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_MOVE:
                case MotionEvent.ACTION_UP:
                    // If action is move or up, save point to path.
                    mPath.lineTo(event.getX(), event.getY());
                    invalidate();
                    return true;
                case MotionEvent.ACTION_DOWN:
                    // If action is down, set the point of path.
                    mPath.moveTo(event.getX(), event.getY());
                    invalidate();
                    return true;
            }
            return super.onTouchEvent(event);
        }
    }
}
