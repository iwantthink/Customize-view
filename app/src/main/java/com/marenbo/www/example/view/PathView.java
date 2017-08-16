package com.marenbo.www.example.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/7/17.
 */
public class PathView extends View {

    private Paint mPaint, mLinePaint;

    private TextPaint mTextPaint;

    private int mWidth, mHeight;

    private Path mPath;


    public PathView(Context context) {
        super(context);
    }

    public PathView(Context context, AttributeSet attrs) {

        super(context, attrs);

        init();
    }

    private void init() {

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mPaint.setStyle(Paint.Style.STROKE);

        mPaint.setColor(Color.parseColor("#FF99FF"));

        mPaint.setStrokeWidth(10);

        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mLinePaint.setColor(Color.BLACK);

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.LINEAR_TEXT_FLAG);

        mTextPaint.setColor(Color.DKGRAY);

        mTextPaint.setTextSize(20);

        mPath = new Path();

//        mPath.moveTo(400, 200);
//
//        RectF rect = new RectF(0, 0, 400, 400);
//
//        mPath.arcTo(rect, 0, 180);
//
//        mPath.rLineTo(500, 500);
//
//        mPath.addArc(new RectF(100, 500, 300, 600), 0, 180);

        mPath.addCircle(100, 100, 50, Path.Direction.CCW);



    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = w;

        mHeight = h;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < mHeight; i += 100) {

            canvas.drawLine(0, i, mWidth, i, mLinePaint);

        }

        for (int i = 0; i < mWidth; i += 100) {

            canvas.drawLine(i, 0, i, mHeight, mLinePaint);

        }

        canvas.drawPath(mPath, mPaint);

        canvas.drawTextOnPath("sadfasadfasadfasadfasadfasadfasadfasadfasadfasadfasadfasadfasadfasadfasadfasadfasadfasadfasadfasadfasadfa", mPath, 0, 0, mTextPaint);
    }
}
