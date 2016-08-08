package com.marenbo.www.example.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/7/12.
 */
public class WaveView extends View {

    // TODO: 2016/7/12  做一个利用重力传感器 摇晃的 水杯应用！！！

    private Paint mPaint;

    private Path mPath;

    private int w, h;

    public WaveView(Context context) {
        super(context);
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        this.w = w;

        this.h = h;

    }

    private GestureDetector mDetector;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                Log.d("WaveView", "down");
                break;

            case MotionEvent.ACTION_UP:

                Log.d("WaveView", "up");

                break;

            case MotionEvent.ACTION_MOVE:

                Log.d("WaveView", "move");
                break;
        }


        return super.onTouchEvent(event);
    }

    private void init(final Context context) {



        setClickable(true);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mPaint.setStyle(Paint.Style.STROKE);

        mPaint.setColor(Color.BLUE);

        mPath = new Path();

        height = 400;

        pointX = w / 2;


        mDetector = new GestureDetector(context, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }

        });


        mDetector.setOnDoubleTapListener(new GestureDetector.OnDoubleTapListener() {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {

                Toast.makeText(context, "onDoubleTap", Toast.LENGTH_SHORT).show();

                Log.d("WaveView", "onDoubleTap");

                return false;
            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                return false;
            }
        });

    }

    private int height;

    private int pointX;

    private boolean addOrMinus = true;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPath.moveTo(-100, height);

//        mPath.cubicTo(w / 3, 300, w / 3 * 2, 700, w, height);

        mPath.quadTo(pointX, height - 250, w + 100, height);

        canvas.drawPath(mPath, mPaint);

        if (pointX > w / 10 * 9) {

            addOrMinus = false;
        }

        if (pointX < w / 10) {

            addOrMinus = true;
        }

        if (addOrMinus) {

            pointX += 15;
        } else {
            pointX -= 15;
        }

        mPath.lineTo(w, h);

        mPath.lineTo(0, h);

        mPath.close();

        canvas.clipPath(mPath);

        canvas.drawColor(Color.BLUE);

        mPath.reset();

        invalidate();


    }

}
