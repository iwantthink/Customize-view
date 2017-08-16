package com.marenbo.www.example.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2016/8/7.
 */
public class SearchView extends BaseView {

    private Path mCirclePath;

    private Path mSearchPath;

    private float mSearchLength;

    private float mCurStopLength, mCurStartLength;

    private PathMeasure searchMeasure, circleMeasure;

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mCirclePath = new Path();

        RectF rectF1 = new RectF(-200, -200, 200, 200);

        mCirclePath.addArc(rectF1, 45.0f, 359.9f);

        mSearchPath = new Path();

        RectF rectF = new RectF(-100, -100, 100, 100);

        mSearchPath.addArc(rectF, 45.0f, 359.9f);

        searchMeasure = new PathMeasure(mSearchPath, true);

        circleMeasure = new PathMeasure(mCirclePath, false);

        float[] pos = new float[2];

        float[] tan = new float[2];

        circleMeasure.getPosTan(0, pos, tan);

        Log.d("SearchView", "x = " + pos[0] + "  y = " + pos[1]);

        mSearchPath.lineTo(pos[0], pos[1]);

        searchMeasure.setPath(mSearchPath, false);

        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(1, 0);

        final ValueAnimator valueAnimator2 = ValueAnimator.ofFloat(0, 1);

        valueAnimator.setDuration(1500);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                float fraction = animation.getAnimatedFraction();

                Log.d("SearchView", "fraction:" + fraction);

                mSearchLength = fraction * searchMeasure.getLength();

                invalidate();

                if (mSearchLength == searchMeasure.getLength()) {

                    valueAnimator2.start();

                }

            }
        });

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                valueAnimator.start();
            }
        });


        valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                float fraction = animation.getAnimatedFraction();

                mCurStopLength = fraction * circleMeasure.getLength();

                mCurStartLength = (float) (mCurStopLength - ((0.5 - Math.abs(fraction - 0.5)) * circleMeasure.getLength()));

                invalidate();

            }
        });

        valueAnimator2.setRepeatCount(5);

        valueAnimator2.setDuration(1500);


    }

    public SearchView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(mViewWidth / 2, mViewHeight / 2);

        Path dst1 = new Path();

        dst1.moveTo(0, 0);

        searchMeasure.getSegment(mSearchLength, searchMeasure.getLength(), dst1, true);
        Log.d("SearchView", "mSearchLength:" + mSearchLength);

        Log.d("SearchView", "searchMeasure.getLength():" + searchMeasure.getLength());

        canvas.drawPath(dst1, mDefaultPaint);


        dst1.reset();

        dst1.moveTo(0, 0);

        circleMeasure.getSegment(mCurStartLength, mCurStopLength, dst1, true);

        canvas.drawPath(dst1, mDefaultPaint);


//        canvas.drawPath(mCirclePath, mDefaultPaint);
//
//        mDefaultPaint.setColor(Color.RED);
//
//        canvas.drawPath(mSearchPath, mDefaultPaint);


    }
}
