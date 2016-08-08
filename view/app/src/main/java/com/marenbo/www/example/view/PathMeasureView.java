package com.marenbo.www.example.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;

/**
 * Created by Administrator on 2016/8/7.
 */
public class PathMeasureView extends BaseView {

    private Path mMeasurePath;

    private Path mDst;

    private PathMeasure mPathMeasure;

    private float mTotalLength;

    private float mCurStopLength, mCurStartLength;

    private Paint mBackGroundPaint;

    private int mTransD;

    private int mCount;

    public PathMeasureView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initPathMeasure(context);
    }

    public PathMeasureView(Context context) {
        super(context);

        initPathMeasure(context);

    }

    private void initPathMeasure(Context context) {

        mBackGroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mBackGroundPaint.setStyle(Paint.Style.STROKE);

        mBackGroundPaint.setStrokeWidth(5);

        mBackGroundPaint.setColor(Color.LTGRAY);

        mDst = new Path();

        mMeasurePath = new Path();

        mMeasurePath.addCircle(0, 0, 100, Path.Direction.CW);

        mPathMeasure = new PathMeasure();

        mPathMeasure.setPath(mMeasurePath, false);

        mTotalLength = mPathMeasure.getLength();

        mCount = (int) (mTotalLength / 30);

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                float fraction = animation.getAnimatedFraction();

                mCurStopLength = fraction * mTotalLength + mTransD * 30;

                mCurStartLength = (float) (mCurStopLength - ((0.5 - Math.abs(fraction - 0.5)) * mTotalLength)) + mTransD * 30;

                invalidate();

            }
        });

        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);

        valueAnimator.setDuration(3000);

        valueAnimator.setInterpolator(new LinearInterpolator());

        valueAnimator.start();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(mViewWidth / 2, mViewHeight / 2);

        mDst.reset();

        mDst.moveTo(0, 0);

        canvas.drawPath(mMeasurePath, mBackGroundPaint);

        mPathMeasure.getSegment(mCurStartLength, mCurStopLength, mDst, true);


        canvas.drawPath(mDst, mDefaultPaint);

    }
}
