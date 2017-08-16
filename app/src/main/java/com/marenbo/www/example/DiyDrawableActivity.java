package com.marenbo.www.example;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

import com.marenbo.www.example.utils.MeasureUtil;

import butterknife.ButterKnife;

public class DiyDrawableActivity extends Activity  {

    private Context mContext = DiyDrawableActivity.this;

    private GestureDetector mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diy_drawable);
        ButterKnife.bind(this);

        Log.d("DiyDrawableActivity", "mContext.getResources().getDisplayMetrics().widthPixels:" + mContext.getResources().getDisplayMetrics().heightPixels);

        Log.d("DiyDrawableActivity", "MeasureUtil.getScreenSize(DiyDrawableActivity.this)[0]:" + MeasureUtil.getScreenSize(DiyDrawableActivity.this)[1 ]);

        mDetector = new GestureDetector(DiyDrawableActivity.this, new GestureDetector.OnGestureListener() {

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

                Toast.makeText(DiyDrawableActivity.this, "onDoubleTap", Toast.LENGTH_SHORT).show();

                return true;
            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                return false;
            }
        });

    }


}
