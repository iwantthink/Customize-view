package com.marenbo.www.example.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/7/17.
 */
public class SaveLayerView extends View {

    private Paint mPaint;

    private int mViewWidth, mViewHeight;

    public SaveLayerView(Context context) {
        super(context);


    }

    public SaveLayerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mPaint.setStyle(Paint.Style.FILL);

        mPaint.setColor(Color.parseColor("#FF99FF"));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mViewHeight = h;

        mViewWidth = w;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /*
     * 绘制一个红色矩形
     */
        mPaint.setColor(Color.RED);
        canvas.drawRect(mViewWidth / 2F - 200, mViewHeight / 2F - 200, mViewWidth / 2F + 200, mViewHeight / 2F + 200, mPaint);

    /*
     * 保存画布并绘制一个蓝色的矩形
     */
        canvas.saveLayerAlpha(0, 0, mViewWidth, mViewHeight, 0x55, Canvas.ALL_SAVE_FLAG);
        mPaint.setColor(Color.BLUE);

        canvas.drawRect(mViewWidth / 2F - 100, mViewHeight / 2F - 100, mViewWidth / 2F + 100, mViewHeight / 2F + 100, mPaint);


        // 旋转画布
        canvas.rotate(15);
        canvas.drawRect(mViewWidth / 2F - 100, mViewHeight / 2F - 100, mViewWidth / 2F + 100, mViewHeight / 2F + 100, mPaint);
        canvas.restore();
    }
}
