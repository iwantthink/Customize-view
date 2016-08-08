package com.marenbo.www.example.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2016/8/7.
 */
public class PathTan extends View implements View.OnClickListener {

    private Path mPath;
    private float[] pos;
    private float[] tan;
    private Paint mPaint;
    float currentValue = 0;
    private PathMeasure mMeasure;

    public PathTan(Context context) {
        super(context);
    }

    public PathTan(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        mMeasure = new PathMeasure();
        mPath.addCircle(0, 0, 200, Path.Direction.CW);
//        mPath.addRect(-100, -100, 100, 100, Path.Direction.CW);
        mMeasure.setPath(mPath, false);
        pos = new float[2];
        tan = new float[2];
        setOnClickListener(this);
    }

    public PathTan(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int mViewWidth, mViewHeight;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mViewWidth = w;

        mViewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mMeasure.getPosTan(mMeasure.getLength() * currentValue, pos, tan);

        float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI);

        Log.d("PathTan", "degrees:" + degrees);

        canvas.save();

        canvas.translate(mViewWidth / 2, mViewHeight / 2);

        canvas.drawPath(mPath, mPaint);

        canvas.drawCircle(pos[0], pos[1], 10, mPaint);

        Log.d("PathTan", "pos[0]:" + pos[0]);

        Log.d("PathTan", "pos[1]:" + pos[1]);

        Log.d("PathTan", "tan[0]:" + tan[0]);

        Log.d("PathTan", "tan[1]:" + tan[1]);

        canvas.rotate(degrees);

        canvas.drawLine(0, -200, 300, -200, mPaint);

        canvas.restore();
    }

    @Override
    public void onClick(View view) {
        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                currentValue = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        animator.setDuration(3000);
        animator.setRepeatCount(0);
        animator.start();
    }

}
