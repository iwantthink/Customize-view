package com.marenbo.www.example.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/7/11.
 */
public class MultiCricleView extends View {

    private Paint mPaint;

    private Paint mTxtPaint;

    private int mWidth;

    private int mRadius;

    private float mTxtPianyi;

    public MultiCricleView(Context context) {
        super(context);
    }

    public MultiCricleView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

        mPaint.setStyle(Paint.Style.STROKE);

        mPaint.setStrokeWidth(5);

        mPaint.setColor(Color.RED);


        mTxtPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);

        mTxtPaint.setStyle(Paint.Style.STROKE);

        mTxtPaint.setStrokeCap(Paint.Cap.ROUND);

        mTxtPaint.setTextSize(15);

        mTxtPaint.setColor(Color.BLUE);

        mTxtPaint.setTextAlign(Paint.Align.CENTER);

        mTxtPianyi = (mTxtPaint.descent() + mTxtPaint.ascent()) / 2;

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = w;

        mRadius = mWidth / 13;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.GRAY);

        canvas.save();

        canvas.translate(mWidth / 2, mWidth / 2);

        canvas.drawCircle(0, 0, mRadius, mPaint);

        canvas.drawLine(-mRadius, 0, mRadius, 0, mTxtPaint);

        canvas.drawText("midmidmid", 0, -mTxtPianyi, mTxtPaint);

        canvas.restore();

        for (int i = 1; i < 6; i++) {

            if (i == 1) {

                mPaint.setColor(Color.YELLOW);

            } else {

                mPaint.setColor(Color.GREEN);
            }

            canvas.save();

            canvas.translate(mWidth / 2, mWidth / 2);

            canvas.rotate(-32);

            canvas.rotate(72f * i);

            if (1 < i && i < 5) {

                canvas.translate(0, -mRadius / 5);
            }

            canvas.drawLine(0, -mRadius, 0, -mRadius * 2, mPaint);

            if (1 < i && i < 5) {

                canvas.translate(0, -mRadius / 5);
            }

            canvas.drawCircle(0, -mRadius * 3, mRadius, mPaint);


            if (i == 5) {

                canvas.drawLine(0, -4 * mRadius, 0, -5 * mRadius, mPaint);

                canvas.drawCircle(0, -6 * mRadius, mRadius, mPaint);

            }

            canvas.translate(0, -3 * mRadius);

            canvas.drawText("hello", 0, -mTxtPianyi, mTxtPaint);

            canvas.restore();

        }
    }
}
