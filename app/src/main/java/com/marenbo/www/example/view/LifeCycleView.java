package com.marenbo.www.example.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2016/7/23.
 */
public class LifeCycleView extends View {

    private static final String TAG = LifeCycleView.class.getSimpleName();

    public LifeCycleView(Context context) {
        super(context);

        Log.d(TAG, "construction with one parameter");
    }

    public LifeCycleView(Context context, AttributeSet attrs) {
        super(context, attrs);

        Log.d(TAG, "construction with two parameter");
    }

    //xml布局被view完全解析了之后会调用
    //也就是说 如果从代码中创建view 不会回调该接口
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        Log.d(TAG, "onFinishInflate");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        Log.d(TAG, "onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        Log.d(TAG, "onLayout");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.d(TAG, "onDraw");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        Log.d(TAG, "onSizeChanged");
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        Log.d(TAG, "onAttachedToWindow");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        Log.d(TAG, "onDetachedFromWindow");
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);

        Log.d(TAG, "onWindowVisibilityChanged");
    }
}
