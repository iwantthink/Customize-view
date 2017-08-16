package com.marenbo.www.example.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/8/2.
 */
public class BaseView extends View {

    protected Paint mDefaultPaint;

    protected TextPaint mDefaultTextPaint;

    protected int mViewWidth, mViewHeight;

    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public BaseView(Context context) {
        super(context);

        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mViewWidth = w;

        mViewHeight = h;
    }

    private void init() {

        mDefaultPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mDefaultPaint.setStyle(Paint.Style.STROKE);

        mDefaultPaint.setColor(Color.BLACK);

        mDefaultPaint.setStrokeWidth(5);


        mDefaultTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);

        mDefaultTextPaint.setStyle(Paint.Style.FILL);

        mDefaultTextPaint.setTextSize(30);
    }
}
