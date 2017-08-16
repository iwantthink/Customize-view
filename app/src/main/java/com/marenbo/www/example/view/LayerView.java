package com.marenbo.www.example.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.marenbo.www.example.R;

/**
 * Created by Administrator on 2016/7/17.
 */
public class LayerView extends View {
    private Bitmap mBitmap;// 位图对象

    private int mViewWidth, mViewHeight;// 控件宽高

    public LayerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 从资源中获取位图对象
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.img01);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        /*
         * 获取控件宽高
         */
        mViewWidth = w;
        mViewHeight = h;

        // 缩放位图与控件一致
        mBitmap = Bitmap.createScaledBitmap(mBitmap, mViewWidth, mViewHeight, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save(Canvas.MATRIX_SAVE_FLAG);
        canvas.scale(0.8F, 0.8F, mViewWidth, 0);
        canvas.drawBitmap(mBitmap, 0, 0, null);
        canvas.restore();
    }
}