package com.marenbo.www.example.diydrawable;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2016/7/25.
 */
public class CircleDrawable extends Drawable {


    private Bitmap mBitmap;

    private Paint mPaint;

    private RectF mRect;

    private int mWidth;

    public CircleDrawable(Bitmap bitmap) {

        mBitmap = bitmap;

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        BitmapShader bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        mPaint.setShader(bitmapShader);

        mWidth = Math.min(mBitmap.getWidth(), mBitmap.getHeight());


    }

    @Override
    public int getIntrinsicHeight() {
        return mWidth ;
    }

    @Override
    public int getIntrinsicWidth() {
        return mWidth ;
    }

    @Override
    public void draw(Canvas canvas) {

        canvas.drawCircle(mWidth / 2, mWidth / 2, mWidth / 2, mPaint);

    }


    @Override
    public void setAlpha(int alpha) {

        mPaint.setAlpha(alpha);

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {


        return PixelFormat.TRANSLUCENT;
    }
}
