package com.marenbo.www.example.diydrawable;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.Log;

/**
 * Created by Administrator on 2016/7/25.
 */
public class RoundDrawable extends Drawable {

    private Bitmap mBitmap;

    private Paint mPaint;

    private RectF mRect;

    public RoundDrawable(Bitmap bitmap) {
        mBitmap = bitmap;

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        BitmapShader bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        mPaint.setShader(bitmapShader);


    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);

        Log.d("RoundDrawable", "setBounds");

        mRect = new RectF();

        mRect.set(left, top, right, bottom);
    }

    @Override
    public void setBounds(Rect bounds) {
        super.setBounds(bounds);

        Log.d("RoundDrawable", "bounds.left:" + bounds.left);

        Log.d("RoundDrawable", "bounds.right:" + bounds.right);

        Log.d("RoundDrawable", "bounds.top:" + bounds.top);

        Log.d("RoundDrawable", "bounds.bottom:" + bounds.bottom);

        mRect = new RectF();

        mRect.set(bounds.left, bounds.top, bounds.right, bounds.bottom);
    }

    @Override
    public int getIntrinsicHeight() {

        return mBitmap.getHeight();
    }

    @Override
    public int getIntrinsicWidth() {

        return mBitmap.getWidth();
    }

    @Override
    public void draw(Canvas canvas) {

        Log.d("RoundDrawable", "draw");

        canvas.drawRoundRect(mRect, 30, 30, mPaint);

    }

    @Override
    public void setAlpha(int alpha) {

        mPaint.setAlpha(alpha);

//        invalidateSelf();

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

        mPaint.setColorFilter(colorFilter);

//        invalidateSelf();
    }

    @Override
    public int getOpacity() {


        return PixelFormat.TRANSLUCENT;
    }


}
