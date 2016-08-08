package com.marenbo.www.example.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * setShadowLayer 是不支持硬件加速的
 * Created by Administrator on 2016/7/10.
 */
public class ShadowView extends View {

    private Paint mPaint;

    public ShadowView(Context context) {
        super(context);
    }

    public ShadowView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mPaint.setStyle(Paint.Style.FILL);

        mPaint.setColor(Color.BLUE);

        mPaint.setStrokeWidth(10);

        mPaint.setShadowLayer(5, 3, 3, Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(222, 222, 444, 444, mPaint);
    }
}
